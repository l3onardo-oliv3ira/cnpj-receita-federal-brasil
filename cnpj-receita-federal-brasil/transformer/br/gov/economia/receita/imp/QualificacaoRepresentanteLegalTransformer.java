package br.gov.economia.receita.imp;

import br.gov.economia.receita.ITransformer;
import br.gov.economia.receita.imp.standard.AbstractTransformer;

public class QualificacaoRepresentanteLegalTransformer extends AbstractTransformer implements ITransformer {
  @Override
  protected String doTransform(String input) {
    switch(Strings.toInt(input, -1)) {
      case 17:  return "Procurador";
      case 14:  return "Mãe";
      case 15:  return "Pai";
      case 35:  return "Tutor";
      case 9:   return "Curador";
      case 5:   return "Administrador";
      default:  return input;
    }
  }
}
