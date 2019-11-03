package br.gov.economia.receita.imp;

import static br.gov.economia.receita.imp.standard.Transformers.DATE;
import static br.gov.economia.receita.imp.standard.Transformers.LONG;
import static br.gov.economia.receita.imp.standard.Transformers.SQUOTE;
import static br.gov.economia.receita.imp.standard.Transformers.ZEROTRIM;
import static br.gov.economia.receita.imp.standard.Transformers.pipe;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import br.gov.economia.receita.IField;

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
  public VisitResult fieldSocio(int row, int col, IField field) {
    writer.print(',');
    writer.print(field.getName());
    values.append(',').append(field.getValue());
    return VisitResult.CONTINUE;
  }
  
  public static void main(String[] args) throws IOException {
    try(PrintWriter output = new PrintWriter(new File("./output/socio.sql"))){
      FileLayout layout =  new FileLayout.Builder().socio().
          cnpj().setup(LONG).
          identificador_de_socio().setup(LONG).
          nome_socio_pf_ou_razao_social_pj().setup(SQUOTE).
          cnpj_ou_cpf_do_s�cio().setup(SQUOTE).
          codigo_qualificacao_socio().setup(LONG).
          percentual_capital_social().setup(LONG).
          data_entrada_sociedade().setup(pipe(ZEROTRIM, DATE, SQUOTE)).
          codigo_pais().setup(LONG).
          nome_pais_socio().setup(SQUOTE).
          cpf_representante_legal().setup(SQUOTE).
          nome_representante().setup(SQUOTE).
          codigo_qualificacao_representante_legal().setup(LONG)
      .builder().build(new File("./input/K3241.K03200DV.D90805.L00001"), new SocioSqlExporter(output, 20000));
      layout.run();
    }
    System.out.println("Use o comando: [Get-Content .\\output.json -Head 100] para ver as 100 primeiras linhas do arquivo");
  }
}