package lab3;

/**
 * Agenda é a classe "repositório". Ela guarda o array de 100
 * objetos do tipo Contato, faz a criação de novos objetos desse tipo e
 * o cadastro desses ojetos no array. Faz também a exibição das informações
 * dos Contatos. Todos os métodos que exibem todos os objetos foram implementados
 * nesta classe, apenas o método que exibe apenas um contato ficou na classe Contato.
 * 
 * @author Gustavo Araujo Santos - 119210741
 *
 */
public class Agenda {
	private Contato[] contatos;
	
	public Agenda() {
		this.contatos = new Contato[100];
	}
	
	/**
	 * Método que cadastra um novo contato no array de contatos.
	 * Tratamos a posição de cadastro como sendo a posição indicada pelo usuário
	 * menos um para evitar o acesso da posição 100 no array.
	 * 
	 * @param pos posição indicada pelo usuário
	 * @param nome nome do contato
	 * @param sobreNome sobrenome do contato
	 * @param ctt1 contato 1
	 * @param ctt2 contato 2
	 * @param ctt3 contato 3
	 * @param cttPriori contato prioritário
	 * @param cttZap contato do whats
	 * @param nvAmizade nível de amizade com o contato
	 * @return 1 caso o contato seja cadastrado, 0 caso a posição seja inválida.
	 */
	public int cadastraContato(int pos, String nome, String sobreNome, String ctt1, String ctt2, String ctt3, String cttPriori, String cttZap, String nvAmizade) {
		if (pos < 1 || pos > 100) {
			return 0;
		}
		Contato contato = new Contato(nome, sobreNome, ctt1, ctt2, ctt3, cttPriori, cttZap, nvAmizade);
		this.contatos[pos - 1] = contato;
		return 1;
	}
	
	private boolean ehValido(int pos) {
		if (pos < 1 || pos > 100) {
			return false;
		}
		return true;
	}
	
	/**
	 * Método que pede uma interpretação textual, presente na classe Contato,
	 * a um objeto do tipo Contato presente no array.
	 * 
	 * @param pos posição do objeto no array.
	 * @return uma interpretação textual dos atributos do Contato
	 */
	public String exibeContato(String pos) {
		if (ehValido(Integer.parseInt(pos))) {
			if (this.contatos[Integer.parseInt(pos) - 1] != null) {
				return this.contatos[Integer.parseInt(pos) - 1].exibeContato();
			} else {
				return "Nenhum contato cadastrado nessa posição!";
			}
		} else {
			return "Posição inválida!";
		}
	}
	
	/**
	 * Método que forma uma interpretação textual de TODOS os contatos do array.
	 * 
	 * @return uma interpretação textual de todos os contatos do array.
	 */
	public String listaContatos() {
		String finn = "";
		for (int i = 0; i < 100; i++) {
			if (this.contatos[i] != null) {
				finn += (i + 1) + " " + "-" + " " + this.contatos[i].getNome() + " " + this.contatos[i].getSobreNome() + "\n";
			}
		}
		if (finn != "") {
			return finn;
		} else {
			return "Nenhum contato cadastrado!";
		}
	}
	
	/**
	 * Método que forma uma interpretação textual de TODOS os contatos prioritários.
	 * 
	 * @return uma interpretação textual de todos os objetos prioritários.
	 */
	public String listaContatosPriori() {
		String finn = "";
		for (int i = 0; i < 100; i++) {
			if (this.contatos[i] != null) {
				if (this.contatos[i].getPriori() != " ") {
					finn += this.contatos[i].getNome() + " " + this.contatos[i].getSobreNome() + " " + "-" + " " + this.contatos[i].getPriori() + "\n";
				} else {
					finn += this.contatos[i].getNome() + " " + this.contatos[i].getSobreNome() + " " + "-" + " " + "Não tem" + "\n";
				}
			}
		}if (finn != "") {
			return finn;
		} else {
			return "Nenhum contato prioritário cadastrado!";
		}
	}
	
