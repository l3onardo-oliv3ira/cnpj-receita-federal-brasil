package br.gov.economia.receita.imp;

import static br.gov.economia.receita.imp.standard.Transformers.T_BOOL;
import static br.gov.economia.receita.imp.standard.Transformers.T_DATE;
import static br.gov.economia.receita.imp.standard.Transformers.T_DQUOTE;
import static br.gov.economia.receita.imp.standard.Transformers.T_LONG;
import static br.gov.economia.receita.imp.standard.Transformers.T_LOWERCASE;
import static br.gov.economia.receita.imp.standard.Transformers.T_SQUOTE;
import static br.gov.economia.receita.imp.standard.Transformers.T_ZTRIM;
import static br.gov.economia.receita.imp.standard.Transformers.pipe;

import br.gov.economia.receita.imp.FileLayout.Builder;
import br.gov.economia.receita.imp.standard.DefaultIfEmpty;

public class LayoutProvider {
  
  private LayoutProvider() {}
  
  public static Builder socioLayout4CSV() {
    return new FileLayout.Builder().socio()
      .cnpj()                            .setup(T_LONG)
      .identificador_de_socio()          .setup("identificador"      , T_LONG)
      .nome_socio_pf_ou_razao_social_pj().setup("razao_social")
      .cnpj_ou_cpf_do_sócio()            .setup("cpf_cnpj"           , pipe(new CpfInvalidoTransformer()))
      .codigo_qualificacao_socio()       .setup("qualificacao"       , pipe(new QualificacaoSocioTransformer()))
      .percentual_capital_social()       .setup("percentual"         , T_LONG)
      .data_entrada_sociedade()          .setup("entrada"            , pipe(T_ZTRIM, T_DATE), DatabaseType.DATE)
      .codigo_pais()                     .setup("pais"               , T_LONG)
      .nome_pais_socio()                 .setup("nome_pais")
      .cpf_representante_legal()         .setup("cpf_representante"  , pipe(new CpfInvalidoTransformer(), new DefaultIfEmpty("indefinido")))
      .nome_representante()              .setup("nome_representante")
      .codigo_qualificacao_representante_legal().setup("cod_repre"   , pipe(new QualificacaoRepresentanteLegalTransformer()))
    .builder();
  }

  public static Builder socioLayout4JSON() {
    return new FileLayout.Builder().socio()
      .cnpj()                            .setup(T_LONG)
      .identificador_de_socio()          .setup("identificador"      , T_LONG)
      .nome_socio_pf_ou_razao_social_pj().setup("razao_social"       , T_DQUOTE)
      .cnpj_ou_cpf_do_sócio()            .setup("cpf_cnpj"           , pipe(new CpfInvalidoTransformer(), T_DQUOTE))
      .codigo_qualificacao_socio()       .setup("qualificacao"       , pipe(new QualificacaoSocioTransformer(), T_DQUOTE))
      .percentual_capital_social()       .setup("percentual"         , T_LONG)
      .data_entrada_sociedade()          .setup("entrada"            , pipe(T_ZTRIM, T_DATE, T_DQUOTE), DatabaseType.DATE)
      .codigo_pais()                     .setup("pais"               , T_LONG)
      .nome_pais_socio()                 .setup("nome_pais"          , T_DQUOTE)
      .cpf_representante_legal()         .setup("cpf_representante"  , pipe(new CpfInvalidoTransformer(), new DefaultIfEmpty("indefinido"), T_DQUOTE))
      .nome_representante()              .setup("nome_representante" , T_DQUOTE)
      .codigo_qualificacao_representante_legal().setup("cod_repre"   , pipe(new QualificacaoRepresentanteLegalTransformer(), T_DQUOTE))
    .builder();
  }

  public static Builder socioLayout4SQL() {
    return new FileLayout.Builder().socio()
      .cnpj()                            .setup(T_LONG)
      .identificador_de_socio()          .setup("identificador"      , T_LONG)
      .nome_socio_pf_ou_razao_social_pj().setup("razao_social"       , T_SQUOTE)
      .cnpj_ou_cpf_do_sócio()            .setup("cpf_cnpj"           , pipe(new CpfInvalidoTransformer(), T_SQUOTE))
      .codigo_qualificacao_socio()       .setup("qualificacao"       , pipe(new QualificacaoSocioTransformer(), T_SQUOTE))
      .percentual_capital_social()       .setup("percentual"         , T_LONG)
      .data_entrada_sociedade()          .setup("entrada"            , pipe(T_ZTRIM, T_DATE, T_SQUOTE), DatabaseType.DATE)
      .codigo_pais()                     .setup("pais"               , T_LONG)
      .nome_pais_socio()                 .setup("nome_pais"          , T_SQUOTE)
      .cpf_representante_legal()         .setup("cpf_representante"  , pipe(new CpfInvalidoTransformer(), new DefaultIfEmpty("indefinido"), T_SQUOTE))
      .nome_representante()              .setup("nome_representante" , T_SQUOTE)
      .codigo_qualificacao_representante_legal().setup("cod_repre"   , pipe(new QualificacaoRepresentanteLegalTransformer(), T_SQUOTE))
    .builder();
  }

