package br.gov.economia.receita.imp;

import java.io.File;
import java.io.IOException;

import br.gov.economia.receita.IField;
import br.gov.economia.receita.imp.adapter.JsonVisitorAdapter;

public class EmpresaJsonExporter extends EmpresaVisitor {

  private JsonVisitorAdapter adapter;
  
  public EmpresaJsonExporter(File output) throws IOException {
    this.adapter = new JsonVisitorAdapter(output);
  }
  
  @Override
  public void start() {
    adapter.start();
  }
  
  @Override
  public void end() {
    adapter.end();
  }

  @Override
  public VisitResult beginEmpresa(long row) {
    adapter.beginData(row);
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult endEmpresa() {
    adapter.endData();
    return VisitResult.CONTINUE;
  }

  @Override
  public VisitResult fieldEmpresa(long row, IField field) {
    adapter.data(row, field);
    return VisitResult.CONTINUE;
  }
  
  public static void main(String[] args) throws IOException {
    File input  = new File("./input/K3241.K03200DV.D90805.L00002");
    File output = new File("./output/empresa.json");
    FileLayout layout = LayoutProvider
        .empresaLayout()
        .build(input, new EmpresaJsonExporter(output));
    layout.run();
    
    System.out.println("Use o comando: [Get-Content .\\empresa.json -Head 100] para ver as 100 primeiras linhas do arquivo");
  }
}
