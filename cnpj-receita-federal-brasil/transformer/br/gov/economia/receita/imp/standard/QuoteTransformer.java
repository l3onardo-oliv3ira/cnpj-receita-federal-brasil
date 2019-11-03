package br.gov.economia.receita.imp.standard;

class QuoteTransformer extends AbstractTransformer{
  
  private char symbol;
  
  public QuoteTransformer() {
    this('"');
  }
  
  public QuoteTransformer(char symbol) {
    this.symbol = symbol;
  }

  @Override
  protected String doTransform(String input) {
    return symbol + input + symbol;
  }
}
