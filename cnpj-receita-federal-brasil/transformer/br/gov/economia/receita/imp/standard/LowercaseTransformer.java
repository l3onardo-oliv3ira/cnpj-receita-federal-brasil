package br.gov.economia.receita.imp.standard;

class LowercaseTransformer extends AbstractTransformer{
  @Override
  protected String doTransform(String input) {
    return input.toLowerCase();
  }
}
