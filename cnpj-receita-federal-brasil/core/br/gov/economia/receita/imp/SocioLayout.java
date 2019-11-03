package br.gov.economia.receita.imp;

import br.gov.economia.receita.IFieldSetup;
import br.gov.economia.receita.IRegisterVisitor;
import br.gov.economia.receita.ISocioLayout;
import br.gov.economia.receita.imp.FileLayout.Builder;

class SocioLayout extends Layout implements ISocioLayout{

  private final Field[] socios = new Field[] {
    new Field(1,"tipo_de_registro"),
    new Field(1,"indicador_full_diario"),
    new Field(1,"tipo_de_atualizacao"),
    new Field(14,"cnpj"),
    new Field(1,"identificador_de_socio"),
    new Field(150,"nome_socio_pf_ou_razao_social_pj"),
    new Field(14,"cnpj_ou_cpf_do_sócio"),
    new Field(2,"codigo_qualificacao_socio"),
    new Field(5,"percentual_capital_social"),
    new Field(8,"data_entrada_sociedade"),
    new Field(3,"codigo_pais"),
    new Field(70,"nome_pais_socio"),
    new Field(11,"cpf_representante_legal"),
    new Field(60,"nome_representante"),
    new Field(2,"codigo_qualificacao_representante_legal"),
    new Field(856,"filler"),
    new Field(1,"fim_registro")
  };
  
  public SocioLayout(Builder builder) {
    super(builder);
  }

  @Override
  public IFieldSetup<ISocioLayout> tipo_de_registro() {
    return new FieldSetup<ISocioLayout>(socios[0], this);
  }

  @Override
  public IFieldSetup<ISocioLayout> indicador_full_diario() {
    return new FieldSetup<ISocioLayout>(socios[1], this);
  }

  @Override
  public IFieldSetup<ISocioLayout> tipo_de_atualizacao() {
    return new FieldSetup<ISocioLayout>(socios[2], this);
  }

  @Override
  public IFieldSetup<ISocioLayout> cnpj() {
    return new FieldSetup<ISocioLayout>(socios[3], this);
  }

  @Override
  public IFieldSetup<ISocioLayout> identificador_de_socio() {
    return new FieldSetup<ISocioLayout>(socios[4], this);
  }

  @Override
  public IFieldSetup<ISocioLayout> nome_socio_pf_ou_razao_social_pj() {
    return new FieldSetup<ISocioLayout>(socios[5], this);
  }

  @Override
  public IFieldSetup<ISocioLayout> cnpj_ou_cpf_do_sócio() {
    return new FieldSetup<ISocioLayout>(socios[6], this);
  }

  @Override
  public IFieldSetup<ISocioLayout> codigo_qualificacao_socio() {
    return new FieldSetup<ISocioLayout>(socios[7], this);
  }

  @Override
  public IFieldSetup<ISocioLayout> percentual_capital_social() {
    return new FieldSetup<ISocioLayout>(socios[8], this);
  }

  @Override
  public IFieldSetup<ISocioLayout> data_entrada_sociedade() {
    return new FieldSetup<ISocioLayout>(socios[9], this);
  }

  @Override
  public IFieldSetup<ISocioLayout> codigo_pais() {
    return new FieldSetup<ISocioLayout>(socios[10], this);
  }

  @Override
  public IFieldSetup<ISocioLayout> nome_pais_socio() {
    return new FieldSetup<ISocioLayout>(socios[11], this);
  }

  @Override
  public IFieldSetup<ISocioLayout> cpf_representante_legal() {
    return new FieldSetup<ISocioLayout>(socios[12], this);
  }

  @Override
  public IFieldSetup<ISocioLayout> nome_representante() {
    return new FieldSetup<ISocioLayout>(socios[13], this);
  }

  @Override
  public IFieldSetup<ISocioLayout> codigo_qualificacao_representante_legal() {
    return new FieldSetup<ISocioLayout>(socios[14], this);
  }

  @Override
  public IFieldSetup<ISocioLayout> filler() {
    return new FieldSetup<ISocioLayout>(socios[15], this);
  }

  @Override
  public IFieldSetup<ISocioLayout> fim_registro() {
    return new FieldSetup<ISocioLayout>(socios[16], this);
  }

  @Override
  public VisitResult begin(IRegisterVisitor visitor, int row) {
    return visitor.beginSocio(row);
  }

  @Override
  public VisitResult end(IRegisterVisitor visitor) {
    return visitor.endSocio();
  }

  @Override
  protected final Field[] getFields() {
    return this.socios;
  }

  @Override
  protected VisitResult invokeField(IRegisterVisitor visitor, int row, int col, Field field) {
    return visitor.fieldSocio(row, col, field);
  }
}
