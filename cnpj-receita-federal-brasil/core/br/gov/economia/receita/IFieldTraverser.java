package br.gov.economia.receita;

import java.util.function.Consumer;

public interface IFieldTraverser {
  public void forEachField(Consumer<IField> consumer);
}
