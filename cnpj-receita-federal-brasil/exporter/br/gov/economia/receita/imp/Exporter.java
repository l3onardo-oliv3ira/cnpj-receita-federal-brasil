package br.gov.economia.receita.imp;

import java.io.File;
import java.io.IOException;

public enum Exporter {
  CNAE {
    @Override
    public FileLayout doExport(File input, File output, OutputFormat format, long max) throws IOException {
      FileLayout.Builder b = format.cnaeLayout();
      return b.build(input, new CnaeExporter(format.getAdapter(output, max, new CnaeDdlOutput(b.cnae()))));
    }
  },
  EMPRESA {
    @Override
    public FileLayout doExport(File input, File output, OutputFormat format, long max) throws IOException {
      FileLayout.Builder b = format.empresaLayout();
      return b.build(input, new EmpresaExporter(format.getAdapter(output, max, new EmpresaDdlOuptut(b.empresa()))));
    }
  },
  SOCIO {
    @Override
    public FileLayout doExport(File input, File output, OutputFormat format, long max) throws IOException {
      FileLayout.Builder b = format.socioLayout();
      return b.build(input, new SocioExporter(format.getAdapter(output, max, new SocioDdlOuptut(b.socio()))));
    }
  };
  
  public void export(File input, File output, OutputFormat outputFormat) throws IOException{
    export(input, output, outputFormat, Long.MAX_VALUE);  
  }

  public void export(File input, File output, OutputFormat outputFormat, long max) throws IOException{
    doExport(input, output, outputFormat, max).run();
  }

  abstract FileLayout doExport(File input, File output, OutputFormat outputFormat, long max) throws IOException;

  public static void main(String[] args) throws IOException {
    long total = 1000;
    File input  = new File("./input");
    
    File output = new File("D:/output/empresa.json");
    Exporter.EMPRESA.export(input, output, OutputFormat.JSON, total);

    output = new File("D:/output/empresa.csv");
    Exporter.EMPRESA.export(input, output, OutputFormat.CSV, total);

    output = new File("D:/output/empresa.sql");
    Exporter.EMPRESA.export(input, output, OutputFormat.SQL, total);
  
    output = new File("D:/output/socio.json");
    Exporter.SOCIO.export(input, output, OutputFormat.JSON, total);

    output = new File("D:/output/socio.csv");
    Exporter.SOCIO.export(input, output, OutputFormat.CSV, total);

    output = new File("D:/output/socio.sql");
    Exporter.SOCIO.export(input, output, OutputFormat.SQL, total);

    output = new File("D:/output/cnae.json");
    Exporter.CNAE.export(input, output, OutputFormat.JSON, total);

    output = new File("D:/output/cnae.csv");
    Exporter.CNAE.export(input, output, OutputFormat.CSV, total);

    output = new File("D:/output/cnae.sql");
    Exporter.CNAE.export(input, output, OutputFormat.SQL, total);
  }
}
