package lab4;
import java.util.ArrayList;

/**
 * GrupoDeEstudos aloca alunos neste grupo e os armazena.
 * GrupoDeEstudos também forma e retorna uma represenatação
 * textual dos alunos aqui alocados.
 * 
 * @author Gustavo Araujo Santos - 119210741
 *
 */
public class GrupoDeEstudos {

	private String nome;
	private ArrayList<Aluno> alunos;
	
	public GrupoDeEstudos(String nome) {
		this.nome = nome;
		this.alunos = new ArrayList<Aluno>();
	}
	
	/**
	 * Aloca um aluno no ArrayList de Aluno.
	 * 
	 * @param aluno o aluno a ser alocado
	 */
	public void alocaAluno(Aluno aluno) {
		this.alunos.add(aluno);
	}
	
	/**
	 * Forma uma representação textual dos alunos alocados.
	 * 
	 * @return a representação textual formada.
	 */
	public String imprimeGrupo() {
		String finn = "Alunos do grupo " + this.nome + ":\n";
		for (Aluno alun : this.alunos) {
			finn += "* " + alun.toString() + "\n";
		}
		return finn.substring(0, finn.length() - 1);
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		if (this.getClass() != o.getClass()) { return false; }
		GrupoDeEstudos grupo = (GrupoDeEstudos) o;
		return this.nome.equals(grupo.getNome());
	}
	
	public int hashCode() {
		return this.nome.hashCode();
	}
}
