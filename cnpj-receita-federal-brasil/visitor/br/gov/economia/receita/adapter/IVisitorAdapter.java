package br.gov.economia.receita.adapter;

import br.gov.economia.receita.IField;

public interface IVisitorAdapter {
  public void start();
  public void end();
  public void beginData(long row);
  public void data(long row, IField field);
  public void endData();
  public long getTotal();
}
