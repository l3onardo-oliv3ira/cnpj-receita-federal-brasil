package br.gov.economia.receita.imp;

public final class Strings {
  private Strings() {}
  
  public static int toInt(String number, int defaultIf) {
    try {
      return Integer.parseInt(number);
    }catch(NumberFormatException e) {
      return defaultIf;
    }
  }
}
