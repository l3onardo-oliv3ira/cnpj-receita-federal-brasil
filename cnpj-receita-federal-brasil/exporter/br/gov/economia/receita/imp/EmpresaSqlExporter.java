package br.gov.economia.receita.imp;

import static br.gov.economia.receita.imp.standard.Transformers.BOOL;
import static br.gov.economia.receita.imp.standard.Transformers.DATE;
import static br.gov.economia.receita.imp.standard.Transformers.DQUOTE;
import static br.gov.economia.receita.imp.standard.Transformers.LONG;
import static br.gov.economia.receita.imp.standard.Transformers.LOWERCASE;
import static br.gov.economia.receita.imp.standard.Transformers.ZEROTRIM;
import static br.gov.economia.receita.imp.standard.Transformers.pipe;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

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
  public VisitResult beginEmpresa(int row) {
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
  public VisitResult fieldEmpresa(int row, int col, IField field) {
    writer.print(',');
    writer.print(field.getName());
    values.append(',').append(field.getValue());
    return VisitResult.CONTINUE;
  }
  
  public static void main(String[] args) throws IOException {
    try(PrintWriter output = new PrintWriter(new File("./output/empresa.sql"))){
      FileLayout layout =  new FileLayout.Builder().empresa()
          .cnpj()                             .setup(LONG)
          .identificador_matriz_ou_filial()   .setup(pipe(new MatrizFilialTransformer(), DQUOTE))
          .razao_social_ou_nome_empresarial() .setup(DQUOTE)
          .nome_fantasia()                    .setup(DQUOTE)
          .situacao_cadastral()               .setup(pipe(new SituacaoCadastralTransformer(), DQUOTE))
          .data_situacao_cadastral()          .setup(pipe(ZEROTRIM, DATE, DQUOTE))
          .motivo_situacao_cadastral()        .setup(pipe(new MotivoSituacaoCadastralTransformer(), DQUOTE))
          .nm_cidade_exterior()               .setup(DQUOTE)
          .co_pais()                          .setup(DQUOTE)
          .nm_pais()                          .setup(DQUOTE)
          .codigo_natureza_juridica()         .setup(LONG)
          .data_inicio_atividade()            .setup(pipe(ZEROTRIM, DATE, DQUOTE))
          .cnae_fiscal()                      .setup(LONG)
          .descricao_tipo_logradouro()        .setup(DQUOTE)
          .logradouro()                       .setup(DQUOTE)
          .numero()                           .setup(DQUOTE)
          .complemento()                      .setup(DQUOTE)
          .bairro()                           .setup(DQUOTE)
          .cep()                              .setup(pipe(new CepTransformer(), DQUOTE))
          .uf()                               .setup(DQUOTE)
          .codigo_municipio()                 .setup(DQUOTE)
          .municipio()                        .setup(DQUOTE)
          .ddd_telefone_1()                   .setup(DQUOTE)
          .ddd_telefone_2()                   .setup(DQUOTE)
          .ddd_fax()                          .setup(DQUOTE)
          .correio_eletronico()               .setup(pipe(LOWERCASE, DQUOTE))
          .qualificacao_do_responsavel()      .setup(pipe(new QualificacaoResponsavelTransformer(), DQUOTE))
          .capital_social_da_empresa()        .setup(pipe(ZEROTRIM, DQUOTE))
          .porte_empresa()                    .setup(pipe(new PortEmpresaTransformer(), DQUOTE))
          .opcao_pelo_simples()               .setup(pipe(new OpcaoSimplesTransformer(), DQUOTE))
          .data_opcao_pelo_simples()          .setup(pipe(ZEROTRIM, DATE, DQUOTE))
          .data_exclusao_do_simples()         .setup(pipe(ZEROTRIM, DATE, DQUOTE))
          .opcao_pelo_mei()                   .setup(pipe(new OpcaoMeiTransformer(), BOOL))
          .situacao_especial()                .setup(DQUOTE)
          .data_situacao_especial()           .setup(pipe(ZEROTRIM, DATE, DQUOTE))
      .builder().build(new File("./input/"), new EmpresaSqlExporter(output));
      layout.run();
    }
    System.out.println("Use o comando: [Get-Content .\\empresa.sql -Head 100] para ver as 100 primeiras linhas do arquivo");
  }
}
