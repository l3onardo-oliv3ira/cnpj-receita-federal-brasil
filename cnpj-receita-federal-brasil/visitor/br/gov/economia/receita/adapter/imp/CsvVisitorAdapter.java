package br.gov.economia.receita.adapter.imp;

import static br.gov.economia.receita.imp.Constants.UTF_8;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import br.gov.economia.receita.IField;

public class CsvVisitorAdapter extends AbstractVisitorAdapter {

  private boolean begin = false;
  private PrintWriter writer;

  public CsvVisitorAdapter(File output) throws IOException {
    this(output, Long.MAX_VALUE);
  }
  
  public CsvVisitorAdapter(File output, long max) throws IOException {
    super(0, max);
    this.writer = new PrintWriter(output, UTF_8);
  }

  @Override
  public void start() {
    writer.print("cnpj,cnae");
  }

  @Override
  public void end() {
    writer.close();
  }

  @Override
  public void beginData(long row) {
    writer.println();
  }

  @Override
  public void data(long row, IField field) {
    if (begin)
      writer.print('|');
    writer.print(field.getValue());
    begin = true;
  }

  @Override
  public void doEndData() {
    begin = false;
  }
}