  public static FileLayout.Builder empresaLayout4CSV() {
    return new FileLayout.Builder().empresa()
      .cnpj()                             .setup()
      .identificador_matriz_ou_filial()   .setup(pipe(new MatrizFilialTransformer()))
      .razao_social_ou_nome_empresarial() .setup()
      .nome_fantasia()                    .setup()
      .situacao_cadastral()               .setup(pipe(new SituacaoCadastralTransformer()))
      .data_situacao_cadastral()          .setup(pipe(T_ZTRIM, T_DATE), DatabaseType.DATE)
      .motivo_situacao_cadastral()        .setup(pipe(new MotivoSituacaoCadastralTransformer()))
      .nm_cidade_exterior()               .setup()
      .co_pais()                          .setup()
      .nm_pais()                          .setup()
      .codigo_natureza_juridica()         .setup()
      .data_inicio_atividade()            .setup(pipe(T_ZTRIM, T_DATE), DatabaseType.DATE)
      .cnae_fiscal()                      .setup(T_LONG)
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
      .correio_eletronico()               .setup(pipe(T_LOWERCASE))
      .qualificacao_do_responsavel()      .setup(pipe(new QualificacaoResponsavelTransformer()))
      .capital_social_da_empresa()        .setup(pipe(T_ZTRIM))
      .porte_empresa()                    .setup(pipe(new PortEmpresaTransformer()))
      .opcao_pelo_simples()               .setup(pipe(new OpcaoSimplesTransformer()))
      .data_opcao_pelo_simples()          .setup(pipe(T_ZTRIM, T_DATE), DatabaseType.DATE)
      .data_exclusao_do_simples()         .setup(pipe(T_ZTRIM, T_DATE), DatabaseType.DATE)
      .opcao_pelo_mei()                   .setup(pipe(new OpcaoMeiTransformer(), T_BOOL), DatabaseType.BOOLEAN)
      .situacao_especial()                .setup()
      .data_situacao_especial()           .setup(pipe(T_ZTRIM, T_DATE), DatabaseType.DATE)
    .builder();
  }

  public static Builder empresaLayout4JSON() {
    return new FileLayout.Builder().empresa()
      .cnpj()                             .setup(T_DQUOTE)
      .identificador_matriz_ou_filial()   .setup(pipe(new MatrizFilialTransformer(), T_DQUOTE))
      .razao_social_ou_nome_empresarial() .setup(T_DQUOTE)
      .nome_fantasia()                    .setup(T_DQUOTE)
      .situacao_cadastral()               .setup(pipe(new SituacaoCadastralTransformer(), T_DQUOTE))
      .data_situacao_cadastral()          .setup(pipe(T_ZTRIM, T_DATE, T_DQUOTE), DatabaseType.DATE)
      .motivo_situacao_cadastral()        .setup(pipe(new MotivoSituacaoCadastralTransformer(), T_DQUOTE))
      .nm_cidade_exterior()               .setup(T_DQUOTE)
      .co_pais()                          .setup(T_DQUOTE)
      .nm_pais()                          .setup(T_DQUOTE)
      .codigo_natureza_juridica()         .setup(T_DQUOTE)
      .data_inicio_atividade()            .setup(pipe(T_ZTRIM, T_DATE, T_DQUOTE), DatabaseType.DATE)
      .cnae_fiscal()                      .setup(T_LONG)
      .descricao_tipo_logradouro()        .setup(T_DQUOTE)
      .logradouro()                       .setup(T_DQUOTE)
      .numero()                           .setup(T_DQUOTE)
      .complemento()                      .setup(T_DQUOTE)
      .bairro()                           .setup(T_DQUOTE)
      .cep()                              .setup(pipe(new CepTransformer(), T_DQUOTE))
      .uf()                               .setup(T_DQUOTE)
      .codigo_municipio()                 .setup(T_DQUOTE)
      .municipio()                        .setup(T_DQUOTE)
      .ddd_telefone_1()                   .setup(T_DQUOTE)
      .ddd_telefone_2()                   .setup(T_DQUOTE)
      .ddd_fax()                          .setup(T_DQUOTE)
      .correio_eletronico()               .setup(pipe(T_LOWERCASE, T_DQUOTE))
      .qualificacao_do_responsavel()      .setup(pipe(new QualificacaoResponsavelTransformer(), T_DQUOTE))
      .capital_social_da_empresa()        .setup(pipe(T_ZTRIM, T_DQUOTE))
      .porte_empresa()                    .setup(pipe(new PortEmpresaTransformer(), T_DQUOTE))
      .opcao_pelo_simples()               .setup(pipe(new OpcaoSimplesTransformer(), T_DQUOTE))
      .data_opcao_pelo_simples()          .setup(pipe(T_ZTRIM, T_DATE, T_DQUOTE), DatabaseType.DATE)
      .data_exclusao_do_simples()         .setup(pipe(T_ZTRIM, T_DATE, T_DQUOTE), DatabaseType.DATE)
      .opcao_pelo_mei()                   .setup(pipe(new OpcaoMeiTransformer(), T_BOOL), DatabaseType.BOOLEAN)
      .situacao_especial()                .setup(T_DQUOTE)
      .data_situacao_especial()           .setup(pipe(T_ZTRIM, T_DATE, T_DQUOTE), DatabaseType.DATE)
    .builder();
  }

