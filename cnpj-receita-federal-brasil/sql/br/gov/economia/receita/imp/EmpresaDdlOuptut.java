package br.gov.economia.receita.imp;

import br.gov.economia.receita.IEmpresaLayout;

public class EmpresaDdlOuptut extends DefaultDdlOutput{
  
  public EmpresaDdlOuptut(IEmpresaLayout layout) {
    super("empresa", layout);
  }
  
  public static void main(String[] args) {
    IEmpresaLayout layout = LayoutProvider.empresaLayout4SQL().empresa();
    IDdlOutput output = new EmpresaDdlOuptut(layout);
    System.out.println(output.create());
  }
}
