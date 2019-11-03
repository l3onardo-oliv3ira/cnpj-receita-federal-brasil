package br.gov.economia.receita.imp.standard;

import br.gov.economia.receita.ITransformer;

public enum Transformers implements ITransformer {
  
  ZEROTRIM(new ZeroTrimTransformer()),
  DQUOTE(new QuoteTransformer('"')),
  SQUOTE(new QuoteTransformer('\'')),
  LONG(new LongTransformer()),
  LOWERCASE(new LowercaseTransformer()),
  DATE(new DateTransformer()),
  BOOL(new BooleanTransformer());

  private ITransformer transformer;
  
  Transformers(ITransformer transformer) {
    this.transformer = transformer;
  }
  
  @Override
  public String transform(String input, String fieldName) {
    return transformer.transform(input, fieldName);
  }
  
  public static ITransformer pipe(ITransformer ... transformers) {
    return new PipeTransformer(transformers);
  }
}

