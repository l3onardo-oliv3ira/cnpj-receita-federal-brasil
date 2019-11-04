package br.gov.economia.receita.imp;

import br.gov.economia.receita.IField;
import br.gov.economia.receita.IRegisterVisitor;

public abstract class SocioVisitor implements IRegisterVisitor{

  @Override
  public VisitResult beginHeader(long row) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult fieldHeader(long row, IField field) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult endHeader() {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult beginCnae(long row) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult fieldCnae(long row, IField field) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult endCnae() {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult beginTrailler(long row) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult fieldTrailler(long row, IField field) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult endTrailler() {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult beginEmpresa(long row) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult fieldEmpresa(long row, IField field) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult endEmpresa() {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult handleError(long lineNumber, Throwable e) {
    e.printStackTrace(System.err);
    return VisitResult.TERMINATE;
  }
}
