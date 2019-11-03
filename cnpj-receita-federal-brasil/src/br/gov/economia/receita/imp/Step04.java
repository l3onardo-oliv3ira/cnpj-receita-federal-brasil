package br.gov.economia.receita.imp;

import static br.gov.economia.receita.imp.standard.Transformers.BOOL;
import static br.gov.economia.receita.imp.standard.Transformers.DATE;
import static br.gov.economia.receita.imp.standard.Transformers.DQUOTE;
import static br.gov.economia.receita.imp.standard.Transformers.LONG;
import static br.gov.economia.receita.imp.standard.Transformers.LOWERCASE;
import static br.gov.economia.receita.imp.standard.Transformers.ZEROTRIM;
import static br.gov.economia.receita.imp.standard.Transformers.pipe;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public final class Step04 {
  
  private Step04() {}
  
  /**
   * Exportando os 100 primeiros registros das empresas em formato json. Voce pode escrever o seu próprio
   * formato de exportação. O core da solução utiliza Visitor design pattern. Basta implementar a sua interface
   * IRegisterVisitor ou derivar (é melhor) das classes de apoio EmpresaVisitor, SocioVisitor, etc.
   * 
   * Neste exemplo, os campos das empresas já foram transformados para os padrões exigidos pela RFC8259. 
   * Se precisar, implemente o seu proprio transformador para adequar os resultados segundo o seu desejo.
   * @param args
   */
  public static void main(String[] args) throws IOException {
    
    File[] inputs = new File[] {
      new File("./input", "entrada01"),
      new File("./input", "entrada02")
    };
    
    List<Thread> threads = new ArrayList<Thread>();
    
    /**
     * Rode a exportação/leitura em mais de uma thread, ThreadPoolExecutor ou o que melhor lhe convier.
     * Se tiver disponivel HDs SSD, divida os arquivos em discos separados e atribuia uma Thread dedicada
     * para cada arquivo de dados. 
     */
    for(int i = 0; i < inputs.length; i++) {
      EmpresaJsonExporter e = new EmpresaJsonExporter(new PrintWriter(new File("./output/output" + i + ".json")), 100);
      FileLayout layout =  new FileLayout.Builder()
        .empresa()
          .tipo_de_registro()                  .setup(LONG)
          .indicador_full_diario()            .setup(DQUOTE)
          .tipo_atualizacao()                  .setup(DQUOTE)
          .cnpj()                              .setup(LONG)
          .identificador_matriz_ou_filial()    .setup(pipe(new MatrizFilialTransformer(), DQUOTE))
          .razao_social_ou_nome_empresarial()  .setup(DQUOTE)
          .nome_fantasia()                    .setup(DQUOTE)
          .situacao_cadastral()                .setup(pipe(new SituacaoCadastralTransformer(), DQUOTE))
          .data_situacao_cadastral()          .setup(pipe(ZEROTRIM, DATE, DQUOTE))
          .motivo_situacao_cadastral()        .setup(DQUOTE)
          .nm_cidade_exterior()                .setup(DQUOTE)
          .co_pais()                          .setup(DQUOTE)
          .nm_pais()                          .setup(DQUOTE)
          .codigo_natureza_juridica()          .setup(LONG)
          .data_inicio_atividade()            .setup(pipe(ZEROTRIM, DATE, DQUOTE))
          .cnae_fiscal()                      .setup(LONG)
          .descricao_tipo_logradouro()        .setup(DQUOTE)
          .logradouro()                        .setup(DQUOTE)
          .numero()                            .setup(DQUOTE)
          .complemento()                      .setup(DQUOTE)
          .bairro()                            .setup(DQUOTE)
          .cep()                              .setup(pipe(new CepTransformer(), DQUOTE))
          .uf()                                .setup(DQUOTE)
          .codigo_municipio()                  .setup(DQUOTE)
          .municipio()                        .setup(DQUOTE)
          .ddd_telefone_1()                    .setup(DQUOTE)
          .ddd_telefone_2()                    .setup(DQUOTE)
          .ddd_fax()                          .setup(DQUOTE)
          .correio_eletronico()                .setup(pipe(LOWERCASE, DQUOTE))
          .qualificacao_do_responsavel()      .setup(DQUOTE)
          .capital_social_da_empresa()        .setup(pipe(ZEROTRIM, DQUOTE))
          .porte_empresa()                    .setup(pipe(new PortEmpresaTransformer(), DQUOTE))
          .opcao_pelo_simples()                .setup(pipe(new OpcaoSimplesTransformer(), DQUOTE))
          .data_opcao_pelo_simples()          .setup(pipe(ZEROTRIM, DATE, DQUOTE))
          .data_exclusao_do_simples()          .setup(pipe(ZEROTRIM, DATE, DQUOTE))
          .opcao_pelo_mei()                    .setup(pipe(new OpcaoMeiTransformer(), BOOL))
          .situacao_especial()                .setup(DQUOTE)
          .data_situacao_especial()            .setup(pipe(ZEROTRIM, DATE, DQUOTE))
          .filler()                            .setup(pipe(ZEROTRIM, DQUOTE))
          .fim_de_registro()                  .setup(DQUOTE)
        .builder().build(inputs[i], e);
  
        Thread t = new Thread(layout);
        threads.add(t);
        t.start();
    }
    while(!threads.isEmpty()) {
      try {
        threads.remove(0).join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
