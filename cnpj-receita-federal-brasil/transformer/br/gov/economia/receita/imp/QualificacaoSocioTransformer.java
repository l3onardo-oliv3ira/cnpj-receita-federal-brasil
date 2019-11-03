package br.gov.economia.receita.imp;

import java.util.HashMap;
import java.util.Map;

import br.gov.economia.receita.imp.standard.AbstractTransformer;

public class QualificacaoSocioTransformer extends AbstractTransformer {

  private static final Map<Integer, String> QUALIFICACAO = new HashMap<>();
  
  static {
    QUALIFICACAO.put(5,"Administrador");
    QUALIFICACAO.put(8,"Conselheiro de Administra��o");
    QUALIFICACAO.put(10,"Diretor");
    QUALIFICACAO.put(16,"Presidente");
    QUALIFICACAO.put(17,"Procurador");
    QUALIFICACAO.put(20,"Sociedade Consorciada");
    QUALIFICACAO.put(21,"Sociedade Filiada");
    QUALIFICACAO.put(22,"S�cio");
    QUALIFICACAO.put(23,"S�cio Capitalista");
    QUALIFICACAO.put(24,"S�cio Comanditado");
    QUALIFICACAO.put(25,"S�cio Comandit�rio");
    QUALIFICACAO.put(26,"S�cio de Ind�stria");
    QUALIFICACAO.put(28,"S�cio-Gerente");
    QUALIFICACAO.put(29,"S�cio Incapaz ou Relat.Incapaz (exceto menor)");
    QUALIFICACAO.put(30,"S�cio Menor (Assistido/Representado)");
    QUALIFICACAO.put(31,"S�cio Ostensivo");
    QUALIFICACAO.put(37,"S�cio Pessoa Jur�dica Domiciliado no Exterior");
    QUALIFICACAO.put(38,"S�cio Pessoa F�sica Residente no Exterior");
    QUALIFICACAO.put(47,"S�cio Pessoa F�sica Residente no Brasil");
    QUALIFICACAO.put(48,"S�cio Pessoa Jur�dica Domiciliado no Brasil");
    QUALIFICACAO.put(49,"S�cio-Administrador");
    QUALIFICACAO.put(52,"S�cio com Capital");
    QUALIFICACAO.put(53,"S�cio sem Capital");
    QUALIFICACAO.put(54,"Fundador");
    QUALIFICACAO.put(55,"S�cio Comanditado Residente no Exterior");
    QUALIFICACAO.put(56,"S�cio Comandit�rio Pessoa F�sica Residente no Exterior");
    QUALIFICACAO.put(57,"S�cio Comandit�rio Pessoa Jur�dica Domiciliado no Exterior");
    QUALIFICACAO.put(58,"S�cio Comandit�rio Incapaz");
    QUALIFICACAO.put(59,"Produtor Rural");
    QUALIFICACAO.put(63,"Cotas em Tesouraria");
    QUALIFICACAO.put(65,"Titular Pessoa F�sica Residente ou Domiciliado no Brasil");
    QUALIFICACAO.put(66,"Titular Pessoa F�sica Residente ou Domiciliado no Exterior");
    QUALIFICACAO.put(67,"Titular Pessoa F�sica Incapaz ou Relativamente Incapaz (exceto menor)");
    QUALIFICACAO.put(68,"Titular Pessoa F�sica Menor (Assistido/Representado)");
    QUALIFICACAO.put(70,"Administrador Residente ou Domiciliado no Exterior");
    QUALIFICACAO.put(71,"Conselheiro de Administra��o Residente ou Domiciliado no Exterior");
    QUALIFICACAO.put(72,"Diretor Residente ou Domiciliado no Exterior");
    QUALIFICACAO.put(73,"Presidente Residente ou Domiciliado no Exterior");
    QUALIFICACAO.put(74,"S�cio-Administrador Residente ou Domiciliado no Exterior");
    QUALIFICACAO.put(75,"Fundador Residente ou Domiciliado no Exterior");
  }
  @Override
  protected String doTransform(String input) {
    return QUALIFICACAO.getOrDefault(Strings.toInt(input, -1), input);
  }

}
