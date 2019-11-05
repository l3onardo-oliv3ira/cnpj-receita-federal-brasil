package br.gov.economia.receita.imp;

import java.io.IOException;

import br.gov.economia.receita.IField;

public class EmpresaExporter extends EmpresaVisitor {

  private IVisitorAdapter adapter;
  
  public EmpresaExporter(IVisitorAdapter adapter) throws IOException {
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
  public VisitResult beginEmpresa(long row) {
    adapter.beginData(row);
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endEmpresa() {
    adapter.endData();
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult fieldEmpresa(long row, IField field) {
    adapter.data(row, field);
    return VisitResult.CONTINUE;
  }
}
