package br.gov.economia.receita.imp;

import br.gov.economia.receita.imp.standard.AbstractTransformer;

public class IdentificadorSocioTransformer extends AbstractTransformer{
	@Override
	protected String doTransform(String input) {
		switch(input) {
			case "1": return "Pessoa Jur�dica";
			case "2": return "Pessoa F�sica";
			case "3": return "Estrangeiro";
			default:  return "Indefinido";
		}
	}
}
