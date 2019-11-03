package br.gov.economia.receita.imp;

import br.gov.economia.receita.imp.standard.AbstractTransformer;

public class CpfInvalidoTransformer extends AbstractTransformer{

  private static final String[] INVALID_FORMAT = new String[] {
    "***000000**",
    "CPF INVALIDO"
  };
  
  @Override
  protected String doTransform(String input) {
    for(int i = 0; i < INVALID_FORMAT.length; i++)
      if (INVALID_FORMAT[i].equalsIgnoreCase(input))
        return "";
    return input;
  }

}
