package br.gov.economia.receita.imp;

import static java.lang.System.out;

import br.gov.economia.receita.IField;

public class SysoutVisitor extends SimpleVisitor{

	@Override
	public void end() {
		out.println("fim do arquivo");
	}
	
	@Override
	public void start() {
		out.println("Iniciado atravessamento do arquivo");
	}
	
	@Override
	public VisitResult beginHeader(int row) {
		out.println("Header iniciando na linha: " + row);
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult endHeader() {
		out.println("fim do header");
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult beginEmpresa(int row) {
		out.println("Empresa iniciando em: " + row);
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult endEmpresa() {
		out.println("fim da empresa");
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult beginSocio(int row) {
		out.println("Socio iniciando em: " + row);
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult endSocio() {
		out.println("fim do sócio");
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult beginCnae(int row) {
		out.println("Cnae iniciando em: " + row);
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult endCnae() {
		out.println("fim cnae");
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult beginTrailler(int row) {
		out.println("Trailler iniciando em: " + row);
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult endTrailler() {
		out.println("fim do trailler");
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult fieldHeader(int row, int col, IField field) {
		return field(row, col, field);
	}

	@Override
	public VisitResult fieldEmpresa(int row, int col, IField field) {
		return field(row, col, field);
	}

	@Override
	public VisitResult fieldSocio(int row, int col, IField field) {
		return field(row, col, field);
	}

	@Override
	public VisitResult fieldCnae(int row, int col, IField field) {
		return field(row, col, field);
	}

	@Override
	public VisitResult fieldTrailler(int row, int column, IField field) {
		return field(row, column, field);
	}
	
	private VisitResult field(int row, int column, IField field) {
		out.println("arquivo["+ row + "," + column + "]->" + field.toString());
		return VisitResult.CONTINUE;
	}
}
