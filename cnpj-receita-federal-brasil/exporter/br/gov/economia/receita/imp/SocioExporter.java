package br.gov.economia.receita.imp;

import br.gov.economia.receita.IField;
import br.gov.economia.receita.adapter.IVisitorAdapter;

public class SocioExporter extends SocioVisitor {

  private IVisitorAdapter adapter;
  
  public SocioExporter(IVisitorAdapter adapter) {
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
  public VisitResult beginSocio(long row) {
    adapter.beginData(row);
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endSocio() {
    adapter.endData();
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult fieldSocio(long row, IField field) {
    adapter.data(row, field);
    return VisitResult.CONTINUE;
  }
}
