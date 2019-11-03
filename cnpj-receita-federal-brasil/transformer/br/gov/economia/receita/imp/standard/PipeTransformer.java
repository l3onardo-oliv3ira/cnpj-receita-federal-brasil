package br.gov.economia.receita.imp.standard;

import br.gov.economia.receita.ITransformer;

class PipeTransformer implements ITransformer {
  private ITransformer[] transformers;
  
  public PipeTransformer(ITransformer... transformers) {
    this.transformers = transformers;
  }
  
  @Override
  public String transform(String input) {
    for(ITransformer t: transformers)
      input = t.transform(input);
    return input;
  }
}
