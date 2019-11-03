package br.gov.economia.receita;

public interface ITransformer {
	public static final ITransformer IDENTITY = (input) -> input;

	public String transform(String input);
}
