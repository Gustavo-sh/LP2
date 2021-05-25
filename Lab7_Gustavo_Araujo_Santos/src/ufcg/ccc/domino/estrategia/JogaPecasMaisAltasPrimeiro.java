package ufcg.ccc.domino.estrategia;

import java.util.Collections;
import java.util.List;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Peca;
import ufcg.ccc.domino.Jogada.TipoJogada;

/**
 * Joga as pecas mais altas que estiverem na mao primeiro.
 * A prioridade é dada para os carroções.
 * 
 * @author Gustavo Araujo Santos 119210741
 *
 */
public class JogaPecasMaisAltasPrimeiro implements EstrategiaDeJogo{

	public Jogada decideJogada(VisaoDaMesa mesa, List<Peca> mao) {
		if (mao.size() >= 2) {
			Peca p1 = mao.get(0);
			Peca p2 = mao.get(1);
			p1.compareTo(p2);
		    Collections.sort(mao);
		}
		for (Peca p : mao) {
			if (p.isCarrocao() && p.encaixa(mesa.getNumNaDireita())) {
				return new Jogada(p, TipoJogada.NA_DIREITA);
			} else if (p.isCarrocao() && p.encaixa(mesa.getNumNaEsquerda())) {
				return new Jogada(p, TipoJogada.NA_ESQUERDA);
			}
		}
	    if (mesa.getNumPecas() == 0) {
			return new Jogada(mao.get(0), TipoJogada.NA_DIREITA);
		}

		for (Peca peca : mao) {
			if (peca.encaixa(mesa.getNumNaDireita())) {
				return new Jogada(peca, TipoJogada.NA_DIREITA);
			}
			if (peca.encaixa(mesa.getNumNaEsquerda())) {
				return new Jogada(peca, TipoJogada.NA_ESQUERDA);
			}
		}
		return new Jogada();
	}
}
