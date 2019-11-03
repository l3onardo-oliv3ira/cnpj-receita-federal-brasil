package br.gov.economia.receita.imp.standard;

public class BooleanTransformer extends AbstractTransformer{

  private static final String[] YES = new String[] {
      "sim",
      "yes",
      "on",
      "1",
      "true",
      "s",
      "y"
  };
  
  @Override
  protected String doTransform(String input) {
    input = input.toLowerCase().trim();
    for(int i = 0; i < YES.length; i++)
      if (YES[i].equals(input))
        return "true";
    return "false";
  }
}
