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
		field.setBypass(false);
		field.setTransformer(transformer);
		return this.layout;
	}
}
