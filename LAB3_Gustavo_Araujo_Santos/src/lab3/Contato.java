package lab3;

/**
 * Contato é a classe que guarda os atributos dos contatos.
 * Guarda também o método de exibição de alguns dos seus atributos.
 * Além dos métodos de conferência dos atributos.
 * 
 * @author Gustavo Araujo Santos - 119210741
 *
 */
public class Contato {

	private String nome;
	private String sobreNome;
	private String ctt1;
	private String ctt2;
	private String ctt3;
	private String cttPriori;
	private String cttZap;
	private String p;
	private String z;
	private String nvAmizade;

	/**
	 * O construtor de Contato faz diversas verificações para concluir a definição
	 * de alguns dos seus atributos. Ele define referências para o contato prioritário e
	 * para o contato zap no intuitito de facilitar a exibição dos contatos.
	 * 
	 * @param nome o nome do contato
	 * @param sobreNome o sobrenome do contato
	 * @param ctt1 contato 1
	 * @param ctt2 contato 2
	 * @param ctt3 contato 3
	 * @param cttPriori contato prioritário
	 * @param cttZap contato zap
	 * @param nvAmizade nível de amizade
	 */
	public Contato(String nome, String sobreNome, String ctt1, String ctt2, String ctt3, String cttPriori,String cttZap, String nvAmizade) {
		
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.ctt1 = ctt1;
		this.ctt2 = ctt2;
		this.ctt3 = ctt3;
		this.cttPriori = cttPriori;
		this.cttZap = cttZap;
		this.p = "";
		this.z = "";
		
		/**
		 * Por padrão, um nível de amizade inicia como 1.
		 */
		if (nvAmizade.equals("")) {
			this.nvAmizade = "1"; // nível de amizade padrão
		} else {
			if (Integer.parseInt(nvAmizade) < 1 || Integer.parseInt(nvAmizade) > 5) { // verifica se o nível indicado está fora do intervalo.
				this.nvAmizade = "1";
			} else {
				this.nvAmizade = nvAmizade;
			}
		}
		
		defRefToPriori();
		defRefToZap();
		verificaValidade();
		
	}

	private void verificaValidade() {
		// não tenho como receber null da entrada padrão... Será tratado como String
		if (this.nome.equals("null")) {
			throw new NullPointerException("Nome nulo!");
		}
		if (this.nome.equals("")) {
			throw new IllegalArgumentException("Nome vazio!");
		} else {
			int cont = 0;
			for (int i = 0; i < this.nome.length(); i++) {
				if (this.nome.charAt(i) == ' ') {
					cont += 1;
				}
			}
			if (cont == this.nome.length()) {
				throw new IllegalArgumentException("Nome formado apenas por espaços!");
			}
		}
	}
	
	private void defRefToZap() {
		// define uma referência para o contato zap
		if (this.cttZap.equals("")) {
			this.z = " "; // para que todos os contatos não sejam prioritarios
		} else {
			if (this.ctt1 != "") {
				if (Integer.parseInt(this.cttZap) == 1) {
					this.z = this.ctt1;
				}
			} if (this.ctt2 != "") {
				if (Integer.parseInt(this.cttZap) == 2) {
					this.z = this.ctt2;
				}
			} if (this.ctt3 != "") {
				if (Integer.parseInt(this.cttZap) == 3) {
					this.z = this.ctt3;
				}
			} if (this.z.equals("")) {
				this.z = " ";
			}
		}
	}

	private void defRefToPriori() {
		// define uma referência para o contato prioritario
		if (this.cttPriori.equals("")) {
			this.p = " "; // para que todos os contatos não sejam prioritarios
		} else {
			if (this.ctt1 != "") {
				if (Integer.parseInt(cttPriori) == 1) {
					this.p = this.ctt1;
				}
			} if (this.ctt2 != "") {
				if (Integer.parseInt(cttPriori) == 2) {
					this.p = this.ctt2;
				}
			} if (this.ctt3 != "") {
				if (Integer.parseInt(cttPriori) == 3) {
					this.p = this.ctt3;
				}
			} if (this.p.equals("")) {
				this.p = " ";
			}
		}
	}
	
	/**
	 * Método responsável por formar uma reprentação textual dos atributos
	 * deste objeto. 
	 * 
	 * @return
	 */
	public String exibeContato() {
		String finn = this.nome + " " + this.sobreNome;
		if (!this.ctt1.equals("")) {
			finn += "\n" + this.ctt1;
		}
		if (this.ctt1.equals(this.p) && this.ctt1 != "") {
			finn += " (prioritario)";
		}
		if (this.ctt1.equals(this.z) && this.ctt1 != ""){
			finn += " (zap)";
		}
		if (!this.ctt2.equals("")) {
			finn += "\n" + this.ctt2;
		}
		if (this.ctt2.equals(this.p) && this.ctt2 != "") {
			finn += " (prioritario)";
		} 
		if (this.ctt2.equals(this.z) && this.ctt2 != ""){
			finn += " (zap)";
		}
		if (!this.ctt3.equals("")) {
			finn += "\n" + this.ctt3;
		}
		if (this.ctt3.equals(this.p) && this.ctt3 != "") {
			finn += " (prioritario)";
		} 
		if (this.ctt3.equals(this.z) && this.ctt3 != "") {
			finn += " (zap)";
		}
		return finn;
	}

	public String getNome() {
		return this.nome;
	}
	
	public String getSobreNome() {
		return this.sobreNome;
	}
	
	public String getPriori() {
		return this.p;
	}
	
	public String getZap() {
		return this.z;
	}
	
	public String getNvAmizade() {
		return this.nvAmizade;
	}
	
}
