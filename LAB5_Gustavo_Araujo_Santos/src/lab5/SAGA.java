package lab5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Cuida de parte das listagens de compras e das exibições ordenadas
 * dos clientes, fornecedores e produtos.
 * 
 * @author Gustavo Araujo Santos
 *
 */
public class SAGA {

	private Exceptions verificacoes;
	private List<Cliente> clientes;
	private List<Fornecedor> fornecedores;
	private List<Produto> produtos;
	
	public SAGA() {
		this.verificacoes = new Exceptions();
		this.clientes = new ArrayList();
		this.fornecedores = new ArrayList();
		this.produtos = new ArrayList();
	}
	
	/**
	 * Exibe todos os clientes cadastrados no sistema ordenados
	 * pelos seus nomes.
	 * 
	 * @param c o clienteController para ter acesso aos clientes.
	 * 
	 * @return uma representação textual
	 */
	public String exibeClientes(ClienteController c) {
		String finn = "";
		HashMap<String, Cliente> clientes = (HashMap<String, Cliente>) c.getClientes();
		for (Cliente cliente : clientes.values()) {
			if (!this.clientes.contains(cliente)) {
				this.clientes.add(cliente);
			}
		}
		for (Cliente clie : this.clientes) {
			if (!clientes.containsValue(clie)) {
				this.clientes.remove(clie);
			}
		}
		Collections.sort(this.clientes, new OrdenaPorNomeCliente());
		for (Cliente clie : this.clientes) {
			finn += clie.toString() + "\n";
		}
		return finn.substring(0, finn.length() - 1);
	}
	
	/**
	 * Exibe todos os forncedores cadastrados no sistema
	 * ordenados pelos seus nomes.
	 * 
	 * @param f o fornecedorController para ter acesso aos fornecedores
	 * 
	 * @return uma representação textual
	 */
	public String exibeFornecedores(FornecedorController f) {
		String finn = "";
		HashMap<String, Fornecedor> fornecedores = (HashMap) f.getFornecedores();
		for (Fornecedor forn : this.fornecedores) {
			if (!fornecedores.containsValue(forn)) {
				this.fornecedores.remove(forn);
			}
		}
		for (Fornecedor fornecedor : fornecedores.values()) {
			if (!this.fornecedores.contains(fornecedor)) {
				this.fornecedores.add(fornecedor);
			}
		}
		Collections.sort(this.fornecedores, new OrdenaPorNomeFornecedor());
		for (Fornecedor forn : this.fornecedores) {
			finn += forn.toString() + "\n";
		}
		return finn.substring(0, finn.length() - 1);
	}
	
	/**
	 * Exibe todos os produtos cadastrados no sistema
	 * ordenados pelos nomes dos fornecedores.
	 * 
	 * @param f o fornecedorController para ter acesso aos produtos
	 * 
	 * @return uma representação textual
	 */
	public String exibeProdutos(FornecedorController f) {
		String finn = "";
		HashMap<String, Fornecedor> fornecedores = (HashMap) f.getFornecedores();
		for (Fornecedor forn : this.fornecedores) {
			if (!fornecedores.containsValue(forn)) {
				this.fornecedores.remove(forn);
			}
		}
		for (Fornecedor fornecedor : fornecedores.values()) {
			if (!this.fornecedores.contains(fornecedor)) {
				this.fornecedores.add(fornecedor);
			}
		}
		Collections.sort(this.fornecedores, new OrdenaPorNomeFornecedor());
		for (Fornecedor forn : this.fornecedores) {
			finn += forn.geraRepProdutos() + "\n";
		}
		return finn.substring(0, finn.length() - 1);
	}
}