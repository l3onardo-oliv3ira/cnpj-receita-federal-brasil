package br.gov.economia.receita.imp;

import br.gov.economia.receita.imp.standard.AbstractTransformer;

public class MatrizFilialTransformer  extends AbstractTransformer{
	@Override
	protected String doTransform(String input) {
		switch(input) {
			case "1": return "Matriz";
			case "2": return "Filial";
			default: return "Indefinido";
		}
	}
}