  public static Builder empresaLayout4SQL() {
    return new FileLayout.Builder().empresa()
      .cnpj()                             .setup(T_SQUOTE)
      .identificador_matriz_ou_filial()   .setup(pipe(new MatrizFilialTransformer(), T_SQUOTE))
      .razao_social_ou_nome_empresarial() .setup(T_SQUOTE)
      .nome_fantasia()                    .setup(T_SQUOTE)
      .situacao_cadastral()               .setup(pipe(new SituacaoCadastralTransformer(), T_SQUOTE))
      .data_situacao_cadastral()          .setup(pipe(T_ZTRIM, T_DATE, T_SQUOTE), DatabaseType.DATE)
      .motivo_situacao_cadastral()        .setup(pipe(new MotivoSituacaoCadastralTransformer(), T_SQUOTE))
      .nm_cidade_exterior()               .setup(T_SQUOTE)
      .co_pais()                          .setup(T_SQUOTE)
      .nm_pais()                          .setup(T_SQUOTE)
      .codigo_natureza_juridica()         .setup(T_SQUOTE)
      .data_inicio_atividade()            .setup(pipe(T_ZTRIM, T_DATE, T_SQUOTE), DatabaseType.DATE)
      .cnae_fiscal()                      .setup(T_LONG, DatabaseType.INTEGER)
      .descricao_tipo_logradouro()        .setup(T_SQUOTE)
      .logradouro()                       .setup(T_SQUOTE)
      .numero()                           .setup(T_SQUOTE)
      .complemento()                      .setup(T_SQUOTE)
      .bairro()                           .setup(T_SQUOTE)
      .cep()                              .setup(pipe(new CepTransformer(), T_SQUOTE))
      .uf()                               .setup(T_SQUOTE)
      .codigo_municipio()                 .setup(T_SQUOTE)
      .municipio()                        .setup(T_SQUOTE)
      .ddd_telefone_1()                   .setup(T_SQUOTE)
      .ddd_telefone_2()                   .setup(T_SQUOTE)
      .ddd_fax()                          .setup(T_SQUOTE)
      .correio_eletronico()               .setup(pipe(T_LOWERCASE, T_SQUOTE))
      .qualificacao_do_responsavel()      .setup(pipe(new QualificacaoResponsavelTransformer(), T_SQUOTE))
      .capital_social_da_empresa()        .setup(pipe(T_ZTRIM, T_SQUOTE))
      .porte_empresa()                    .setup(pipe(new PortEmpresaTransformer(), T_SQUOTE))
      .opcao_pelo_simples()               .setup(pipe(new OpcaoSimplesTransformer(), T_SQUOTE))
      .data_opcao_pelo_simples()          .setup(pipe(T_ZTRIM, T_DATE, T_SQUOTE), DatabaseType.DATE)
      .data_exclusao_do_simples()         .setup(pipe(T_ZTRIM, T_DATE, T_SQUOTE), DatabaseType.DATE)
      .opcao_pelo_mei()                   .setup(pipe(new OpcaoMeiTransformer(), T_BOOL), DatabaseType.BOOLEAN)
      .situacao_especial()                .setup(T_SQUOTE)
      .data_situacao_especial()           .setup(pipe(T_ZTRIM, T_DATE, T_SQUOTE), DatabaseType.DATE)
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
      .cnpj()                 .setup(T_DQUOTE)
      .cnae_secundaria()      .setup(T_DQUOTE)
    .builder();
  }

  public static Builder cnaeLayout4SQL() {
    return new FileLayout.Builder().cnae()
      .cnpj()                 .setup(T_SQUOTE)
      .cnae_secundaria()      .setup(T_SQUOTE)
    .builder();
   }
}
