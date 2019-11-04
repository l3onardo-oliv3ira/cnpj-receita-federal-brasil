package br.gov.economia.receita.imp.standard;

public class DefaultIfEmpty extends AbstractTransformer {

  private String defaultValue;
  
  public DefaultIfEmpty(String defaultValue) {
    this.defaultValue = defaultValue;
  }
  
  @Override
  protected String doTransform(String input) {
    return input.isEmpty() ? defaultValue: input;
  }
}
