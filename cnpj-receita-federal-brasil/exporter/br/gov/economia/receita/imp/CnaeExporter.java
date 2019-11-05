package br.gov.economia.receita.imp;

import br.gov.economia.receita.IField;
import br.gov.economia.receita.adapter.IVisitorAdapter;

public class CnaeExporter extends CnaeVisitor {

  private final IVisitorAdapter adapter;
  
  public CnaeExporter(IVisitorAdapter adapter) {
    this.adapter = adapter;
  }
  
  @Override
  public void start() {
    adapter.start();
  }
  
  @Override
  public void end() {
    adapter.end();
  }

  @Override
  public VisitResult beginCnae(long row) {
    adapter.beginData(row);
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult fieldCnae(long row, IField field) {
    adapter.data(row, field);
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endCnae() {
    adapter.endData();
    return VisitResult.CONTINUE;
  }
}
