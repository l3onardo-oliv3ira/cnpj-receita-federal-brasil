package br.gov.economia.receita;

import br.gov.economia.receita.imp.VisitResult;

public interface IRegisterVisitor {
  public VisitResult beginHeader(int row);
  public VisitResult fieldHeader(int row, IField field);
  public VisitResult endHeader();
  
  public VisitResult beginEmpresa(int row);
  public VisitResult fieldEmpresa(int row, IField field);
  public VisitResult endEmpresa();
  
  public VisitResult beginSocio(int row);
  public VisitResult fieldSocio(int row, IField field);
  public VisitResult endSocio();
      
  public VisitResult beginCnae(int row);
  public VisitResult fieldCnae(int row, IField field);
  public VisitResult endCnae();

  public VisitResult beginTrailler(int row);
  public VisitResult fieldTrailler(int row, IField field);
  public VisitResult endTrailler();
  
  public VisitResult handleError(int row, Throwable e);
  
  public void end();
  public void start();
}
