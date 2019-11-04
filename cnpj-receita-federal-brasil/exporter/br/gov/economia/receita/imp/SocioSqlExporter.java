package br.gov.economia.receita.imp;

import static br.gov.economia.receita.imp.standard.Transformers.DATE;
import static br.gov.economia.receita.imp.standard.Transformers.LONG;
import static br.gov.economia.receita.imp.standard.Transformers.SQUOTE;
import static br.gov.economia.receita.imp.standard.Transformers.ZEROTRIM;
import static br.gov.economia.receita.imp.standard.Transformers.pipe;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import br.gov.economia.receita.IField;
import br.gov.economia.receita.imp.standard.DefaultIfEmpty;

public class SocioSqlExporter extends SocioVisitor {

  private final int max;
  private final PrintWriter writer;
  private int total = 0;
  private StringBuilder values = new StringBuilder();
  
  public SocioSqlExporter(PrintWriter writer) {
    this(writer, Integer.MAX_VALUE);
  }
  
  public SocioSqlExporter(PrintWriter writer, int max) {
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
  public VisitResult beginSocio(int row) {
    values.setLength(0);
    writer.print("insert into socio (id");
    values.append(row);
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endSocio() {
    writer.print(")values(");
    writer.print(values);
    writer.println(");");
    if (++total == max)
      return VisitResult.TERMINATE;
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult fieldSocio(int row, IField field) {
    writer.print(',');
    writer.print(field.getName());
    values.append(',').append(field.getValue());
    return VisitResult.CONTINUE;
  }
  
  public static void main(String[] args) throws IOException {
    try(PrintWriter output = new PrintWriter(new File("./output/socio.sql"), Charset.forName("UTF-8"))){
      FileLayout layout =  new FileLayout.Builder().socio().
          cnpj().setup(LONG).
          identificador_de_socio()          .setup("identificador"      , LONG).
          nome_socio_pf_ou_razao_social_pj().setup("razao_social"       , SQUOTE).
          cnpj_ou_cpf_do_sócio()            .setup("cpf_cnpj"           , pipe(new CpfInvalidoTransformer(), SQUOTE)).
          codigo_qualificacao_socio()       .setup("qualificacao"       , pipe(new QualificacaoSocioTransformer(), SQUOTE)).
          percentual_capital_social()       .setup("percentual"         , pipe(LONG, new DefaultIfEmpty("VAZIO"))).
          data_entrada_sociedade()          .setup("entrada"            , pipe(ZEROTRIM, DATE, SQUOTE)).
          codigo_pais()                     .setup("pais"               , pipe(LONG, new DefaultIfEmpty("VAZIO"), SQUOTE)).
          nome_pais_socio()                 .setup("nome_pais"          , SQUOTE).
          cpf_representante_legal()         .setup("cpf_representante"  , pipe(new CpfInvalidoTransformer(), SQUOTE)).
          nome_representante()              .setup("nome_representante" , SQUOTE).
          codigo_qualificacao_representante_legal().setup("cod_repre"   , pipe(new QualificacaoRepresentanteLegalTransformer(), SQUOTE)).
      builder().build(new File("./input/K3241.K03200DV.D90805.L00002")  , new SocioSqlExporter(output, 2000));
      layout.run();
    }
    System.out.println("Use o comando: [Get-Content .\\socio.sql -Head 100] para ver as 100 primeiras linhas do arquivo");
  }
}
