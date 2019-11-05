package br.gov.economia.receita.imp.adapter;

public class MaxRegisterFoundException extends RuntimeException {
  private static final long serialVersionUID = -409595744617943615L;

  public MaxRegisterFoundException(long max) {
    super("Valor m�ximo de registros alcan�ado (" + max + ")");
  }
}
