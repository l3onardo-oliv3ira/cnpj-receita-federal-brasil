package br.gov.economia.receita.imp;

import br.gov.economia.receita.IField;
import br.gov.economia.receita.IRegisterVisitor;

/**
 * Visitor que ignora o layout dos demais tipos de dados e considera apenas os registros
 * de dados de empresas.
 * @author LEOOLIVE
 *
 */
public abstract class EmpresaVisitor implements IRegisterVisitor{

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
  public VisitResult beginSocio(long row) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult fieldSocio(long row, IField field) {
    return VisitResult.SKIP;
  }

  @Override
  public VisitResult endSocio() {
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
  public VisitResult handleError(long row, Throwable e) {
    e.printStackTrace(System.err);
    return VisitResult.TERMINATE;
  }
}
