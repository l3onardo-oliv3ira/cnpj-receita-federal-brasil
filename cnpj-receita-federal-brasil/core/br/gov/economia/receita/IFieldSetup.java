package br.gov.economia.receita;

public interface IFieldSetup<T> {
	public T setup(ITransformer transformer);
	public default T setup() {
		return setup(ITransformer.IDENTITY);
	}
}
