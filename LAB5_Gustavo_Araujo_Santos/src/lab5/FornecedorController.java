package lab5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
/**
 * FornecedorController age em cima do Fornecedor,
 * das Contas e dos Produtos.
 * "Classe sobrecarregada" sorry
 * 
 * @author Gustavo Araujo Santos
 *
 */
public class FornecedorController {

	private HashMap<String, Fornecedor> fornecedores;
	private Exceptions verificacoes;
	private List<Fornecedor> listFornecedores;
	private List<Conta> contas;
	private String criterio;
	
	public FornecedorController() {
		this.fornecedores = new HashMap();
		this.verificacoes = new Exceptions();
		this.listFornecedores = new ArrayList<Fornecedor>();
		this.contas = new ArrayList<Conta>();
		this.criterio = "";
	}
	
	/**
	 * Cadastra um fornecedor no sistema.
	 * 
	 * @param nome o nome do fornecedor
	 * @param email o email do fornecedor
	 * @param telefone o telefone do fornecedor
	 * @return o nome do fornecedor
	 */
	public String adicionaFornecedor(String nome, String email, String telefone) throws RuntimeException {
		if (this.fornecedores.containsKey(nome)) {
			throw new RuntimeException("Erro no cadastro do fornecedor: fornecedor ja existe.");
		}
		this.verificacoes.validaCadastroFornecedor(nome, email, telefone, "Erro no cadastro do fornecedor: ");
		Fornecedor fornecedor = new Fornecedor(nome, email, telefone);
		this.fornecedores.put(nome, fornecedor);
		return nome;
	}
	
	/**
	 * exibe informações sobre determinado fornecedor.
	 * 
	 * @param nome o nome do fornecedor
	 * @return uma representação textual
	 */
	public String exibeFornecedor(String nome) throws RuntimeException {
	    this.verificacoes.verificaNome(nome, "Erro na exibicao do fornecedor: ");
	    if (!this.fornecedores.containsKey(nome)) {
	    	throw new RuntimeException("Erro na exibicao do fornecedor: fornecedor nao existe.");
	    }
	    return this.fornecedores.get(nome).toString();
	}
	
	/**
	 * edita determinado atributo de um dado fornecedor.
	 * 
	 * @param nome o nome do fornecedor
	 * @param atributo o atributo a ser editado
	 * @param novoValor o novo valor do atributo
	 * @return o nome do cliente
	 */
	public String editaFornecedor(String nome, String atributo, String novoValor) throws RuntimeException {
		this.verificacoes.verificaAtributo(atributo, "Erro na edicao do fornecedor: ");
		this.verificacoes.verificaNovoValor(novoValor, "Erro na edicao do fornecedor: ");
		this.verificacoes.verificaNome(nome, "Erro na edicao do fornecedor: ");
		if (!this.fornecedores.containsKey(nome)) {
			throw new RuntimeException("Erro na edicao do fornecedor: fornecedor nao existe.");
		} 
		if (atributo.equals("nome")) {
			throw new RuntimeException("Erro na edicao do fornecedor: nome nao pode ser editado.");
		} 
		if (atributo != "email" && atributo != "telefone") {
			throw new RuntimeException("Erro na edicao do fornecedor: atributo nao existe.");
		}
		this.fornecedores.get(nome).setAtributo(atributo, novoValor);
		return nome;
	}
	
	/**
	 * deleta determinado fornecedor do sistema.
	 * 
	 * @param nome o nome do fornecedor
	 * @return o nome do fornecedor
	 */
	public String removeFornecedor(String nome) throws RuntimeException {
		this.verificacoes.verificaFornecedor(nome, "Erro na remocao do fornecedor: ");
		if (!this.fornecedores.containsKey(nome)) {
			throw new RuntimeException("Erro na remocao do fornecedor: fornecedor nao existe.");
		}
		this.fornecedores.remove(nome, this.fornecedores.get(nome));
		return nome;
	}
	
