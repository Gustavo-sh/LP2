package lab5;

/**
 * Classe comparável que representa uma compra efetuada
 * no sistema.
 * 
 * @author Gustavo Araujo Santos - 119210741
 *
 */
public class Compra implements Comparable<Compra> {

	private String data;
	private String nome;
	private String descricao;
	private double preco;
	private String fornecedor;
	private String nomeCliente;
	
	public Compra(String data, String nome, String descricao, double preco, String fornecedor, String nomeCliente) {
		this.data = data;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.fornecedor = fornecedor;
		this.nomeCliente = nomeCliente;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public String getData() {
		return this.data;
	}
	
	@Override
	public String toString() {
		return (this.nome + " - " + this.data);
	}
	
	public double getPreco() {
		 return this.preco;
	}
	
	public String getFornecedor() {
		return this.fornecedor;
	}
	
	public String getCliente() {
		return this.nomeCliente;
	}
	
	@Override
	public int compareTo(Compra c) {
		return this.data.compareTo(c.getData());
	}
}
