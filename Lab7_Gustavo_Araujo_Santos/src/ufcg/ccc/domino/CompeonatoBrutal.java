package ufcg.ccc.domino;

import java.util.Scanner;

import ufcg.ccc.domino.estrategia.EstrategiaDeJogo;
import ufcg.ccc.domino.estrategia.EstrategiaInvalidaException;
import ufcg.ccc.domino.estrategia.JogaCarrocaoPrimeiro;
import ufcg.ccc.domino.estrategia.JogaPecasMaisAltasPrimeiro;
import ufcg.ccc.domino.estrategia.JogaPrimeiraPossivel;

/**
 * Faz com que uma estratégia escolhida jogue 10000 vezes
 * contra as outras duas.
 * 
 * @author Gustavo Araujo Santos - 119210741
 *
 */
public class CompeonatoBrutal {

	public static void menu() {
		System.out.println("Entradas disponívei: ");
		System.out.println("1: JogaCarrocaoPrimeiro");
		System.out.println("2: JogaPecasMaisAltasPrimeiro");
		System.out.println("3: JogaPrimeiraPossivel");
		System.out.println("S: SAIR");
		System.out.println("");
		System.out.print("Digite o número da estratégia que jogará 10000 vezes contra as outras: ");
	}
	
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		int pontuacaoE1 = 0;
		int pontuacaoE2 = 0;
		int pontuacaoE3 = 0;
		EstrategiaDeJogo estrategia1 = new JogaCarrocaoPrimeiro(), estrategia2 = new JogaPrimeiraPossivel(), estrategia3 = new JogaPecasMaisAltasPrimeiro();
		
		menu();
		String entrada = input.nextLine();
		
		while (entrada != "S") {
			if (entrada.equals("S")) {
				break;
			}
			if (Integer.parseInt(entrada) == 1) {
				for (int i = 0; i < 10000; i++) {
					Jogo j = new Jogo("E1", estrategia1, "E2", estrategia2, 14);
					HistoricoDeJogo historico = j.jogaJogoCompleto();
					if (historico.getVencedor() == "E1") {
						pontuacaoE1 += historico.getPontuacaoVencedor();
					} else if (historico.getVencedor() == "E2") {
						pontuacaoE2 += historico.getPontuacaoVencedor();
					}
				}
				System.out.println("");
				System.out.println("     ---Pontuações---");
				System.out.println("");
				System.out.println("JogaCarrocaoPrimeiro Vs JogaPecasMaisAltasPrimeiro:");
				System.out.println("JogaCarrocaoPrimeiro: " + pontuacaoE1);
				System.out.println("JogaPecasMaisAltasPrimeiro: " + pontuacaoE2);
				pontuacaoE1 = 0;
				pontuacaoE2 = 0;
				for (int i = 0; i < 10000; i++) {
					Jogo j = new Jogo("E1", estrategia1, "E3", estrategia3, 14);
					HistoricoDeJogo historico = j.jogaJogoCompleto();
					if (historico.getVencedor() == "E1") {
						pontuacaoE1 += historico.getPontuacaoVencedor();
					} else if (historico.getVencedor() == "E3") {
						pontuacaoE3 += historico.getPontuacaoVencedor();
					}
				}
				System.out.println("");
				System.out.println("     ---Pontuações---");
				System.out.println("");
				System.out.println("JogaCarrocaoPrimeiro Vs JogaPrimeiraPossivel:");
				System.out.println("JogaCarrocaoPrimeiro: " + pontuacaoE1);
				System.out.println("JogaPrimeiraPossivel: " + pontuacaoE3);
				pontuacaoE1 = 0;
				pontuacaoE3 = 0;
			} else if (Integer.parseInt(entrada) == 2) {
				for (int i = 0; i < 10000; i++) {
					Jogo j = new Jogo("E2", estrategia2, "E1", estrategia1, 14);
					HistoricoDeJogo historico = j.jogaJogoCompleto();
					if (historico.getVencedor() == "E2") {
						pontuacaoE2 += historico.getPontuacaoVencedor();
					} else if (historico.getVencedor() == "E1") {
						pontuacaoE1 += historico.getPontuacaoVencedor();
					}
				}
				System.out.println("");
				System.out.println("     ---Pontuações---");
				System.out.println("");
				System.out.println("JogaPecasMaisAltasPrimeiro Vs JogaCarrocaoPrimeiro:");
				System.out.println("JogaPecasMaisAltasPrimeiro: " + pontuacaoE2);
				System.out.println("JogaCarrocaoPrimeiro: " + pontuacaoE1);
				pontuacaoE2 = 0;
				pontuacaoE1 = 0;
				for (int i = 0; i < 10000; i++) {
					Jogo j = new Jogo("E2", estrategia2, "E3", estrategia3, 14);
					HistoricoDeJogo historico = j.jogaJogoCompleto();
					if (historico.getVencedor() == "E2") {
						pontuacaoE2 += historico.getPontuacaoVencedor();
					} else if (historico.getVencedor() == "E3") {
						pontuacaoE3 += historico.getPontuacaoVencedor();
					}
				}
				System.out.println("");
				System.out.println("     ---Pontuações---");
				System.out.println("");
				System.out.println("JogaPecasMaisAltasPrimeiro Vs JogaPrimeiraPossivel:");
				System.out.println("JogaPecasMaisAltasPrimeiro: " + pontuacaoE2);
				System.out.println("JogaPrimeiraPossivel: " + pontuacaoE3);
				pontuacaoE2 = 0;
				pontuacaoE3 = 0;
			} else if (Integer.parseInt(entrada) == 3) {
				for (int i = 0; i < 10000; i++) {
					Jogo j = new Jogo("E3", estrategia3, "E1", estrategia1, 14);
					HistoricoDeJogo historico = j.jogaJogoCompleto();
					if (historico.getVencedor() == "E3") {
						pontuacaoE3 += historico.getPontuacaoVencedor();
					} else if (historico.getVencedor() == "E1") {
						pontuacaoE1 += historico.getPontuacaoVencedor();
					}
				}
				System.out.println("");
				System.out.println("     ---Pontuações---");
				System.out.println("");
				System.out.println("JogaPrimeiraPossivel Vs JogaCarrocaoPrimeiro:");
				System.out.println("JogaPrimeiraPossivel: " + pontuacaoE3);
				System.out.println("JogaCarrocaoPrimeiro: " + pontuacaoE1);
				pontuacaoE3 = 0;
				pontuacaoE1 = 0;
				for (int i = 0; i < 10000; i++) {
					Jogo j = new Jogo("E3", estrategia3, "E2", estrategia2, 14);
					HistoricoDeJogo historico = j.jogaJogoCompleto();
					if (historico.getVencedor() == "E3") {
						pontuacaoE3 += historico.getPontuacaoVencedor();
					} else if (historico.getVencedor() == "E2") {
						pontuacaoE2 += historico.getPontuacaoVencedor();
					}
				}
				System.out.println("");
				System.out.println("     ---Pontuações---");
				System.out.println("");
				System.out.println("JogaPrimeiraPossivel Vs JogaPecasMaisAltasPrimeiro:");
				System.out.println("JogaPrimeiraPossivel: " + pontuacaoE3);
				System.out.println("JogaPecasMaisAltasPrimeiro: " + pontuacaoE2);
				pontuacaoE3 = 0;
				pontuacaoE2 = 0;
			} else {
				System.out.println("Entrada inválida!");
			}
			menu();
			entrada = input.nextLine();
		}
	}
}
