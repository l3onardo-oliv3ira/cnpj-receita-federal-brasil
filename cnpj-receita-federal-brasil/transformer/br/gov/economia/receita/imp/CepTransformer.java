package br.gov.economia.receita.imp;

import br.gov.economia.receita.imp.standard.AbstractTransformer;

public class CepTransformer extends AbstractTransformer{
  @Override
  protected String doTransform(String input) {
    if (input.length() != 8)
      return input;
    return input.substring(0, 5) + '-' + input.substring(5);
  }
}
