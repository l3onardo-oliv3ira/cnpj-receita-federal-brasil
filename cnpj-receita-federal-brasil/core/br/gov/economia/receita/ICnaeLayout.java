package br.gov.economia.receita;

public interface ICnaeLayout extends IBuilderProvider{
	public IFieldSetup<ICnaeLayout> tipo_do_registro();
	public IFieldSetup<ICnaeLayout> indicador_full_diario();
	public IFieldSetup<ICnaeLayout> tipo_de_atualizacao();
	public IFieldSetup<ICnaeLayout> cnpj();
	public IFieldSetup<ICnaeLayout> cnae_secundaria();
	public IFieldSetup<ICnaeLayout> filler();
	public IFieldSetup<ICnaeLayout> fim_registro();
}
