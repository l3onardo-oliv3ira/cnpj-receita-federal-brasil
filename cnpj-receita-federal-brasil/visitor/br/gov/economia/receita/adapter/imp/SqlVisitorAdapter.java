package br.gov.economia.receita.adapter.imp;

import static br.gov.economia.receita.imp.Constants.UTF_8;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import br.gov.economia.receita.IField;
import br.gov.economia.receita.imp.IDdlOutput;

public class SqlVisitorAdapter extends AbstractVisitorAdapter{

  private boolean comma = false;
  private final IDdlOutput ddl;
  private final PrintWriter writer;
  
  private final StringBuilder values = new StringBuilder();
  
  public SqlVisitorAdapter(File output, IDdlOutput ddl) throws IOException {
    this(output, Long.MAX_VALUE, ddl);
  }
  
  public SqlVisitorAdapter(File output, long max, IDdlOutput ddl) throws IOException {
    super(0, max);
    this.writer = new PrintWriter(output, UTF_8);
    this.ddl = ddl;
  }
  
  @Override
  public void start() {
    writer.println("BEGIN TRANSACTION;");
    writer.println(ddl.drop());
    writer.println(ddl.create());
  }
  
  @Override
  public void end() {
    writer.println("COMMIT;");
    writer.close();
  }

  @Override
  public void beginData(long row) {
    comma = false;
    values.setLength(0);
    writer.print("insert into " + ddl.getName() + " (");
  }

  @Override
  public void data(long row, IField field) {
    if (comma)
      writer.print(',');
    writer.print(field.getName());
    if (comma)
      values.append(',');
    values.append(field.getValue());
    comma = true;
  }
  
  @Override
  public void doEndData() {
    writer.print(")values(");
    writer.print(values);
    writer.println(");");
  }
}