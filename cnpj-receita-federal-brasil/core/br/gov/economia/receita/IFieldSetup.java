package br.gov.economia.receita;

public interface IFieldSetup<T> {
  public T setup(String name);
  public T setup(IFieldType type);
  public T setup(ITransformer transformer);

  public T setup(String name, IFieldType type);
  public T setup(String name, ITransformer transformer);

  public T setup(ITransformer transformer, IFieldType type);

  public T setup(String name, ITransformer transformer, IFieldType type);
  
  public default T setup() {
    return setup(ITransformer.IDENTITY);
  }
}
