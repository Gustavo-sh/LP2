package lab4;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * ControleDeAlunos faz as operações em cima das demais classes
 * e armazena esses objetos. 
 * 
 * @author Gustavo Araujo Santos - 119210741
 *
 */
public class ControleDeAlunos {

	private HashMap<String, Aluno> alunos;
	private ArrayList<GrupoDeEstudos> grupos;
	private ArrayList<Aluno> alunosQueResponderam;
	private Aluno aluno;
	
	public ControleDeAlunos() {
		this.alunos = new HashMap<String, Aluno>();
		this.grupos = new ArrayList<GrupoDeEstudos>();
		this.alunosQueResponderam = new ArrayList<Aluno>();
		this.aluno = new Aluno("119210741", "Gustavo", "CC");
	}
	
	/**
	 * Cadastra um aluno no mapa de Alunos.
	 * 
	 * @param matricula matrícula do aluno a ser cadastrado
	 * @param nome nome do aluno
	 * @param curso curso do aluno
	 * 
	 * @return uma mensagem de sucesso ou erro no cadastro
	 */
	public String cadastraAluno(String matricula, String nome, String curso) {
		if (matricula.equals("") || nome.equals("") || curso.equals("")) {
			throw new IllegalArgumentException("Matrícula, nome ou curso vazios!");
		} else if (matricula.equals("null") || nome.equals("null") || curso.equals("null")) {
			throw new NullPointerException("Matrícula, nome ou curso nulos!");
		} else if (aluno.verificaStringsVazias(matricula, nome, curso)) {
			throw new IllegalArgumentException("Matrícula, nome ou curso formado(s) apenas por espaço(s)!");
		}
		if (this.alunos.containsKey(matricula)) {
			return "MATRÍCULA JÁ CADASTRADA!";
		}
		Aluno aluno = new Aluno(matricula, nome, curso);
		this.alunos.put(matricula, aluno);
		return "CADASTRO REALIZADO!";
	}
	
	/**
	 * Exibe um aluno cadastrado.
	 * 
	 * @param matricula matrícula do aluno a ser procurado.
	 * 
	 * @return uma representação textual do aluno ou uma mensagem de erro
	 */
	public String consultaAluno(String matricula) {
		if (matricula.equals("")) {
			throw new IllegalArgumentException("Matrícula vazia!");
		} else if (matricula.equals("null")) {
			throw new NullPointerException("Matrícula nula!");
		}
		int cont = 0;
		for (int i = 0; i < matricula.length(); i++) {
			if (matricula.charAt(i) == ' ') {
				cont += 1;
			}
		}
		if (cont == matricula.length()) {
			throw new IllegalArgumentException("Matrícula formada apenas por espaços!");
		}
		String finn = "Aluno: ";
		if (!this.alunos.containsKey(matricula)) {
			return "Aluno não cadastrado.";
		}
		for (String str : this.alunos.keySet()) {
			if (str.equals(matricula)) {
				finn += this.alunos.get(str).toString();
				break;
			}
		}
		return finn;
	}
	
	/**
	 * Cadastra um grupo no ArrayList de GruposDeEstudo.
	 * 
	 * @param nome nome do grupo a ser cadastrado
	 * 
	 * @return uma mensagem de sucesso ou erro
	 */
	public String cadastraGrupo(String nome) {
		if (nome.equals("")) {
			throw new IllegalArgumentException("Grupo vazio!");
		} else if (nome.equals("null")) {
			throw new NullPointerException("Grupo nulo!");
		}
		int cont = 0;
		for (int i = 0; i < nome.length(); i++) {
			if (nome.charAt(i) == ' ') {
				cont += 1;
			}
		}
		if (cont == nome.length()) {
			throw new IllegalArgumentException("Nome do Grupo formado apenas por espaços!");
		}
		for (int i = 0; i < this.grupos.size(); i++) {
			if (this.grupos.get(i).getNome().toLowerCase().equals(nome.toLowerCase())) {
				return "GRUPO JÁ CADASTRADO!";
			}
		}
		GrupoDeEstudos grup = new GrupoDeEstudos(nome);
		this.grupos.add(grup);
		return "CADASTRO REALIZADO!";
	}
	
