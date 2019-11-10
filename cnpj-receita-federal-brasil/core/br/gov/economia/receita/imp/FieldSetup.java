package br.gov.economia.receita.imp;

import br.gov.economia.receita.IFieldSetup;
import br.gov.economia.receita.IFieldType;
import br.gov.economia.receita.ITransformer;

class FieldSetup<T> implements IFieldSetup<T> {

  private Field field;
  private T layout;
  
  public FieldSetup(Field field, T layout) {
    this.field = field;
    this.layout = layout;
  }

  @Override
  public T setup(ITransformer transformer) {
    field.setTransformer(transformer);
    return this.layout;
  }

  @Override
  public T setup(String name) {
    field.setName(name);
    return this.layout;
  }

  @Override
  public T setup(IFieldType type) {
    field.setType(type);
    return this.layout;
  }

  @Override
  public T setup(String name, ITransformer transformer) {
    field.setName(name);
    field.setTransformer(transformer);
    return this.layout;
  }

  @Override
  public T setup(String name, IFieldType type) {
    field.setName(name);
    field.setType(type);
    return this.layout;
  }

  @Override
  public T setup(String name, ITransformer transformer, IFieldType type) {
    field.setName(name);
    return setup(transformer, type);
  }

  @Override
  public T setup(ITransformer transformer, IFieldType type) {
    field.setType(type);
    field.setTransformer(transformer);
    return this.layout;
  }
}
