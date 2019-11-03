package br.gov.economia.receita.imp;

import br.gov.economia.receita.imp.standard.AbstractTransformer;

public class OpcaoSimplesTransformer extends AbstractTransformer{
	@Override
	protected String doTransform(String input) {
		switch(input) {
			case "0": return "N�o optante";
			case "5":
			case "7": return "Optante pelo simples";
			case "6":
			case "8": return "Exclu�dos do simples";
			default:  return "Indefinido";
		}
	}
}
