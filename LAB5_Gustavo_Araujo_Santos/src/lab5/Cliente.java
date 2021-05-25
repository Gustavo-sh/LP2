package lab5;

/**
 * Classe básica do sistema. Guarda as informações sobre
 * os clientes.
 * 
 * @author Gustavo Araujo Santos - 119210741
 *
 */
public class Cliente {

	private String cpf;
	private String nome;
	private String email;
	private String localizacao;
	
	public Cliente(String cpf, String nome, String email, String localizacao) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.localizacao = localizacao;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) { return false; }
		if (this.getClass() != o.getClass()) { return false; }
		Cliente cliente = (Cliente) o;
		return this.cpf.equals(cliente.cpf);
	}
	
	@Override
	public int hashCode() {
		return this.cpf.hashCode();
	}
	
	@Override
	public String toString() {
		return (this.nome + " - " + this.localizacao + " - " + this.email);
	}
	
	/**
	 * Método que modifica determinado atributo
	 * passsado como parâmetro.
	 */
	public void setAtributo(String atributo, String novoValor) {
		if (atributo.equals("nome")) {
			this.nome = novoValor;
		} else if (atributo.equals("email")) {
			this.email = novoValor;
		} else if (atributo.equals("localizacao")) {
			this.localizacao = novoValor;
		}
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getLocalizacao() {
		return this.localizacao;
	}
	
	public String getEmail() {
		return this.email;
	}
}
