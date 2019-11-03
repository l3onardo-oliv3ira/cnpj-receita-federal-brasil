package br.gov.economia.receita;

public interface ISocioLayout extends IBuilderProvider{
	public IFieldSetup<ISocioLayout> tipo_de_registro();
	public IFieldSetup<ISocioLayout> indicador_full_diario();
	public IFieldSetup<ISocioLayout> tipo_de_atualizacao();
	public IFieldSetup<ISocioLayout> cnpj();
	public IFieldSetup<ISocioLayout> identificador_de_socio();
	public IFieldSetup<ISocioLayout> nome_socio_pf_ou_razao_social_pj();
	public IFieldSetup<ISocioLayout> cnpj_ou_cpf_do_sócio();
	public IFieldSetup<ISocioLayout> codigo_qualificacao_socio();
	public IFieldSetup<ISocioLayout> percentual_capital_social();
	public IFieldSetup<ISocioLayout> data_entrada_sociedade();
	public IFieldSetup<ISocioLayout> codigo_pais();
	public IFieldSetup<ISocioLayout> nome_pais_socio();
	public IFieldSetup<ISocioLayout> cpf_representante_legal();
	public IFieldSetup<ISocioLayout> nome_representante();
	public IFieldSetup<ISocioLayout> codigo_qualificacao_representante_legal();
	public IFieldSetup<ISocioLayout> filler();
	public IFieldSetup<ISocioLayout> fim_registro();
}
