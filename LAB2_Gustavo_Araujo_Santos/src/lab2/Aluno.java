package lab2;
/**
 * Aluno é o intermediário de COISA e de todas as outras classes do sistema.
 * Ele controla todas as ações que são feitas em COISA, guarda
 * e altera todos os objetos do sistema.
 * 
 * @author Gustavo A. Santos - 119210741
 */
public class Aluno {
	/**
	 * Os arrays dos objetos presentes no sistema e o
	 * estado de saude do aluno.
	 */
	private ContaLaboratorio[] laboratorios = new ContaLaboratorio[1000];
	private int posLabs = 0;
	private Disciplina[] disciplinas = new Disciplina[1000];
	private int posDisc = 0;
	private ContaCantina[] cantinas = new ContaCantina[1000];
	private int posCant = 0;
	private Saude saude = new Saude();
	
	/*
	 * Todos os métodos não documentados abaixo, seguem o mesmo
	 * padrão dos métodos presentes nas outras classes que já
	 * foram documentados. Com a excessão de que os daqui
	 * buscam o elemento no array para poder fazer a ação
	 * sobre esse elemento.
	 */
	public void cadastraLaboratorio(String nomeLaboratorio) {
		ContaLaboratorio lab = new ContaLaboratorio(nomeLaboratorio);
		laboratorios[posLabs] = lab;
		posLabs += 1;
	}

	public void cadastraLaboratorio(String nomeLaboratorio, int cota) {
		ContaLaboratorio lab = new ContaLaboratorio(nomeLaboratorio, cota);
		laboratorios[posLabs] = lab;
		posLabs += 1;
	}
	
	public void consomeEspaco(String nomeLaboratorio, int mbytes) {
		for(int i = 0; i < posLabs; i++) {
			if(laboratorios[i].getNome().equals(nomeLaboratorio)) {
				laboratorios[i].consomeEspaco(mbytes);
			}
		}
	}
	
	public void liberaEspaco(String nomeLaboratorio, int mbytes) {
		for(int i = 0; i < posLabs; i++) {
			if(laboratorios[i].getNome().equals(nomeLaboratorio)) {
				laboratorios[i].liberaEspaco(mbytes);
			}
		}
	}
	
	public boolean atingiuCota(String nomeLaboratorio) {
		for(int i = 0; i < posLabs; i++) {
			if(laboratorios[i].getNome().equals(nomeLaboratorio)) {
				return laboratorios[i].atingiuCota();
			}
		}
		return false;
	}
	
	public String laboratorioToString(String nomeLaboratorio) {
		for(int i = 0; i < posLabs; i++) {
			if(laboratorios[i].getNome().equals(nomeLaboratorio)) {
				return laboratorios[i].toString();
			}
		}
		return "";
	}
	
	public void cadastraDisciplina(int quantN, String nomeDisciplina) {
		Disciplina disci = new Disciplina(quantN, nomeDisciplina);
		disciplinas[posDisc] = disci;
		posDisc += 1;
		
	}
	
	public void cadastraDisciplina(int quantN, String[] pesos, String nomeDisc) {
		Disciplina disci = new Disciplina(quantN, pesos, nomeDisc);
		disciplinas[posDisc] = disci;
		posDisc += 1;
	}
	
	public void cadastraHoras(String nomeDisciplina, int horas) {
		for(int i = 0; i < posDisc; i++) {
			if(disciplinas[i].getNome().equals(nomeDisciplina)) {
				disciplinas[i].cadastraHoras(horas);
			}
		}
	}
	
	public void cadastraNota(String nomeDisciplina, int nota, double valorNota) {
		for(int i = 0; i < posDisc; i++) {
			if(disciplinas[i].getNome().equals(nomeDisciplina)) {
				disciplinas[i].cadastraNota(nota, valorNota);
			}
		}
	}
	
	public boolean aprovado(String nomeDisciplina) {
		for(int i = 0; i < posDisc; i++) {
			if(disciplinas[i].getNome().equals(nomeDisciplina)) {
				return disciplinas[i].aprovado();
			}
		}
		return false;
	}
	