	/**
	 * cadastra uma compra em uma conta 
	 * @param cpf
	 * @param fornecedor
	 * @param data
	 * @param nome
	 * @param descricao
	 * @param nomeCliente
	 * @throws RuntimeException
	 */
	public void adicionaCompra(String cpf, String fornecedor, String data, String nome, String descricao, String nomeCliente) throws RuntimeException {
		this.verificacoes.validaCadastroDeCompra(cpf, fornecedor, data, nome, descricao, "Erro ao cadastrar compra: ");
		if (cpf.length() > 11 || cpf.length() < 11) {
			throw new RuntimeException("Erro ao cadastrar compra: cpf invalido.");
		} else if (!this.fornecedores.containsKey(fornecedor)) {
			throw new RuntimeException("Erro ao cadastrar compra: fornecedor nao existe.");
		} else if (!this.fornecedores.get(fornecedor).haProduto(nome)) {
			throw new RuntimeException("Erro ao cadastrar compra: produto nao existe.");
		}
		this.fornecedores.get(fornecedor).adicionaCompra(cpf, data, nome, descricao, nomeCliente);
	}
	
	/**
	 * Verifica se existe determinado fornecedor cadastrado no sistema.
	 * 
	 * @param fornecedor o nome do fornecedor
	 * @return true caso ele esteja cadastrado. false, caso não.
	 */
	public boolean haFornecedor(String fornecedor) {
		if (this.fornecedores.containsKey(fornecedor)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Método que cadastra um produto no sistema.
	 * 
	 * @param fornecedor o nome do fornecedor que ira receber o produto
	 * @param nome o nome do produto
	 * @param descricao a descricao do produto
	 * @param preco o preco do produto
	 */
	public void adicionaProduto(String fornecedor, String nome, String descricao, double preco) throws RuntimeException {
		this.verificacoes.verificaFornecedor(fornecedor, "Erro no cadastro de produto: ");
		this.verificacoes.verificaNome(nome, "Erro no cadastro de produto: ");
		this.verificacoes.verificaDescricao(descricao, "Erro no cadastro de produto: ");
		if (!this.fornecedores.containsKey(fornecedor)) {
			throw new RuntimeException("Erro no cadastro de produto: fornecedor nao existe.");
		}
		this.fornecedores.get(fornecedor).adicionaProduto(nome, descricao, preco);
	}
	
	/**
	 * exibe uma representação textual de um dado produto
	 * @param fornecedor o nome do fornecedor que contém o produto 
	 * @param nome o nome do produto
	 * @param descricao a descricao do produto
	 * @return uma representação textual
	 */
	public String exibeProduto(String fornecedor, String nome, String descricao) throws RuntimeException {
		this.verificacoes.verificaFornecedor(fornecedor, "Erro na exibicao de produto: ");
		this.verificacoes.verificaNome(nome, "Erro na exibicao de produto: ");
		this.verificacoes.verificaDescricao(descricao, "Erro na exibicao de produto: ");
		if (!this.fornecedores.containsKey(fornecedor)) {
			throw new RuntimeException("Erro na exibicao de produto: fornecedor nao existe.");
		}
		return this.fornecedores.get(fornecedor).exibeProduto(nome, descricao);
	}
	
	/**
	 * Método que edita o preco de um produto.
	 * Os demais atributos não podem ser alterados.
	 * 
	 * @param nome o nome do produto
	 * @param descricao a descricao do produto
	 * @param fornecedor o nome do fornecedor
	 * @param novoPreco o novo preco
	 */
	public void editaProduto(String nome, String descricao, String fornecedor, double novoPreco) throws RuntimeException {
		this.verificacoes.verificaFornecedor(fornecedor, "Erro na edicao de produto: ");
		this.verificacoes.verificaNome(nome, "Erro na edicao de produto: ");
		this.verificacoes.verificaDescricao(descricao, "Erro na edicao de produto: ");
		if (novoPreco < 0) {
			throw new RuntimeException("Erro na edicao de produto: preco invalido."); 
		}
		if (!this.fornecedores.containsKey(fornecedor)) {
			throw new RuntimeException("Erro na edicao de produto: fornecedor nao existe.");
		}
		this.fornecedores.get(fornecedor).editaProduto(nome, descricao, novoPreco);
	}
	
	/**
	 * Deleta determinado produto do sistema.
	 * 
	 * @param nome o nome do produto
	 * @param descricao a descricao do produto
	 * @param fornecedor o nome do fornecedor
	 */
	public void removeProduto(String nome, String descricao, String fornecedor) {
		this.verificacoes.verificaFornecedor(fornecedor, "Erro na remocao de produto: ");
		this.verificacoes.verificaNome(nome, "Erro na remocao de produto: ");
		this.verificacoes.verificaDescricao(descricao, "Erro na remocao de produto: ");
		if (!this.fornecedores.containsKey(fornecedor)) {
			throw new RuntimeException("Erro na remocao de produto: fornecedor nao existe.");
		}
		this.fornecedores.get(fornecedor).removeProduto(nome, descricao);
	}
	
	/**
	 * pega o débito de um cliente com um dado fornecedor.
	 * 
	 * @param cpf o cpf do cliente
	 * @param fornecedor o nome do fornecedor
	 * @return o debito do cliente
	 */
	public double getDebito(String cpf, String fornecedor) throws RuntimeException {
		if (!this.fornecedores.containsKey(fornecedor)) {
			throw new RuntimeException("Erro ao recuperar debito: fornecedor nao existe.");
		} else if (!this.fornecedores.get(fornecedor).haConta(cpf)) {
			throw new RuntimeException("Erro ao recuperar debito: cliente nao tem debito com fornecedor.");
		} else if (this.fornecedores.get(fornecedor).getDebito(cpf) == 0) {
			throw new RuntimeException("Erro ao recuperar debito: cliente nao tem debito com fornecedor.");
		}
		this.verificacoes.verificaCpf(cpf, "Erro ao recuperar debito: ");
		this.verificacoes.verificaFornecedor(fornecedor, "Erro ao recuperar debito: ");
		return this.fornecedores.get(fornecedor).getDebito(cpf);
	}
	
	/**
	 * gera uma representação textual de todas as compras
	 * ordenadas pelo nome do fornecedor.
	 * 
	 * @return uma representação textual.
	 */
	public String retornaComprasPorFornecedor() {
		String finn = "";
		defineFornecedores();
		Collections.sort(this.listFornecedores, new OrdenaPorNomeFornecedor());
		for (int i = 0; i < this.listFornecedores.size(); i++) {
			finn += this.listFornecedores.get(i).retornaComprasPorFornecedor() + "\n";
		}
		return finn.substring(0, finn.length() - 1);
	}
	
	/**
	 * define a lista de fornecedores (para ordenação) com base no
	 * HashSet de fornecedores do sistema.
	 */
	private void defineFornecedores() {
		for (Fornecedor f : this.fornecedores.values()) {
			this.listFornecedores.add(f);
		}
	}
	
	/**
	 * junta todas as compras do sistema em uma lista (para ordenação)
	 */
	private void preencheContas() {
		for (Fornecedor f : this.fornecedores.values()) {
			HashMap<String, Conta> cont = (HashMap<String, Conta>) f.getContas();
			for (Conta c : cont.values()) {
				if (!this.contas.contains(c)) {
					this.contas.add(c);
				}
			}
		}
	}
	
	public Object getFornecedores() {
		return this.fornecedores;
	}
	
	/**
	 * exibe todos os produtos de um dado fornecedor
	 * @param fornecedor o nome do fornecedor
	 * @return um representação textual
	 */
	public String exibeProdutos(String fornecedor) throws RuntimeException {
		this.verificacoes.verificaFornecedor(fornecedor, "Erro na exibicao de produto ");
		if (!this.fornecedores.containsKey(fornecedor)) {
			throw new RuntimeException("Erro na exibicao de produto: fornecedor nao existe.");
		} else {
			return this.fornecedores.get(fornecedor).geraRepProdutos(); 
		}
	}
	
	/**
	 * exibe uma representação textual de todas as contas de um
	 * dado cliente com um determinado fornecedor.
	 * 
	 * @param cpf o cpf do cliente
	 * @param fornecedor o nome do fornecedor
	 * @return uma representação textual
	 */
	public String exibeContas(String cpf, String fornecedor) throws RuntimeException {
		if (!this.fornecedores.containsKey(fornecedor)) {
			throw new RuntimeException("Erro ao exibir conta do cliente: fornecedor nao existe.");
		} else if (!this.fornecedores.get(fornecedor).haConta(cpf)) {
			throw new RuntimeException("Erro ao exibir conta do cliente: cliente nao tem nenhuma conta com o fornecedor.");
		}
		this.verificacoes.verificaCpf(cpf, "Erro ao exibir conta do cliente: ");
		this.verificacoes.verificaFornecedor(fornecedor, "Erro ao exibir conta do cliente: ");
		return this.fornecedores.get(fornecedor).exibeConta(cpf);
	}
	
	/**
	 * exibe as contas de um determinado cliebte com todos os fornecedores
	 * cadastrados no sistema.
	 * 
	 * @param cpf o cpf do cliente
	 * @param nomeCliente o nome do cliente
	 * @return uma representação textual
	 */
	public String exibeConta(String cpf, String nomeCliente) throws RuntimeException {
		String finn = "Cliente: " + nomeCliente + "\n";
		int cont = 0;
		for (Fornecedor f : this.fornecedores.values()) {
			if (f.haConta(cpf)) {
				cont += 1;
				finn += f.geraRepCompras(cpf) + "\n";
			}
		}
		if (cont == 0) {
			throw new RuntimeException("Erro ao exibir contas do cliente: cliente nao tem nenhuma conta.");
		}
		return finn.substring(0, finn.length() - 1);
	}

	/**
	 * gera uma representação de todas as compras cadastradas no sistema
	 * ordenadas pelo nome do cliente.
	 * 
	 * @return uma representação textual
	 */
	public String retornarComprasPorCliente() {
		String finn = "";
		preencheContas();
		Collections.sort(this.contas, new OrdenaComprasPorCliente());
		for (int i = 0; i < this.contas.size(); i++) {
			finn += this.contas.get(i).retornaComprasPorCliente() + "\n";
		}
		return finn.substring(0, finn.length() - 1);
	}
	
	/**
	 * faltou o ordena por compras... não deu...
	 */
	
	public String pegaEmail(String fornecedor) {
		if (this.fornecedores.containsKey(fornecedor)) {
			return this.fornecedores.get(fornecedor).getEmail();
		}
		return "";
	}
	
	public String pegaTelefone(String fornecedor) {
		if (this.fornecedores.containsKey(fornecedor)) {
			return this.fornecedores.get(fornecedor).getTelefone();
		}
		return "";
	}
	
	/**
	 * Define o criterio de ordenação.
	 * 
	 * @param criterio o criterio de ordenação
	 */
	public void ordenaPor(String criterio) throws RuntimeException {
		this.verificacoes.verificaCriterio(criterio, "Erro na listagem de compras: ");
		if (criterio.equals("Cliente")) {
			this.criterio = criterio;
		} else if (criterio.equals("Fornecedor")) {
			this.criterio = criterio;
		} else if (criterio.equals("Data")) {
			this.criterio = criterio;
		} else {
			throw new RuntimeException("Erro na listagem de compras: criterio nao oferecido pelo sistema.");
		}
	}
	
	/**
	 * Lista as compras de acordo com o criterio definido pelo sistema.
	 * 
	 * @param f o fornecedorController para ter acesso às compras
	 * @return uma representação textual
	 */
	public String listarCompras() throws RuntimeException {
		if (this.criterio.equals("")) {
			throw new RuntimeException("Erro na listagem de compras: criterio ainda nao definido pelo sistema.");
		} else if (this.criterio.equals("Fornecedor")) {
			return retornaComprasPorFornecedor();
		} else if (this.criterio.equals("Cliente")) {
			return retornarComprasPorCliente();
		}
		return "";
	}
}
