package br.gov.economia.receita.imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.gov.economia.receita.IField;
import br.gov.economia.receita.ITransformer;

class Field implements IField {
  
  private String name;
  private List<String> values;
  private final int start;
  private final int size;
  private final LineReader reader;
  private ITransformer transformer = ITransformer.IDENTITY;
  
  Field(String name, int start, int size, LineReader reader) {
    this.name = name;
    this.start = start;
    this.size = size;
    this.reader = reader;
  }
  
  @Override
  public final String getName() {
    return this.name;
  } 
  
  @Override
  public final String getValue() {
    return this.values == null ? "" : this.values.get(0);
  }
  
  public final int getStart() {
    return this.start;
  }
  
  public final int getSize() {
    return this.size;
  }

  @Override
  public final List<String> getValues() {
    return Collections.unmodifiableList(this.values);
  }

  @Override
  public final boolean isMultivalued() {
    return this.values != null && this.values.size() > 1;
  }
  
  @Override
  public final String toString() {
    return this.name + ": " + getValue();
  }
  
  final void setName(String name) {
    this.name = name;
  }
  
  final void setTransformer(ITransformer transformer) {
    this.transformer = transformer;
  }

  final void read(String line) {
    List<String> output = reader.read(line, start, size);
    List<String> transf = new ArrayList<String>(output.size());
    for(String v: output)
      transf.add(transformer.transform(v, this.name));
    this.values = transf;
  }
}