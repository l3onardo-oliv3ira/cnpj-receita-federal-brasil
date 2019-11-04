package br.gov.economia.receita.imp;

import br.gov.economia.receita.IFieldSetup;
import br.gov.economia.receita.IRegisterVisitor;
import br.gov.economia.receita.ITraillerLayout;
import br.gov.economia.receita.imp.FileLayout.Builder;

class TraillerLayout extends Layout implements ITraillerLayout{

  public TraillerLayout(Builder builder) {
    super(builder);
  }

  @Override
  public IFieldSetup<ITraillerLayout> tipo_do_registro() {
    return new FieldSetup<ITraillerLayout>(add("tipo_do_registro", 0, 1), this);
  }

  @Override
  public IFieldSetup<ITraillerLayout> filler() {
    return new FieldSetup<ITraillerLayout>(add("filler", 1, 16), this);
  }

  @Override
  public IFieldSetup<ITraillerLayout> total_de_registro_t1() {
    return new FieldSetup<ITraillerLayout>(add("total_de_registro_t1", 17, 9), this);
  }

  @Override
  public IFieldSetup<ITraillerLayout> total_de_registro_t2() {
    return new FieldSetup<ITraillerLayout>(add("total_de_registro_t2", 26, 9), this);
  }

  @Override
  public IFieldSetup<ITraillerLayout> total_de_registros_t3() {
    return new FieldSetup<ITraillerLayout>(add("total_de_registros_t3", 35, 9), this);
  }

  @Override
  public IFieldSetup<ITraillerLayout> total_de_registros() {
    return new FieldSetup<ITraillerLayout>(add("total_de_registros", 44, 11), this);
  }

  @Override
  public IFieldSetup<ITraillerLayout> next_filler() {
    return new FieldSetup<ITraillerLayout>(add("next_filler", 55, 1144), this);
  }

  @Override
  public IFieldSetup<ITraillerLayout> fim_de_registro() {
    return new FieldSetup<ITraillerLayout>(add("fim_de_registro", 1199, 1), this);
  }
  
  @Override
  public VisitResult begin(IRegisterVisitor visitor, long row) {
    return visitor.beginTrailler(row);
  }

  @Override
  public VisitResult end(IRegisterVisitor visitor) {
    return visitor.endTrailler();
  }

  @Override
  protected VisitResult visitField(IRegisterVisitor visitor, Field field, long row) {
    return visitor.fieldTrailler(row, field);
  }
}
