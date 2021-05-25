package test.ufcg.ccc.domino;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import ufcg.ccc.domino.HistoricoDeJogo;
import ufcg.ccc.domino.JogadaInvalidaException;
import ufcg.ccc.domino.Jogo;
import ufcg.ccc.domino.Peca;
import ufcg.ccc.domino.estrategia.EstrategiaInvalidaException;
import ufcg.ccc.domino.estrategia.JogaPrimeiraPossivel;

class JogoTest {

	
	@Test
	void testRodadaInicial() throws JogadaInvalidaException, EstrategiaInvalidaException {
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), 14);

		assertEquals(0, j.getNumRodadas());
		assertEquals(14, j.getNumPecasJ1());
		assertEquals(14, j.getNumPecasJ2());

		j.rodada();

		assertEquals(1, j.getNumRodadas());
		assertEquals(13, j.getNumPecasJ1());
		assertEquals(13, j.getNumPecasJ2());
	}
	
	@Test
	void testJogoAleatorio() throws JogadaInvalidaException, EstrategiaInvalidaException {
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), 10, new Random(1));

		HistoricoDeJogo historico = j.jogaJogoCompleto();

		assertTrue(j.isFinalizado());
		assertEquals("J1", j.getVencedor());
		
		//System.out.println(historico.toString());
	}

	@Test
	void testVencedorJ1Simples() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 0), new Peca(0, 1));
		List<Peca> mao2 = List.of(new Peca(0, 2), new Peca(0, 3));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);

		assertFalse(j.isFinalizado());
		assertEquals(null, j.getVencedor());

		j.jogaJogoCompleto();
		
		assertTrue(j.isFinalizado());
		assertEquals("J1", j.getVencedor());
	}
	
	//contém novos testes
	@Test
	void testEmpate() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 0), new Peca(4, 6));
		List<Peca> mao2 = List.of(new Peca(0, 1), new Peca(5, 6));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);
		HistoricoDeJogo h = j.jogaJogoCompleto();
		
		assertTrue(j.isFinalizado());
		assertEquals("J1", j.getVencedor());
		
		List<Peca> m1 = List.of(new Peca(0, 0), new Peca(4, 6));
		List<Peca> m2 = List.of(new Peca(0, 1), new Peca(5, 6), new Peca(6, 6));
		
		Jogo j2 = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), m1, m2);
		HistoricoDeJogo h2 = j2.jogaJogoCompleto();
		
		assertTrue(j2.isFinalizado());
		assertEquals("J1", j2.getVencedor());
		
		List<Peca> hand1 = List.of(new Peca(0, 0), new Peca(4, 6));
		List<Peca> hand2 = List.of(new Peca(0, 1), new Peca(5, 5));
		
		Jogo game2 = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), hand1, hand2);
		HistoricoDeJogo hi2 = game2.jogaJogoCompleto();
		
		assertTrue(game2.isFinalizado());
		assertNull(game2.getVencedor());
	}
	
	@Test
	void testVitoriaJ2() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 0), new Peca(6, 6));
		List<Peca> mao2 = List.of(new Peca(0, 1), new Peca(1, 2));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);

		j.jogaJogoCompleto();
		
		assertTrue(j.isFinalizado());
		assertEquals("J2", j.getVencedor());
	}
	
	//teste novo
	@Test
	public void testaPontuacao() throws EstrategiaInvalidaException, JogadaInvalidaException {
		List<Peca> mao1 = List.of(new Peca(0, 0), new Peca(6, 6));
		List<Peca> mao2 = List.of(new Peca(0, 1), new Peca(1, 2));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);
		HistoricoDeJogo h = j.jogaJogoCompleto();
		
		assertEquals(1, h.getPontuacaoVencedor());
		
		//
		
		List<Peca> m1 = List.of(new Peca(0, 0), new Peca(1, 1));
		List<Peca> m2 = List.of(new Peca(0, 1), new Peca(1, 2));
		
		Jogo j2 = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), m1, m2);
		HistoricoDeJogo h2 = j2.jogaJogoCompleto();
		
		assertEquals(2, h2.getPontuacaoVencedor());
		
		//
		
		List<Peca> hand1 = List.of(new Peca(0, 2), new Peca(0, 1));
		List<Peca> hand2 = List.of(new Peca(1, 1), new Peca(1, 2));
		
		Jogo j3 = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), hand1, hand2);
		HistoricoDeJogo h3 = j3.jogaJogoCompleto();
		
		assertEquals(3, h3.getPontuacaoVencedor());
		
		//
		
		List<Peca> han1 = List.of(new Peca(0, 2), new Peca(0, 1), new Peca(5, 5));
		List<Peca> han2 = List.of(new Peca(1, 2), new Peca(0, 0));
		
		Jogo j4 = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), han1, han2);
		HistoricoDeJogo h4 = j4.jogaJogoCompleto();
		System.out.println(h4.toString());
		
		assertEquals(6, h4.getPontuacaoVencedor());
		
		//
		
	}
}
