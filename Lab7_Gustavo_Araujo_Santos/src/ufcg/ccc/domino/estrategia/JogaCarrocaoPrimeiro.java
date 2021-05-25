package ufcg.ccc.domino.estrategia;

import java.util.ArrayList;
import java.util.List;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Peca;
import ufcg.ccc.domino.Jogada.TipoJogada;

/**
 * Joga os carroções primeiro.
 * A prioridade é dos maiores carroções.
 * 
 * @author Gustavo Araujo Santos - 119210741
 *
 */
public class JogaCarrocaoPrimeiro implements EstrategiaDeJogo{

	@Override
	public Jogada decideJogada(VisaoDaMesa mesa, List<Peca> mao) {
		List<Peca> carrocoes = preencheCarrocoes(mao);
        if (mesa.getNumPecas() == 0) {
			if (carrocoes.size() >= 2) {
				Peca p = maiorCarrocao(carrocoes);
				return new Jogada(p, TipoJogada.NA_DIREITA);
			} else if (carrocoes.size() == 1) {
				return new Jogada(carrocoes.get(0), TipoJogada.NA_DIREITA);
			} else {
				return new Jogada(mao.get(0), TipoJogada.NA_DIREITA);
			}
		}
		if (carrocoes.size() == 0) {
			for (Peca peca : mao) {
				if (peca.encaixa(mesa.getNumNaDireita())) {
					return new Jogada(peca, TipoJogada.NA_DIREITA);
				}
				if (peca.encaixa(mesa.getNumNaEsquerda())) {
					return new Jogada(peca, TipoJogada.NA_ESQUERDA);
				}
			}
		}
		if (carrocoes.size() == 1) {
			if (carrocoes.get(0).encaixa(mesa.getNumNaDireita())) {
				return new Jogada(carrocoes.get(0), TipoJogada.NA_DIREITA);
			} else if (carrocoes.get(0).encaixa(mesa.getNumNaEsquerda())) {
				return new Jogada(carrocoes.get(0), TipoJogada.NA_ESQUERDA);
			}
		}
		if (carrocoes.size() >= 2) {
			Peca p = maiorCarrocao(carrocoes);
			if (p.encaixa(mesa.getNumNaDireita())) {
				return new Jogada(p, TipoJogada.NA_DIREITA);
			} else if (p.encaixa(mesa.getNumNaEsquerda())) {
				return new Jogada(p, TipoJogada.NA_ESQUERDA);
			}
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
	
	/**
	 * Busca os carroções na mão e os retorna numa lista.
	 * 
	 * @param mao a mão do jogador
	 * @return uma lista com os carroções
	 */
	public List<Peca> preencheCarrocoes(List<Peca> mao) {
		List<Peca> carrocoes = new ArrayList<>();
		for (Peca p : mao) {
			if (p.isCarrocao()) {
				carrocoes.add(p);
			}
		}
		return carrocoes;
	}
	
	/**
	 * Acha o maior carroção na lista de carroções.
	 * 
	 * @param carrocoes a lista de carroções
	 * @return o maior carroção
	 */
	public Peca maiorCarrocao(List<Peca> carrocoes) {
		Peca maior = carrocoes.get(0);
		for (Peca p : carrocoes) {
			if (p.isMaior(maior)) {
				maior = p;
			}
		}
		return maior;
	}
}