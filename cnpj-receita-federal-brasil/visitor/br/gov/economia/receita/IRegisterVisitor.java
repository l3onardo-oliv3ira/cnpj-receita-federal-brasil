package br.gov.economia.receita;

import br.gov.economia.receita.imp.VisitResult;

public interface IRegisterVisitor {
  public VisitResult beginHeader(long row);
  public VisitResult fieldHeader(long row, IField field);
  public VisitResult endHeader();
  
  public VisitResult beginEmpresa(long row);
  public VisitResult fieldEmpresa(long row, IField field);
  public VisitResult endEmpresa();
  
  public VisitResult beginSocio(long row);
  public VisitResult fieldSocio(long row, IField field);
  public VisitResult endSocio();
      
  public VisitResult beginCnae(long row);
  public VisitResult fieldCnae(long row, IField field);
  public VisitResult endCnae();

  public VisitResult beginTrailler(long row);
  public VisitResult fieldTrailler(long row, IField field);
  public VisitResult endTrailler();
  
  public VisitResult handleError(long row, Throwable e);
  
  public void end();
  public void start();
}
