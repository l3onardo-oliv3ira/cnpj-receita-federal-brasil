package br.gov.economia.receita.imp.standard;

class LongTransformer extends AbstractTransformer{

  @Override
  protected String doTransform(String input) {
    return String.valueOf(Long.parseLong(input)); //remove zero's (left side)
  }
}
