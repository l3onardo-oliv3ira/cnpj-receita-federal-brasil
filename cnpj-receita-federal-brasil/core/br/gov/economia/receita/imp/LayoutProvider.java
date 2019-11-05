package br.gov.economia.receita.imp;

import static br.gov.economia.receita.imp.standard.Transformers.BOOL;
import static br.gov.economia.receita.imp.standard.Transformers.DATE;
import static br.gov.economia.receita.imp.standard.Transformers.DQUOTE;
import static br.gov.economia.receita.imp.standard.Transformers.LONG;
import static br.gov.economia.receita.imp.standard.Transformers.LOWERCASE;
import static br.gov.economia.receita.imp.standard.Transformers.SQUOTE;
import static br.gov.economia.receita.imp.standard.Transformers.ZEROTRIM;
import static br.gov.economia.receita.imp.standard.Transformers.pipe;

import br.gov.economia.receita.imp.FileLayout.Builder;
import br.gov.economia.receita.imp.standard.DefaultIfEmpty;

public class LayoutProvider {
  
  private LayoutProvider() {}
  
  public static Builder socioLayout4CSV() {
    return new FileLayout.Builder().socio()
      .cnpj()                            .setup(LONG)
      .identificador_de_socio()          .setup("identificador"      , LONG)
      .nome_socio_pf_ou_razao_social_pj().setup("razao_social")
      .cnpj_ou_cpf_do_sócio()            .setup("cpf_cnpj"           , pipe(new CpfInvalidoTransformer()))
      .codigo_qualificacao_socio()       .setup("qualificacao"       , pipe(new QualificacaoSocioTransformer()))
      .percentual_capital_social()       .setup("percentual"         , LONG)
      .data_entrada_sociedade()          .setup("entrada"            , pipe(ZEROTRIM, DATE))
      .codigo_pais()                     .setup("pais"               , LONG)
      .nome_pais_socio()                 .setup("nome_pais")
      .cpf_representante_legal()         .setup("cpf_representante"  , pipe(new CpfInvalidoTransformer(), new DefaultIfEmpty("indefinido")))
      .nome_representante()              .setup("nome_representante")
      .codigo_qualificacao_representante_legal().setup("cod_repre"   , pipe(new QualificacaoRepresentanteLegalTransformer()))
    .builder();
  }

  public static Builder socioLayout4JSON() {
    return new FileLayout.Builder().socio()
      .cnpj()                            .setup(LONG)
      .identificador_de_socio()          .setup("identificador"      , LONG)
      .nome_socio_pf_ou_razao_social_pj().setup("razao_social"       , DQUOTE)
      .cnpj_ou_cpf_do_sócio()            .setup("cpf_cnpj"           , pipe(new CpfInvalidoTransformer(), DQUOTE))
      .codigo_qualificacao_socio()       .setup("qualificacao"       , pipe(new QualificacaoSocioTransformer(), DQUOTE))
      .percentual_capital_social()       .setup("percentual"         , LONG)
      .data_entrada_sociedade()          .setup("entrada"            , pipe(ZEROTRIM, DATE, DQUOTE))
      .codigo_pais()                     .setup("pais"               , LONG)
      .nome_pais_socio()                 .setup("nome_pais"          , DQUOTE)
      .cpf_representante_legal()         .setup("cpf_representante"  , pipe(new CpfInvalidoTransformer(), new DefaultIfEmpty("indefinido"), DQUOTE))
      .nome_representante()              .setup("nome_representante" , DQUOTE)
      .codigo_qualificacao_representante_legal().setup("cod_repre"   , pipe(new QualificacaoRepresentanteLegalTransformer(), DQUOTE))
    .builder();
  }

  public static Builder socioLayout4SQL() {
    return new FileLayout.Builder().socio()
      .cnpj()                            .setup(LONG)
      .identificador_de_socio()          .setup("identificador"      , LONG)
      .nome_socio_pf_ou_razao_social_pj().setup("razao_social"       , SQUOTE)
      .cnpj_ou_cpf_do_sócio()            .setup("cpf_cnpj"           , pipe(new CpfInvalidoTransformer(), SQUOTE))
      .codigo_qualificacao_socio()       .setup("qualificacao"       , pipe(new QualificacaoSocioTransformer(), SQUOTE))
      .percentual_capital_social()       .setup("percentual"         , LONG)
      .data_entrada_sociedade()          .setup("entrada"            , pipe(ZEROTRIM, DATE, SQUOTE))
      .codigo_pais()                     .setup("pais"               , LONG)
      .nome_pais_socio()                 .setup("nome_pais"          , SQUOTE)
      .cpf_representante_legal()         .setup("cpf_representante"  , pipe(new CpfInvalidoTransformer(), new DefaultIfEmpty("indefinido"), SQUOTE))
      .nome_representante()              .setup("nome_representante" , SQUOTE)
      .codigo_qualificacao_representante_legal().setup("cod_repre"   , pipe(new QualificacaoRepresentanteLegalTransformer(), SQUOTE))
    .builder();
  }

