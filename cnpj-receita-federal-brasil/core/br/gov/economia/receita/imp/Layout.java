package br.gov.economia.receita.imp;

import java.util.LinkedHashMap;
import java.util.Map;

import br.gov.economia.receita.IBuilderProvider;
import br.gov.economia.receita.IRegisterVisitor;

abstract class Layout implements IBuilderProvider, ILayout{

  private FileLayout.Builder builder;
  
  private Map<String, Field> fields = new LinkedHashMap<>();
  
  private boolean enabled = false;

  public Layout(FileLayout.Builder builder) {
    this.builder = builder;
  }

  @Override
  public FileLayout.Builder builder() {
    return builder;
  }

  @SuppressWarnings("unchecked")
  public final <T extends ILayout> T enable() {
    enabled = true;
    return (T)this;
  }
  
  public final boolean isEnabled() {
    return enabled;
  }
  
  @Override
  public final VisitResult visit(IRegisterVisitor visitor, long row, String line) {
    for(Field f: fields.values()) {
      VisitResult vr;
      try {
        f.read(line);
      }catch(Exception e) {
        vr = visitor.handleError(row, e);
        if (VisitResult.CONTINUE != vr)
          return vr;
      }
      vr = visitField(visitor, f, row);
      if (VisitResult.CONTINUE != vr)
        return vr;
    }
    return VisitResult.CONTINUE;
  };
  
  protected final Field add(String name, int start, int size) {
    return add(name, start, size, LineReader.DEFAULT);
  }
  
  protected final Field add(String name, int start, int size, LineReader reader) {
    if (fields.containsKey(name))
      return fields.get(name);
    Field field = new Field(name, start, size, reader);
    fields.put(name, field);
    return field;
  }
  
  protected abstract VisitResult visitField(IRegisterVisitor visitor, Field field, long row);
}
