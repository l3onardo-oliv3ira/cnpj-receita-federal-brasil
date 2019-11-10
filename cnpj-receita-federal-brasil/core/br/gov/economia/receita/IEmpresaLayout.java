package br.gov.economia.receita;

import java.io.OutputStream;

public interface IEmpresaLayout extends IBuilderProvider{
  public IFieldSetup<IEmpresaLayout> tipo_de_registro();
  public IFieldSetup<IEmpresaLayout> indicador_full_diario();
  public IFieldSetup<IEmpresaLayout> tipo_atualizacao();
  public IFieldSetup<IEmpresaLayout> cnpj();
  public IFieldSetup<IEmpresaLayout> identificador_matriz_ou_filial();
  public IFieldSetup<IEmpresaLayout> razao_social_ou_nome_empresarial();
  public IFieldSetup<IEmpresaLayout> nome_fantasia();
  public IFieldSetup<IEmpresaLayout> situacao_cadastral();
  public IFieldSetup<IEmpresaLayout> data_situacao_cadastral();
  public IFieldSetup<IEmpresaLayout> motivo_situacao_cadastral();
  public IFieldSetup<IEmpresaLayout> nm_cidade_exterior();
  public IFieldSetup<IEmpresaLayout> co_pais();
  public IFieldSetup<IEmpresaLayout> nm_pais();
  public IFieldSetup<IEmpresaLayout> codigo_natureza_juridica();
  public IFieldSetup<IEmpresaLayout> data_inicio_atividade();
  public IFieldSetup<IEmpresaLayout> cnae_fiscal();
  public IFieldSetup<IEmpresaLayout> descricao_tipo_logradouro();
  public IFieldSetup<IEmpresaLayout> logradouro();
  public IFieldSetup<IEmpresaLayout> numero();
  public IFieldSetup<IEmpresaLayout> complemento();
  public IFieldSetup<IEmpresaLayout> bairro();
  public IFieldSetup<IEmpresaLayout> cep();
  public IFieldSetup<IEmpresaLayout> uf();
  public IFieldSetup<IEmpresaLayout> codigo_municipio();
  public IFieldSetup<IEmpresaLayout> municipio();
  public IFieldSetup<IEmpresaLayout> ddd_telefone_1();
  public IFieldSetup<IEmpresaLayout> ddd_telefone_2();
  public IFieldSetup<IEmpresaLayout> ddd_fax();
  public IFieldSetup<IEmpresaLayout> correio_eletronico();
  public IFieldSetup<IEmpresaLayout> qualificacao_do_responsavel();
  public IFieldSetup<IEmpresaLayout> capital_social_da_empresa();
  public IFieldSetup<IEmpresaLayout> porte_empresa();
  public IFieldSetup<IEmpresaLayout> opcao_pelo_simples();
  public IFieldSetup<IEmpresaLayout> data_opcao_pelo_simples();
  public IFieldSetup<IEmpresaLayout> data_exclusao_do_simples();
  public IFieldSetup<IEmpresaLayout> opcao_pelo_mei();
  public IFieldSetup<IEmpresaLayout> situacao_especial();
  public IFieldSetup<IEmpresaLayout> data_situacao_especial();
  public IFieldSetup<IEmpresaLayout> filler();
  public IFieldSetup<IEmpresaLayout> fim_de_registro();
}
