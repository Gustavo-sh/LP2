import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import lab5.*;

public class SAGATeste {

	private SAGA sg;
	private ClienteController cc;
	private FornecedorController fc;
	
	@BeforeEach
	public void initSAGA() {
		this.sg = new SAGA();
		this.cc = new ClienteController();
		this.fc = new FornecedorController();
	}
	
	@Test
	public void testaExibeClientes() {
		this.cc.adicionaCliente("12345678911", "G", "E", "sla");
		this.cc.adicionaCliente("12345678988", "A", "E", "sla");
		this.cc.adicionaCliente("12345678999", "H", "E", "sla");
		assertEquals("A - sla - E\nG - sla - E\nH - sla - E", this.sg.exibeClientes(cc));
		this.cc.removeCliente("12345678911");
		assertEquals("A - sla - E\nH - sla - E", this.sg.exibeClientes(cc));
	}
	
	@Test
	public void testaExibeFornecedores() {
		this.fc.adicionaFornecedor("g", "sla", "sla");
		this.fc.adicionaFornecedor("au", "sla", "sla");
		this.fc.adicionaFornecedor("hug", "sla", "sla");
		assertEquals("au - sla - sla\ng - sla - sla\nhug - sla - sla", this.sg.exibeFornecedores(fc));
		this.fc.adicionaFornecedor("fug", "sla", "sla");
		this.fc.removeFornecedor("g");
		assertEquals("au - sla - sla\nfug - sla - sla\nhug - sla - sla", this.sg.exibeFornecedores(fc));
	}
	
	@Test
	public void testaExibeProdutos() {
		this.fc.adicionaFornecedor("g", "sla", "sla");
		this.fc.adicionaFornecedor("a", "sla", "sla");
		this.fc.removeFornecedor("a");
		this.fc.adicionaFornecedor("h", "sla", "sla");
		this.fc.adicionaProduto("g", "ban", "sla", 3);
		this.fc.adicionaProduto("h", "ban", "sla", 1);
		assertEquals("g - ban - sla - R$3.0\nh - ban - sla - R$1.0", this.sg.exibeProdutos(fc));
		this.fc.adicionaProduto("g", "lan", "la", 2);
		assertEquals("g - lan - la - R$2.0\ng - ban - sla - R$3.0\nh - ban - sla - R$1.0", this.sg.exibeProdutos(fc));
	}
}
