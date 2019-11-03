# cnpj-receita-federal-brasil
Projeto capaz de ler o [layout](https://github.com/l3onardo-oliv3ira/cnpj-receita-federal-brasil/blob/master/cnpj-receita-federal-brasil/layout/LAYOUT_DADOS_ABERTOS_CNPJ.pdf) dos arquivos de dados do [Cadastro Nacional de Pessoas Jurídicas](http://idg.receita.fazenda.gov.br/orientacao/tributaria/cadastros/cadastro-nacional-de-pessoas-juridicas-cnpj/dados-publicos-cnpj), mantido e atualizado pela Secretaria da Receita Federal do Brasil e [exportar](https://github.com/l3onardo-oliv3ira/cnpj-receita-federal-brasil/tree/master/cnpj-receita-federal-brasil/exporter/br/gov/economia/receita/imp) para o formato que melhor se adequar ao seu projeto.

# Exportação/Importação
A importação/exportação dos dados pode ser customizada implementando sua própria [interface](https://github.com/l3onardo-oliv3ira/cnpj-receita-federal-brasil/tree/master/cnpj-receita-federal-brasil/visitor/br/gov/economia/receita) de saida de dados segundo  [Visitor Pattern](http://alumni.cs.ucr.edu/~lgao/teaching/visitor.html) ou derivações específicas das entidades [empresa](https://github.com/l3onardo-oliv3ira/cnpj-receita-federal-brasil/blob/master/cnpj-receita-federal-brasil/exporter/br/gov/economia/receita/imp/EmpresaJsonExporter.java), [sócio](https://github.com/l3onardo-oliv3ira/cnpj-receita-federal-brasil/blob/master/cnpj-receita-federal-brasil/exporter/br/gov/economia/receita/imp/SocioSqlExporter.java), etc. Implemente sua interface e contribua com este projeto enviando seu push request :-)
