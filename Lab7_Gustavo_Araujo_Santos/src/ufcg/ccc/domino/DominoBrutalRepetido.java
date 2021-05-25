package ufcg.ccc.domino;

import ufcg.ccc.domino.estrategia.EstrategiaDeJogo;
import ufcg.ccc.domino.estrategia.EstrategiaInvalidaException;
import ufcg.ccc.domino.estrategia.JogaCarrocaoPrimeiro;
import ufcg.ccc.domino.estrategia.JogaPecasMaisAltasPrimeiro;
import ufcg.ccc.domino.estrategia.JogaPrimeiraPossivel;

/**
 * Exemplo de como fazer um um main com uma disputa de muuuitos jogos entre duas
 * estratégias.
 * 
 */
public class DominoBrutalRepetido {

	public static void main(String[] args) throws EstrategiaInvalidaException, JogadaInvalidaException {
		int pontuacaoJ1 = 0, pontuacaoJ2 = 0;
		int vitoriasJ1 = 0, vitoriasJ2 = 0, empates = 0;
		int vitoriasCarrocaoJ1 = 0, vitoriasCarrocaoJ2 = 0;
		int vitoriasLaeLoJ1 = 0, vitoriasLaeLoJ2 = 0;
		int vitoriasLaeLoCarrocaoJ1 = 0, vitoriasLaeLoCarrocaoJ2 = 0;

		EstrategiaDeJogo estrategia1 = new JogaPecasMaisAltasPrimeiro(), estrategia2 = new JogaPrimeiraPossivel();
		
		for (int i = 0; i < 10000; i++) {
			Jogo j = new Jogo("J1", estrategia1, "J2", estrategia2, 14);
			HistoricoDeJogo historico = j.jogaJogoCompleto();
			if (historico.isEmpate()) {
				empates++;
			} else if (historico.getVencedor() == "J1") {
				if (historico.getPontuacaoVencedor() == 1) {
					vitoriasJ1 += 1;
					pontuacaoJ1 += 1;
				} else if (historico.getPontuacaoVencedor() == 2) {
					vitoriasCarrocaoJ1 += 1;
					pontuacaoJ1 += 2;
				} else if (historico.getPontuacaoVencedor() == 3) {
					vitoriasLaeLoJ1 += 1;
					pontuacaoJ1 += 3;
				} else if (historico.getPontuacaoVencedor() == 6) {
					vitoriasLaeLoCarrocaoJ1 += 1;
					pontuacaoJ1 += 6;
				}
			} else if (historico.getVencedor() == "J2") {
				if (historico.getPontuacaoVencedor() == 1) {
					vitoriasJ2 += 1;
					pontuacaoJ2 += 1;
				} else if (historico.getPontuacaoVencedor() == 2) {
					vitoriasCarrocaoJ2 += 1;
					pontuacaoJ2 += 2;
				} else if (historico.getPontuacaoVencedor() == 3) {
					vitoriasLaeLoJ2 += 1;
					pontuacaoJ2 += 3;
				} else if (historico.getPontuacaoVencedor() == 6) {
					vitoriasLaeLoCarrocaoJ2 += 1;
					pontuacaoJ2 += 6;
				}
			}
		}

		System.out.println("Vitórias normais J1: " + vitoriasJ1 + "\nVitórias normais J2: " + vitoriasJ2);
		System.out.println("Vitórias Carroção J1: " + vitoriasCarrocaoJ1 + "\nVitórias Carroção J2: " + vitoriasCarrocaoJ2);
		System.out.println("Vitórias Lá e Lô J1: " + vitoriasLaeLoJ1 + "\nVitórias Lá e Lô J2: " + vitoriasLaeLoJ2);
		System.out.println("Vitórias Lá e Lô Carroção J1: " + vitoriasLaeLoCarrocaoJ1 + "\nVitórias Lá e Lô Carroção J2: " + vitoriasLaeLoCarrocaoJ2 );
		System.out.println("Empates: " + empates);
		System.out.println("Pontuação J1: " + pontuacaoJ1 + "\nPontuação J2: " + pontuacaoJ2);
	}

}
