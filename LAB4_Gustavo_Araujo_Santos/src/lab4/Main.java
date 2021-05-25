package lab4;
import java.util.Scanner;

/**
 * O Main trata da entrada e saída do sistema.
 * 
 * @author Gustavo Araujo Santos - 119210741
 *
 */
public class Main {

	public static void menu() {
		System.out.println("(C)adastrar Aluno");
		System.out.println("(E)xibir Aluno");
		System.out.println("(N)ovo Grupo");
		System.out.println("(A)locar Aluno no Grupo e Imprimir Grupos");
		System.out.println("(R)egistrar Aluno que Respondeu");
		System.out.println("(I)mprimir Alunos que Responderam");
		System.out.println("(O)ra, vamos fechar o programa!");
		System.out.println("");
		System.out.print("Opção> ");
	}
	
	public static void main(String[] args) {
		ControleDeAlunos controle = new ControleDeAlunos();
		Scanner input = new Scanner(System.in);
		while(true) {
			menu();
			String entrada = input.nextLine();
			System.out.println("");
			if (entrada.equals("C")) {
				System.out.print("Matrícula: ");
				String matricula = input.nextLine();
				System.out.print("Nome: ");
				String nome = input.nextLine();
				System.out.print("Curso: ");
				String curso = input.nextLine();
				System.out.println(controle.cadastraAluno(matricula, nome, curso));
				System.out.println("");
			} else if (entrada.equals("E")) {
				System.out.print("Matrícula: ");
				String matricula = input.nextLine();
				System.out.println("");
				System.out.println(controle.consultaAluno(matricula));
				System.out.println("");
			} else if (entrada.equals("N")) {
				System.out.print("Grupo: ");
				String nome = input.nextLine();
				System.out.println(controle.cadastraGrupo(nome));
				System.out.println("");
			} else if (entrada.equals("A")) {
				System.out.print("(A)locar Aluno ou (I)mprimir Grupo? ");
				String aoi = input.nextLine();
				System.out.println("");
				if (aoi.equals("A")) {
					System.out.print("Matrícula: ");
					String matricula = input.nextLine();
					System.out.print("Grupo: ");
					String nome = input.nextLine();
					System.out.println(controle.alocaAluno(matricula, nome));
					System.out.println("");
				} else if (aoi.equals("I")) {
					System.out.print("Grupo: ");
					String nome = input.nextLine();
					System.out.println("");
					System.out.println(controle.imprimeGrupo(nome));
					System.out.println("");
				} else {
					System.out.println("Entrada inválida!");
					System.out.println("");
				}
			} else if (entrada.equals("R")) {
				System.out.print("Matrícula: ");
				String matricula = input.nextLine();
				System.out.println(controle.cadastraAlunoRespondeuQuadro(matricula));
				System.out.println("");
			} else if (entrada.equals("I")) {
				System.out.println(controle.imprimeAlunosQueResponderam());
				System.out.println("");
			} else if (entrada.equals("O")) {
				break;
			} else {
				System.out.println("Entrada inválida! Tente novamente");
				System.out.println("");
			}
		}
		input.close();
	}
}
