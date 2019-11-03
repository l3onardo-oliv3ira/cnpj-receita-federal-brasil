package br.gov.economia.receita.imp;

import br.gov.economia.receita.IField;
import br.gov.economia.receita.IRegisterVisitor;

/**
 * Visitor que ignora o layout dos demais tipos de dados e considera apenas os registros
 * de dados de empresas.
 * @author LEOOLIVE
 *
 */
public abstract class EmpresaVisitor implements IRegisterVisitor{

	@Override
	public VisitResult beginHeader(int row) {
		return VisitResult.SKIP;
	}

	@Override
	public VisitResult fieldHeader(int row, int col, IField field) {
		return VisitResult.SKIP;
	}

	@Override
	public VisitResult endHeader() {
		return VisitResult.SKIP;
	}

	@Override
	public VisitResult beginSocio(int row) {
		return VisitResult.SKIP;
	}

	@Override
	public VisitResult fieldSocio(int row, int col, IField field) {
		return VisitResult.SKIP;
	}

	@Override
	public VisitResult endSocio() {
		return VisitResult.SKIP;
	}

	@Override
	public VisitResult beginCnae(int row) {
		return VisitResult.SKIP;
	}

	@Override
	public VisitResult fieldCnae(int row, int col, IField field) {
		return VisitResult.SKIP;
	}

	@Override
	public VisitResult endCnae() {
		return VisitResult.SKIP;
	}

	@Override
	public VisitResult beginTrailler(int row) {
		return VisitResult.SKIP;
	}

	@Override
	public VisitResult fieldTrailler(int row, int column, IField field) {
		return VisitResult.SKIP;
	}

	@Override
	public VisitResult endTrailler() {
		return VisitResult.SKIP;
	}

	@Override
	public VisitResult handleError(int lineNumber, Throwable e) {
		e.printStackTrace(System.err);
		return VisitResult.TERMINATE;
	}
}
