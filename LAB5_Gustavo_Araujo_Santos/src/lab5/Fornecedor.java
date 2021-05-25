package lab5;

import java.util.HashMap;

/**
 * Fornecedor é a classe que administra as ações referentes
 * as contas e produtos.
 * "Classe sobrecarregada" sorry
 *  
 * @author Gustavo Araujo Santos
 *
 */
public class Fornecedor {

	
	private String nome;
	private String email;
	private String telefone;
	private HashMap<String, Produto> produtos;
	private HashMap<String, Conta> contas;
	private Exceptions verificacoes;
    
	public Fornecedor(String nome, String email, String telefone) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.produtos = new HashMap();
		this.contas = new HashMap();
		this.verificacoes = new Exceptions();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) { return false; }
		if (this.getClass() != o.getClass()) { return false; }
	    Fornecedor fornecedor = (Fornecedor) o;
		return this.nome.equals(fornecedor.nome);
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}
	
	@Override
	public String toString() {
		return (this.nome + " - " + this.email + " - " + this.telefone);
	}
	
	/**
	 * Método que muda o email ou o telefone de um
	 * fornecedor. O nome não pode ser alterado.
	 * 
	 * @param atributo o atributo a ser alterado
	 * @param novoValor o novo valor do atributo
	 */
	public void setAtributo(String atributo, String novoValor) {
		if (atributo.equals("email")) {
			this.email = novoValor;
		} else if (atributo.equals("telefone")) {
			this.telefone = novoValor;
		}
	}
	
	/**
	 * Método que adiciona uma compra em uma
	 * conta.
	 * 
	 * @param cpf o cpf do cliente dono da conta
	 * @param data a data da compra
	 * @param nome o nome do produto
	 * @param descricao a descricao do produto
	 * @param nomeCliente o nome do cliente
	 */
	public void adicionaCompra(String cpf, String data, String nome, String descricao, String nomeCliente) {
		double preco = buscaPreco(nome, descricao);
		if (!this.contas.containsKey(cpf)) {
			Conta conta = new Conta(cpf, this.nome, nomeCliente);
			conta.adicionaCompra(data, nome, descricao, preco);
			this.contas.put(cpf, conta);
		} else {
			this.contas.get(cpf).adicionaCompra(data, nome, descricao, preco);
		}
	}
	
	/**
	 * Método que retorna o preco de um produto
	 * atravez do seu nome e da sua descricao.
	 * 
	 * @param nome o nome do produto
	 * @param descricao a descricao do produto
	 * @return o preco do produto
	 */
	private double buscaPreco(String nome, String descricao) {
		if (!this.produtos.containsKey(nome) || this.produtos.get(nome).getDescricao() != descricao) { // potencial erro nos testes de aceitacao
			throw new IllegalArgumentException("Erro ao cadastrar compra: produto nao existe.");
		}
		
		return this.produtos.get(nome).getPreco();
	}
	
	/**
	 * Método que verifica se determinado produto
	 * está cadastrado ou não.
	 * 
	 * @param produto o nome do produto
	 * @return true caso o produto exista. False, caso não.
	 */
	public boolean haProduto(String produto) {
		if (this.produtos.containsKey(produto)){
			return true;
		}
		return false;
	}
	
	/**
	 * Método que cadastra um produto no sistema.
	 * 
	 * @param nome o nome do produto
	 * @param descricao a descricao do produto
	 * @param preco o preco do produto
	 */
	public void adicionaProduto(String nome, String descricao, double preco) throws RuntimeException {
		if (preco < 0) {
			throw new RuntimeException("Erro no cadastro de produto: preco invalido.");
		}
		this.verificacoes.verificaNome(nome, "Erro no cadastro de produto: ");
		this.verificacoes.verificaDescricao(descricao, "Erro no cadastro de produto: ");
		if (this.produtos.containsKey(nome)) {
			throw new RuntimeException("Erro no cadastro de produto: produto ja existe.");
		}
		Produto produto = new Produto(nome, preco, descricao);
		this.produtos.put(nome, produto);
	}
	
	/**
	 * Método que exibe uma representação textual de
	 * determinado produto.
	 * 
	 * @param nome o nome do produto
	 * @param descricao a descricao do produto
	 * @return uma representação textual
	 */
	public String exibeProduto(String nome, String descricao) throws RuntimeException {
		if (!this.produtos.containsKey(nome)) {
			throw new RuntimeException("Erro na exibicao de produto: produto nao existe.");
		} else if(this.produtos.get(nome).getDescricao() != descricao) {
			throw new RuntimeException("Erro na exibicao de produto: produto nao existe.");
		}
		this.verificacoes.verificaNome(nome, "Erro na exibicao de produto: ");
		this.verificacoes.verificaDescricao(descricao, "Erro na exibicao de produto: ");
		return this.produtos.get(nome).toString();
	}
	
	/**
	 * Método que edita o preco de
	 * um produto cadastrado no sistema.
	 * Os outros atributos são os ids, não podem
	 * ser alterados.
	 * 
	 * @param nome o nome do produto
	 * @param descricao a descricao do produto
	 * @param novoPreco o novo preco
	 */
	public void editaProduto(String nome, String descricao, double novoPreco) {
		if (!this.produtos.containsKey(nome)) {
			throw new RuntimeException("Erro na edicao de produto: produto nao existe.");
		} else if(this.produtos.get(nome).getDescricao() != descricao) {
			throw new RuntimeException("Erro na edicao de produto: produto nao existe.");
		}
		this.verificacoes.verificaNome(nome, "Erro na edicao de produto: ");
		this.verificacoes.verificaDescricao(descricao, "Erro na edicao de produto: ");
		if (novoPreco < 0) {
			throw new RuntimeException("Erro na edicao de produto: preco invalido.");
		}
		this.produtos.get(nome).setPreco(novoPreco);
	}
	
	/**
	 * Método que deleta determinado produto do sistema.
	 * 
	 * @param nome o nome do produto
	 * @param descricao a descricao do produto
	 */
	public void removeProduto(String nome, String descricao) throws RuntimeException {
		if (!this.produtos.containsKey(nome)) {
			throw new RuntimeException("Erro na remocao de produto: produto nao existe.");
		} else if(this.produtos.get(nome).getDescricao() != descricao) {
			throw new RuntimeException("Erro na remocao de produto: produto nao existe.");
		}
		this.verificacoes.verificaNome(nome, "Erro na remocao de produto: ");
		this.verificacoes.verificaDescricao(descricao, "Erro na remocao de produto: ");
		this.produtos.remove(nome, this.produtos.get(nome));
	}
	
	/**
	 * Método que verifica se o cpf indicado pertence
	 * há alguma conta de cliente cadastrada no sistema.
	 * 
	 * @param cpf o cpf do cliente
	 * @return true caso tenha a conta. false, caso não.
	 */
	public boolean haConta(String cpf) {
		if (this.contas.containsKey(cpf)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Retorna o debito de determinado cliente.[
	 * 
	 * @param cpf o cpf do cliente
	 * @return o debito
	 */
	public double getDebito(String cpf) {
		return this.contas.get(cpf).calculaDebito();
	}
	
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Método que retorna as compras ordenadas pelo
	 * nome do fornecedor.
	 * 
	 * @return uma representação textual.
	 */
	public String retornaComprasPorFornecedor() {
		String finn = "";
		for (String s : this.contas.keySet()) {
			finn += this.contas.get(s).retornaComprasPorFornecedor() + "\n";
		}
		return finn.substring(0, finn.length() - 1);
	}
	
	/**
	 * @return o HashMap de contas
	 */
	public Object getContas() {
		return this.contas;
	}
	
	/**
	 * Método que gera uma representação textual de todos
	 * os produtos cadastrados no sistema.
	 * 
	 * @return uma representação textual de todos os produtos
	 */
	public String geraRepProdutos() {
		String finn = "";
		for (Produto p : this.produtos.values()) {
			finn += this.nome + " - " + p.toString() + "\n";
		}
		return finn.substring(0, finn.length() - 1);
	}
	
	/**
	 * Método que exibe informações da conta de
	 * um cliente.
	 * 
	 * @param cpf o cpf do cliente
	 * @return uma representação textual
	 */
	public String exibeConta(String cpf) {
		return this.contas.get(cpf).toString();
	}
	/**
	 * Método que retorna uma representação textual
	 * de todas as compras da conta de um determinado cliente.
	 * @param cpf o cpf do cliente
	 * @return uma representação textual
	 */
	public String geraRepCompras(String cpf) {
		String finn = this.nome + "\n";
		finn += this.contas.get(cpf).geraRepCompras();
		return finn;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getTelefone() {
		return this.telefone;
	}
}
