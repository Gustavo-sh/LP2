package lab5;

import java.util.Comparator;

public class OrdenaComprasPorCliente implements Comparator<Conta>{

	@Override
	public int compare(Conta c1, Conta c2) {
		return c1.getCliente().compareTo(c2.getCliente());
	}
}
