package br.gov.economia.receita.imp;

import java.io.PrintWriter;

import br.gov.economia.receita.IField;

public class EmpresaJsonExporter extends EmpresaVisitor {

	private final int max;
	private boolean comma = false;
	private final PrintWriter writer;
	private int total = 0;
	
	public EmpresaJsonExporter(PrintWriter writer) {
		this(writer, Integer.MAX_VALUE);
	}
	
	public EmpresaJsonExporter(PrintWriter writer, int max) {
		this.writer = writer;
		this.max = max;
		this.total = 0;
	}
	
	@Override
	public void start() {
		writer.print('[');
	}
	
	
	@Override
	public void end() {
		writer.print(']');
		writer.close();
	}

	@Override
	public VisitResult beginEmpresa(int row) {
		if (comma)
			writer.println(',');
		writer.println('{');
		writer.print(" \"id\": " + row);
		comma = true;
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult endEmpresa() {
		writer.println();
		writer.print('}');
		if (++total == max)
			return VisitResult.TERMINATE;
		return VisitResult.CONTINUE;
	}

	@Override
	public VisitResult fieldEmpresa(int row, int col, IField field) {
		writer.println(',');
		writer.print(" \"" + field.getName() + "\":");
		if (!field.isMultivalued())
			writer.print(field.getValue());
		else {
			writer.print('[');
			boolean comma = false;
			for(String v: field.getValues()) {
				if (comma)
					writer.print(',');
				writer.print(v);
				comma = true;
			}
		}
		return VisitResult.CONTINUE;
	}
}
