package br.gov.economia.receita;

import java.util.List;

public interface IField {
  public String getName();
  public String getValue();
  public boolean isMultivalued();
  public List<String> getValues();
}