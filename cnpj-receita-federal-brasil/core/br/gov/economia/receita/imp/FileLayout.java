package br.gov.economia.receita.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import br.gov.economia.receita.ICnaeLayout;
import br.gov.economia.receita.IEmpresaLayout;
import br.gov.economia.receita.IHeaderLayout;
import br.gov.economia.receita.IRegisterVisitor;
import br.gov.economia.receita.ISocioLayout;
import br.gov.economia.receita.ITraillerLayout;

public final class FileLayout implements Runnable{
  
  private static final int DEFAULT_BUFFER =  8*1024*1024;
  
  public static class Builder {

    private HeaderLayout   headerLayout    = new HeaderLayout(this);
    private EmpresaLayout  empresaLayout   = new EmpresaLayout(this);
    private SocioLayout    socioLayout     = new SocioLayout(this);
    private CnaeLayout     cnaeLayout      = new CnaeLayout(this);
    private TraillerLayout traillerLayout  = new TraillerLayout(this);
    
    private IRegisterVisitor visitor      = new SysoutVisitor();
    
    public IHeaderLayout header() {
      return headerLayout.enable();
    }
    
    public IEmpresaLayout empresa() {
      return empresaLayout.enable();
    }
    
    public ISocioLayout socio() {
      return socioLayout.enable();
    }

    public ICnaeLayout cnae() {
      return cnaeLayout.enable();
    }
    
    public ITraillerLayout trailler() {
      return traillerLayout.enable();
    }
    
    public FileLayout build(File input) {
      return build(input, DEFAULT_BUFFER);
    }
    
    public FileLayout build(File input, int bufferSize) {
      return build(input, bufferSize, visitor);
    }
    
    public FileLayout build(File input, IRegisterVisitor visitor) {
      return build(input, DEFAULT_BUFFER, visitor);
    }
    
    public FileLayout build(File input, int bufferSize, IRegisterVisitor visitor) {
      return new FileLayout(input, bufferSize, visitor, this);
    }
  }
  
  private File input;
  private int bufferSize;
  private IRegisterVisitor visitor;
  private Builder builder;
  private AtomicLong row = new AtomicLong(0);
  
  private FileLayout(File input, int bufferSize, IRegisterVisitor visitor, Builder builder) {
    this.input = input;
    this.bufferSize = bufferSize;
    this.visitor = visitor;
    this.builder = builder;
  }

  public long getCurrentRow() {
    return this.row.get();
  }
  
  @Override
  public void run() {
    File[] files = input.isFile() ? new File[] {input} : input.listFiles((File dir, String name) -> !name.startsWith("."));
    try {
      Charset charset = Charset.forName("iso-8859-1");
      
      visitor.start();
      
      for(int i = 0; i < files.length; i++) {
        try(var buffer = new BufferedReader(new FileReader(files[i], charset), bufferSize)){
          if (iterate(buffer) == VisitResult.TERMINATE)
            break;
        } catch (IOException e) {
          e.printStackTrace();
          continue;
        }
      }
      
    }finally {
      visitor.end();
    }
  }
  
  private ILayout from(char number) {
    switch(number) {
      case '0': return builder.headerLayout.isEnabled()   ? builder.headerLayout  : SkipLayout.INSTANCE;
      case '1': return builder.empresaLayout.isEnabled()  ? builder.empresaLayout : SkipLayout.INSTANCE;
      case '2': return builder.socioLayout.isEnabled()    ? builder.socioLayout   : SkipLayout.INSTANCE;
      case '6': return builder.cnaeLayout.isEnabled()     ? builder.cnaeLayout    : SkipLayout.INSTANCE;
      case '9': return builder.traillerLayout.isEnabled() ? builder.traillerLayout: SkipLayout.INSTANCE;
      default: return SkipLayout.INSTANCE;
    }
  }

  private VisitResult iterate(BufferedReader buffer){
    String line = null;
   
    do {
      try {
        line = buffer.readLine();
      } catch (IOException e) {
        VisitResult vr = visitor.handleError(row.get(), e);
        if (VisitResult.TERMINATE == vr)
          return vr;
        continue;
      }
      
      if (line == null)
        return VisitResult.CONTINUE;
      
      row.incrementAndGet();

      if (line.length() == 0)
        continue;
      
      ILayout layout = from(line.charAt(0));
      
      VisitResult vr = layout.begin(visitor, row.get());
      if (VisitResult.SKIP == vr)
        continue;
      if (VisitResult.TERMINATE == vr)
        return vr;
      vr = layout.visit(visitor, row.get(), line);
      if (VisitResult.SKIP == vr)
        continue;
      if (VisitResult.TERMINATE == vr)
        return vr;
      vr = layout.end(visitor);
      if (VisitResult.TERMINATE == vr)
        return vr;
    }while(true);
  }
}
