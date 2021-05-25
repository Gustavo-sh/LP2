package test.ufcg.ccc.domino.estrategia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Jogada.TipoJogada;
import ufcg.ccc.domino.JogadaInvalidaException;
import ufcg.ccc.domino.Mesa;
import ufcg.ccc.domino.Peca;
import ufcg.ccc.domino.estrategia.EstrategiaDeJogo;
import ufcg.ccc.domino.estrategia.JogaPecasMaisAltasPrimeiro;
import ufcg.ccc.domino.estrategia.JogaPrimeiraPossivel;

public class JogaPecasMaisAltasPrimeiroTest {

	private EstrategiaDeJogo estrategia;
	private Mesa mesa;
	
	@BeforeEach
	public void initEstrategiaDeJogo() {
		estrategia = new JogaPecasMaisAltasPrimeiro();
		mesa = new Mesa();
	}
	
	@Test
	public void testPass() throws JogadaInvalidaException {
		mesa.jogaNaDireita(new Peca(6, 6));
		List<Peca> m = new ArrayList();
		m.add(new Peca(5, 5));
		m.add(new Peca(4, 4));
		
		Jogada j = estrategia.decideJogada(mesa, m);
		
		assertEquals(TipoJogada.PASSA, j.getTipo());
	}
	
	@Test
	public void testOrdenacao() throws JogadaInvalidaException {
		List<Peca> m = new ArrayList();
		m.add(new Peca(1, 1));
		m.add(new Peca(4, 4));
		m.add(new Peca(5, 4));
		
		assertEquals(1, m.get(0).getNumDireito());
		assertEquals(1, m.get(0).getNumEsquerdo());
				
		m.get(0).compareTo(m.get(1));
	    Collections.sort(m);
		
		assertEquals(4, m.get(0).getNumDireito());
		assertEquals(5, m.get(0).getNumEsquerdo());
	}
	
	@Test
	public void testSemPecasNaMesa() throws JogadaInvalidaException {
		List<Peca> m = new ArrayList();
		m.add(new Peca(1, 1));
		m.add(new Peca(4, 4));
		m.add(new Peca(5, 4));
		
		Jogada j = estrategia.decideJogada(mesa, m);

		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
		assertEquals(5, j.getPeca().getNumEsquerdo());
		assertEquals(4, j.getPeca().getNumDireito());
	}
	
	@Test
	public void testPecasAltasNumeracaoIgual() throws JogadaInvalidaException {
		mesa.jogaNaDireita(new Peca(5, 3));
		List<Peca> m = new ArrayList();
		m.add(new Peca(1, 1));
		m.add(new Peca(6, 3));
		m.add(new Peca(5, 4));
		
		Jogada j = estrategia.decideJogada(mesa, m);

		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
		assertEquals(6, j.getPeca().getNumEsquerdo());
		assertEquals(3, j.getPeca().getNumDireito());
	}
	
	@Test
	public void testPrioridadeDosCarrocoes() throws JogadaInvalidaException {
		mesa.jogaNaDireita(new Peca(5, 3));
		List<Peca> m = new ArrayList();
		m.add(new Peca(3, 3));
		m.add(new Peca(6, 3));
		m.add(new Peca(5, 4));
		
		Jogada j = estrategia.decideJogada(mesa, m);

		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
		assertEquals(3, j.getPeca().getNumEsquerdo());
		assertEquals(3, j.getPeca().getNumDireito());
	}
}
