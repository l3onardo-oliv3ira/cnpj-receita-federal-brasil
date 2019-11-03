package br.gov.economia.receita.imp;

import br.gov.economia.receita.IFieldSetup;
import br.gov.economia.receita.IRegisterVisitor;
import br.gov.economia.receita.ITraillerLayout;
import br.gov.economia.receita.imp.FileLayout.Builder;

class TraillerLayout extends Layout implements ITraillerLayout{

	private final Field[] traillers = new Field[] {
		new Field(1, "tipo_do_registro"),
		new Field(16, "filler"),
		new Field(9, "total_de_registro_t1"),
		new Field(9, "total_de_registro_t2"),
		new Field(11, "total_de_registros_t3"),
		new Field(1144, "filler"),
		new Field(1, "fim_de_registro")
	};
	
	public TraillerLayout(Builder builder) {
		super(builder);
	}

	@Override
	public VisitResult begin(IRegisterVisitor visitor, int row) {
		return visitor.beginTrailler(row);
	}

	@Override
	public VisitResult end(IRegisterVisitor visitor) {
		return visitor.endTrailler();
	}

	@Override
	protected final Field[] getFields() {
		return this.traillers;
	}

	@Override
	protected VisitResult invokeField(IRegisterVisitor visitor, int row, int col, Field field) {
		return visitor.fieldTrailler(row, col, field);
	}

	@Override
	public IFieldSetup<ITraillerLayout> tipo_do_registro() {
		return new FieldSetup<ITraillerLayout>(traillers[0], this);
	}

	@Override
	public IFieldSetup<ITraillerLayout> filler() {
		return new FieldSetup<ITraillerLayout>(traillers[1], this);
	}

	@Override
	public IFieldSetup<ITraillerLayout> total_de_registro_t1() {
		return new FieldSetup<ITraillerLayout>(traillers[2], this);
	}

	@Override
	public IFieldSetup<ITraillerLayout> total_de_registro_t2() {
		return new FieldSetup<ITraillerLayout>(traillers[3], this);
	}

	@Override
	public IFieldSetup<ITraillerLayout> total_de_registros_t3() {
		return new FieldSetup<ITraillerLayout>(traillers[4], this);
	}

	@Override
	public IFieldSetup<ITraillerLayout> nextFiller() {
		return new FieldSetup<ITraillerLayout>(traillers[5], this);
	}

	@Override
	public IFieldSetup<ITraillerLayout> fim_de_registro() {
		return new FieldSetup<ITraillerLayout>(traillers[6], this);
	}
}
