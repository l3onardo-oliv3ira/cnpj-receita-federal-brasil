package br.gov.economia.receita.imp.adapter;

import static br.gov.economia.receita.imp.Constants.ISO_8859_15;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import br.gov.economia.receita.IField;
import br.gov.economia.receita.imp.Constants;

public class JsonVisitorAdapter extends AbstractVisitorAdapter{

  private boolean comma = false;
  private final PrintWriter writer;
  
  public JsonVisitorAdapter(File output) throws IOException {
    this(output, Long.MAX_VALUE);
  }
  
  public JsonVisitorAdapter(File output, long max) throws IOException {
    super(0, max);
    this.writer = new PrintWriter(output, ISO_8859_15);
  }
  
  @Override
  public void start() {
    writer.print('[');
  }
  
  @Override
  public void end() {
    writer.print(']');
    writer.close();
  }

  @Override
  public void beginData(long row) {
    if (comma)
      writer.println(',');
    writer.println('{');
    writer.print(" \"id\": " + row);
    comma = true;
  }

  @Override
  public void doEndData() {
    writer.println();
    writer.print('}');
  }

  @Override
  public void data(long row, IField field) {
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
  }
}
