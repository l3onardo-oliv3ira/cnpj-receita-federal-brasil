package br.gov.economia.receita.imp;

import br.gov.economia.receita.IField;
import br.gov.economia.receita.IFieldTraverser;

public class DefaultDdlOutput implements IDdlOutput{
  
  protected final String tableName;
  protected final IFieldTraverser traverser;

  public DefaultDdlOutput(String tableName, IFieldTraverser traverser) {
    this.tableName = tableName;
    this.traverser = traverser;
  }
  
  @Override
  public final String getName() {
    return tableName;
  }
  
  @Override
  public final String create() {
    StringBuilder fields = new StringBuilder();
    traverser.forEachField((IField f) -> {
      if (fields.length() > 0)
        fields.append(',').append('\n');
      fields.append(f.getName()).append(' ').append(f.getType());
    });
    return "CREATE TABLE " + tableName + "(\n" + fields.toString() + "\n);";
  }

  @Override
  public String drop() {
    return "DROP TABLE IF EXISTS '" + tableName + "';";
  };
}
