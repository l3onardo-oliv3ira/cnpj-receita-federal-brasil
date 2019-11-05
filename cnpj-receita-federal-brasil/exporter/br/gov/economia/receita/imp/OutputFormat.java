package br.gov.economia.receita.imp;

import java.io.File;
import java.io.IOException;

import br.gov.economia.receita.imp.FileLayout.Builder;
import br.gov.economia.receita.imp.adapter.CsvVisitorAdapter;
import br.gov.economia.receita.imp.adapter.JsonVisitorAdapter;
import br.gov.economia.receita.imp.adapter.SqlVisitorAdapter;

public enum OutputFormat {
  CSV {
    @Override
    IVisitorAdapter getAdapter(File output, long max, Object... payload) throws IOException  {
      return new CsvVisitorAdapter(output, max);
    }

    @Override
    protected Builder empresaLayout() {
      return LayoutProvider.empresaLayout4CSV();
    }

    @Override
    protected Builder socioLayout() {
      return LayoutProvider.socioLayout4CSV();
    }

    @Override
    protected Builder cnaeLayout() {
      return LayoutProvider.cnaiLayout4CSV();
    }
  },
  JSON {
    @Override
    IVisitorAdapter getAdapter(File output, long max, Object... payload) throws IOException  {
      return new JsonVisitorAdapter(output, max);
    }

    @Override
    protected Builder empresaLayout() {
      return LayoutProvider.empresaLayout4JSON();
    }

    @Override
    protected Builder socioLayout() {
      return LayoutProvider.socioLayout4JSON();
    }

    @Override
    protected Builder cnaeLayout() {
      return LayoutProvider.cnaeLayout4JSON();
    }
  },
  SQL {
    @Override
    IVisitorAdapter getAdapter(File output, long max, Object... payload) throws IOException {
      return new SqlVisitorAdapter(output, max, payload[0].toString());
    }

    @Override
    protected Builder empresaLayout() {
      return LayoutProvider.empresaLayout4SQL();
    }

    @Override
    protected Builder socioLayout() {
      return LayoutProvider.socioLayout4SQL();
    }

    @Override
    protected Builder cnaeLayout() {
      return LayoutProvider.cnaeLayout4SQL();
    }
  };

  abstract IVisitorAdapter getAdapter(File output, long max, Object... payload) throws IOException ;

  protected abstract Builder empresaLayout();
  protected abstract Builder socioLayout();
  protected abstract Builder cnaeLayout();
}
