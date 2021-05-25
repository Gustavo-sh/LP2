package lab5;

import java.util.Comparator;

public class OrdenaPorNomeCliente implements Comparator<Cliente>{

	@Override
	public int compare(Cliente c1, Cliente c2) {
		return (c1.getNome().compareTo(c2.getNome()));
	}
}
