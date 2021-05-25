package lab5;

import java.util.Comparator;

public class OrdenaPorNomeFornecedor implements Comparator<Fornecedor>{

	@Override
	public int compare(Fornecedor f1, Fornecedor f2) {
		return f1.getNome().compareTo(f2.getNome());
	}
}
