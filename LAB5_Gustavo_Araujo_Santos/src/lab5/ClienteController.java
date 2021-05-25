package lab5;

import java.util.HashMap;
/**
 * Classe que controla as ações que ocorrem
 * em cima dos clientes. Além das operações
 * com os clientes, esta classe também armazena
 * os mesmos.
 */
public class ClienteController {

	private HashMap<String, Cliente> clientes;
	private Exceptions verificacoes;
	
	public ClienteController() {
		this.clientes = new HashMap<String, Cliente>();
		this.verificacoes = new Exceptions();
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
	public String adicionaCliente(String cpf, String nome, String email, String localizacao) throws RuntimeException {
		this.verificacoes.validacaoNoCadastroCliente(cpf, nome, email, localizacao, "Erro no cadastro do cliente: ");
		if (this.clientes.containsKey(cpf)) {
			throw new RuntimeException("Erro no cadastro do cliente: cliente ja existe.");
		}
		Cliente cliente = new Cliente(cpf, nome, email, localizacao);
		this.clientes.put(cpf, cliente);
		return cpf;
	}
	
	/**
	 *Método que gera uma representação textual de
	 *determinado cliente cadastrado no sistema.
	 * 
	 * @param cpf cpf do cliente a ser exibido
	 * @return retorna uma representação do cliente
	 */
	public String exibeCliente(String cpf) {
		if (cpf == "" || cpf == null) {
			this.verificacoes.verificaCpf(cpf, "Erro na exibicao do cliente: ");
		} else if (cpf.length() < 11 || cpf.length() > 11) {
			throw new RuntimeException("Erro na exibicao do cliente: cpf invalido.");
		}
		if (!this.clientes.containsKey(cpf)) {
	    	throw new RuntimeException("Erro na exibicao do cliente: cliente nao existe.");
	    }
	    return this.clientes.get(cpf).toString();
	}
	
	/**
	 * Método que edita deternado atributo de um cliebte.
	 * o cpf não pode ser alterado.
	 * @param cpf o cpf do cliente que terá o atributo alterado
	 * @param atributo o atributo a ser alterado
	 * @param novoValor o novo valor a ser colocado
	 * @return o cpf do cliente que teve o atributo alterado
	 */
	public String editaCliente(String cpf, String atributo, String novoValor) throws RuntimeException {
		this.verificacoes.verificaAtributo(atributo, "Erro na edicao do cliente: ");
		this.verificacoes.verificaNovoValor(novoValor, "Erro na edicao do cliente: ");
		if (atributo.equals("cpf")) {
			throw new RuntimeException("Erro na edicao do cliente: cpf nao pode ser editado.");
		} 
		this.verificacoes.verificaCpf(cpf, "Erro na edicao do cliente: ");
		if (!this.clientes.containsKey(cpf)) {
			throw new RuntimeException("Erro na edicao do cliente: cliente nao existe.");
		} 
		if (atributo != "nome" && atributo != "email" && atributo != "localizacao") {
			throw new RuntimeException("Erro na edicao do cliente: atributo nao existe.");
		}
		this.clientes.get(cpf).setAtributo(atributo, novoValor);
		return cpf;
	}
	
	/**
	 *Método que deleta determinado cliente do sistema. 
	 * @param cpf o cpf do cliente a ser deletado.
	 * @return o cpf do cliente deletado
	 */
	public String removeCliente(String cpf) throws RuntimeException {
		this.verificacoes.verificaCpfRemocao(cpf, "Erro na remocao do cliente: ");
		if (!this.clientes.containsKey(cpf)) {
			throw new RuntimeException("Erro na remocao do cliente: cliente nao existe.");
		} else if (cpf.length() > 11 || cpf.length() < 11) {
			throw new RuntimeException("Erro na remocao do cliente: cpf invalido.");
		}
		this.clientes.remove(cpf, this.clientes.get(cpf));
		return cpf;
	}
	
	/**
	 * Método que verifica se o cpf passado
	 * já está cadastrado no sistema.
	 * @param cpf o cpf a ser verificado
	 * @return true caso já esteja. false, caso não.
	 */
	public boolean haCpf(String cpf) {
		if (this.clientes.containsKey(cpf)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Método que pega o nome de um cliente
	 * com o acf informado.
	 * @param cpf o cpf do cliente
	 * @return o nome do cliente
	 */
	public String pegaNome(String cpf) {
		if (this.clientes.containsKey(cpf)) {
			return this.clientes.get(cpf).getNome();
		}
	    return "";
	}
	
	/**
	 * Método que retorna todos os clientes cadastrados no sistema.
	 * @return os clientes cadatrastrados
	 */
	public Object getClientes() {
		return this.clientes;
	}
	
	public String pegaLocalizacao(String cpf) {
		if (this.clientes.containsKey(cpf)) {
			return this.clientes.get(cpf).getLocalizacao();
		}
		return "";
	}
	
	public String pegaEmail(String cpf) {
		if (this.clientes.containsKey(cpf)) {
			return this.clientes.get(cpf).getEmail();
		}
		return "";
	}
}