	public String disciplinaToString(String nomeDisciplina) {
		for(int i = 0; i < posDisc; i++) {
			if(disciplinas[i].getNome().equals(nomeDisciplina)) {
				return disciplinas[i].toString();
			}
		}
		return "";
	}
	
	public void cadastraCantina(String nomeCantina) {
		ContaCantina cant = new ContaCantina(nomeCantina);
		cantinas[posCant] = cant;
		posCant += 1;
	}
	
	public void cadastraLanche(String nomeCantina, int qtdItens, int valorCentavos) {
		for(int i = 0; i < posCant; i++) {
			if(cantinas[i].getNome().equals(nomeCantina)) {
				cantinas[i].cadastraLanche(qtdItens, valorCentavos);
			}
		}
	}
	
	public void pagarConta(String nomeCantina, int valorCentavos) {
		for(int i = 0; i < posCant; i++) {
			if(cantinas[i].getNome().equals(nomeCantina)) {
				cantinas[i].pagaConta(valorCentavos);
			}
		}
	}
	
	public int getFaltaPagar(String nomeCantina) {
		for(int i = 0; i < posCant; i++) {
			if(cantinas[i].getNome().equals(nomeCantina)) {
				return cantinas[i].getFaltaPagar();
			}
		}
		return -1;
	}
	
	public String cantinaToString(String nomeCantina) {
		for(int i = 0; i < posCant; i++) {
			if(cantinas[i].getNome().equals(nomeCantina)) {
				return cantinas[i].toString();
			}
		}
		return "";
	}
	
	public void defineSaudeMental(String valor) {
		this.saude.defineSaudeMental(valor);
	}
	
	public void defineSaudeFisica(String valor) {
		this.saude.defineSaudeFisica(valor);
	}
	
	public String getStatusGeral() {
		return this.saude.getStatusGeral();
	}
	
	public void defineEmoji(String valor) {
		this.saude.defineEmoji(valor);
	}
	
	public void cadastraLanches(String nomeCantina, int qtdItens, int valorCentavos, String detalhe) {
		for(int i = 0; i < posCant; i++) {
			if(cantinas[i].getNome().equals(nomeCantina)) {
				cantinas[i].cadastraLanche(qtdItens, valorCentavos, detalhe);
			}
		}
	}
	
	public String listaDetalhes(String nomeCant) {
		for(int i = 0; i < posCant; i++) {
			if(cantinas[i].getNome().equals(nomeCant)) {
				return cantinas[i].listarDetalhes();
			}
		}
		return "";
	}

	/**
	 * Método de busca. Serve para achar o objeto de ContaLaboratorio no
	 * array laboratorios.
	 * 
	 * @param nomeL o nome do laboratório a ser achado.
	 * @return true caso ele esteja no array, false caso
	 * ele não esteja.
	 */
	public boolean buscaLab(String nomeL) {
		for(int i = 0; i < posLabs; i++) {
			if(laboratorios[i].getNome().equals(nomeL)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Método de busca. Serve para achar o objeto de Disciplina no
	 * array disciplinas.
	 * 
	 * @param nomeD o nome do laboratório a ser achado.
	 * @return true caso ele esteja no array, false caso
	 * ele não esteja.
	 */
	public boolean buscaDisc(String nomeD) {
		for(int i = 0; i < posDisc; i++) {
			if(disciplinas[i].getNome().equals(nomeD)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Método de busca. Serve para achar o objeto de ContaCantina no
	 * array cantinas.
	 * 
	 * @param nomeC o nome do laboratório a ser achado.
	 * @return true caso ele esteja no array, false caso
	 * ele não esteja.
	 */
	public boolean buscaCant(String nomeC) {
		for(int i = 0; i < posCant; i++) {
			if(cantinas[i].getNome().equals(nomeC)) {
				return true;
			}
		}
		return false;
	}
}
