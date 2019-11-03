package br.gov.economia.receita.imp;

import br.gov.economia.receita.IField;
import br.gov.economia.receita.IRegisterVisitor;

public abstract class SocioVisitor implements IRegisterVisitor{

  @Override
  public VisitResult beginHeader(int row) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult fieldHeader(int row, int col, IField field) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult endHeader() {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult beginCnae(int row) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult fieldCnae(int row, int col, IField field) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult endCnae() {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult beginTrailler(int row) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult fieldTrailler(int row, int column, IField field) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult endTrailler() {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult beginEmpresa(int row) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult fieldEmpresa(int row, int col, IField field) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult endEmpresa() {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult handleError(int lineNumber, Throwable e) {
    e.printStackTrace(System.err);
    return VisitResult.TERMINATE;
  }
}
