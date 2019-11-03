package br.gov.economia.receita.imp;

import static br.gov.economia.receita.imp.standard.Transformers.DATE;
import static br.gov.economia.receita.imp.standard.Transformers.DQUOTE;
import static br.gov.economia.receita.imp.standard.Transformers.ZEROTRIM;
import static br.gov.economia.receita.imp.standard.Transformers.pipe;

import java.io.File;
import java.io.IOException;

public final class Step01 {
  
  private Step01() {}
  
  public static void main(String[] args) throws IOException {
    File input = new File("./input", "K3241.K03200DV.D90805.L00001");
    sample01(input); 
    sample02(input);
    sample03(input);
  }

  private static void sample03(File input) {
    FileLayout l3 =  new FileLayout.Builder()
        .header()
          .tipo_do_registro().setup(DQUOTE) 
          .data_de_gravacao().setup(pipe(ZEROTRIM, DATE)) 
          .builder().build(input);
    l3.run(); 
  }

  private static void sample02(File input) {
    FileLayout l2 =  new FileLayout.Builder()
      .header()
        .tipo_do_registro().setup()
        .filler().setup()
        .nome_do_arquivo().setup()
        .data_de_gravacao().setup()
        .numero_da_remessa().setup()
        .nextFiller().setup()
        .fim_de_registro().setup()
        .builder().build(input);
    l2.run();
  }

  private static void sample01(File input) {
    FileLayout l1 =  new FileLayout.Builder()
      .header() 
        .nome_do_arquivo().setup()
        .data_de_gravacao().setup()
        .builder().build(input);
    l1.run();
  }
}
