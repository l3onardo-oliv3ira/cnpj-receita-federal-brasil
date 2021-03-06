package br.gov.economia.receita.imp;

import java.util.HashMap;
import java.util.Map;

import br.gov.economia.receita.imp.standard.AbstractTransformer;

public class QualificacaoResponsavelTransformer extends AbstractTransformer{

  private static final Map<Integer, String> QUALIFICACAO = new HashMap<>();
  
  static {
    QUALIFICACAO.put(5," ADMINISTRADOR");
    QUALIFICACAO.put(10,"DIRETOR");
    QUALIFICACAO.put(11,"INTERVENTOR");
    QUALIFICACAO.put(12,"INVENTARIANTE");
    QUALIFICACAO.put(13,"LIQUIDANTE");
    QUALIFICACAO.put(16,"PRESIDENTE");
    QUALIFICACAO.put(17,"PROCURADOR");
    QUALIFICACAO.put(18,"SECRET�RIO");
    QUALIFICACAO.put(19,"S�NDICO (CONDOM�NIO)");
    QUALIFICACAO.put(23,"S�CIO CAPITALISTA");
    QUALIFICACAO.put(24,"S�CIO COMANDITADO");
    QUALIFICACAO.put(31,"S�CIO OSTENSIVO");
    QUALIFICACAO.put(32,"TABELI�O");
    QUALIFICACAO.put(33,"TESOUREIRO");
    QUALIFICACAO.put(34,"TITULAR DE EMPRESA INDIVIDUAL IMOBILI�RIA");
    QUALIFICACAO.put(39,"DIPLOMATA");
    QUALIFICACAO.put(40,"C�NSUL");
    QUALIFICACAO.put(41,"REPRESENTANTE DE ORGANIZA��O INTERNACIONAL");
    QUALIFICACAO.put(42,"OFICIAL DE REGISTRO");
    QUALIFICACAO.put(43,"RESPONS�VEL");
    QUALIFICACAO.put(46,"MINISTRO DE ESTADO DAS RELA��ES EXTERIORES");
    QUALIFICACAO.put(49,"S�CIO-ADMINISTRADOR");
    QUALIFICACAO.put(50,"EMPRES�RIO");
    QUALIFICACAO.put(51,"CANDIDATO A CARGO POL�TICO ELETIVO");
    QUALIFICACAO.put(54,"FUNDADOR");
    QUALIFICACAO.put(59,"PRODUTOR RURAL");
    QUALIFICACAO.put(60,"C�NSUL HONOR�RIO");
    QUALIFICACAO.put(61,"RESPONS�VEL IND�GENA");
    QUALIFICACAO.put(62,"REPRESENTANTE DA INSTITUI��O EXTRATERRITORIAL");
    QUALIFICACAO.put(64,"ADMINISTRADOR JUDICIAL");
    QUALIFICACAO.put(65,"TITULAR PESSOA F�SICA RESIDENTE OU DOMICILIADO NO BRASIL");
    QUALIFICACAO.put(77,"VICE-PRESIDENTE");
  }

  @Override
  protected String doTransform(String input) {
    return QUALIFICACAO.getOrDefault(Strings.toInt(input, -1), input);
  }
}
