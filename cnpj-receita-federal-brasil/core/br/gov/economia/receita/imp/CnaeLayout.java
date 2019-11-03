package br.gov.economia.receita.imp;

import br.gov.economia.receita.ICnaeLayout;
import br.gov.economia.receita.IFieldSetup;
import br.gov.economia.receita.IRegisterVisitor;
import br.gov.economia.receita.imp.FileLayout.Builder;

class CnaeLayout extends Layout implements ICnaeLayout {

  private final Field[] cnaes = new Field[] {
    new Field(1, "tipo_do_registro"),
    new Field(1, "indicador_full_diario"),
    new Field(1, "tipo_de_atualizacao"),
    new Field(14, "cnpj"),
    new Field(693, "cnae_secundaria", LineReader.CNAE_SECUNDARIA),
    new Field(489, "filler"),
    new Field(1, "fim_registro")
  };
  
  public CnaeLayout(Builder builder) {
    super(builder);
  }

  @Override
  public IFieldSetup<ICnaeLayout> tipo_do_registro() {
    return new FieldSetup<ICnaeLayout>(cnaes[0], this);
  }

  @Override
  public IFieldSetup<ICnaeLayout> indicador_full_diario() {
    return new FieldSetup<ICnaeLayout>(cnaes[1], this);
  }

  @Override
  public IFieldSetup<ICnaeLayout> tipo_de_atualizacao() {
    return new FieldSetup<ICnaeLayout>(cnaes[2], this);
  }

  @Override
  public IFieldSetup<ICnaeLayout> cnpj() {
    return new FieldSetup<ICnaeLayout>(cnaes[3], this);
  }

  @Override
  public IFieldSetup<ICnaeLayout> cnae_secundaria() {
    return new FieldSetup<ICnaeLayout>(cnaes[4], this);
  }

  @Override
  public IFieldSetup<ICnaeLayout> filler() {
    return new FieldSetup<ICnaeLayout>(cnaes[5], this);
  }

  @Override
  public IFieldSetup<ICnaeLayout> fim_registro() {
    return new FieldSetup<ICnaeLayout>(cnaes[6], this);
  }

  @Override
  public VisitResult begin(IRegisterVisitor visitor, int row) {
    return visitor.beginCnae(row);
  }

  @Override
  public VisitResult end(IRegisterVisitor visitor) {
    return visitor.endCnae();
  }

  @Override
  protected final Field[] getFields() {
    return this.cnaes;
  }

  @Override
  protected VisitResult invokeField(IRegisterVisitor visitor, int row, int col, Field field) {
    return visitor.fieldCnae(row, col, field);
  }
}
