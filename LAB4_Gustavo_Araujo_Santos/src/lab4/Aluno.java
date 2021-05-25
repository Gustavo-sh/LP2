package lab4;

/**
 * Aluno é classe mais básica do sistema. Ela guarda
 * a matrícula de um aluno, seu nome e curso.
 * 
 * @author Gustavo Araujo Santos - 119210741
 *
 */
public class Aluno {

	private String matricula;
	private String nome;
	private String curso;
	
	public Aluno(String matricula, String nome, String curso) {
		if (matricula.equals("") || nome.equals("") || curso.equals("")) {
			throw new IllegalArgumentException("Matrícula, nome ou curso vazios!");
		} else if (matricula.equals("null") || nome.equals("null") || curso.equals("null")) {
			throw new NullPointerException("Matrícula, nome ou curso nulos!");
		} else if(verificaStringsVazias(matricula, nome, curso)) {
			throw new IllegalArgumentException("Matrícula, nome ou curso formado(s) apenas por espaço(s)!");
		}
		
		this.matricula = matricula;
		this.nome = nome;
		this.curso = curso;
	}
	
	/**
	 * Verifica se algum dos parâmetros passados é formado apenas por
	 * espaços.
	 * 
	 * @param matricula matrícula do aluno
	 * @param nome nome do aluno
	 * @param curso curso do aluno
	 * 
	 * @return true caso seja, false caso não.
	 */
	public static boolean verificaStringsVazias(String matricula, String nome, String curso) {
		int cont = 0;
		for (int i = 0; i < matricula.length(); i++) {
			if (matricula.charAt(i) == ' ') {
				cont += 1;
			}
		}
		if (cont == matricula.length()) {
			return true;
		}
		cont = 0;
		for (int i = 0; i < nome.length(); i++) {
			if (nome.charAt(i) == ' ') {
				cont += 1;
			}
		}
		if (cont == nome.length()) {
			return true;
		}
		cont = 0;
		for (int i = 0; i < curso.length(); i++) {
			if (curso.charAt(i) == ' ') {
				cont += 1;
			}
		}
		if (cont == curso.length()) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return (this.matricula + " - " + this.nome + " - " + this.curso);
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		if (this.getClass() != o.getClass()) { return false; }
		Aluno aluno = (Aluno) o;
		return this.matricula.equals(aluno.matricula);
	}
	
	public int hashCode() {
		return this.matricula.hashCode();
	}
	
	public String getMatricula() {
		return this.matricula;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getCurso() {
		return this.curso;
	}
}
