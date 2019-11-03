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
        .identificador_matriz_ou_filial()   .setup(pipe(new MatrizFilialTransformer(), SQUOTE))
        .razao_social_ou_nome_empresarial() .setup(SQUOTE)
        .nome_fantasia()                    .setup(SQUOTE)
        .situacao_cadastral()               .setup(pipe(new SituacaoCadastralTransformer(), SQUOTE))
        .data_situacao_cadastral()          .setup(pipe(ZEROTRIM, DATE, SQUOTE))
        .motivo_situacao_cadastral()        .setup(SQUOTE)
        .nm_cidade_exterior()               .setup(SQUOTE)
        .co_pais()                          .setup(SQUOTE)
        .nm_pais()                          .setup(SQUOTE)
        .codigo_natureza_juridica()         .setup(LONG)
        .data_inicio_atividade()            .setup(pipe(ZEROTRIM, DATE, SQUOTE))
        .cnae_fiscal()                      .setup(LONG)
        .descricao_tipo_logradouro()        .setup(SQUOTE)
        .logradouro()                       .setup(SQUOTE)
        .numero()                           .setup(SQUOTE)
        .complemento()                      .setup(SQUOTE)
        .bairro()                           .setup(SQUOTE)
        .cep()                              .setup(pipe(new CepTransformer(), SQUOTE))
        .uf()                               .setup(SQUOTE)
        .codigo_municipio()                 .setup(SQUOTE)
        .municipio()                        .setup(SQUOTE)
        .ddd_telefone_1()                   .setup(SQUOTE)
        .ddd_telefone_2()                   .setup(SQUOTE)
        .ddd_fax()                          .setup(SQUOTE)
        .correio_eletronico()               .setup(pipe(LOWERCASE, SQUOTE))
        .qualificacao_do_responsavel()      .setup(SQUOTE)
        .capital_social_da_empresa()        .setup(pipe(ZEROTRIM, SQUOTE))
        .porte_empresa()                    .setup(pipe(new PortEmpresaTransformer(), SQUOTE))
        .opcao_pelo_simples()               .setup(pipe(new OpcaoSimplesTransformer(), SQUOTE))
        .data_opcao_pelo_simples()          .setup(pipe(ZEROTRIM, DATE, SQUOTE))
        .data_exclusao_do_simples()         .setup(pipe(ZEROTRIM, DATE, SQUOTE))
        .opcao_pelo_mei()                   .setup(pipe(new OpcaoMeiTransformer(), BOOL))
        .situacao_especial()                .setup(SQUOTE)
        .data_situacao_especial()           .setup(pipe(ZEROTRIM, DATE, SQUOTE))
      .builder().build(new File("./input/K3241.K03200DV.D90805.L00001"), new EmpresaSqlExporter(output, 200));
      layout.run();
    }
    System.out.println("Use o comando: [Get-Content .\\empresa.sql -Head 100] para ver as 100 primeiras linhas do arquivo");
  }
}
