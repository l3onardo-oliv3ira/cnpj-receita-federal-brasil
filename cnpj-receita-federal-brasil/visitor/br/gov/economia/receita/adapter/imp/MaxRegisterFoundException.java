package br.gov.economia.receita.adapter.imp;

public class MaxRegisterFoundException extends RuntimeException {
  private static final long serialVersionUID = -409595744617943615L;

  public MaxRegisterFoundException(long max) {
    super("Valor máximo de registros alcançado (" + max + ")");
  }
}
