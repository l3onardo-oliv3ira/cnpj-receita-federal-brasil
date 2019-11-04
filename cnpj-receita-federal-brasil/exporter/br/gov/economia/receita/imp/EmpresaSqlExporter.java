package br.gov.economia.receita.imp;

import static br.gov.economia.receita.imp.standard.Transformers.BOOL;
import static br.gov.economia.receita.imp.standard.Transformers.DATE;
import static br.gov.economia.receita.imp.standard.Transformers.SQUOTE;
import static br.gov.economia.receita.imp.standard.Transformers.LONG;
import static br.gov.economia.receita.imp.standard.Transformers.LOWERCASE;
import static br.gov.economia.receita.imp.standard.Transformers.ZEROTRIM;
import static br.gov.economia.receita.imp.standard.Transformers.pipe;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import br.gov.economia.receita.IField;

public class EmpresaSqlExporter extends EmpresaVisitor {

  private final int max;
  private final PrintWriter writer;
  private int total = 0;
  private StringBuilder values = new StringBuilder();
  
  public EmpresaSqlExporter(PrintWriter writer) {
    this(writer, Integer.MAX_VALUE);
  }
  
  public EmpresaSqlExporter(PrintWriter writer, int max) {
    this.writer = writer;
    this.max = max;
    this.total = 0;
  }
  
  @Override
  public void start() {
    writer.println(";start transaction here");
  }
  
  @Override
  public void end() {
    writer.println(";commit");
  }

  @Override
  public VisitResult beginEmpresa(long row) {
    values.setLength(0);
    writer.print("insert into empresa (id");
    values.append(row);
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endEmpresa() {
    writer.print(")values(");
    writer.print(values);
    writer.println(");");
    if (++total == max)
      return VisitResult.TERMINATE;
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult fieldEmpresa(long row, IField field) {
    writer.print(',');
    writer.print(field.getName());
    values.append(',').append(field.getValue());
    return VisitResult.CONTINUE;
  }
  
  public static void main(String[] args) throws IOException {
    try(PrintWriter output = new PrintWriter(new File("./output/empresa.sql"), Charset.forName("UTF-8"))){
      FileLayout layout =  new FileLayout.Builder().empresa()
          .cnpj()                             .setup("c1", LONG)
          .identificador_matriz_ou_filial()   .setup("c2", pipe(new MatrizFilialTransformer(), SQUOTE))
          .razao_social_ou_nome_empresarial() .setup("c3", SQUOTE)
          .nome_fantasia()                    .setup("c4", SQUOTE)
          .situacao_cadastral()               .setup("c5", pipe(new SituacaoCadastralTransformer(), SQUOTE))
          .data_situacao_cadastral()          .setup("c6", pipe(ZEROTRIM, DATE, SQUOTE))
          .motivo_situacao_cadastral()        .setup("c7", pipe(new MotivoSituacaoCadastralTransformer(), SQUOTE))
          .nm_cidade_exterior()               .setup("c8", SQUOTE)
          .co_pais()                          .setup("c9", SQUOTE)
          .nm_pais()                          .setup("c10", SQUOTE)
          .codigo_natureza_juridica()         .setup("c11", LONG)
          .data_inicio_atividade()            .setup("c12", pipe(ZEROTRIM, DATE, SQUOTE))
          .cnae_fiscal()                      .setup("c13", LONG)
          .descricao_tipo_logradouro()        .setup("c14", SQUOTE)
          .logradouro()                       .setup("c15", SQUOTE)
          .numero()                           .setup("c16", SQUOTE)
          .complemento()                      .setup("c17", SQUOTE)
          .bairro()                           .setup("c18", SQUOTE)
          .cep()                              .setup("c19", pipe(new CepTransformer(), SQUOTE))
          .uf()                               .setup("c20", SQUOTE)
          .codigo_municipio()                 .setup("c21", SQUOTE)
          .municipio()                        .setup("c22", SQUOTE)
          .ddd_telefone_1()                   .setup("c23", SQUOTE)
          .ddd_telefone_2()                   .setup("c24",SQUOTE)
          .ddd_fax()                          .setup("c25",SQUOTE)
          .correio_eletronico()               .setup("c26",pipe(LOWERCASE, SQUOTE))
          .qualificacao_do_responsavel()      .setup("c27",pipe(new QualificacaoResponsavelTransformer(), SQUOTE))
          .capital_social_da_empresa()        .setup("c28",pipe(ZEROTRIM, SQUOTE))
          .porte_empresa()                    .setup("c29",pipe(new PortEmpresaTransformer(), SQUOTE))
          .opcao_pelo_simples()               .setup("c31",pipe(new OpcaoSimplesTransformer(), SQUOTE))
          .data_opcao_pelo_simples()          .setup("c32",pipe(ZEROTRIM, DATE, SQUOTE))
          .data_exclusao_do_simples()         .setup("c33",pipe(ZEROTRIM, DATE, SQUOTE))
          .opcao_pelo_mei()                   .setup("c34",pipe(new OpcaoMeiTransformer(), BOOL))
          .situacao_especial()                .setup("c35",SQUOTE)
          .data_situacao_especial()           .setup("c36",pipe(ZEROTRIM, DATE, SQUOTE))
      .builder().build(new File("./input/"), new EmpresaSqlExporter(output, 2000));
    }
    System.out.println("Use o comando: [Get-Content .\\empresa.sql -Head 100] para ver as 100 primeiras linhas do arquivo");
  }
}
