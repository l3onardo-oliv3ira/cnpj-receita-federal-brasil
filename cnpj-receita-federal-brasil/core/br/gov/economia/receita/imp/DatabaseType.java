package br.gov.economia.receita.imp;

import br.gov.economia.receita.IFieldType;

public enum DatabaseType implements IFieldType {
  UNDEFINED,
  BIGINT,
  BLOB,
  BOOLEAN,
  CHAR,
  DATE,
  DATETIME,
  DECIMAL,
  DOUBLE,
  INTEGER,
  INT,
  NONE,
  NUMERIC,
  REAL,
  STRING,
  TEXT,
  TIME,
  VARCHAR;

  @Override
  public String toDdl() {
    return name();
  }
}
