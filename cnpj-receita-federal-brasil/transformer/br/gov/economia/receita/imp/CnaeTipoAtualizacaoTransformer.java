package br.gov.economia.receita.imp;

import br.gov.economia.receita.imp.standard.AbstractTransformer;

public class CnaeTipoAtualizacaoTransformer extends AbstractTransformer {
	@Override
	protected String doTransform(String input) {
		switch(input) {
			case "A": return "Atualiza��o do estabelecimento";
			case "I": return "Inclus�o de um novo estabelecimento";
			case "E": return "Exclus�o do estabelecimento";
			default: return "Indefinido";
		}
	}
}
