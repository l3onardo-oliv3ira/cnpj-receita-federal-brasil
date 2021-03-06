package br.gov.economia.receita.imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.gov.economia.receita.IField;
import br.gov.economia.receita.IFieldType;
import br.gov.economia.receita.ITransformer;

class Field implements IField {
  
  private String name;
  private final int start;
  private final int size;
  private final LineReader reader;
  private ITransformer transformer = ITransformer.IDENTITY;
  private List<String> values = new ArrayList<>(1);
  private IFieldType type;
  
  Field(String name, int start, int size, LineReader reader) {
    this.name = name;
    this.start = start;
    this.size = size;
    this.reader = reader;
    this.type = DatabaseType.TEXT;
  }
  
  @Override
  public final String getName() {
    return this.name;
  } 
  
  @Override
  public final String getValue() {
    return this.values.size() == 0 ? "" : this.values.get(0);
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
    return reader.isMultiValued();
  }
  
  @Override
  public IFieldType getType() {
    return type;
  }
  
  @Override
  public final String toString() {
    return this.name + ": " + getValue();
  }
  
  final void setName(String name) {
    this.name = name;
  }
  
  final void setType(IFieldType type) {
    this.type = type;
  }
  
  final void setTransformer(ITransformer transformer) {
    this.transformer = transformer;
  }

  final void read(String line) {
    values.clear();
    reader.read(line, start, size, values);
    int size = values.size();
    for(int i = 0; i < size; i++) {
      values.set(i, transformer.transform(values.get(i), name));
    }
  }
}