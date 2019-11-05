package br.gov.economia.receita.imp.adapter;

import static br.gov.economia.receita.imp.Constants.ISO_8859_15;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import br.gov.economia.receita.IField;

public class SqlVisitorAdapter extends AbstractVisitorAdapter{

  private final String tableName;
  private final PrintWriter writer;
  
  private final StringBuilder values = new StringBuilder();
  
  public SqlVisitorAdapter(File output, String tableName) throws IOException {
    this(output, Long.MAX_VALUE, tableName);
  }
  
  public SqlVisitorAdapter(File output, long max, String tableName) throws IOException {
    super(0, max);
    this.writer = new PrintWriter(output, ISO_8859_15);
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
  public void doEndData() {
    writer.print(")values(");
    writer.print(values);
    writer.println(");");
  }
}