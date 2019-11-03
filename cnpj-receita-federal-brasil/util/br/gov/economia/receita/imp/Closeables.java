package br.gov.economia.receita.imp;

import java.io.Closeable;
import java.io.IOException;

public final class Closeables {
	private Closeables() {}
	
	public static void closeQuietly(Closeable c) {
		try {
			c.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
