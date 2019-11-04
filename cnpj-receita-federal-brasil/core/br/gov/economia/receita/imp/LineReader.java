package br.gov.economia.receita.imp;

import java.util.List;

enum LineReader {
  DEFAULT(){
    @Override
    public void read(String line, int start, int size, List<String> output) {
      output.add(line.substring(start, start + size).trim());
    }
  },
  CNAE_SECUNDARIA(true){
    @Override
    public void read(String line, int start, int size, List<String> output) {
      String v = line.substring(start, start + size);
      do{
        String s = v.substring(0, 7);
        if (!"0000000".equals(s)) {
          output.add(s.trim());
          v = v.substring(7);
          continue;
        }
        break;
      }while(v.length() <= 7);
    }
  };
  
  private boolean multiValued;
  
  LineReader(){
    this(false);
  }
  
  LineReader(boolean multiValued){
    this.multiValued = multiValued;
  }
  
  public final boolean isMultiValued() {
    return this.multiValued;
  }

  public abstract void read(String line, int previous, int size, List<String> output);
}