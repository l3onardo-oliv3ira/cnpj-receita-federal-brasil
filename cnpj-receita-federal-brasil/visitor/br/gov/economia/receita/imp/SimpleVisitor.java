package br.gov.economia.receita.imp;

import br.gov.economia.receita.IField;
import br.gov.economia.receita.IRegisterVisitor;

public abstract class SimpleVisitor implements IRegisterVisitor {

  @Override
  public void start() {}
  
  @Override
  public void end() {}
  
  @Override
  public VisitResult beginHeader(long row) {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endHeader() {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult beginEmpresa(long row) {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endEmpresa() {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult beginSocio(long row) {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endSocio() {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult beginCnae(long row) {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endCnae() {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult beginTrailler(long row) {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endTrailler() {
    return VisitResult.CONTINUE;
  }
  
  @Override
  public VisitResult fieldHeader(long row, IField field) {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult fieldEmpresa(long row, IField field) {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult fieldSocio(long row, IField field) {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult fieldCnae(long row, IField field) {
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult fieldTrailler(long row, IField field) {
    return VisitResult.CONTINUE;
  }
  
  @Override
  public VisitResult handleError(long row, Throwable e) {
    System.err.println("Erro na linha: " + row);
    e.printStackTrace(System.err);
    return VisitResult.TERMINATE;
  }
}
