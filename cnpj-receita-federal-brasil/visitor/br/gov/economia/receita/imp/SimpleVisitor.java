package br.gov.economia.receita.imp;

import br.gov.economia.receita.IField;
import br.gov.economia.receita.IRegisterVisitor;

public abstract class SimpleVisitor implements IRegisterVisitor {

  @Override
  public void start() {}
  
  @Override
  public void end() {}
  
  @Override
  public VisitResult beginHeader(int row) {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endHeader() {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult beginEmpresa(int row) {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endEmpresa() {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult beginSocio(int row) {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endSocio() {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult beginCnae(int row) {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endCnae() {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult beginTrailler(int row) {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endTrailler() {
    return VisitResult.CONTINUE;
  }
  
  @Override
  public VisitResult fieldHeader(int row, int col, IField field) {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult fieldEmpresa(int row, int col, IField field) {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult fieldSocio(int row, int col, IField field) {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult fieldCnae(int row, int col, IField field) {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult fieldTrailler(int row, int column, IField field) {
    return VisitResult.CONTINUE;
  }
  
  @Override
  public VisitResult handleError(int lineNumber, Throwable e) {
    System.err.println("Erro na linha: " + lineNumber);
    e.printStackTrace(System.err);
    return VisitResult.TERMINATE;
  }
}
