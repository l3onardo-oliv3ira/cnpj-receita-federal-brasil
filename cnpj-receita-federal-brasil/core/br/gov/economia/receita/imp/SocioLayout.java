package br.gov.economia.receita.imp;

import br.gov.economia.receita.IFieldSetup;
import br.gov.economia.receita.IRegisterVisitor;
import br.gov.economia.receita.ISocioLayout;
import br.gov.economia.receita.imp.FileLayout.Builder;

class SocioLayout extends Layout implements ISocioLayout{

  public SocioLayout(Builder builder) {
    super(builder);
  }

  @Override
  public IFieldSetup<ISocioLayout> tipo_de_registro() {
    return new FieldSetup<ISocioLayout>(add("tipo_de_registro", 0, 1), this);
  }

  @Override
  public IFieldSetup<ISocioLayout> indicador_full_diario() {
    return new FieldSetup<ISocioLayout>(add("indicador_full_diario", 1, 1), this);
  }

  @Override
  public IFieldSetup<ISocioLayout> tipo_de_atualizacao() {
    return new FieldSetup<ISocioLayout>(add("tipo_de_atualizacao", 2, 1), this);
  }

  @Override
  public IFieldSetup<ISocioLayout> cnpj() {
    return new FieldSetup<ISocioLayout>(add("cnpj", 3, 14), this);
  }

  @Override
  public IFieldSetup<ISocioLayout> identificador_de_socio() {
    return new FieldSetup<ISocioLayout>(add("identificador_de_socio", 17, 1), this);
  }

  @Override
  public IFieldSetup<ISocioLayout> nome_socio_pf_ou_razao_social_pj() {
    return new FieldSetup<ISocioLayout>(add("nome_socio_pf_ou_razao_social_pj", 18, 150), this);
  }

  @Override
  public IFieldSetup<ISocioLayout> cnpj_ou_cpf_do_sócio() {
    return new FieldSetup<ISocioLayout>(add("cnpj_ou_cpf_do_sócio", 168, 14), this);
  }

  @Override
  public IFieldSetup<ISocioLayout> codigo_qualificacao_socio() {
    return new FieldSetup<ISocioLayout>(add("codigo_qualificacao_socio", 182, 2), this);
  }

  @Override
  public IFieldSetup<ISocioLayout> percentual_capital_social() {
    return new FieldSetup<ISocioLayout>(add("percentual_capital_social", 184, 5), this);
  }

  @Override
  public IFieldSetup<ISocioLayout> data_entrada_sociedade() {
    return new FieldSetup<ISocioLayout>(add("data_entrada_sociedade", 189, 8), this);
  }

  @Override
  public IFieldSetup<ISocioLayout> codigo_pais() {
    return new FieldSetup<ISocioLayout>(add("codigo_pais", 197, 3), this);
  }

  @Override
  public IFieldSetup<ISocioLayout> nome_pais_socio() {
    return new FieldSetup<ISocioLayout>(add("nome_pais_socio", 200, 70), this);
  }

  @Override
  public IFieldSetup<ISocioLayout> cpf_representante_legal() {
    return new FieldSetup<ISocioLayout>(add("cpf_representante_legal", 270, 11), this);
  }

  @Override
  public IFieldSetup<ISocioLayout> nome_representante() {
    return new FieldSetup<ISocioLayout>(add("nome_representante", 281, 60), this);
  }

  @Override
  public IFieldSetup<ISocioLayout> codigo_qualificacao_representante_legal() {
    return new FieldSetup<ISocioLayout>(add("codigo_qualificacao_representante_legal", 341, 2), this);
  }

  @Override
  public IFieldSetup<ISocioLayout> filler() {
    return new FieldSetup<ISocioLayout>(add("filler", 343, 885), this);
  }

  @Override
  public IFieldSetup<ISocioLayout> fim_registro() {
    return new FieldSetup<ISocioLayout>(add("fim_registro", 1199, 1), this);
  }

  @Override
  public VisitResult begin(IRegisterVisitor visitor, long row) {
    return visitor.beginSocio(row);
  }

  @Override
  public VisitResult end(IRegisterVisitor visitor) {
    return visitor.endSocio();
  }

  @Override
  protected VisitResult visitField(IRegisterVisitor visitor, Field field, long row) {
    return visitor.fieldSocio(row, field);
  }
}
