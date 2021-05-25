import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import lab5.*;

public class FornecedorControllerTeste {

	private FornecedorController fc;
	
	@BeforeEach
	public void initFornecedorController() {
		this.fc = new FornecedorController();
	}
	
	@Test
	public void testaAdicionaFornecedor() {
		String nome = this.fc.adicionaFornecedor("g", "g", "gg");
		try {
			this.fc.adicionaFornecedor("g", "gg", "ggg");
		} catch (RuntimeException nexc) {
			assertEquals("Erro no cadastro do fornecedor: fornecedor ja existe.", nexc.getMessage());
		}
		try {
			this.fc.adicionaFornecedor("", "gg", "ggg");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.adicionaFornecedor(null, "gg", "ggg");
		} catch (NullPointerException nexc) {
			assertEquals("Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.adicionaFornecedor("gusta", "", "ggg");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.adicionaFornecedor("gusta", null, "ggg");
		} catch (NullPointerException nexc) {
			assertEquals("Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.adicionaFornecedor("gusta", "sla", "");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro no cadastro do fornecedor: telefone nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.adicionaFornecedor("gusta", "sla", null);
		} catch (NullPointerException nexc) {
			assertEquals("Erro no cadastro do fornecedor: telefone nao pode ser vazio ou nulo.", nexc.getMessage());
		}
	}
	
	@Test
	public void testaExibeFornecedor() {
		String nome = this.fc.adicionaFornecedor("g", "g", "gg");
		try {
			this.fc.exibeFornecedor("gu");
		} catch (RuntimeException nexc) {
			assertEquals("Erro na exibicao do fornecedor: fornecedor nao existe.", nexc.getMessage());
		}
		try {
			this.fc.exibeFornecedor("");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na exibicao do fornecedor: nome nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.exibeFornecedor(null);
		} catch (NullPointerException nexc) {
			assertEquals("Erro na exibicao do fornecedor: nome nao pode ser vazio ou nulo.", nexc.getMessage());
		}
	}
	
	@Test
	public void testaEditaFornecedor() {
		String nome = this.fc.adicionaFornecedor("g", "g", "gg");
		assertEquals("g", this.fc.pegaEmail(nome));
		assertEquals("gg", this.fc.pegaTelefone(nome));
		try {
			this.fc.editaFornecedor(nome, "nome", "name");
		} catch (RuntimeException nexc) {
			assertEquals("Erro na edicao do fornecedor: nome nao pode ser editado.", nexc.getMessage());
		}
		try {
			this.fc.editaFornecedor("gu", "nome", "name");
		} catch (RuntimeException nexc) {
			assertEquals("Erro na edicao do fornecedor: fornecedor nao existe.", nexc.getMessage());
		}
		try {
			this.fc.editaFornecedor(nome, "", "ffwe");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na edicao do fornecedor: atributo nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.editaFornecedor(nome, null, "efgewg");
		} catch (NullPointerException nexc) {
			assertEquals("Erro na edicao do fornecedor: atributo nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.editaFornecedor(nome, "telefone", "");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.editaFornecedor(nome, "email", null);
		} catch (NullPointerException nexc) {
			assertEquals("Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.editaFornecedor(nome, "sla", "name");
		} catch (RuntimeException nexc) {
			assertEquals("Erro na edicao do fornecedor: atributo nao existe.", nexc.getMessage());
		}
		try {
			this.fc.editaFornecedor("", "email", "ffwe");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na edicao do fornecedor: nome nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.editaFornecedor(null, "email", "efgewg");
		} catch (NullPointerException nexc) {
			assertEquals("Erro na edicao do fornecedor: nome nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		this.fc.editaFornecedor(nome, "email", "sla");
		this.fc.editaFornecedor(nome, "telefone", "sla");
		assertEquals("sla", this.fc.pegaEmail(nome));
		assertEquals("sla", this.fc.pegaTelefone(nome));
	}
	
	@Test
	public void testaRemoveFornecedor() {
		String nome = this.fc.adicionaFornecedor("g", "g", "gg");
		try {
			this.fc.removeFornecedor("gu");
		} catch (RuntimeException nexc) {
			assertEquals("Erro na remocao do fornecedor: fornecedor nao existe.", nexc.getMessage());
		}
		try {
			this.fc.removeFornecedor("");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na remocao do fornecedor: fornecedor nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.removeFornecedor(null);
		} catch (NullPointerException nexc) {
			assertEquals("Erro na remocao do fornecedor: fornecedor nao pode ser vazio ou nulo.", nexc.getMessage());
		}
	}
	
	@Test
	public void testaAdicionaProduto() {
		this.fc.adicionaFornecedor("g", "gg", "ggg");
		this.fc.adicionaProduto("g", "salada", "saladinha", 2.5);
		try {
			this.fc.adicionaProduto("gu", "egerg", "regwerge", 2.5);
		} catch (RuntimeException nexc){
			assertEquals("Erro no cadastro de produto: fornecedor nao existe.", nexc.getMessage());
		}
		try {
			this.fc.adicionaProduto("g", "salada", "saladinha", 2.5);
		} catch (RuntimeException nexc){
			assertEquals("Erro no cadastro de produto: produto ja existe.", nexc.getMessage());
		}
		try {
			this.fc.adicionaProduto("g", "salad", "saladinha", -1);
		} catch (RuntimeException nexc){
			assertEquals("Erro no cadastro de produto: preco invalido.", nexc.getMessage());
		}
		try {
			this.fc.adicionaProduto("", "", "", 2.5);
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro no cadastro de produto: fornecedor nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.adicionaProduto(null, "", "", 2.5);
		} catch (NullPointerException nexc) {
			assertEquals("Erro no cadastro de produto: fornecedor nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.adicionaProduto("g", "", "", 2.5);
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro no cadastro de produto: nome nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.adicionaProduto("g", null, "", 2.5);
		} catch (NullPointerException nexc) {
			assertEquals("Erro no cadastro de produto: nome nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.adicionaProduto("g", "sla", "", 2.5);
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro no cadastro de produto: descricao nao pode ser vazia ou nula.", nexc.getMessage());
		}
		try {
			this.fc.adicionaProduto("g", "sla", null, 2.5);
		} catch (NullPointerException nexc) {
			assertEquals("Erro no cadastro de produto: descricao nao pode ser vazia ou nula.", nexc.getMessage());
		}
	}
	
	@Test
	public void testaExibeProduto() {
		this.fc.adicionaFornecedor("g", "gg", "ggg");
		this.fc.adicionaProduto("g", "salada", "saladinha", 2.5);
		assertEquals("salada - saladinha - R$2.5", this.fc.exibeProduto("g", "salada", "saladinha"));
		try {
			this.fc.exibeProduto("gu", "salada", "saladinha");
		} catch (RuntimeException nexc){
			assertEquals("Erro na exibicao de produto: fornecedor nao existe.", nexc.getMessage());
		}
		try {
			this.fc.exibeProduto("g", "egerg", "regwerge");
		} catch (RuntimeException nexc){
			assertEquals("Erro na exibicao de produto: produto nao existe.", nexc.getMessage());
		}
		try {
			this.fc.exibeProduto("", "sla", "qw");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.exibeProduto(null, "sla", "df");
		} catch (NullPointerException nexc) {
			assertEquals("Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.exibeProduto("g", "", "qw");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na exibicao de produto: nome nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.exibeProduto("g", null, "df");
		} catch (NullPointerException nexc) {
			assertEquals("Erro na exibicao de produto: nome nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.exibeProduto("g", "sla", "");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na exibicao de produto: descricao nao pode ser vazia ou nula.", nexc.getMessage());
		}
		try {
			this.fc.exibeProduto("g", "sla", null);
		} catch (NullPointerException nexc) {
			assertEquals("Erro na exibicao de produto: descricao nao pode ser vazia ou nula.", nexc.getMessage());
		}
	}
	
	@Test
	public void testaEditaProduto() {
		this.fc.adicionaFornecedor("g", "gg", "ggg");
		this.fc.adicionaProduto("g", "salada", "saladinha", 2.5);
		assertEquals("salada - saladinha - R$2.5", this.fc.exibeProduto("g", "salada", "saladinha"));
		try {
			this.fc.editaProduto("salada", "saladiha", "gu", 3);
		} catch (RuntimeException nexc){
			assertEquals("Erro na edicao de produto: fornecedor nao existe.", nexc.getMessage());
		}
		try {
			this.fc.editaProduto("salada", "saladiha", "gu", -1);
		} catch (RuntimeException nexc){
			assertEquals("Erro na edicao de produto: preco invalido.", nexc.getMessage());
		}
		try {
			this.fc.editaProduto("salad", "saladiha", "g", 3);
		} catch (RuntimeException nexc){
			assertEquals("Erro na edicao de produto: produto nao existe.", nexc.getMessage());
		}
		try {
			this.fc.editaProduto("salada", "sla", "", 3);
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na edicao de produto: fornecedor nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.editaProduto("salada", "sla", null, 3);
		} catch (NullPointerException nexc) {
			assertEquals("Erro na edicao de produto: fornecedor nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.editaProduto("salada", "", "sla", 3);
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na edicao de produto: descricao nao pode ser vazia ou nula.", nexc.getMessage());
		}
		try {
			this.fc.editaProduto("salada", null, "sla", 3);
		} catch (NullPointerException nexc) {
			assertEquals("Erro na edicao de produto: descricao nao pode ser vazia ou nula.", nexc.getMessage());
		}
		try {
			this.fc.editaProduto("", "sla", "sla", 3);
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na edicao de produto: nome nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.editaProduto(null, "sla", "sla", 3);
		} catch (NullPointerException nexc) {
			assertEquals("Erro na edicao de produto: nome nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		this.fc.editaProduto("salada", "saladinha", "g", 3);
		assertEquals("salada - saladinha - R$3.0", this.fc.exibeProduto("g", "salada", "saladinha"));
	}
	
	@Test
	public void testaRemoveProduto() {
		this.fc.adicionaFornecedor("g", "gg", "ggg");
		this.fc.adicionaProduto("g", "salada", "saladinha", 2.5);
		try {
			this.fc.removeProduto("salad", "saladiha", "g");
		} catch (RuntimeException nexc){
			assertEquals("Erro na remocao de produto: produto nao existe.", nexc.getMessage());
		}
		try {
			this.fc.removeProduto("salada", "saladiha", "gu");
		} catch (RuntimeException nexc){
			assertEquals("Erro na remocao de produto: fornecedor nao existe.", nexc.getMessage());
		}
		try {
			this.fc.removeProduto("", "sla", "sla");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na remocao de produto: nome nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.removeProduto(null, "sla", "sla");
		} catch (NullPointerException nexc) {
			assertEquals("Erro na remocao de produto: nome nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.removeProduto("salada", "", "sla");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na remocao de produto: descricao nao pode ser vazia ou nula.", nexc.getMessage());
		}
		try {
			this.fc.removeProduto("salada", null, "sla");
		} catch (NullPointerException nexc) {
			assertEquals("Erro na remocao de produto: descricao nao pode ser vazia ou nula.", nexc.getMessage());
		}
		try {
			this.fc.removeProduto("salada", "sla", "");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na remocao de produto: fornecedor nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		try {
			this.fc.removeProduto("salada", "sla", null);
		} catch (NullPointerException nexc) {
			assertEquals("Erro na remocao de produto: fornecedor nao pode ser vazio ou nulo.", nexc.getMessage());
		}
		this.fc.adicionaFornecedor("gug", "gg", "ggg");
		this.fc.adicionaProduto("gug", "banana", "saladadebanana", 2.5);
		assertEquals("banana - saladadebanana - R$2.5", this.fc.exibeProduto("gug", "banana", "saladadebanana"));
		this.fc.removeProduto("banana", "saladadebanana", "gug");
		try {
			this.fc.exibeProduto("gug", "banan", "saladadenana");
		} catch (RuntimeException nexc) {
			assertEquals("Erro na exibicao de produto: produto nao existe.", nexc.getMessage());
		}
	}
}