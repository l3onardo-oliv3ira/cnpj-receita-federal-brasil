package br.gov.economia.receita.imp;

import br.gov.economia.receita.IEmpresaLayout;
import br.gov.economia.receita.IFieldSetup;
import br.gov.economia.receita.IRegisterVisitor;

class EmpresaLayout extends Layout implements IEmpresaLayout {

  public EmpresaLayout(FileLayout.Builder builder) {
    super(builder);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> tipo_de_registro() {
    return new FieldSetup<IEmpresaLayout>(add("tipo_de_registro", 0, 1), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> indicador_full_diario() {
    return new FieldSetup<IEmpresaLayout>(add("indicador_full_diario", 1, 1), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> tipo_atualizacao() {
    return new FieldSetup<IEmpresaLayout>(add("tipo_atualizacao", 2, 1), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> cnpj() {
    return new FieldSetup<IEmpresaLayout>(add("cnpj", 3, 14), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> identificador_matriz_ou_filial() {
    return new FieldSetup<IEmpresaLayout>(add("identificador_matriz_ou_filial", 17, 1), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> razao_social_ou_nome_empresarial() {
    return new FieldSetup<IEmpresaLayout>(add("razao_social_ou_nome_empresarial", 18, 150), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> nome_fantasia() {
    return new FieldSetup<IEmpresaLayout>(add("nome_fantasia", 168, 55), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> situacao_cadastral() {
    return new FieldSetup<IEmpresaLayout>(add("situacao_cadastral", 223, 2), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> data_situacao_cadastral() {
    return new FieldSetup<IEmpresaLayout>(add("data_situacao_cadastral", 225, 8), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> motivo_situacao_cadastral() {
    return new FieldSetup<IEmpresaLayout>(add("motivo_situacao_cadastral", 223, 2), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> nm_cidade_exterior() {
    return new FieldSetup<IEmpresaLayout>(add("nm_cidade_exterior", 235, 55), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> co_pais() {
    return new FieldSetup<IEmpresaLayout>(add("co_pais", 290, 3), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> nm_pais() {
    return new FieldSetup<IEmpresaLayout>(add("nm_pais", 293, 70), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> codigo_natureza_juridica() {
    return new FieldSetup<IEmpresaLayout>(add("codigo_natureza_juridica", 363, 4), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> data_inicio_atividade() {
    return new FieldSetup<IEmpresaLayout>(add("data_inicio_atividade", 367, 8), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> cnae_fiscal() {
    return new FieldSetup<IEmpresaLayout>(add("cnae_fiscal", 375, 7), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> descricao_tipo_logradouro() {
    return new FieldSetup<IEmpresaLayout>(add("descricao_tipo_logradouro", 382, 20), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> logradouro() {
    return new FieldSetup<IEmpresaLayout>(add("logradouro", 402, 60), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> numero() {
    return new FieldSetup<IEmpresaLayout>(add("numero", 462, 6), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> complemento() {
    return new FieldSetup<IEmpresaLayout>(add("complemento", 468, 156), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> bairro() {
    return new FieldSetup<IEmpresaLayout>(add("bairro", 624, 50), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> cep() {
    return new FieldSetup<IEmpresaLayout>(add("cep", 674, 8), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> uf() {
    return new FieldSetup<IEmpresaLayout>(add("uf", 682, 2), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> codigo_municipio() {
    return new FieldSetup<IEmpresaLayout>(add("codigo_municipio", 684, 4), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> municipio() {
    return new FieldSetup<IEmpresaLayout>(add("municipio", 688, 50), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> ddd_telefone_1() {
    return new FieldSetup<IEmpresaLayout>(add("ddd_telefone_1", 738, 12), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> ddd_telefone_2() {
    return new FieldSetup<IEmpresaLayout>(add("ddd_telefone_2", 750, 12), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> ddd_fax() {
    return new FieldSetup<IEmpresaLayout>(add("ddd_fax", 762, 12), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> correio_eletronico() {
    return new FieldSetup<IEmpresaLayout>(add("correio_eletronico", 774, 115), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> qualificacao_do_responsavel() {
    return new FieldSetup<IEmpresaLayout>(add("qualificacao_do_responsavel", 889, 2), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> capital_social_da_empresa() {
    return new FieldSetup<IEmpresaLayout>(add("capital_social_da_empresa", 891, 14), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> porte_empresa() {
    return new FieldSetup<IEmpresaLayout>(add("porte_empresa", 905, 2), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> opcao_pelo_simples() {
    return new FieldSetup<IEmpresaLayout>(add("opcao_pelo_simples", 907, 2), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> data_opcao_pelo_simples() {
    return new FieldSetup<IEmpresaLayout>(add("data_opcao_pelo_simples", 908, 8), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> data_exclusao_do_simples() {
    return new FieldSetup<IEmpresaLayout>(add("data_exclusao_do_simples", 916, 8), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> opcao_pelo_mei() {
    return new FieldSetup<IEmpresaLayout>(add("opcao_pelo_mei", 924, 1), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> situacao_especial() {
    return new FieldSetup<IEmpresaLayout>(add("situacao_especial", 925, 23), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> data_situacao_especial() {
    return new FieldSetup<IEmpresaLayout>(add("data_situacao_especial", 948, 8), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> filler() {
    return new FieldSetup<IEmpresaLayout>(add("filler", 956, 243), this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> fim_de_registro() {
    return new FieldSetup<IEmpresaLayout>(add("fim_de_registro", 1199, 1), this);
  }

  @Override
  public VisitResult begin(IRegisterVisitor visitor, int row) {
    return visitor.beginEmpresa(row);
  }

  @Override
  public VisitResult end(IRegisterVisitor visitor) {
    return visitor.endEmpresa();
  }

  @Override
  protected VisitResult visitField(IRegisterVisitor visitor, Field field, int row) {
    return visitor.fieldEmpresa(row, field);
  }
}
