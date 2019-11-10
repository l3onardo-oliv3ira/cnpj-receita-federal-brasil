package br.gov.economia.receita.imp;

import br.gov.economia.receita.ICnaeLayout;

public class CnaeDdlOutput extends DefaultDdlOutput{
  
  public CnaeDdlOutput(ICnaeLayout layout) {
    super("cnae", layout);
  }
  
  public static void main(String[] args) {
    ICnaeLayout layout = LayoutProvider.cnaeLayout4SQL().cnae();
    IDdlOutput output = new CnaeDdlOutput(layout);
    System.out.println(output.create());
  }
}
