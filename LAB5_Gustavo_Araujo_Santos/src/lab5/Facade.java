package lab5;

/**
 * Facade delega as operações que devem ocorrer nos
 * controllers.
 * 
 * @author Gustavo Araujo Santos - 119210741
 *
 */
public class Facade {

	private ClienteController clienteController;
	private FornecedorController fornecedorController;
	private SAGA saga;
	private String nome;
	
	public Facade() {
		this.clienteController = new ClienteController();
		this.fornecedorController = new FornecedorController();
		this.saga = new SAGA();
	}
	
	/**
	 * Método que cadastra um cliente no sistema.
	 * 
	 * @param cpf id do cliente
	 * @param nome nome do cliene
	 * @param email email do cliente
	 * @param localizacao local do cliente
	 * @return o cpd do cliente
	 */
	public void adicionaCliente(String cpf, String nome, String email, String localizacao) {
		this.clienteController.adicionaCliente(cpf, nome, email, localizacao);
	}
	
	/**
	 *Método que gera uma representação textual de
	 *determinado cliente cadastrado no sistema.
	 * 
	 * @param cpf cpf do cliente a ser exibido
	 * @return retorna uma representação do cliente
	 */
	public String exibeCliente(String cpf) {
		return this.clienteController.exibeCliente(cpf);
	}
	
	/**
	 *Método que gera uma representação textual de
	 *todos os clientes cadastrados no sistema.
	 * 
	 */
	public String exibeClientes(){
		return this.saga.exibeClientes(this.clienteController);
	}
	
	/**
	 * Método que edita deternado atributo de um cliebte.
	 * o cpf não pode ser alterado.
	 * @param cpf o cpf do cliente que terá o atributo alterado
	 * @param atributo o atributo a ser alterado
	 * @param novoValor o novo valor a ser colocado
	 * @return o cpf do cliente que teve o atributo alterado
	 */
	public void editaCliente(String cpf, String atributo, String novoValor) {
		this.clienteController.editaCliente(cpf, atributo, novoValor);
	}
	
	/**
	 *Método que deleta determinado cliente do sistema. 
	 * @param cpf o cpf do cliente a ser deletado.
	 * @return o cpf do cliente deletado
	 */
	public void removeCliente(String cpf) {
		this.clienteController.removeCliente(cpf);
	}
	
	/**
	 * Método que cadastra um fornecedor no sistema.
	 * 
	 * @param nome o nome do fornecedor
	 * @param email o email do fornecedor
	 * @param telefone o telefone do fornecedor
	 */
	public void adicionaFornecedor(String nome, String email, String telefone) {
		this.fornecedorController.adicionaFornecedor(nome, email, telefone);
	}
	
	/**
	 * Método que gera e retorna uma representação
	 * tetual de determinado fornecedor identificado
	 * pelo nome.
	 * 
	 * @param nome o nome do fornecedor
	 * @return uma representação textual
	 */
	public String exibeFornecedor(String nome) {
		return this.fornecedorController.exibeFornecedor(nome);
	}
	
	/**
	 * Método que exibe uma representação
	 * textual de todos os fornecedores
	 * cadastrados no sistama.
	 * 
	 * @return uma representação textual
	 */
	public String exibeFornecedores() {
		return this.saga.exibeFornecedores(this.fornecedorController);
	}
	
	/**
	 * Método que edita determinado atributo de uma fornecedor
	 * identificado pelo nome. O nome não pode ser alterado.
	 * 
	 * @param nome o nome do fornecedor
	 * @param atributo o atributo a ser alterado
	 * @param novoValor o novo valor do atributo
	 */
	public void editaFornecedor(String nome, String atributo, String novoValor) {
		this.fornecedorController.editaFornecedor(nome, atributo, novoValor);
	}
	
	/**
	 * Método que deleta determinado fornecedor
	 * do sistema.
	 * 
	 * @param nome o nome do fornecedor
	 * @return o nome do cliente removido 
	 */
	public String removeFornecedor(String nome) {
		return this.fornecedorController.removeFornecedor(nome);
	}
	
