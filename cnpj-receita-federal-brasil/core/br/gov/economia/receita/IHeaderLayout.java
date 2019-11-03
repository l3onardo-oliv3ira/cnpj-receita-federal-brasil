package br.gov.economia.receita;

public interface IHeaderLayout extends IBuilderProvider{
	public IFieldSetup<IHeaderLayout> tipo_do_registro();
	public IFieldSetup<IHeaderLayout> filler();
	public IFieldSetup<IHeaderLayout> nome_do_arquivo();
	public IFieldSetup<IHeaderLayout> data_de_gravacao();
	public IFieldSetup<IHeaderLayout> numero_da_remessa();
	public IFieldSetup<IHeaderLayout> nextFiller();
	public IFieldSetup<IHeaderLayout> fim_de_registro();
}
