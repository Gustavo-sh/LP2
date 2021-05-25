package produto;

/**
 * Classe que representa um produto, que tem nome, descricao e preco
 * 
 * @author Aluno de período anterior
 *
 */
public class Produto {
	/**
	 * Nome do produto
	 */
	private String nome;

	/**
	 * Descricao do produto
	 */
	private String descricao;

	/**
	 * Preço do produto
	 */
	private double preco;

	/**
	 * Fator do combo
	 */
	private double fator;
	
	/**
	 * Guarda os produtos que compõe um combo
	 */
	private Produto[] produtos;
	
	/**
	 * 
	 * @param nome
	 * @param descricao
	 * @param preco
	 */
	public Produto(String nome, String descricao, double preco) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	/**
	 * Construtor de um combo.
	 * 
	 * @param nomeCombo
	 * @param descricaoCombo
	 * @param fator
	 * @param p1
	 * @param p2
	 */
	public Produto(String nomeCombo, String descricaoCombo, double fator, Produto p1, Produto p2) {
		this.nome = nomeCombo;
		this.descricao = descricaoCombo;
		this.fator = fator;
		this.produtos = new Produto[2];
		this.produtos[0] = p1;
		this.produtos[1] = p2;
		this.preco = calculaPreco();
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return this.getNome() + " - " + this.getDescricao() + " - R$" + String.format("%.2f", this.preco);
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof Produto) {
			Produto p = (Produto) o;

			if ((this.getNome() + this.getDescricao()).equals(p.getNome() + p.getDescricao())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return (this.getNome() + this.getDescricao()).hashCode();
	}

	/**
	 * 
	 * @return
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * 
	 * @return
	 */
	public double getPreco() {
		return this.preco;
	}

	/**
	 * Define o preco de um combo de acordo com
	 * o fator atual do mesmo.
	 * 
	 * @return o preco definido
	 */
	public double calculaPreco() {
		double valor1 = this.produtos[0].getPreco();
		double valor2 = this.produtos[1].getPreco();
		double retirar = (valor1 + valor2) * fator;
		return (valor1 + valor2) - retirar;
	}
	
	/**
	 * 
	 * @param preco
	 */
	protected void setPreco(double preco) {
		this.preco = preco;
	}

	/**
	 * 
	 * @param novoValor
	 */
	public void modificaProduto(double novoValor) {
		this.setPreco(novoValor);
	}

	/**
	 * Verifica se um nome e descricao concatenados passados
	 * como parâmetro são os mesmos nome e descricao desse
	 * objeto.
	 * 
	 * @param key nome + descricao
	 * @return true caso seja igual. false, caso não
	 */
	public boolean verificaProduto(String key) {
		if (key.equals(this.getNome() + this.getDescricao())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Edita o fator do combo
	 * 
	 * @param novoFator o novo fator
	 */
	public void editaCombo(double novoFator) {
		this.fator = novoFator;
	    this.preco = calculaPreco();
	}
}
