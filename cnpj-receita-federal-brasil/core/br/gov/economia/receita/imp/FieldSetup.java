package br.gov.economia.receita.imp;

import br.gov.economia.receita.IFieldSetup;
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
  public T setup(String name, ITransformer transformer) {
    field.setName(name);
    field.setTransformer(transformer);
    return this.layout;
  }

}
