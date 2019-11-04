package br.gov.economia.receita.imp;

import static br.gov.economia.receita.imp.LineReader.CNAE_SECUNDARIA;

import br.gov.economia.receita.ICnaeLayout;
import br.gov.economia.receita.IFieldSetup;
import br.gov.economia.receita.IRegisterVisitor;
import br.gov.economia.receita.imp.FileLayout.Builder;

class CnaeLayout extends Layout implements ICnaeLayout {

  public CnaeLayout(Builder builder) {
    super(builder);
  }

  @Override
  public IFieldSetup<ICnaeLayout> tipo_do_registro() {
    return new FieldSetup<ICnaeLayout>(add("tipo_do_registro", 0, 1), this);
  }

  @Override
  public IFieldSetup<ICnaeLayout> indicador_full_diario() {
    return new FieldSetup<ICnaeLayout>(add("indicador_full_diario", 1, 1), this);
  }

  @Override
  public IFieldSetup<ICnaeLayout> tipo_de_atualizacao() {
    return new FieldSetup<ICnaeLayout>(add("tipo_de_atualizacao", 2, 1), this);
  }

  @Override
  public IFieldSetup<ICnaeLayout> cnpj() {
    return new FieldSetup<ICnaeLayout>(add("cnpj", 3, 14), this);
  }

  @Override
  public IFieldSetup<ICnaeLayout> cnae_secundaria() {
    return new FieldSetup<ICnaeLayout>(add("cnae_secundaria", 17, 693, CNAE_SECUNDARIA), this);
  }

  @Override
  public IFieldSetup<ICnaeLayout> filler() {
    return new FieldSetup<ICnaeLayout>(add("filler", 710, 489), this);
  }

  @Override
  public IFieldSetup<ICnaeLayout> fim_registro() {
    return new FieldSetup<ICnaeLayout>(add("fim_registro", 1199, 1), this);
  }

  @Override
  public VisitResult begin(IRegisterVisitor visitor, long row) {
    return visitor.beginCnae(row);
  }

  @Override
  public VisitResult end(IRegisterVisitor visitor) {
    return visitor.endCnae();
  }

  @Override
  protected VisitResult visitField(IRegisterVisitor visitor, Field field, long row) {
    return visitor.fieldCnae(row, field);
  }
}