  public static FileLayout.Builder empresaLayout4CSV() {
    return new FileLayout.Builder().empresa()
      .cnpj()                             .setup()
      .identificador_matriz_ou_filial()   .setup(pipe(new MatrizFilialTransformer()))
      .razao_social_ou_nome_empresarial() .setup()
      .nome_fantasia()                    .setup()
      .situacao_cadastral()               .setup(pipe(new SituacaoCadastralTransformer()))
      .data_situacao_cadastral()          .setup(pipe(ZEROTRIM, DATE))
      .motivo_situacao_cadastral()        .setup(pipe(new MotivoSituacaoCadastralTransformer()))
      .nm_cidade_exterior()               .setup()
      .co_pais()                          .setup()
      .nm_pais()                          .setup()
      .codigo_natureza_juridica()         .setup()
      .data_inicio_atividade()            .setup(pipe(ZEROTRIM, DATE))
      .cnae_fiscal()                      .setup(LONG)
      .descricao_tipo_logradouro()        .setup()
      .logradouro()                       .setup()
      .numero()                           .setup()
      .complemento()                      .setup()
      .bairro()                           .setup()
      .cep()                              .setup(pipe(new CepTransformer()))
      .uf()                               .setup()
      .codigo_municipio()                 .setup()
      .municipio()                        .setup()
      .ddd_telefone_1()                   .setup()
      .ddd_telefone_2()                   .setup()
      .ddd_fax()                          .setup()
      .correio_eletronico()               .setup(pipe(LOWERCASE))
      .qualificacao_do_responsavel()      .setup(pipe(new QualificacaoResponsavelTransformer()))
      .capital_social_da_empresa()        .setup(pipe(ZEROTRIM))
      .porte_empresa()                    .setup(pipe(new PortEmpresaTransformer()))
      .opcao_pelo_simples()               .setup(pipe(new OpcaoSimplesTransformer()))
      .data_opcao_pelo_simples()          .setup(pipe(ZEROTRIM, DATE))
      .data_exclusao_do_simples()         .setup(pipe(ZEROTRIM, DATE))
      .opcao_pelo_mei()                   .setup(pipe(new OpcaoMeiTransformer(), BOOL))
      .situacao_especial()                .setup()
      .data_situacao_especial()           .setup(pipe(ZEROTRIM, DATE))
    .builder();
  }

  public static Builder empresaLayout4JSON() {
    return new FileLayout.Builder().empresa()
      .cnpj()                             .setup(DQUOTE)
      .identificador_matriz_ou_filial()   .setup(pipe(new MatrizFilialTransformer(), DQUOTE))
      .razao_social_ou_nome_empresarial() .setup(DQUOTE)
      .nome_fantasia()                    .setup(DQUOTE)
      .situacao_cadastral()               .setup(pipe(new SituacaoCadastralTransformer(), DQUOTE))
      .data_situacao_cadastral()          .setup(pipe(ZEROTRIM, DATE, DQUOTE))
      .motivo_situacao_cadastral()        .setup(pipe(new MotivoSituacaoCadastralTransformer(), DQUOTE))
      .nm_cidade_exterior()               .setup(DQUOTE)
      .co_pais()                          .setup(DQUOTE)
      .nm_pais()                          .setup(DQUOTE)
      .codigo_natureza_juridica()         .setup(DQUOTE)
      .data_inicio_atividade()            .setup(pipe(ZEROTRIM, DATE, DQUOTE))
      .cnae_fiscal()                      .setup(LONG)
      .descricao_tipo_logradouro()        .setup(DQUOTE)
      .logradouro()                       .setup(DQUOTE)
      .numero()                           .setup(DQUOTE)
      .complemento()                      .setup(DQUOTE)
      .bairro()                           .setup(DQUOTE)
      .cep()                              .setup(pipe(new CepTransformer(), DQUOTE))
      .uf()                               .setup(DQUOTE)
      .codigo_municipio()                 .setup(DQUOTE)
      .municipio()                        .setup(DQUOTE)
      .ddd_telefone_1()                   .setup(DQUOTE)
      .ddd_telefone_2()                   .setup(DQUOTE)
      .ddd_fax()                          .setup(DQUOTE)
      .correio_eletronico()               .setup(pipe(LOWERCASE, DQUOTE))
      .qualificacao_do_responsavel()      .setup(pipe(new QualificacaoResponsavelTransformer(), DQUOTE))
      .capital_social_da_empresa()        .setup(pipe(ZEROTRIM, DQUOTE))
      .porte_empresa()                    .setup(pipe(new PortEmpresaTransformer(), DQUOTE))
      .opcao_pelo_simples()               .setup(pipe(new OpcaoSimplesTransformer(), DQUOTE))
      .data_opcao_pelo_simples()          .setup(pipe(ZEROTRIM, DATE, DQUOTE))
      .data_exclusao_do_simples()         .setup(pipe(ZEROTRIM, DATE, DQUOTE))
      .opcao_pelo_mei()                   .setup(pipe(new OpcaoMeiTransformer(), BOOL))
      .situacao_especial()                .setup(DQUOTE)
      .data_situacao_especial()           .setup(pipe(ZEROTRIM, DATE, DQUOTE))
    .builder();
  }

