package br.gov.economia.receita.imp.adapter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import br.gov.economia.receita.IField;

public class SqlVisitorAdapter implements IVisitorAdapter {

  private final String tableName;
  private final PrintWriter writer;
  
  private final StringBuilder values = new StringBuilder();
  
  public SqlVisitorAdapter(String tableName, File output) throws IOException {
    this.writer = new PrintWriter(output, Charset.forName("UTF-8"));
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
    values.setLength(0);
    writer.print("insert into " + tableName + " (id");
    values.append(row);
  }

  @Override
  public void data(long row, IField field) {
    writer.print(',');
    writer.print(field.getName());
    values.append(',').append(field.getValue());
  }
  
  @Override
  public void endData() {
    writer.print(")values(");
    writer.print(values);
    writer.println(");");
  }
}