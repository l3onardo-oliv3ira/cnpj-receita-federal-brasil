package br.gov.economia.receita.imp.standard;

class DateTransformer extends AbstractTransformer{
  
  private char separator;
  
  public DateTransformer() {
    this('-');
  }
  
  public DateTransformer(char separator) {
    this.separator = separator;
  }

  @Override
  protected String doTransform(String input) {
    if (input.length() != 8)
      return input;
    return input.substring(0, 4) + separator + input.substring(4, 6) + separator + input.substring(6) + " 00:00:00.000";
  }
}
