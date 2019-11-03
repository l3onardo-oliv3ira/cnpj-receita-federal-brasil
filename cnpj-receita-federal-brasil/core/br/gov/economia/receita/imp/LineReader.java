package br.gov.economia.receita.imp;

import java.util.ArrayList;
import java.util.List;

enum LineReader {
	DEFAULT(){
		@Override
		public List<String> read(String line, int previous, int size) {
			var values = new ArrayList<String>(1);
			values.add(line.substring(previous, previous + size).trim());
			return values;
		}
	},
	CNAE_SECUNDARIA(){
		@Override
		public List<String> read(String line, int previous, int size) {
			String v = line.substring(previous, previous + size);
			var values = new ArrayList<String>(1);
			do{
				String s = v.substring(0, 7);
				if (!"0000000".equals(s)) {
					values.add(s.trim());
					v = v.substring(7);
					continue;
				}
				break;
			}while(true);
			return values;
		}
	};

	public abstract List<String> read(String line, int previous, int size);
}