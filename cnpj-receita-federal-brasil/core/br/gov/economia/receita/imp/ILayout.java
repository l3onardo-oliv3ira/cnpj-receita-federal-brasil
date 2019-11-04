package br.gov.economia.receita.imp;

import br.gov.economia.receita.IRegisterVisitor;

interface ILayout {
  public VisitResult begin(IRegisterVisitor visitor, long row);
  public VisitResult visit(IRegisterVisitor visitor, long row, String line);
  public VisitResult end(IRegisterVisitor visitor);
}

enum SkipLayout implements ILayout {
  INSTANCE;
  
  @Override
  public VisitResult visit(IRegisterVisitor visitor, long row, String line) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult begin(IRegisterVisitor visitor, long row) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult end(IRegisterVisitor visitor) {
    return VisitResult.SKIP;
  }
}