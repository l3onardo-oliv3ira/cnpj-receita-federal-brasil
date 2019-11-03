package br.gov.economia.receita.imp.standard;

import br.gov.economia.receita.ITransformer;

public abstract class AbstractTransformer implements ITransformer {
  
  @Override
  public final String transform(String input) {
    //we have to go back here to work on input argument before doTransform (template method design pattern)
    return doTransform(input);
  }
  
  protected abstract String doTransform(String input);
}
