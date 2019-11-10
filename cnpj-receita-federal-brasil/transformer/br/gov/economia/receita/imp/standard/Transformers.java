package br.gov.economia.receita.imp.standard;

import br.gov.economia.receita.ITransformer;

public enum Transformers implements ITransformer {
  
  T_ZTRIM(new ZeroTrimTransformer()),
  T_DQUOTE(new QuoteTransformer('"')),
  T_SQUOTE(new QuoteTransformer('\'')),
  T_LONG(new LongTransformer()),
  T_LOWERCASE(new LowercaseTransformer()),
  T_DATE(new DateTransformer()),
  T_BOOL(new BooleanTransformer());
  
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

