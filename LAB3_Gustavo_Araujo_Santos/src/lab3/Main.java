package lab3;
import java.util.Scanner;

/**
 * Main é a classe responsável por entrada e saída do nosso sistema.
 * Não há lógica avançada nessa classe.
 * 
 * @author Gustavo Araujo Santos - 119210741
 *
 */
public class Main {
	public static void menu() {
		System.out.println("(C)adastra Contato");
		System.out.println("(L)istar Contatos");
		System.out.println("(E)xibir Contato");
		System.out.println("(T)elefones Preferidos");
		System.out.println("(Z)aps");
		System.out.println("(CO)nsulta por Nome");
		System.out.println("(PO)r Amizade");
		System.out.println("(Q)uantidade por Nível de Amizade");
		System.out.println("(M)édia Amizades");
		System.out.println("(S)air");
		System.out.println("");
		System.out.print("Opção> ");
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Agenda agenda = new Agenda();
		String entrada;
		while (true) {
			menu();
			entrada = input.nextLine();
			if (entrada.equals("C")) {
				System.out.println("");
				System.out.print("Posição: ");
				int pos  = input.nextInt();
				input.nextLine();
				System.out.print("Nome: ");
				String nome = input.nextLine();
				System.out.print("Sobrenome: ");
				String sobreNome = input.nextLine();
				System.out.print("Telefone1: ");
				String ctt1 = input.nextLine();
				System.out.print("Telefone2: ");
				String ctt2 = input.nextLine();
				System.out.print("Telefone3: ");
				String ctt3 = input.nextLine();
				System.out.print("Telefone prioritário: ");
				String cttPriori = input.nextLine();
				System.out.print("Zap: ");
				String cttZap = input.nextLine();
				System.out.print("Nível de Amizade (1 a 5): ");
				String nvAmizade = input.nextLine();
				agenda.cadastraContato(pos, nome, sobreNome, ctt1, ctt2, ctt3, cttPriori, cttZap, nvAmizade);
				System.out.println("CADASTRO REALIZADO!");
				System.out.println(ctt1);
				System.out.println("");
			} else if (entrada.equals("L")) {
				System.out.println("");
				System.out.println(agenda.listaContatos());
			} else if (entrada.equals("E")) {
				System.out.print("Contato> ");
				String ctt = input.nextLine(); //tive que tratar como String porque o Scanner estava bugando
				System.out.println("");
				System.out.println(agenda.exibeContato(ctt));
				System.out.println("");
			} else if (entrada.equals("T")) {
				System.out.println("");
				System.out.println(agenda.listaContatosPriori());
			} else if (entrada.equals("Z")) {
				System.out.println("");
				System.out.println(agenda.listaContatosZap());
				System.out.println("");
			} else if (entrada.equals("CO")) {
				System.out.print("Nome: ");
				String nome = input.nextLine();
				System.out.println("");
				System.out.println(agenda.consultaPorNome(nome));
				System.out.println("");
			} else if (entrada.equals("PO")) {
				System.out.print("Nível de amizade: ");
				String nv = input.nextLine(); // buga quando é colocada como int
				System.out.println("");
				System.out.println("1: distante; 2: colega; 3: amigo; 4: amigão; 5: irmão.");
				System.out.println(agenda.consultaPorNvAmizade(nv));
				System.out.println("");
			} else if (entrada.equals("Q")) {
				System.out.print("Nível de amizade: ");
				String nv = input.nextLine(); // buga quando é colocado como int
				System.out.println("");
				System.out.println("Há " + agenda.quantNvAmizade(nv) + " contato(s) com esse nível de amizade.");
				System.out.println("");
			} else if (entrada.equals("M")) {
				System.out.println("");
				System.out.println("Média: " + agenda.mediaAmizades());
				System.out.println("");
			} else if (entrada.equals("S")) {
				break;
			}
		}
		input.close();
	}
}
