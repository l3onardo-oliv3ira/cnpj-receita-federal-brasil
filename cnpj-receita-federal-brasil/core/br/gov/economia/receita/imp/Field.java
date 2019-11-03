package br.gov.economia.receita.imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.gov.economia.receita.IField;
import br.gov.economia.receita.ITransformer;

class Field implements IField {
	
	private final int size;
	private final String name;
	private List<String> values;
	private final LineReader reader;
	private ITransformer transformer = ITransformer.IDENTITY;
	private boolean bypass;
	
	public Field(int size, String name) {
		this(size, name, LineReader.DEFAULT);
	}
	
	public Field(int size, String name, LineReader reader) {
		this.size = size;
		this.name = name;
		this.bypass = true;
		this.reader = reader;
	}
	
	@Override
	public final String getName() {
		return this.name;
	} 
	
	@Override
	public final String getValue() {
		return this.values == null ? "" : this.values.get(0);
	}
	
	public final int getSize() {
		return this.size;
	}

	@Override
	public final List<String> getValues() {
		return Collections.unmodifiableList(this.values);
	}

	@Override
	public final boolean isMultivalued() {
		return this.values != null && this.values.size() > 1;
	}
	
	@Override
	public final String toString() {
		return this.name + ": " + getValue();
	}
	
	final void setTransformer(ITransformer transformer) {
		if (!isBypass()) {
			this.transformer = transformer;
		}
	}

	final int read(String line, int previous) {
		List<String> output = reader.read(line, previous, size);
		List<String> transf = new ArrayList<String>(output.size());
		for(String v: output)
			transf.add(transformer.transform(v));
		values = transf;
		return previous + size;
	}

	final void setBypass(boolean bypass) {
		this.bypass = bypass;
	}
	
	final boolean isBypass() {
		return bypass;
	}
}