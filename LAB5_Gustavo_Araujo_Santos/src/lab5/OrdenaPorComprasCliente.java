package lab5;

import java.util.Comparator;

public class OrdenaPorComprasCliente implements Comparator<Compra>{

	@Override
	public int compare(Compra c1, Compra c2) {
		return (c1.getFornecedor() + c1.getDescricao() + c1.getData()).compareTo(c2.getFornecedor() + c2.getDescricao() + c2.getData());
	}
}
