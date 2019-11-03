package br.gov.economia.receita.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

import br.gov.economia.receita.ICnaeLayout;
import br.gov.economia.receita.IEmpresaLayout;
import br.gov.economia.receita.IHeaderLayout;
import br.gov.economia.receita.IRegisterVisitor;
import br.gov.economia.receita.ISocioLayout;
import br.gov.economia.receita.ITraillerLayout;

public final class FileLayout implements Runnable{
	
	private static final int DEFAULT_BUFFER =  8*1024*1024;
	
	public static class Builder {

		private HeaderLayout 	headerLayout 		= new HeaderLayout(this);
		private EmpresaLayout empresaLayout 	= new EmpresaLayout(this);
		private SocioLayout   socioLayout     = new SocioLayout(this);
		private CnaeLayout    cnaeLayout      = new CnaeLayout(this);
		private TraillerLayout traillerLayout = new TraillerLayout(this);
		
		private IRegisterVisitor visitor      = new SysoutVisitor();
		
		public IHeaderLayout header() {
			return headerLayout.enable();
		}
		
		public IEmpresaLayout empresa() {
			return empresaLayout.enable();
		}
		
		public ISocioLayout socio() {
			return socioLayout.enable();
		}

		public ICnaeLayout cnae() {
			return cnaeLayout.enable();
		}
		
		public ITraillerLayout trailler() {
			return traillerLayout.enable();
		}
		
		public FileLayout build(File input) {
			return build(input, DEFAULT_BUFFER);
		}
		
		public FileLayout build(File input, int bufferSize) {
			return build(input, bufferSize, visitor);
		}
		
		public FileLayout build(File input, IRegisterVisitor visitor) {
			return build(input, DEFAULT_BUFFER, visitor);
		}
		
		public FileLayout build(File input, int bufferSize, IRegisterVisitor visitor) {
			return new FileLayout(input, bufferSize, visitor, this);
		}
	}
	
	private File input;
	private int bufferSize;
	private IRegisterVisitor visitor;
	private Builder builder;
	
	private FileLayout(File input, int bufferSize, IRegisterVisitor visitor, Builder builder) {
		this.input = input;
		this.bufferSize = bufferSize;
		this.visitor = visitor;
		this.builder = builder;
	}

	@Override
	public void run() {
		BufferedReader buffer;
		try {
			buffer = new BufferedReader(new FileReader(input, Charset.forName("ISO-8859-1")), bufferSize);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		try {
			visitor.start();
			iterate(buffer);
		}finally {
			Closeables.closeQuietly(buffer);
			visitor.end();
		}
	}
	
	private ILayout from(char number) {
		switch(number) {
			case '0': return builder.headerLayout.isEnabled() 	? builder.headerLayout	: SkipLayout.INSTANCE;
			case '1': return builder.empresaLayout.isEnabled() 	? builder.empresaLayout	: SkipLayout.INSTANCE;
			case '2': return builder.socioLayout.isEnabled()		? builder.socioLayout		: SkipLayout.INSTANCE;
			case '6': return builder.cnaeLayout.isEnabled()			? builder.cnaeLayout		: SkipLayout.INSTANCE;
			case '9': return builder.traillerLayout.isEnabled() ? builder.traillerLayout: SkipLayout.INSTANCE;
			default: return SkipLayout.INSTANCE;
		}
	}

	private void iterate(BufferedReader buffer){
		String line = null;
		int row = 0;
		do {
			try {
				line = buffer.readLine();
			} catch (IOException e) {
				VisitResult vr = visitor.handleError(row, e);
				if (VisitResult.TERMINATE != vr)
					continue;
				break;
			}
			
			if (line == null)
				break;
			
			row++;

			if (line.length() == 0)
				continue;
			
			ILayout layout = from(line.charAt(0));
			
			VisitResult vr = layout.begin(visitor, row);;
			if (VisitResult.SKIP == vr)
				continue;
			if (VisitResult.TERMINATE == vr)
				break;
			vr = layout.process(visitor, row, line);
			if (VisitResult.SKIP == vr)
				continue;
			if (VisitResult.TERMINATE == vr)
				break;
			vr = layout.end(visitor);
			if (VisitResult.TERMINATE == vr)
				break;
		}while(true);
	}
}
