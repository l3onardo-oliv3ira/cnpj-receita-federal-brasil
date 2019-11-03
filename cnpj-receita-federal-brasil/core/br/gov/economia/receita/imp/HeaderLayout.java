package br.gov.economia.receita.imp;

import br.gov.economia.receita.IFieldSetup;
import br.gov.economia.receita.IHeaderLayout;
import br.gov.economia.receita.IRegisterVisitor;

class HeaderLayout extends Layout implements IHeaderLayout {

	private final Field[] headers = new Field[] {
		new Field(1 ,"tipo_do_registro"),
		new Field(16,"filler"),
		new Field(11,"nome_do_arquivo"),
		new Field(8,"data_de_gravacao"),
		new Field(8,"número_da_remessa"),
		new Field(1155,"filler"),
		new Field(1,"fim_de_registro"),
	};

	public HeaderLayout(FileLayout.Builder builder) {
		super(builder);
	}
	
	@Override
	public IFieldSetup<IHeaderLayout> tipo_do_registro() {
		return new FieldSetup<IHeaderLayout>(headers[0], this);
	}

	@Override
	public IFieldSetup<IHeaderLayout> filler() {
		return new FieldSetup<IHeaderLayout>(headers[1], this);
	}

	@Override
	public IFieldSetup<IHeaderLayout> nome_do_arquivo() {
		return new FieldSetup<IHeaderLayout>(headers[2], this);
	}

	@Override
	public IFieldSetup<IHeaderLayout> data_de_gravacao() {
		return new FieldSetup<IHeaderLayout>(headers[3], this);
	}

	@Override
	public IFieldSetup<IHeaderLayout> numero_da_remessa() {
		return new FieldSetup<IHeaderLayout>(headers[4], this);
	}

	@Override
	public IFieldSetup<IHeaderLayout> nextFiller() {
		return new FieldSetup<IHeaderLayout>(headers[5], this);
	}

	@Override
	public IFieldSetup<IHeaderLayout> fim_de_registro() {
		return new FieldSetup<IHeaderLayout>(headers[6], this);
	}

	@Override
	public VisitResult begin(IRegisterVisitor visitor, int row) {
		return visitor.beginHeader(row);
	}

	@Override
	public VisitResult end(IRegisterVisitor visitor) {
		return visitor.endHeader();
	}

	@Override
	protected final Field[] getFields() {
		return this.headers;
	}

	@Override
	protected VisitResult invokeField(IRegisterVisitor visitor, int row, int col, Field field) {
		return visitor.fieldHeader(row, col, field);
	}
}