	/**
	 * Método que cadastra uma compra em determinada
	 * conta de cliente.
	 * 
	 * @param cpf o cpf do cliente que efetuou a compra.
	 * @param fornecedor o nome do fornecedor
	 * @param data a data da compra
	 * @param nome o nome do produto
	 * @param descricao a descricao do produto
	 */
	public void adicionaCompra(String cpf, String fornecedor, String data, String nome, String descricao) throws RuntimeException {
		if (!this.clienteController.haCpf(cpf)) {
			throw new RuntimeException("Erro ao cadastrar compra: cliente nao existe.");
		}
		String nomeCliente = this.clienteController.pegaNome(cpf);
		this.fornecedorController.adicionaCompra(cpf, fornecedor, data, nome, descricao, nomeCliente);
	}
	
	/**
	 * Método que cadastra um produto no sistema.
	 * 
	 * @param fornecedor nome do fornecedor que irá ter o produto
	 * @param nome nome do produto
	 * @param descricao descricao do produto
	 * @param preco preco do produto
	 */
	public void adicionaProduto(String fornecedor, String nome, String descricao, double preco) {
		this.fornecedorController.adicionaProduto(fornecedor, nome, descricao, preco);
	}
	
	/**
	 * Método que gera e retorna uma representação
	 * textual de determinado produto.
	 * 
	 * @param nome o nome do produto
	 * @param descricao a descricao do produto
	 * @param fornecedor o nome do fornecedor
	 * @return uma representação textual
	 */
	public String exibeProduto(String nome, String descricao, String fornecedor) {
		return this.fornecedorController.exibeProduto(fornecedor, nome, descricao);
	}
	
	/**
	 * Método que gera e retorna uma representação
	 * textual de todos os produtos de um determinado
	 * fornecedor.
	 * 
	 * @param nome o nome do fornecedor
	 * 
	 * @return uma representação textual
	 */
	public String exibeProdutosFornecedor(String nome) {
		return this.fornecedorController.exibeProdutos(nome);
	}
	
	/**
	 * Método que gera e retorna uma representação
	 * textual de todos os produtos de todos os
	 * fornecedores cadastrados no sistema.
	 * 
	 * @return uma representação textual
	 */
	public String exibeProdutos() {
		return this.saga.exibeProdutos(this.fornecedorController);
	}
	
	/**
	 * Método que edita o preco de um produto. Os
	 * demais atributos não podem ser alterados.
	 * 
	 * @param nome o nome do produto
	 * @param descricao a descricao do produto
	 * @param fornecedor o fornecedor que contém o produto
	 * @param novoPreco o novo preco do produto
	 */
	public void editaProduto(String nome, String descricao, String fornecedor, double novoPreco) {
		this.fornecedorController.editaProduto(nome, descricao, fornecedor, novoPreco);
	}
	
	/**
	 * Método que deleta determinado produto do sistema.
	 * 
	 * @param nome o nome do produto a ser deletado
	 * @param descricao a descricao do produto
	 * @param fornecedor o fornecedor que contém o produto
	 */
	public void removeProduto(String nome, String descricao, String fornecedor) {
		this.fornecedorController.removeProduto(nome, descricao, fornecedor);
	}
	
	/**
	 * Método que pega o debito de determinada conta de um
	 * fornecedor.
	 * 
	 * @param cpf o cpf da conta do cliente
	 * @param fornecedor o fornecedor que guarda a conta
	 * @return o debito do cliente com o fornecedor
	 */
	public double getDebito(String cpf, String fornecedor) throws RuntimeException {
		if (!this.clienteController.haCpf(cpf)) {
			throw new RuntimeException("Erro ao recuperar debito: cliente nao existe.");
		}
		return this.fornecedorController.getDebito(cpf, fornecedor);
	}
	
	/**
	 * Método que exibe as contas de um cliente com
	 * um determinado fornecedor.
	 * 
	 * @param cpf o cpf do cliente
	 * @param fornecedor o nome do fornecedor que guarda a conta
	 * @return um representação textual
	 */
	public String exibeContas(String cpf, String fornecedor) throws RuntimeException  {
		if (!this.clienteController.haCpf(cpf)) {
			throw new RuntimeException("Erro ao exibir conta do cliente: cliente nao existe.");
		}
		return this.fornecedorController.exibeContas(cpf, fornecedor);
	}
	
	/**
	 * Método que exibe todas as contas de um cliente com
	 * todos os fornecedores.
	 * 
	 * @param cpf o cpf do cliente
	 * @return uma representação textual
	 */
	public String exibeContasClientes(String cpf) {
		String nomeCliente = this.clienteController.pegaNome(cpf);
		return this.fornecedorController.exibeConta(cpf, nomeCliente);
	}
}