	/**
	 * Aloca um aluno em um ArrayList existente demtro de um GrupoDeEstudos.
	 * 
	 * @param matricula matrícula do aluno a ser alocado
	 * @param grupo grupo de estudo onde o aluno será alocado
	 * 
	 * @return uma mensagem de sucesso ou erro
	 */
	public String alocaAluno(String matricula, String grupo) {
		if (matricula.equals("") || grupo.equals("")) {
			throw new IllegalArgumentException("Matrícula/Grupo vazio(s)!");
		} else if (matricula.equals("null") || grupo.equals("null")) {
			throw new NullPointerException("Matrícula/Grupo nulo(s)!");
		}
		int cont1 = 0;
		int cont2 = 0;
		for (int i = 0; i < matricula.length(); i++) {
			if (matricula.charAt(i) == ' ') {
				cont1 += 1;
			}
		}
		for (int i = 0; i < grupo.length(); i++) {
			if (grupo.charAt(i) == ' ') {
				cont2 += 1;
			}
		}
		if (cont1 == matricula.length() || cont2 == grupo.length()) {
			throw new IllegalArgumentException("Matrícula/Grupo formado(s) apenas por espaço(s)!");
		}
		Aluno alun = new Aluno("1234", "Lukinhas do grau", "VrumVrum");
		boolean houveAluno = false;
		boolean houveGrupo = false;
		for (String str : this.alunos.keySet()) {
			if (str.equals(matricula)) {
				alun = this.alunos.get(str);
				houveAluno = true;
			}
		}
		if (!houveAluno) {
			return "Aluno não cadastrado.\nGrupo não cadastrado.";
		}
		for (int i = 0; i < this.grupos.size(); i++) {
			if (this.grupos.get(i).getNome().toLowerCase().equals(grupo.toLowerCase())) {
				this.grupos.get(i).alocaAluno(alun);
				houveGrupo = true;
			}
		}
		if (!houveGrupo) {
			return "Aluno não cadastrado.\nGrupo não cadastrado.";
		}
		return "ALUNO ALOCADO!";
	}
	
	/**
	 * Imprime os alunos de um grupo de estudo específico.
	 * 
	 * @param nome o nome do grupo
	 * 
	 * @return uma representação textual contendo todos os alunos
	 * alocados naquele grupo ou uma das mensagens de erro.
	 */
	public String imprimeGrupo(String nome) {
		if (nome.equals("")) {
			throw new IllegalArgumentException("Grupo vazio!");
		} else if(nome.equals("null")) {
			throw new NullPointerException("Grupo nulo!");
		}
		int cont = 0;
		for (int i = 0; i < nome.length(); i++) {
			if (nome.charAt(i) == ' ') {
				cont += 1;
			}
		}
		if (cont == nome.length()) {
			throw new IllegalArgumentException("Nome do Grupo formado apenas por espaços!");
		}
		for (int i = 0; i < this.grupos.size(); i++) {
			if (this.grupos.get(i).getNome().toLowerCase().equals(nome.toLowerCase())) {
				return this.grupos.get(i).imprimeGrupo();
			}
		}
		return "Grupo não cadastrado.";
	}
	
	/**
	 * Cadastra um aluno que respondeu no quadro.
	 * 
	 * @param matricula matrícula do aluno que respondeu
	 * 
	 * @return uma mensagem de sucesso ou erro
	 */
	public String cadastraAlunoRespondeuQuadro(String matricula) {
		if (matricula.equals("")) {
			throw new IllegalArgumentException("Matrícula vazia!");
		} else if (matricula.equals("null")) {
			throw new NullPointerException("Matrícula nula!");
		}
		int cont = 0;
		for (int i = 0; i < matricula.length(); i++) {
			if (matricula.charAt(i) == ' ') {
				cont += 1;
			}
		}
		if (cont == matricula.length()) {
			throw new IllegalArgumentException("Matrícula formada apenas por espaços!");
		}
		if (!this.alunos.containsKey(matricula)) {
			return "Aluno não cadastrado.";
		}
		Aluno alun = new Aluno("1234", "Lukinhas do grau", "VrumVrum");
		for (String str : this.alunos.keySet()) {
			if (str.equals(matricula)) {
				alun = this.alunos.get(str);
			}
		}
		this.alunosQueResponderam.add(alun);
		return "ALUNO REGISTRADO!";
	}
	
	/**
	 * Imprime todos os alunos que responderam no quadro.
	 * 
	 * @return uma representação textual de todos cadastrados ou
	 * "Nenhum aluno cadastrado.".
	 */
	public String imprimeAlunosQueResponderam() {
		String finn = "Alunos:\n";
		int num = 1;
		for (Aluno alun : this.alunosQueResponderam) {
			finn += num + ". " + alun.toString() + "\n";
			num += 1;
		}
		if (finn.equals("Alunos:\n")) {
			return "Nenhum aluno cadastrado.";
		}
		return finn.substring(0, finn.length() - 1);
	}
}