	/**
	 * Método que forma uma interpretação textual de TODOS os contatos zaps.
	 * 
	 * @return uma interpretação textual de todos os objetos zaps.
	 */
	public String listaContatosZap() {
		String finn = "";
		for (int i = 0; i < 100; i++) {
			if (this.contatos[i] != null) {
				if (this.contatos[i].getZap() != " ") {
					finn += this.contatos[i].getNome() + " " + this.contatos[i].getSobreNome() + " " + "-" + " " + this.contatos[i].getZap() + "\n";
				} else {
					finn += this.contatos[i].getNome() + " " + this.contatos[i].getSobreNome() + " " + "-" + " " + "Não tem" + "\n";
				}
			}
		}
		if (finn != "") {
			return finn.substring(0, finn.length() - 1);
		} else {
			return "Nenhum contato zap cadastrado!";
		}
	}
	
	/**
	 * Método que busca todos os contatos com o mesmo nome (por não especificação não verificamos o sobreNome)
	 * e forma uma interpretação textual desses contatos.
	 * 
	 * @param nome nome a ser comparado.
	 * @return uma interpretação textual dos objetos com o mesmo nome.
	 */
	public String consultaPorNome(String nome) {
		String finn = "";
		for (int i = 0; i < 100; i++) {
			if (this.contatos[i] != null) {
				if (this.contatos[i].getNome().equals(nome)) {
					finn += (i + 1) + " " + "-" + " " + this.contatos[i].getNome() + " " + this.contatos[i].getSobreNome() + "\n";
				}
			}
		}
		if (finn != "") {
			return finn.substring(0, finn.length() - 1); // para eliminar o último \n
		} else {
			return "Nenhum contato com esse nome!";
		}
	}
	
	/**
	 * Método que busca todos os contatos com o mesmo nível de amizade e guarda o nome
	 * e sobrenome desses contatos numa String que será retornada no fim do método.
	 * 
	 * @param nv o nível de amizade que será usado como referência.
	 * @return uma interpretação textual dos contatos com o mesmo níve de amizade.
	 */
	public String consultaPorNvAmizade(String nv) {
		try {
			Integer.parseInt(nv);
		} catch (NumberFormatException nexc) {
			return "Digite um número correspondente ao nível de amizade!";
		}
		String finn = "";
		for (int i = 0; i < 100; i++) {
			if (this.contatos[i] != null) {
				if (Integer.parseInt(this.contatos[i].getNvAmizade()) == Integer.parseInt(nv)) {
					finn += (i + 1) + " " + "-" + " " + this.contatos[i].getNome() + " " + this.contatos[i].getSobreNome() + "\n";
				}
			}
		}
		if (!finn.equals("")) {
			return finn.substring(0, finn.length() - 1);
		} else {
			return "Nenhum contato com esse nível de amizade!";
		}
	}
	
	/**
	 * Método que retorna a quantidade de contatos cadastrados com um 
	 * determinado nível de amizade.
	 * 
	 * @param nv o nível de amizade a ser comparado.
	 * @return a quantidade de contatos cadastrados com o nível de amizade indicado.
	 */
	public int quantNvAmizade(String nv) {
		int cont = 0;
		for (int i = 0; i < 100; i++) {
			if (this.contatos[i] != null) {
				if (Integer.parseInt(this.contatos[i].getNvAmizade()) == Integer.parseInt(nv)) {
					cont += 1;
				}
			}
		}
		return cont;
	}
	/**
	 * Método que calcula a média das amizades dos contatos cadastrados.
	 * 
	 * @return a média das amizades.
	 */
	public double mediaAmizades() {
		double soma = 0;
		double cont = 0;
		for (int i = 0; i < 100; i++) {
			if (this.contatos[i] != null) {
				soma += Integer.parseInt(this.contatos[i].getNvAmizade());
				cont += 1;
			}
		}
		if (soma == 0) {
			return 0;
		}
		return (soma/cont);
	}
}
