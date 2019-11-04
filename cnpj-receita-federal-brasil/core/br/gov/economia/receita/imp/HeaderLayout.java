package br.gov.economia.receita.imp;

import br.gov.economia.receita.IFieldSetup;
import br.gov.economia.receita.IHeaderLayout;
import br.gov.economia.receita.IRegisterVisitor;

class HeaderLayout extends Layout implements IHeaderLayout {

  public HeaderLayout(FileLayout.Builder builder) {
    super(builder);
  }
  
  @Override
  public IFieldSetup<IHeaderLayout> tipo_do_registro() {
    return new FieldSetup<IHeaderLayout>(add("tipo_do_registro", 0, 1), this);
  }

  @Override
  public IFieldSetup<IHeaderLayout> filler() {
    return new FieldSetup<IHeaderLayout>(add("filler", 1, 16), this);
  }

  @Override
  public IFieldSetup<IHeaderLayout> nome_do_arquivo() {
    return new FieldSetup<IHeaderLayout>(add("nome_do_arquivo", 17, 11), this);
  }

  @Override
  public IFieldSetup<IHeaderLayout> data_de_gravacao() {
    return new FieldSetup<IHeaderLayout>(add("data_de_gravacao", 28, 8), this);
  }

  @Override
  public IFieldSetup<IHeaderLayout> numero_da_remessa() {
    return new FieldSetup<IHeaderLayout>(add("numero_da_remessa", 36, 8), this);
  }

  @Override
  public IFieldSetup<IHeaderLayout> next_filler() {
    return new FieldSetup<IHeaderLayout>(add("next_filler", 44, 1155), this);
  }

  @Override
  public IFieldSetup<IHeaderLayout> fim_de_registro() {
    return new FieldSetup<IHeaderLayout>(add("fim_de_registro", 1199, 1), this);
  }
  
  @Override
  public VisitResult begin(IRegisterVisitor visitor, long row) {
    return visitor.beginHeader(row);
  }

  @Override
  public VisitResult end(IRegisterVisitor visitor) {
    return visitor.endHeader();
  }

  @Override
  protected VisitResult visitField(IRegisterVisitor visitor, Field field, long row) {
    return visitor.fieldHeader(row, field);
  }
}
