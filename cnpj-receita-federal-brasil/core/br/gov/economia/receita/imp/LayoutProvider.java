package br.gov.economia.receita.imp;

import static br.gov.economia.receita.imp.standard.Transformers.BOOL;
import static br.gov.economia.receita.imp.standard.Transformers.DATE;
import static br.gov.economia.receita.imp.standard.Transformers.LONG;
import static br.gov.economia.receita.imp.standard.Transformers.LOWERCASE;
import static br.gov.economia.receita.imp.standard.Transformers.SQUOTE;
import static br.gov.economia.receita.imp.standard.Transformers.ZEROTRIM;
import static br.gov.economia.receita.imp.standard.Transformers.pipe;

import br.gov.economia.receita.imp.FileLayout.Builder;
import br.gov.economia.receita.imp.standard.DefaultIfEmpty;

public class LayoutProvider {
  
  private LayoutProvider() {}
  
  public static Builder cnaeLayout() {
    return new FileLayout.Builder().cnae()
      .tipo_do_registro()     .setup(SQUOTE)
      .indicador_full_diario().setup(SQUOTE)
      .tipo_de_atualizacao()  .setup(SQUOTE)
      .cnpj()                 .setup(SQUOTE)
      .cnae_secundaria()      .setup(SQUOTE)
      .filler()               .setup(SQUOTE)
      .fim_registro()         .setup(SQUOTE)
    .builder();
  }
  
  public static FileLayout.Builder socioLayout() {
    return new FileLayout.Builder().socio()
      .cnpj()                            .setup(LONG)
      .identificador_de_socio()          .setup("identificador"      , LONG)
      .nome_socio_pf_ou_razao_social_pj().setup("razao_social"       , SQUOTE)
      .cnpj_ou_cpf_do_sócio()            .setup("cpf_cnpj"           , pipe(new CpfInvalidoTransformer(), SQUOTE))
      .codigo_qualificacao_socio()       .setup("qualificacao"       , pipe(new QualificacaoSocioTransformer(), SQUOTE))
      .percentual_capital_social()       .setup("percentual"         , pipe(LONG, new DefaultIfEmpty("VAZIO")))
      .data_entrada_sociedade()          .setup("entrada"            , pipe(ZEROTRIM, DATE, SQUOTE))
      .codigo_pais()                     .setup("pais"               , pipe(LONG, new DefaultIfEmpty("VAZIO"), SQUOTE))
      .nome_pais_socio()                 .setup("nome_pais"          , SQUOTE)
      .cpf_representante_legal()         .setup("cpf_representante"  , pipe(new CpfInvalidoTransformer(), SQUOTE))
      .nome_representante()              .setup("nome_representante" , SQUOTE)
      .codigo_qualificacao_representante_legal().setup("cod_repre"   , pipe(new QualificacaoRepresentanteLegalTransformer(), SQUOTE))
      .builder();
  }
  
  /**
   * Exemplo demonstrando a possibilidade de renomear os campos para o que melhor convier. No caso, campos c1, c2, etc.
   * @return
   */
  public static FileLayout.Builder empresaLayout() {
    return new FileLayout.Builder().empresa()
      .cnpj()                             .setup("c1", LONG)
      .identificador_matriz_ou_filial()   .setup("c2", pipe(new MatrizFilialTransformer(), SQUOTE))
      .razao_social_ou_nome_empresarial() .setup("c3", SQUOTE)
      .nome_fantasia()                    .setup("c4", SQUOTE)
      .situacao_cadastral()               .setup("c5", pipe(new SituacaoCadastralTransformer(), SQUOTE))
      .data_situacao_cadastral()          .setup("c6", pipe(ZEROTRIM, DATE, SQUOTE))
      .motivo_situacao_cadastral()        .setup("c7", pipe(new MotivoSituacaoCadastralTransformer(), SQUOTE))
      .nm_cidade_exterior()               .setup("c8", SQUOTE)
      .co_pais()                          .setup("c9", SQUOTE)
      .nm_pais()                          .setup("c10", SQUOTE)
      .codigo_natureza_juridica()         .setup("c11", LONG)
      .data_inicio_atividade()            .setup("c12", pipe(ZEROTRIM, DATE, SQUOTE))
      .cnae_fiscal()                      .setup("c13", LONG)
      .descricao_tipo_logradouro()        .setup("c14", SQUOTE)
      .logradouro()                       .setup("c15", SQUOTE)
      .numero()                           .setup("c16", SQUOTE)
      .complemento()                      .setup("c17", SQUOTE)
      .bairro()                           .setup("c18", SQUOTE)
      .cep()                              .setup("c19", pipe(new CepTransformer(), SQUOTE))
      .uf()                               .setup("c20", SQUOTE)
      .codigo_municipio()                 .setup("c21", SQUOTE)
      .municipio()                        .setup("c22", SQUOTE)
      .ddd_telefone_1()                   .setup("c23", SQUOTE)
      .ddd_telefone_2()                   .setup("c24",SQUOTE)
      .ddd_fax()                          .setup("c25",SQUOTE)
      .correio_eletronico()               .setup("c26",pipe(LOWERCASE, SQUOTE))
      .qualificacao_do_responsavel()      .setup("c27",pipe(new QualificacaoResponsavelTransformer(), SQUOTE))
      .capital_social_da_empresa()        .setup("c28",pipe(ZEROTRIM, SQUOTE))
      .porte_empresa()                    .setup("c29",pipe(new PortEmpresaTransformer(), SQUOTE))
      .opcao_pelo_simples()               .setup("c31",pipe(new OpcaoSimplesTransformer(), SQUOTE))
      .data_opcao_pelo_simples()          .setup("c32",pipe(ZEROTRIM, DATE, SQUOTE))
      .data_exclusao_do_simples()         .setup("c33",pipe(ZEROTRIM, DATE, SQUOTE))
      .opcao_pelo_mei()                   .setup("c34",pipe(new OpcaoMeiTransformer(), BOOL))
      .situacao_especial()                .setup("c35",SQUOTE)
      .data_situacao_especial()           .setup("c36",pipe(ZEROTRIM, DATE, SQUOTE))
    .builder();
  }
}
