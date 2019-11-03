package br.gov.economia.receita.imp;

import br.gov.economia.receita.IBuilderProvider;
import br.gov.economia.receita.IRegisterVisitor;

abstract class Layout implements IBuilderProvider, ILayout{

	private FileLayout.Builder builder;
	protected boolean enabled = false;

	public Layout( FileLayout.Builder builder) {
		this.builder = builder;
	}

	@Override
	public FileLayout.Builder builder() {
		return builder;
	}

	@SuppressWarnings("unchecked")
	public final <T extends ILayout> T enable() {
		enabled = true;
		return (T)this;
	}
	
	public final boolean isEnabled() {
		return enabled;
	}
	
	protected abstract Field[] getFields();
	
	protected abstract VisitResult invokeField(IRegisterVisitor visitor, int row, int col, Field field);
	
	@Override
	public final VisitResult process(IRegisterVisitor visitor, int row, String line) {
		int col, previous = 0;
		Field[] fields = getFields();
		for(int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			if (f.isBypass()) {
				previous = previous + f.getSize();
				continue;
			}
			VisitResult vr;
			col = previous;
			try {
				previous = f.read(line, col);
			}catch(Exception e) {
				vr = visitor.handleError(row, e);
				if (VisitResult.CONTINUE != vr)
					return vr;
			}
			vr = invokeField(visitor, row, col, f);
			if (VisitResult.CONTINUE != vr)
				return vr;
		}
		return VisitResult.CONTINUE;
	};
	
	public <T extends ILayout> T setHeaderBypass(boolean bypass) {
		Field[] fds = getFields();
		for(int i = 0; i < fds.length; i++)
			fds[i].setBypass(bypass);
		return (T)this;
	}
}
