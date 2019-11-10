package br.gov.economia.receita;

import br.gov.economia.receita.imp.FileLayout;

public interface IBuilderProvider extends IFieldTraverser{
  public FileLayout.Builder builder();
}
