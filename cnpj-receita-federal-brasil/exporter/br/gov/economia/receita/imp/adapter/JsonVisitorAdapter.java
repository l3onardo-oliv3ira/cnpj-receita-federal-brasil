package br.gov.economia.receita.imp.adapter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import br.gov.economia.receita.IField;

public class JsonVisitorAdapter implements IVisitorAdapter{

  private boolean comma = false;
  private final PrintWriter writer;
  
  public JsonVisitorAdapter(File output) throws IOException {
    this.writer = new PrintWriter(output, Charset.forName("UTF-8"));
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
  public void endData() {
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
