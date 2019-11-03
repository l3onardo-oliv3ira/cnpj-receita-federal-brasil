package br.gov.economia.receita.imp.standard;

class ZeroTrimTransformer extends AbstractTransformer{
  @Override
  protected String doTransform(String input) {
    int i = 0;
    while(i < input.length() && input.charAt(i) == '0')
      i++;
    if (i == input.length())
      return "";
    return input.substring(i);
  }
}
