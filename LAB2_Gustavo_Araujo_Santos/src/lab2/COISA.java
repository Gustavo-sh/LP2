package lab2;
import java.util.Scanner;
/**
 * COISA é a classe que contém o nosso main. Ela controla a entrada e 
 * saída padrão dos dados e como esses dados serão passados para a 
 * classe Aluno. 
 * 
 * @author Gustavo Araujo Santos - 119210741
 *
 */
public class COISA {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Aluno aluno = new Aluno();
		// for de opções gerais
		for(;;)
		{
			System.out.println("Opções: LABORATORIO, DISCIPLINA, CANTINA, SAUDE E SAIR.");
			String entrada = input.nextLine();
			String[] splitted;
			if (entrada.equals("LABORATORIO")) {
				System.out.println("Nome do Laboratório: ");
				String nomeL = input.nextLine();
				/*
				 * Verifica se o laboratório solicitado pelo aluno
				 * já havia sido criado anteriormente. Entrando nesse if,
				 * o instancimento de um novo laboratório é feito. 
				 */
				if(!aluno.buscaLab(nomeL)) {
					System.out.println("Cota padrão de 2GB, digite uma nova caso queira.");
					String novaCota = input.nextLine();
					// verifica se o aluno digitou pesos ou não
					if(novaCota.equals("")) {
						aluno.cadastraLaboratorio(nomeL);
					} else {
						aluno.cadastraLaboratorio(nomeL, Integer.parseInt(novaCota));
					}
				}
				// for das ações em LABORATORIO
				for(;;) {
					System.out.println("Opções: LIBERA ..., CONSOME ..., MOSTRAR, COTA e SAIR.");
					entrada = input.nextLine();
					splitted = entrada.split(" ");
					/*
					 * Exemplo de libera espaço:
					 * LIBERA 200
					 * Exemplo de consome espaço:
					 * CONSOME 2000
					 */
					if (splitted[0].equals("LIBERA")){
						aluno.liberaEspaco(nomeL, Integer.parseInt(splitted[1]));
					} else if(splitted[0].equals("CONSOME")){
						aluno.consomeEspaco(nomeL, Integer.parseInt(splitted[1]));
					} else if(splitted[0].equals("COTA")){
						System.out.println(aluno.atingiuCota(nomeL));
					} else if(splitted[0].equals("MOSTRAR")){
						System.out.println(aluno.laboratorioToString(nomeL));
					} else if(entrada.equals("SAIR")) {
						break;
					} else {
						System.out.println("Entrada inválida! Tente novamente: ");
					}
				}
			} else if(entrada.equals("DISCIPLINA")) {
				System.out.println("Nome da Disciplina: ");
				String nomeD = input.nextLine();
				/*
				 * Verifica se a disciplina solicitada pelo aluno
				 * já havia sido criada antes. Entrando nesse if,
				 * o instanciamento de uma nova disciplina é feito.
				 */
				if(!aluno.buscaDisc(nomeD)) {
					System.out.println("Quantidade de notas: ");
					int qNotas = input.nextInt();
					System.out.println("Os pesos das notas separados por espaço: ");
					input.nextLine();
					String pesos = input.nextLine();
					String[] pesosSplitted = pesos.split(" ");
					// verifica se o aluno digitou os pesos das notas ou não
					if(pesos.equals("")) {
						aluno.cadastraDisciplina(qNotas, nomeD);
					} else {
						aluno.cadastraDisciplina(qNotas, pesosSplitted, nomeD);
					}
				}
				// for das ações em DISCIPLINA
				for(;;) {
					System.out.println("Opções: CADASTRAR HORAS ..., CADASTRAR NOTA ... ..., MOSTRAR, APROVADO e SAIR.");
					entrada = input.nextLine();
					splitted = entrada.split(" ");
					/*
					 * Exemplo de nota cadastrada:
					 * CADASTRA NOTA 1 10
					 * Exemplo de horas cadastradas:
					 * CADASTRA HORAS 2
					 */
					if (splitted[0].equals("CADASTRA")) {
						if (splitted[1].equals("HORAS")) {
							aluno.cadastraHoras(nomeD, Integer.parseInt(splitted[2]));
							System.out.println("Horas cadastradas!");
						} else {
							aluno.cadastraNota(nomeD, Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]));
							System.out.println("Nota cadastrada!");
						}
					} else if (splitted[0].equals("APROVADO")) {
						System.out.println(aluno.aprovado(nomeD));
					} else if (splitted[0].equals("MOSTRAR")) {
						System.out.println(aluno.disciplinaToString(nomeD));
					} else if (splitted[0].equals("SAIR")) {
						break;
					} else {
						System.out.println("Entrada inválida! Tente novamente: ");
					}
				}
			} else if (entrada.equals("CANTINA")) {
				System.out.println("Nome da Cantina: ");
				String nomeC = input.nextLine();
				/*
				 * Verifica se a cantina solicitada pelo aluno
				 * já havia sido criada antes. Entrando nesse if,
				 * o instanciamento de uma nova cantina é feito.
				 */
				if(!aluno.buscaCant(nomeC)) {
					aluno.cadastraCantina(nomeC);
				}
				// for das ações de CANTINA
				for (;;) {
					System.out.println("Opções: CADASTRA, PAGA ..., DIVIDA, MOSTRAR, DETALHES e SAIR.");
					entrada = input.nextLine();
					splitted = entrada.split(" ");
					if (splitted[0].equals("CADASTRA")) {
						System.out.println("Quantidade de ítens: ");
						int quant = input.nextInt();
						System.out.println("Valor dos ítens: ");
						int valor = input.nextInt();
						System.out.println("Descrição dos ítens: ");
						input.nextLine();
						String desc = input.nextLine();
						// verifica se o aluno colocou a descrição do lanche ou não
						if (desc.equals("")) {
							aluno.cadastraLanche(nomeC, quant, valor);
						} else {
							aluno.cadastraLanches(nomeC, quant, valor, desc);
						}
					/*
					 * Exemplo de paga conta:
					 * PAGA 100
					 */
					} else if (splitted[0].equals("PAGA")) {
						aluno.pagarConta(nomeC, Integer.parseInt(splitted[1]));
					} else if (splitted[0].equals("DIVIDA")) {
						System.out.println(aluno.getFaltaPagar(nomeC));
					} else if (splitted[0].equals("MOSTRAR")) {
						System.out.println(aluno.cantinaToString(nomeC));
					} else if (splitted[0].equals("DETALHES")) {
						System.out.println(aluno.listaDetalhes(nomeC));
					} else if (splitted[0].equals("SAIR")) {
						break;
					} else {
						System.out.println("Entrada inválida! Tente novamente: ");
					}
				}
			} else if (entrada.equals("SAUDE")) {
				// for de ações em SAUDE
				for (;;) {
					System.out.println("Opções: DEFINE, STATUS e SAIR.");
					entrada = input.nextLine();
					splitted = entrada.split(" ");
					if (entrada.equals("DEFINE")) {
						System.out.println("Opções: FISICA, MENTAL, EMOJI e SAIR.");
						entrada = input.nextLine();
						splitted = entrada.split(" ");
						if (entrada.equals("FISICA")) {
							System.out.println("Digite a saude: ");
							System.out.println("Opções: fraca e boa. Digite em minúsculo.");
							String saud = input.nextLine();
							aluno.defineSaudeFisica(saud);
						} else if (entrada.equals("MENTAL")) {
							System.out.println("Digite a saude: ");
							System.out.println("Opções: fraca e boa. Digite em minúsculo");
							String saud = input.nextLine();
							aluno.defineSaudeMental(saud);
						} else if (entrada.equals("EMOJI")) {
							System.out.println("Digite o emoji: ");
							String emoji = input.nextLine();
							aluno.defineEmoji(emoji);
						}else if(entrada.equals("SAIR")){
							break;
						} else {
							System.out.println("Saude inválida!");
						}
					} else if (entrada.equals("STATUS")){
						System.out.println(aluno.getStatusGeral());
					} else if (entrada.equals("SAIR")) {
						break;
					} else {
						System.out.println("Entrada inválida! Tente novamente: ");
					}
				}
			} else if (entrada.equals("SAIR")) {
				break;
			} else {
				System.out.println("Entrada inválida! Tente novamente: ");
			}
		}
		input.close();
	}
}
