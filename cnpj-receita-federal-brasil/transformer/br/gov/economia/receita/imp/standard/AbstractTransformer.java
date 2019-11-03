package br.gov.economia.receita.imp.standard;

import br.gov.economia.receita.ITransformer;

public abstract class AbstractTransformer implements ITransformer {
  
  @Override
  public final String transform(String input, String fieldName) {
    try {
      //System.out.println("antes: " + fieldName + ": " + input);
      String output = doTransform(input);
      //System.out.println("depois: " + fieldName + ": " + output);
      return output;
    }catch(Exception e) {
      System.out.println("erro em " + fieldName + " : " + input);
      e.printStackTrace();
      return input;
    }
  }
  
  protected abstract String doTransform(String input);
}
