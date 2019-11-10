package br.gov.economia.receita.imp;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

import br.gov.economia.receita.IBuilderProvider;
import br.gov.economia.receita.IField;
import br.gov.economia.receita.IRegisterVisitor;

abstract class Layout implements IBuilderProvider, ILayout{

  private final FileLayout.Builder builder;
  
  /** LinkedHashMap is better than HashMap when we need to iterate over values */
  private final Map<String, Field> fields = new LinkedHashMap<>();
  
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
  
  @Override
  public final void forEachField(Consumer<IField> consumer) {
    fields.values().forEach(consumer);
  }

  protected abstract VisitResult visitField(IRegisterVisitor visitor, Field field, long row);
  
}
