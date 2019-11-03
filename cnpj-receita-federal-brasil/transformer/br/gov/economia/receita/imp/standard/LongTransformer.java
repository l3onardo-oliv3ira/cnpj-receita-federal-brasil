package br.gov.economia.receita.imp.standard;

import br.gov.economia.receita.imp.Strings;

class LongTransformer extends AbstractTransformer{

  @Override
  protected String doTransform(String input) {
    int value = Strings.toInt(input, -1);
    return "" + (value > 0 ? value : "");
  }
}
