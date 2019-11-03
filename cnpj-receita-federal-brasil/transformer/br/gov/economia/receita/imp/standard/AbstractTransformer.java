package br.gov.economia.receita.imp.standard;

import br.gov.economia.receita.ITransformer;

public abstract class AbstractTransformer implements ITransformer {
  
  @Override
  public String transform(String input) {
    return doTransform(input);
  }
  
  protected abstract String doTransform(String input);
}
