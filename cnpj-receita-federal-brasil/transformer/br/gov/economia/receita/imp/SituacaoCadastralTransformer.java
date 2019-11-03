package br.gov.economia.receita.imp;

import br.gov.economia.receita.imp.standard.AbstractTransformer;

public class SituacaoCadastralTransformer extends AbstractTransformer{
	@Override
	protected String doTransform(String input) {
		switch(input) {
			case "01": return "Nula";
			case "02": return "Ativa";
			case "03": return "Suspensa";
			case "04": return "Inapta";
			case "08": return "Baixada";
			default: return "Indefinida";
		}
	}
}
