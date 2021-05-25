import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import lab5.*;

public class US5US6Teste {
	
	private ClienteController cc;
	private FornecedorController fc;

	@BeforeEach
	public void initFornecedorController() {
		this.fc = new FornecedorController();
		this.cc = new ClienteController();
	}
	
	@Test
	public void testaCompras() {
		this.cc.adicionaCliente("11122233344", "dsan", "sla", "tbm n sei");
		this.fc.adicionaFornecedor("guga", "sla", "999");
		this.fc.adicionaFornecedor("sla", ".com", "40028922");
		this.fc.adicionaProduto("guga", "potato", "uma potatinha", 3.0);
		this.fc.adicionaProduto("sla", "potato", "uma potatinha", 3.0);
		this.fc.adicionaProduto("sla", "potata", "um potatinho", 2.0);
		this.fc.adicionaCompra("11122233344", "guga", "11/11/2020", "potato", "uma potatinha", "dsan");
		assertEquals(3.0, this.fc.getDebito("11122233344", "guga"));
		assertEquals("Cliente: dsan\nguga\npotato - 11/11/2020", this.fc.exibeContas("11122233344", "guga"));
		this.fc.adicionaCompra("11122233344", "sla", "11/11/2020", "potata", "um potatinho", "dsan");
		this.fc.adicionaCompra("11122233344", "sla", "11/11/2020", "potato", "uma potatinha", "dsan");
		assertEquals("Cliente: dsan\nguga\npotato - 11/11/2020\nsla\npotata - 11/11/2020\npotato - 11/11/2020", this.fc.exibeConta("11122233344", "dsan"));
		this.cc.adicionaCliente("12345678900", "algo", "algo", "algo");
		this.cc.adicionaCliente("12345678901", "lagoaki", "lagoaki", "algoaki");
		this.fc.adicionaCompra("12345678900", "sla", "12/11/2020", "potato", "uma potatinha", "algo");
		this.fc.adicionaCompra("12345678901", "sla", "12/11/2020", "potata", "um potatinho", "lagoaki");
		this.fc.ordenaPor("Cliente");
		assertEquals("algo, sla, potato, 12/11/2020\ndsan, guga, potato, 11/11/2020\ndsan, sla, potata, 11/11/2020\ndsan, sla, potato, 11/11/2020\nlagoaki, sla, potata, 12/11/2020", this.fc.listarCompras());
		this.fc.adicionaCompra("12345678901", "sla", "12/11/2020", "potato", "uma potatinha", "lagoaki");
		assertEquals("algo, sla, potato, 12/11/2020\ndsan, guga, potato, 11/11/2020\ndsan, sla, potata, 11/11/2020\ndsan, sla, potato, 11/11/2020\nlagoaki, sla, potata, 12/11/2020\nlagoaki, sla, potato, 12/11/2020", this.fc.listarCompras());
		this.fc.ordenaPor("Fornecedor");
		assertEquals("guga, dsan, potato, 11/11/2020\nsla, lagoaki, potata, 12/11/2020\nsla, lagoaki, potato, 12/11/2020\nsla, dsan, potata, 11/11/2020\nsla, dsan, potato, 11/11/2020\nsla, algo, potato, 12/11/2020", this.fc.listarCompras());
	}
}