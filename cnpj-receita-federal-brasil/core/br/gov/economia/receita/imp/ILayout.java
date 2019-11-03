package br.gov.economia.receita.imp;

import br.gov.economia.receita.IRegisterVisitor;

interface ILayout {
  public VisitResult begin(IRegisterVisitor visitor, int row);
  public VisitResult process(IRegisterVisitor visitor, int row, String line);
  public VisitResult end(IRegisterVisitor visitor);
}

enum SkipLayout implements ILayout {
  INSTANCE;
  
  @Override
  public VisitResult process(IRegisterVisitor visitor, int row, String line) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult begin(IRegisterVisitor visitor, int row) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult end(IRegisterVisitor visitor) {
    return VisitResult.SKIP;
  }
}