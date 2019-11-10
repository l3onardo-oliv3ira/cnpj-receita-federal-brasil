package br.gov.economia.receita.imp;

import br.gov.economia.receita.ISocioLayout;

public class SocioDdlOuptut extends DefaultDdlOutput{
  
  public SocioDdlOuptut(ISocioLayout layout) {
    super("socio", layout);
  }
  
  public static void main(String[] args) {
    ISocioLayout layout = LayoutProvider.socioLayout4SQL().socio();
    IDdlOutput output = new SocioDdlOuptut(layout);
    System.out.println(output.create());
  }
}
