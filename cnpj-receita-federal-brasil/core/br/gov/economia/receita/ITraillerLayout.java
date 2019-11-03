package br.gov.economia.receita;

public interface ITraillerLayout extends IBuilderProvider {
	public IFieldSetup<ITraillerLayout> tipo_do_registro();
	public IFieldSetup<ITraillerLayout> filler();
	public IFieldSetup<ITraillerLayout> total_de_registro_t1();
	public IFieldSetup<ITraillerLayout> total_de_registro_t2();
	public IFieldSetup<ITraillerLayout> total_de_registros_t3();
	public IFieldSetup<ITraillerLayout> nextFiller();
	public IFieldSetup<ITraillerLayout> fim_de_registro();
}
