import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import lab5.*;

public class ClienteControllerTeste {

	private ClienteController cc;
	
	@BeforeEach
	public void initClienteController() {
		this.cc = new ClienteController();
	}
	
	@Test
	public void testaAdcionaCliente() {
		try {
			this.cc.adicionaCliente("", "g", "g", "gg");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro no cadastro do cliente: cpf nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.cc.adicionaCliente(null, "g", "g", "gg");
		} catch (NullPointerException nexc) {
			assertEquals("Erro no cadastro do cliente: cpf nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.cc.adicionaCliente("1234", "g", "g", "gg");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro no cadastro do cliente: cpf invalido.", nexc.getMessage());
		}
		this.cc.adicionaCliente("11122233344", "g", "g", "gg");
		try {
			this.cc.adicionaCliente("11122233344", "", "g", "gg");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro no cadastro do cliente: nome nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.cc.adicionaCliente("11122233344", null, "g", "gg");
		} catch (NullPointerException nexc) {
			assertEquals("Erro no cadastro do cliente: nome nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.cc.adicionaCliente("11122233344", "guga", "", "gg");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro no cadastro do cliente: email nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.cc.adicionaCliente("11122233344", "guga", null, "gg");
		} catch (NullPointerException nexc) {
			assertEquals("Erro no cadastro do cliente: email nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.cc.adicionaCliente("11122233344", "guga", "guga.com", "");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro no cadastro do cliente: localizacao nao pode ser vazia ou nula.", nexc.getMessage());
		}
		try {
			this.cc.adicionaCliente("11122233344", "guga", "guga.com", null);
		} catch (NullPointerException nexc) {
			assertEquals("Erro no cadastro do cliente: localizacao nao pode ser vazia ou nula.", nexc.getMessage());
		}
		try {
			this.cc.adicionaCliente("11122233344", "guga", "guga.com", "dgergwe");
		} catch (RuntimeException nexc) {
			assertEquals("Erro no cadastro do cliente: cliente ja existe.", nexc.getMessage());
		}
		try {
			this.cc.adicionaCliente("1112223334", "guga", "guga.com", "wgergetq");
		} catch (RuntimeException nexc) {
			assertEquals("Erro no cadastro do cliente: cpf invalido.", nexc.getMessage());
		}
	}
	
	@Test
	public void testaExibeCliente() {
		this.cc.adicionaCliente("11122233344", "g", "g", "gg");
		try {
			this.cc.exibeCliente("");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na exibicao do cliente: cpf nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.cc.exibeCliente(null);
		} catch (NullPointerException nexc) {
			assertEquals("Erro na exibicao do cliente: cpf nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.cc.exibeCliente("11122233345");
		} catch (RuntimeException nexc) {
			assertEquals("Erro na exibicao do cliente: cliente nao existe.", nexc.getMessage());
		}
		assertEquals("g - gg - g", this.cc.exibeCliente("11122233344"));
	}
	
	@Test
	public void testaEditaCliente() {
		this.cc.adicionaCliente("11122233344", "g", "g", "gg");
		assertEquals("g", this.cc.pegaNome("11122233344"));
		assertEquals("g", this.cc.pegaEmail("11122233344"));
		assertEquals("gg", this.cc.pegaLocalizacao("11122233344"));
		try {
			this.cc.editaCliente("", "nome", "guga");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na edicao do cliente: cpf nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.cc.editaCliente("11122233344", "cpf", "guga");
		} catch (RuntimeException nexc) {
			assertEquals("Erro na edicao do cliente: cpf nao pode ser editado.", nexc.getMessage());
		}
		try {
			this.cc.editaCliente("11122233344", "descricao", "guga");
		} catch (RuntimeException nexc) {
			assertEquals("Erro na edicao do cliente: atributo nao existe.", nexc.getMessage());
		}
		try {
			this.cc.editaCliente("11122233345", "nome", "guga");
		} catch (RuntimeException nexc) {
			assertEquals("Erro na edicao do cliente: cliente nao existe.", nexc.getMessage());
		}
		try {
			this.cc.editaCliente(null, "nome", "guga");
		} catch (NullPointerException nexc) {
			assertEquals("Erro na edicao do cliente: cpf nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.cc.editaCliente("11122233344", "", "g");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na edicao do cliente: atributo nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.cc.editaCliente("11122233344", null, "g");
		} catch (NullPointerException nexc) {
			assertEquals("Erro na edicao do cliente: atributo nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.cc.editaCliente("11122233344", "nome", "");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.cc.editaCliente("11122233344", "nome", null);
		} catch (NullPointerException nexc) {
			assertEquals("Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		this.cc.editaCliente("11122233344", "nome", "guga");
		this.cc.editaCliente("11122233344", "email", "guga.com");
		this.cc.editaCliente("11122233344", "localizacao", "sla");
		assertEquals("guga", this.cc.pegaNome("11122233344"));
		assertEquals("guga.com", this.cc.pegaEmail("11122233344"));
		assertEquals("sla", this.cc.pegaLocalizacao("11122233344"));
	}
	
	@Test
	public void testaRemoveCLiente() {
		this.cc.adicionaCliente("11122233344", "g", "g", "gg");
		try {
			this.cc.removeCliente("");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na remocao do cliente: cpf nao pode ser vazio ou nulo", nexc.getMessage());
		}
		try {
			this.cc.removeCliente(null);
		} catch (NullPointerException nexc) {
			assertEquals("Erro na remocao do cliente: cpf nao pode ser vazio ou nulo", nexc.getMessage());
		}
		try {
			this.cc.removeCliente("11122233345");
		} catch (RuntimeException nexc) {
			assertEquals("Erro na remocao do cliente: cliente nao existe.", nexc.getMessage());
		}
		assertEquals("g - gg - g", this.cc.exibeCliente("11122233344"));
		this.cc.removeCliente("11122233344");
		try {
			this.cc.exibeCliente("11122233344");
		} catch (RuntimeException nexc) {
			assertEquals("Erro na exibicao do cliente: cliente nao existe.", nexc.getMessage());
		}
	}
}