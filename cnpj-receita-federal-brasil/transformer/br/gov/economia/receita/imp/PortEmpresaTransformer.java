package br.gov.economia.receita.imp;

import br.gov.economia.receita.imp.standard.AbstractTransformer;

public class PortEmpresaTransformer extends AbstractTransformer{
	@Override
	protected String doTransform(String input) {
		switch(input) {
				case "00": return "N�o informado";
				case "01": return "Micro empresa";
				case "03": return "Empresa de pequeno porte"; 
				default: 	 return "Demais";
		}
	}
}
