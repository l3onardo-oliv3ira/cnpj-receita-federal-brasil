package br.gov.economia.receita.imp;

import static java.lang.System.out;

import br.gov.economia.receita.IField;

public class SysoutVisitor extends SimpleVisitor{

  @Override
  public void end() {
    out.println("fim do arquivo");
  }
  
  @Override
  public void start() {
    out.println("Iniciado atravessamento do arquivo");
  }
  
  @Override
  public VisitResult beginHeader(long row) {
    out.println("Header iniciando na linha: " + row);
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endHeader() {
    out.println("fim do header");
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult beginEmpresa(long row) {
    out.println("Empresa iniciando em: " + row);
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endEmpresa() {
    out.println("fim da empresa");
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult beginSocio(long row) {
    out.println("Socio iniciando em: " + row);
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endSocio() {
    out.println("fim do s�cio");
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult beginCnae(long row) {
    out.println("Cnae iniciando em: " + row);
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endCnae() {
    out.println("fim cnae");
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult beginTrailler(long row) {
    out.println("Trailler iniciando em: " + row);
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endTrailler() {
    out.println("fim do trailler");
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult fieldHeader(long row, IField field) {
    return field(row, field);
  }

  @Override
  public VisitResult fieldEmpresa(long row, IField field) {
    return field(row, field);
  }

  @Override
  public VisitResult fieldSocio(long row, IField field) {
    return field(row, field);
  }

  @Override
  public VisitResult fieldCnae(long row, IField field) {
    return field(row, field);
  }

  @Override
  public VisitResult fieldTrailler(long row, IField field) {
    return field(row, field);
  }
  
  private VisitResult field(long row, IField field) {
    out.println("arquivo["+ row + "," + field.getStart() + "]->" + field.toString());
    return VisitResult.CONTINUE;
  }
}
