package br.gov.economia.receita.imp;

import br.gov.economia.receita.IEmpresaLayout;
import br.gov.economia.receita.IFieldSetup;
import br.gov.economia.receita.IRegisterVisitor;

class EmpresaLayout extends Layout implements IEmpresaLayout {

  private final Field[] empresas = new Field[] {
    new Field(1,"tipo_de_registro"),
    new Field(1,"indicador_full_diario"),
    new Field(1,"tipo_atualizacao"),
    new Field(14,"cnpj"),
    new Field(1,"identificador_matriz_ou_filial"),
    new Field(150,"razao_social_ou_nome_empresarial"),
    new Field(55,"nome_fantasia"),
    new Field(2,"situacao_cadastral"),
    new Field(8,"data_situacao_cadastral"),
    new Field(2,"motivo_situacao_cadastral"),
    new Field(55,"nm_cidade_exterior"),
    new Field(3,"co_pais"),
    new Field(70,"nm_pais"),
    new Field(4,"codigo_natureza_juridica"),
    new Field(8,"data_inicio_atividade"),
    new Field(7,"cnae_fiscal"),
    new Field(20,"descricao_tipo_logradouro"),
    new Field(60,"logradouro"),
    new Field(6,"numero"),
    new Field(156,"complemento"),
    new Field(50,"bairro"),
    new Field(8,"cep"),
    new Field(2,"uf"),
    new Field(4,"codigo_municipio"),
    new Field(50,"municipio"),
    new Field(12,"ddd_telefone_1"),
    new Field(12,"ddd_telefone_2"),
    new Field(12,"ddd_fax"),
    new Field(115,"correio_eletronico"),
    new Field(2,"qualificacao_do_responsavel"),
    new Field(14,"capital_social_da_empresa"),
    new Field(2,"porte_empresa"),
    new Field(1,"opcao_pelo_simples"),
    new Field(8,"data_opcao_pelo_simples"),
    new Field(8,"data_exclusao_do_simples"),
    new Field(1,"opcao_pelo_mei"),
    new Field(23,"situacao_especial"),
    new Field(8,"data_situacao_especial"),
    new Field(243,"filler"),
    new Field(1,"fim_de_registro")
  };

  public EmpresaLayout(FileLayout.Builder builder) {
    super(builder);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> tipo_de_registro() {
    return new FieldSetup<IEmpresaLayout>(empresas[0], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> indicador_full_diario() {
    return new FieldSetup<IEmpresaLayout>(empresas[1], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> tipo_atualizacao() {
    return new FieldSetup<IEmpresaLayout>(empresas[2], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> cnpj() {
    return new FieldSetup<IEmpresaLayout>(empresas[3], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> identificador_matriz_ou_filial() {
    return new FieldSetup<IEmpresaLayout>(empresas[4], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> razao_social_ou_nome_empresarial() {
    return new FieldSetup<IEmpresaLayout>(empresas[5], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> nome_fantasia() {
    return new FieldSetup<IEmpresaLayout>(empresas[6], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> situacao_cadastral() {
    return new FieldSetup<IEmpresaLayout>(empresas[7], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> data_situacao_cadastral() {
    return new FieldSetup<IEmpresaLayout>(empresas[8], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> motivo_situacao_cadastral() {
    return new FieldSetup<IEmpresaLayout>(empresas[9], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> nm_cidade_exterior() {
    return new FieldSetup<IEmpresaLayout>(empresas[10], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> co_pais() {
    return new FieldSetup<IEmpresaLayout>(empresas[11], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> nm_pais() {
    return new FieldSetup<IEmpresaLayout>(empresas[12], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> codigo_natureza_juridica() {
    return new FieldSetup<IEmpresaLayout>(empresas[13], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> data_inicio_atividade() {
    return new FieldSetup<IEmpresaLayout>(empresas[14], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> cnae_fiscal() {
    return new FieldSetup<IEmpresaLayout>(empresas[15], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> descricao_tipo_logradouro() {
    return new FieldSetup<IEmpresaLayout>(empresas[16], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> logradouro() {
    return new FieldSetup<IEmpresaLayout>(empresas[17], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> numero() {
    return new FieldSetup<IEmpresaLayout>(empresas[18], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> complemento() {
    return new FieldSetup<IEmpresaLayout>(empresas[19], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> bairro() {
    return new FieldSetup<IEmpresaLayout>(empresas[20], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> cep() {
    return new FieldSetup<IEmpresaLayout>(empresas[21], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> uf() {
    return new FieldSetup<IEmpresaLayout>(empresas[22], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> codigo_municipio() {
    return new FieldSetup<IEmpresaLayout>(empresas[23], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> municipio() {
    return new FieldSetup<IEmpresaLayout>(empresas[24], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> ddd_telefone_1() {
    return new FieldSetup<IEmpresaLayout>(empresas[25], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> ddd_telefone_2() {
    return new FieldSetup<IEmpresaLayout>(empresas[26], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> ddd_fax() {
    
    return new FieldSetup<IEmpresaLayout>(empresas[27], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> correio_eletronico() {
    
    return new FieldSetup<IEmpresaLayout>(empresas[28], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> qualificacao_do_responsavel() {
    return new FieldSetup<IEmpresaLayout>(empresas[29], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> capital_social_da_empresa() {
    return new FieldSetup<IEmpresaLayout>(empresas[30], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> porte_empresa() {
    return new FieldSetup<IEmpresaLayout>(empresas[31], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> opcao_pelo_simples() {
    return new FieldSetup<IEmpresaLayout>(empresas[32], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> data_opcao_pelo_simples() {
    return new FieldSetup<IEmpresaLayout>(empresas[33], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> data_exclusao_do_simples() {
    return new FieldSetup<IEmpresaLayout>(empresas[34], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> opcao_pelo_mei() {
    return new FieldSetup<IEmpresaLayout>(empresas[35], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> situacao_especial() {
    return new FieldSetup<IEmpresaLayout>(empresas[36], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> data_situacao_especial() {
    return new FieldSetup<IEmpresaLayout>(empresas[37], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> filler() {
    return new FieldSetup<IEmpresaLayout>(empresas[38], this);
  }

  @Override
  public IFieldSetup<IEmpresaLayout> fim_de_registro() {
    return new FieldSetup<IEmpresaLayout>(empresas[39], this);
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
  protected final Field[] getFields() {
    return this.empresas;
  }

  @Override
  protected VisitResult invokeField(IRegisterVisitor visitor, int row, int col, Field field) {
    return visitor.fieldEmpresa(row, col, field);
  }
}
