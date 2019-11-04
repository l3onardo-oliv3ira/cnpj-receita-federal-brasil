package br.gov.economia.receita;

public interface IFieldSetup<T> {
  public T setup(String name);
  public T setup(ITransformer transformer);
  public T setup(String name, ITransformer transformer);
  public default T setup() {
    return setup(ITransformer.IDENTITY);
  }
}