  public static Builder empresaLayout4SQL() {
    return new FileLayout.Builder().empresa()
      .cnpj()                             .setup(SQUOTE)
      .identificador_matriz_ou_filial()   .setup(pipe(new MatrizFilialTransformer(), SQUOTE))
      .razao_social_ou_nome_empresarial() .setup(SQUOTE)
      .nome_fantasia()                    .setup(SQUOTE)
      .situacao_cadastral()               .setup(pipe(new SituacaoCadastralTransformer(), SQUOTE))
      .data_situacao_cadastral()          .setup(pipe(ZEROTRIM, DATE, SQUOTE))
      .motivo_situacao_cadastral()        .setup(pipe(new MotivoSituacaoCadastralTransformer(), SQUOTE))
      .nm_cidade_exterior()               .setup(SQUOTE)
      .co_pais()                          .setup(SQUOTE)
      .nm_pais()                          .setup(SQUOTE)
      .codigo_natureza_juridica()         .setup(SQUOTE)
      .data_inicio_atividade()            .setup(pipe(ZEROTRIM, DATE, SQUOTE))
      .cnae_fiscal()                      .setup(LONG)
      .descricao_tipo_logradouro()        .setup(SQUOTE)
      .logradouro()                       .setup(SQUOTE)
      .numero()                           .setup(SQUOTE)
      .complemento()                      .setup(SQUOTE)
      .bairro()                           .setup(SQUOTE)
      .cep()                              .setup(pipe(new CepTransformer(), SQUOTE))
      .uf()                               .setup(SQUOTE)
      .codigo_municipio()                 .setup(SQUOTE)
      .municipio()                        .setup(SQUOTE)
      .ddd_telefone_1()                   .setup(SQUOTE)
      .ddd_telefone_2()                   .setup(SQUOTE)
      .ddd_fax()                          .setup(SQUOTE)
      .correio_eletronico()               .setup(pipe(LOWERCASE, SQUOTE))
      .qualificacao_do_responsavel()      .setup(pipe(new QualificacaoResponsavelTransformer(), SQUOTE))
      .capital_social_da_empresa()        .setup(pipe(ZEROTRIM, SQUOTE))
      .porte_empresa()                    .setup(pipe(new PortEmpresaTransformer(), SQUOTE))
      .opcao_pelo_simples()               .setup(pipe(new OpcaoSimplesTransformer(), SQUOTE))
      .data_opcao_pelo_simples()          .setup(pipe(ZEROTRIM, DATE, SQUOTE))
      .data_exclusao_do_simples()         .setup(pipe(ZEROTRIM, DATE, SQUOTE))
      .opcao_pelo_mei()                   .setup(pipe(new OpcaoMeiTransformer(), BOOL))
      .situacao_especial()                .setup(SQUOTE)
      .data_situacao_especial()           .setup(pipe(ZEROTRIM, DATE, SQUOTE))
    .builder();
  }

  public static Builder cnaiLayout4CSV() {
    return new FileLayout.Builder().cnae()
      .cnpj()                 .setup()
      .cnae_secundaria()      .setup()
    .builder();
  }

  public static Builder cnaeLayout4JSON() {
    return new FileLayout.Builder().cnae()
      .cnpj()                 .setup(DQUOTE)
      .cnae_secundaria()      .setup(DQUOTE)
    .builder();
  }

  public static Builder cnaeLayout4SQL() {
    return new FileLayout.Builder().cnae()
      .cnpj()                 .setup(SQUOTE)
      .cnae_secundaria()      .setup(SQUOTE)
    .builder();
   }
}
