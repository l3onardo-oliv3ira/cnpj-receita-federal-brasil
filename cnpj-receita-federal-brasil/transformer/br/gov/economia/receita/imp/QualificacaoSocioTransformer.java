package br.gov.economia.receita.imp;

import java.util.HashMap;
import java.util.Map;

import br.gov.economia.receita.imp.standard.AbstractTransformer;

public class QualificacaoSocioTransformer extends AbstractTransformer {

  private static final Map<Integer, String> QUALIFICACAO = new HashMap<>();
  
  static {
    QUALIFICACAO.put(5,"Administrador");
    QUALIFICACAO.put(8,"Conselheiro de Administração");
    QUALIFICACAO.put(10,"Diretor");
    QUALIFICACAO.put(16,"Presidente");
    QUALIFICACAO.put(17,"Procurador");
    QUALIFICACAO.put(20,"Sociedade Consorciada");
    QUALIFICACAO.put(21,"Sociedade Filiada");
    QUALIFICACAO.put(22,"Sócio");
    QUALIFICACAO.put(23,"Sócio Capitalista");
    QUALIFICACAO.put(24,"Sócio Comanditado");
    QUALIFICACAO.put(25,"Sócio Comanditário");
    QUALIFICACAO.put(26,"Sócio de Indústria");
    QUALIFICACAO.put(28,"Sócio-Gerente");
    QUALIFICACAO.put(29,"Sócio Incapaz ou Relat.Incapaz (exceto menor)");
    QUALIFICACAO.put(30,"Sócio Menor (Assistido/Representado)");
    QUALIFICACAO.put(31,"Sócio Ostensivo");
    QUALIFICACAO.put(37,"Sócio Pessoa Jurídica Domiciliado no Exterior");
    QUALIFICACAO.put(38,"Sócio Pessoa Física Residente no Exterior");
    QUALIFICACAO.put(47,"Sócio Pessoa Física Residente no Brasil");
    QUALIFICACAO.put(48,"Sócio Pessoa Jurídica Domiciliado no Brasil");
    QUALIFICACAO.put(49,"Sócio-Administrador");
    QUALIFICACAO.put(52,"Sócio com Capital");
    QUALIFICACAO.put(53,"Sócio sem Capital");
    QUALIFICACAO.put(54,"Fundador");
    QUALIFICACAO.put(55,"Sócio Comanditado Residente no Exterior");
    QUALIFICACAO.put(56,"Sócio Comanditário Pessoa Física Residente no Exterior");
    QUALIFICACAO.put(57,"Sócio Comanditário Pessoa Jurídica Domiciliado no Exterior");
    QUALIFICACAO.put(58,"Sócio Comanditário Incapaz");
    QUALIFICACAO.put(59,"Produtor Rural");
    QUALIFICACAO.put(63,"Cotas em Tesouraria");
    QUALIFICACAO.put(65,"Titular Pessoa Física Residente ou Domiciliado no Brasil");
    QUALIFICACAO.put(66,"Titular Pessoa Física Residente ou Domiciliado no Exterior");
    QUALIFICACAO.put(67,"Titular Pessoa Física Incapaz ou Relativamente Incapaz (exceto menor)");
    QUALIFICACAO.put(68,"Titular Pessoa Física Menor (Assistido/Representado)");
    QUALIFICACAO.put(70,"Administrador Residente ou Domiciliado no Exterior");
    QUALIFICACAO.put(71,"Conselheiro de Administração Residente ou Domiciliado no Exterior");
    QUALIFICACAO.put(72,"Diretor Residente ou Domiciliado no Exterior");
    QUALIFICACAO.put(73,"Presidente Residente ou Domiciliado no Exterior");
    QUALIFICACAO.put(74,"Sócio-Administrador Residente ou Domiciliado no Exterior");
    QUALIFICACAO.put(75,"Fundador Residente ou Domiciliado no Exterior");
  }
  @Override
  protected String doTransform(String input) {
    return QUALIFICACAO.getOrDefault(Strings.toInt(input, -1), input);
  }

}
