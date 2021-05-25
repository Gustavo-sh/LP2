package lab5;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Classe que guarda e cuida das compras cadastradas
 * no sistema.
 * 
 * @author Gustavo Araujo Santos - 119210741
 */
public class Conta {

	private double debito;
	private List<Compra> compras;
	private String cpf;
	private String fornecedor;
	private String cliente;
	
	public Conta(String cpf, String fornecedor, String cliente) {
		this.cpf = cpf;
		this.debito = 0;
		this.fornecedor = fornecedor;
		this.cliente = cliente;
		this.compras = new ArrayList();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) { return false; }
		if (this.getClass() != o.getClass()) { return false; }
		Conta conta = (Conta) o;
		return (this.cpf.equals(conta.cpf) && this.fornecedor.equals(conta.fornecedor));
	}
	
	@Override
	public int hashCode() {
		return this.cpf.hashCode();
	}
	
	/**
	 * Método que aciciona uma compra no sistema.
	 * 
	 * @param data a data da compra
	 * @param nome o nome do produto
	 * @param descricao a descricao do produto
	 * @param preco o preco dod produto
	 */
	public void adicionaCompra(String data, String nome, String descricao, double preco) {
		Compra compra = new Compra(data, nome, descricao, preco, this.fornecedor, this.cliente);
		this.compras.add(compra);
	}
	
	/**
	 * Método que gera o débito total das compras
	 * efetuadas.
	 * 
	 * @return o valor total das compras
	 */
	public double calculaDebito() {
		double debt = 0;
		for (Compra compra : this.compras) {
			debt += compra.getPreco();
		}
		return debt;
	}
	
	public double getDebito() {
		return this.debito;
	}
	
	@Override
	public String toString() {
		String finn = ("Cliente: " + this.cliente + "\n" + this.fornecedor + "\n");
		for (Compra compra : this.compras) {
			finn += compra.toString() + "\n";
		}
		
		return finn.substring(0, finn.length() - 1);
	}
	
	public String getFornecedor() {
		return this.fornecedor;
	}
	
	public String getDescricaoCompra(int index) {
		return this.compras.get(index).getDescricao();
	}
	
	public String getDataCompra(int index) {
		return this.compras.get(index).getData();
	}
	
	/**
	 * Método que retorna todas as compras 
	 * efetuadas ordenando-as pelo nome do
	 * fornecedor.
	 * 
	 * @return uma representação textual das compras
	 */
	public String retornaComprasPorFornecedor() {
		String finn = "";
		Collections.sort(this.compras, new OrdenaPorCompraFornecedor());
		for (Compra c : this.compras) {
			finn += this.fornecedor + ", " + this.cliente + ", " + c.getNome() + ", " + c.getData() + "\n";
		}
		return finn.substring(0, finn.length() - 1);
	}
	
	public String getCliente() {
		return this.cliente;
	}
	
	/**
	 * Método que retorna uma representação textual
	 * das compras efetuas.
	 * 
	 * @return uma representação textual das compras
	 */
	public String geraRepCompras() {
		String finn = "";
		for (Compra c : this.compras) {
			finn += c.toString() + "\n";
		}
		return finn.substring(0, finn.length() - 1);
	}

	/**
	 * Método que gera e retorna uma representação
	 * textual ordenada pelos nomes dos clientes 
	 * cadastrados.
	 * 
	 * @return uma representação textual
	 */
	public String retornaComprasPorCliente() {
		String finn = "";
		Collections.sort(this.compras, new OrdenaPorComprasCliente());
		for (Compra c : this.compras) {
			finn += this.cliente + ", " + this.fornecedor + ", " + c.getNome() + ", " + c.getData() + "\n";
		}
		return finn.substring(0, finn.length() - 1);
	}
}
