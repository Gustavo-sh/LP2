package fornecedor;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import produto.Produto;
import utilidades.ComparatorProduto;

/**
 * Classe que representa um fornecedor do sistema
 * 
 * @author Aluno de período anterior
 *
 */
public class Fornecedor {
	/**
	 * Nome do fornecedor
	 */
	private String nome;

	/**
	 * Email do fornecedor
	 */
	private String email;

	/**
	 * Telefone do fornecedor
	 */
	private String telefone;

	/**
	 * Hash map dos produtos que o fornecedor oferece sendo as chaves a concatenação
	 * das String que representam o nome e descrição do produto respectivamente, e
	 * os valores são os objetos que representam os produtos
	 */
	private HashMap<String, Produto> produtos;

	/**
	 * ArrayList de Strings que são a concatenação do nome e descrição dos combos
	 */
	private ArrayList<String> combosCadastrados;
	
	/**
	 * ArrayList de Strings que são a concatenação do nome e descrição dos produtos
	 */
	private ArrayList<String> produtosCadastrados;

	/**
	 * Comparator que compara dois produtos
	 */
	private Comparator<Produto> comparador;
	
	/**
	 * Constrói um fornecedor dado seu nome, email e telefone
	 * 
	 * @param nome     Nome do fornecedor
	 * @param email    Email do fornecedor
	 * @param telefone Telefone do fornecedor
	 */
	public Fornecedor(String nome, String email, String telefone) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;

		this.produtos = new HashMap<String, Produto>();
		this.produtosCadastrados = new ArrayList<String>();
		this.combosCadastrados = new ArrayList<>();

