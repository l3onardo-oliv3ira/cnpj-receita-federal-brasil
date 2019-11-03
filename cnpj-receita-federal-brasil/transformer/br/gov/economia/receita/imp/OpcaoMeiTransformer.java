package br.gov.economia.receita.imp;

import br.gov.economia.receita.imp.standard.AbstractTransformer;

public class OpcaoMeiTransformer extends AbstractTransformer{
  @Override
  protected String doTransform(String input) {
    switch(input) {
      case "S": return "Sim";
      default:  return "Não";
    }
  }
}
