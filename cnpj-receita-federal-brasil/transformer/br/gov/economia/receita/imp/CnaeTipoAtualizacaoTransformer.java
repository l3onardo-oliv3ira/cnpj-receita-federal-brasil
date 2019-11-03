package br.gov.economia.receita.imp;

import br.gov.economia.receita.imp.standard.AbstractTransformer;

public class CnaeTipoAtualizacaoTransformer extends AbstractTransformer {
	@Override
	protected String doTransform(String input) {
		switch(input) {
			case "A": return "Atualização do estabelecimento";
			case "I": return "Inclusão de um novo estabelecimento";
			case "E": return "Exclusão do estabelecimento";
			default: return "Indefinido";
		}
	}
}
