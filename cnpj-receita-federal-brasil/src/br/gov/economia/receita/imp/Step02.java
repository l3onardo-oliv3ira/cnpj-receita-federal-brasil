package br.gov.economia.receita.imp;

import static br.gov.economia.receita.imp.standard.Transformers.BOOL;
import static br.gov.economia.receita.imp.standard.Transformers.DATE;
import static br.gov.economia.receita.imp.standard.Transformers.DQUOTE;
import static br.gov.economia.receita.imp.standard.Transformers.LONG;
import static br.gov.economia.receita.imp.standard.Transformers.LOWERCASE;
import static br.gov.economia.receita.imp.standard.Transformers.ZEROTRIM;
import static br.gov.economia.receita.imp.standard.Transformers.pipe;

import java.io.File;

public final class Step02 {
	
	private Step02() {}
	
	public static void main(String[] args) {
		
		File input = new File("./input", "K3241.K03200DV.D90805.L00001");
		
		FileLayout layout =  new FileLayout.Builder()
			.empresa()
					.tipo_de_registro()									.setup(LONG)
					.indicador_full_diario()						.setup(DQUOTE)
					.tipo_atualizacao()									.setup(DQUOTE)
					.cnpj()															.setup(LONG)
					.identificador_matriz_ou_filial()		.setup(pipe(new MatrizFilialTransformer(), DQUOTE))
					.razao_social_ou_nome_empresarial()	.setup(DQUOTE)
					.nome_fantasia()										.setup(DQUOTE)
					.situacao_cadastral()								.setup(pipe(new SituacaoCadastralTransformer(), DQUOTE))
					.data_situacao_cadastral()					.setup(pipe(ZEROTRIM, DATE, DQUOTE))
					.motivo_situacao_cadastral()				.setup(DQUOTE)
					.nm_cidade_exterior()								.setup(DQUOTE)
					.co_pais()													.setup(DQUOTE)
					.nm_pais()													.setup(DQUOTE)
					.codigo_natureza_juridica()					.setup(LONG)
					.data_inicio_atividade()						.setup(pipe(ZEROTRIM, DATE, DQUOTE))
					.cnae_fiscal()											.setup(LONG)
					.descricao_tipo_logradouro()				.setup(DQUOTE)
					.logradouro()												.setup(DQUOTE)
					.numero()														.setup(DQUOTE)
					.complemento()											.setup(DQUOTE)
					.bairro()														.setup(DQUOTE)
					.cep()															.setup(pipe(new CepTransformer(), DQUOTE))
					.uf()																.setup(DQUOTE)
					.codigo_municipio()									.setup(DQUOTE)
					.municipio()												.setup(DQUOTE)
					.ddd_telefone_1()										.setup(DQUOTE)
					.ddd_telefone_2()										.setup(DQUOTE)
					.ddd_fax()													.setup(DQUOTE)
					.correio_eletronico()								.setup(pipe(LOWERCASE, DQUOTE))
					.qualificacao_do_responsavel()			.setup(DQUOTE)
					.capital_social_da_empresa()				.setup(pipe(ZEROTRIM, DQUOTE))
					.porte_empresa()										.setup(pipe(new PortEmpresaTransformer(), DQUOTE))
					.opcao_pelo_simples()								.setup(pipe(new OpcaoSimplesTransformer(), DQUOTE))
					.data_opcao_pelo_simples()					.setup(pipe(ZEROTRIM, DATE, DQUOTE))
					.data_exclusao_do_simples()					.setup(pipe(ZEROTRIM, DATE, DQUOTE))
					.opcao_pelo_mei()										.setup(pipe(new OpcaoMeiTransformer(), BOOL))
					.situacao_especial()								.setup(DQUOTE)
					.data_situacao_especial()						.setup(pipe(ZEROTRIM, DATE, DQUOTE))
					.filler()														.setup(pipe(ZEROTRIM, DQUOTE))
					.fim_de_registro()									.setup(DQUOTE)
			.builder().build(input);
			layout.run();
	}
}
