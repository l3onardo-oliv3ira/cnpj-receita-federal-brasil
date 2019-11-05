package br.gov.economia.receita.imp.adapter;

import br.gov.economia.receita.imp.IVisitorAdapter;

public abstract class AbstractVisitorAdapter implements IVisitorAdapter {
  private long total;
  private long max;
  
  protected AbstractVisitorAdapter() {
    this(0);
  }
  
  protected AbstractVisitorAdapter(long total) {
    this(total, Long.MAX_VALUE);
  }

  protected AbstractVisitorAdapter(long total, long max) {
    this.total = total;
    this.max = max;
  }

  @Override
  public final long getTotal() {
    return total;
  }
  
  protected final long incrementAndGet() {
    return ++total;
  }
  
  protected final long getAndIncrement() {
    return total++;
  }
  
  @Override
  public final void endData() {
    doEndData();
    if (incrementAndGet() == max) {
      throw new MaxRegisterFoundException(max);
    }
  }

  protected abstract void doEndData();
}
