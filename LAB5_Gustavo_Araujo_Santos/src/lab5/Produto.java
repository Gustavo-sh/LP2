package lab5;

/**
 * Classe básica do sistema. Guarda as informações dos produtos.
 * @author shiro
 *
 */
public class Produto {

	private String nome;
	private double preco;
	private String descricao;
	
	Produto(String nome, double preco, String descricao) {
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) { return false; }
		if (this.getClass() != o.getClass()) { return false; }
	    Produto produto = (Produto) o;
		return (this.nome.equals(produto.nome) && this.descricao.equals(produto.descricao));
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode() + this.descricao.hashCode();
	}
	
	@Override
	public String toString() {
		return (this.nome + " - " + this.descricao + " - R$" + this.preco);
	}
	
	public String getDescricao() {
		return this.descricao;
	}

	public double getPreco() {
		return this.preco;
	}
	
	public void setPreco(double novoPreco) {
		this.preco = novoPreco;
	}
}
