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

public class EmpresaJsonExporter extends EmpresaVisitor {

  private final int max;
  private boolean comma = false;
  private final PrintWriter writer;
  private int total = 0;
  
  public EmpresaJsonExporter(PrintWriter writer) {
    this(writer, Integer.MAX_VALUE);
  }
  
  public EmpresaJsonExporter(PrintWriter writer, int max) {
    this.writer = writer;
    this.max = max;
    this.total = 0;
  }
  
  @Override
  public void start() {
    writer.print('[');
  }
  
  @Override
  public void end() {
    writer.print(']');
  }

  @Override
  public VisitResult beginEmpresa(int row) {
    if (comma)
      writer.println(',');
    writer.println('{');
    writer.print(" \"id\": " + row);
    comma = true;
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endEmpresa() {
    writer.println();
    writer.print('}');
    if (++total == max)
      return VisitResult.TERMINATE;
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult fieldEmpresa(int row, int col, IField field) {
    writer.println(',');
    writer.print(" \"" + field.getName() + "\":");
    if (!field.isMultivalued())
      writer.print(field.getValue());
    else {
      writer.print('[');
      boolean comma = false;
      for(String v: field.getValues()) {
        if (comma)
          writer.print(',');
        writer.print(v);
        comma = true;
      }
      writer.print(']');
    }
    return VisitResult.CONTINUE;
  }
  
  public static void main(String[] args) throws IOException {
    try(PrintWriter output = new PrintWriter(new File("./output/empresa.json"))){
      FileLayout layout =  new FileLayout.Builder().empresa()
        .cnpj()                             .setup(LONG)
        .identificador_matriz_ou_filial()   .setup(pipe(new MatrizFilialTransformer(), DQUOTE))
        .razao_social_ou_nome_empresarial() .setup(DQUOTE)
        .nome_fantasia()                    .setup(DQUOTE)
        .situacao_cadastral()               .setup(pipe(new SituacaoCadastralTransformer(), DQUOTE))
        .data_situacao_cadastral()          .setup(pipe(ZEROTRIM, DATE, DQUOTE))
        .motivo_situacao_cadastral()        .setup(DQUOTE)
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
        .qualificacao_do_responsavel()      .setup(DQUOTE)
        .capital_social_da_empresa()        .setup(pipe(ZEROTRIM, DQUOTE))
        .porte_empresa()                    .setup(pipe(new PortEmpresaTransformer(), DQUOTE))
        .opcao_pelo_simples()               .setup(pipe(new OpcaoSimplesTransformer(), DQUOTE))
        .data_opcao_pelo_simples()          .setup(pipe(ZEROTRIM, DATE, DQUOTE))
        .data_exclusao_do_simples()         .setup(pipe(ZEROTRIM, DATE, DQUOTE))
        .opcao_pelo_mei()                   .setup(pipe(new OpcaoMeiTransformer(), BOOL))
        .situacao_especial()                .setup(DQUOTE)
        .data_situacao_especial()           .setup(pipe(ZEROTRIM, DATE, DQUOTE))
      .builder().build(new File("./input/K3241.K03200DV.D90805.L00001"), new EmpresaJsonExporter(output));
      layout.run();
    }
    System.out.println("Use o comando: [Get-Content .\\empresa.json -Head 100] para ver as 100 primeiras linhas do arquivo");
  }
}
