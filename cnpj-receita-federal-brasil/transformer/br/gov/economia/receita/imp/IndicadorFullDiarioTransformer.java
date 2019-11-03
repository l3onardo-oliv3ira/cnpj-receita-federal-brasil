package br.gov.economia.receita.imp;

import br.gov.economia.receita.imp.standard.AbstractTransformer;

public class IndicadorFullDiarioTransformer extends AbstractTransformer{

  @Override
  protected String doTransform(String input) {
    switch(input) {
      case "F": return "Full";
      case "D": return "Diário";
      case "M": return "Mensal";
      case "T": return "Trimestral";
      default: return "Indefinido";
    }
  }

}