		this.comparador = new ComparatorProduto();
	}

	/**
	 * Retorna a representação textual do fornecedor no formato "Nome" - "Email" -
	 * "Telefone"
	 */
	@Override
	public String toString() {
		return this.getNome() + " - " + this.getEmail() + " - " + this.getTelefone();
	}

	/**
	 * Compara dois fornecedores pelo nome
	 */
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof Fornecedor) {
			Fornecedor f = (Fornecedor) o;

			if (this.getNome().equals(f.getNome())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gera o hashCode do fornecedor baseado no nome
	 */
	@Override
	public int hashCode() {
		return this.getNome().hashCode();
	}

	/**
	 * Retorna o nome do fornecedor
	 * 
	 * @return String contendo o nome do fornecedor
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Retorna o telefone do fornecedor
	 * 
	 * @return String contendo o telefone do fornecedor
	 */
	private String getTelefone() {
		return this.telefone;
	}

	/**
	 * Retorna o email do fornecedor
	 * 
	 * @return String contendo o email do fornecedor
	 */
	private String getEmail() {
		return this.email;
	}

	/**
	 * Redefine o email do fornecedor dado o novo valor
	 * 
	 * @param email Novo email do fornecedor
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Redefine o telefone do fornecedor dado o novo valor
	 * 
	 * @param telefone Novo numero de telefone do fornecedor
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * Adiciona um produto ao fornecedor dado o nome, descricao e preco do produto
	 * 
	 * @param nome      Nome do produto
	 * @param descricao Descricao do produto
	 * @param preco     Preco do produto
	 */
	public void adicionaProduto(String nome, String descricao, double preco) {
		String key = nome + descricao;

		if (this.produtos.containsKey(key)) {
			throw new IllegalArgumentException("Erro no cadastro de produto: produto ja existe.");
		}

		this.produtos.put(key, new Produto(nome, descricao, preco));
		this.produtosCadastrados.add(key);

		for (int i = this.produtosCadastrados.size() - 1; i > 0; i--) {
			String produto1 = this.produtosCadastrados.get(i);
			String produto2 = this.produtosCadastrados.get(i - 1);

			Produto p1 = this.produtos.get(produto1);
			Produto p2 = this.produtos.get(produto2);

			int compare = this.comparador.compare(p1, p2);

			if (compare < 0) {
				Collections.swap(this.produtosCadastrados, i, i - 1);
			}
		}
	}
	
	/**
	 * Adiciona um combo ao forncedor dado o nome do fornecedor, nome do combo, 
	 * descricao, fator e os produtos que compõe o combo
	 * 
	 * @param fornecedor     o nome do fornecedor
	 * @param nomeCombo      o nome do combo
	 * @param descricaoCombo a descricao do combo
	 * @param fator          o fator que irá definir o preco do combo
	 * @param produtos       os produtos que compõe o combo
	 */
	public void adicionaCombo(String nomeCombo, String descricaoCombo, double fator, String produtos) {
		String key = nomeCombo + descricaoCombo;

		if (this.produtos.containsKey(key)) {
			throw new IllegalArgumentException("Erro no cadastro de combo: combo ja existe.");
		}
		
		String[] p = produtos.split(", ");

		Produto p1 = null;
		Produto p2 = null;
		
		String[] produto1 = p[0].split(" - ");
		String[] produto2 = p[1].split(" - ");
		
		if (this.combosCadastrados.contains(produto1[0] + produto1[1])) {
			throw new IllegalArgumentException("Erro no cadastro de combo: um combo nao pode possuir combos na lista de produtos.");
		}
		if (this.combosCadastrados.contains(produto2[0] + produto2[1])) {
			throw new IllegalArgumentException("Erro no cadastro de combo: um combo nao pode possuir combos na lista de produtos.");
		}
			
		if (!this.produtosCadastrados.contains(produto1[0] + produto1[1])) {
			throw new IllegalArgumentException("Erro no cadastro de combo: produto nao existe.");
		}
		if (!this.produtosCadastrados.contains(produto2[0] + produto2[1])) {
			throw new IllegalArgumentException("Erro no cadastro de combo: produto nao existe.");
		}
		
		for (Produto prodt : this.produtos.values()) {
			if (prodt.verificaProduto(produto1[0] + produto1[1])) {
				p1 = prodt;
			}
			if (prodt.verificaProduto(produto2[0] + produto2[1])) {
				p2 = prodt;
			}
		}
		
		this.produtos.put(key, new Produto(nomeCombo, descricaoCombo, fator, p1, p2));
		this.produtosCadastrados.add(key);
		this.combosCadastrados.add(key);
		
		for (int i = this.produtosCadastrados.size() - 1; i > 0; i--) {
			String pdt1 = this.produtosCadastrados.get(i);
			String pdt2 = this.produtosCadastrados.get(i - 1);

			Produto pro1 = this.produtos.get(pdt1);
			Produto pro2 = this.produtos.get(pdt2);

			int compare = this.comparador.compare(pro1, pro2);

			if (compare < 0) {
				Collections.swap(this.produtosCadastrados, i, i - 1);
			}
		}
	}

	/**
	 * Exibe um produto do fornecedor dado o nome e a descricao do produto
	 * 
	 * @param nome      Nome do produto
	 * @param descricao Descricao do produto
	 * @return Representação String do produto
	 */
	public String exibeProduto(String nome, String descricao) {
		if (this.produtos.containsKey(nome + descricao)) {
			return this.produtos.get(nome + descricao).toString();
		}
		else if (!this.combosCadastrados.contains(nome + descricao)) {
			throw new IllegalArgumentException("Erro na exibicao de combo: combo nao existe.");
		}
		else if (!this.produtos.containsKey(nome + descricao)) {
			throw new IllegalArgumentException("Erro na exibicao de produto: produto nao existe.");
		}
		return "";
	}

	/**
	 * Lista todos os produtos do forncedor em ordem alfabética do nome do produto
	 * 
	 * @return String contendo a lista de todos os produtos do fornecedor
	 */
	public String listaProdutos() {
		String retorno = this.getNome() + " -";

		for (int i = 0; i < this.produtosCadastrados.size(); i++) {
			String produto = this.produtosCadastrados.get(i);

			if (i == 0) {
				retorno += " " + this.produtos.get(produto).toString();
			}

			else {
				retorno += " | " + this.getNome() + " - " + this.produtos.get(produto).toString();
			}
		}

		return retorno;
	}

	/**
	 * Edita o preco de um produto do fornecedor dado o nome e descricao do produto
	 * e o novo preco
	 * 
	 * @param nome      Nome do produto
	 * @param descricao Descricao do produto
	 * @param novoPreco Novo preco do produto
	 */
	public void editaProduto(String nome, String descricao, double novoPreco) {
		String key = nome + descricao;

		if (!this.produtos.containsKey(key)) {
			throw new IllegalArgumentException("Erro na edicao de produto: produto nao existe.");
		}

		this.produtos.get(key).modificaProduto(novoPreco);

	}
	
	/**
	 * Edita o fator de um combo do fornecedor dado o nome e descricao do combo
	 * e o novo fator
	 * 
	 * @param nome o nome do combo
	 * @param descricao a descricao do combo
	 * @param novoFator o novo fator
	 */
	public void editaCombo(String nome, String descricao, double novoFator) {
		String key = nome + descricao;
		
		if (!this.produtos.containsKey(key)) {
			throw new IllegalArgumentException("Erro na edicao de combo: produto nao existe.");
		}
		
		this.produtos.get(key).editaCombo(novoFator);
	}

	/**
	 * Remove um produto do fornecedor
	 * 
	 * @param nome      Nome do produto
	 * @param descricao Descricao do produto
	 */
	public void removeProduto(String nome, String descricao) {
		String key = nome + descricao;

		if (!this.produtos.containsKey(key)) {
			throw new IllegalArgumentException("Erro na remocao de produto: produto nao existe.");
		}

		this.produtos.remove(key);

		this.produtosCadastrados.remove(key);
	}

	/**
	 * Retorna o preco de um produto dado o nome e descricao do produto
	 * 
	 * @param nomeProduto      Nome do produto
	 * @param descricaoProduto Descricao do produto
	 * @return Preco do produto
	 */
	public double getPrecoProduto(String nomeProduto, String descricaoProduto) {
		if (!this.produtos.containsKey(nomeProduto + descricaoProduto)) {
			throw new IllegalArgumentException("Erro ao pegar preco do produto: produto nao existe.");
		}

		return this.produtos.get(nomeProduto + descricaoProduto).getPreco();
	}

}
