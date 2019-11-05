package br.gov.economia.receita.adapter.imp;

import static br.gov.economia.receita.imp.Constants.UTF_8;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import br.gov.economia.receita.IField;

public class SqlVisitorAdapter extends AbstractVisitorAdapter{

  private boolean comma = false;
  private final String tableName;
  private final PrintWriter writer;
  
  private final StringBuilder values = new StringBuilder();
  
  public SqlVisitorAdapter(File output, String tableName) throws IOException {
    this(output, Long.MAX_VALUE, tableName);
  }
  
  public SqlVisitorAdapter(File output, long max, String tableName) throws IOException {
    super(0, max);
    this.writer = new PrintWriter(output, UTF_8);
    this.tableName = tableName;
  }
  
  @Override
  public void start() {
    writer.println(";start transaction here (insert your script here)");
  }
  
  @Override
  public void end() {
    writer.println(";commit transaction here (insert your script here)");
    writer.close();
  }

  @Override
  public void beginData(long row) {
    comma = false;
    values.setLength(0);
    writer.print("insert into " + tableName + " (");
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