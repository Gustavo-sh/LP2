package test.ufcg.ccc.domino.estrategia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Jogada.TipoJogada;
import ufcg.ccc.domino.Mesa;
import ufcg.ccc.domino.Peca;
import ufcg.ccc.domino.estrategia.JogaCarrocaoPrimeiro;

public class JogaCarrocaoPrimeiroTest {
	
	private Mesa mesa;
	private JogaCarrocaoPrimeiro est;
	private Mesa mesa2;
	
	@BeforeEach
	void setUp() throws Exception {
		mesa = new Mesa();
		mesa2 = new Mesa();
		mesa.jogaNaDireita(new Peca(1, 2));
		mesa.jogaNaEsquerda(new Peca(1, 5));
		mesa.jogaNaDireita(new Peca(2, 3));
		est = new JogaCarrocaoPrimeiro();
		mesa2.jogaNaDireita(new Peca(4, 2));
		mesa2.jogaNaDireita(new Peca(2, 6));
		mesa2.jogaNaDireita(new Peca(6, 5));
	}
	
	@Test
	public void testaPassa() {
		List<Peca> m = new ArrayList();
		m.add(new Peca(6, 6));
		m.add(new Peca(4, 4));
		
		Jogada j = est.decideJogada(mesa, m);
		
		assertEquals(TipoJogada.PASSA, j.getTipo());
	}
	
	
	@Test
	public void testaJogaUnicoCarrocao() {
		List<Peca> m = new ArrayList();
		m.add(new Peca(3, 3));
		m.add(new Peca(4, 6));
		
		Jogada j = est.decideJogada(mesa, m);
		
		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
		assertEquals(3, j.getPeca().getNumDireito());
		assertEquals(3, j.getPeca().getNumEsquerdo());
	}
	
	@Test
	public void testaJogaMaiorCarrocao() {
		List<Peca> m = new ArrayList();
		m.add(new Peca(3, 3));
		m.add(new Peca(4, 6));
		m.add(new Peca(5, 5));
		m.add(new Peca(4, 4));
		
		Jogada j = est.decideJogada(mesa2, m);
		
		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
		assertEquals(5, j.getPeca().getNumDireito());
		assertEquals(5, j.getPeca().getNumEsquerdo());
	}
	
	@Test
	public void testaSemCarrocao() {
		List<Peca> m = new ArrayList();
		m.add(new Peca(3, 2));
		m.add(new Peca(4, 6));
		m.add(new Peca(5, 4));
		
		Jogada j = est.decideJogada(mesa2, m);

		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
		assertEquals(6, j.getPeca().getNumDireito());
		assertEquals(4, j.getPeca().getNumEsquerdo());
	}
	
	@Test
	public void testaJogadaComum() {
		List<Peca> m = new ArrayList();
		m.add(new Peca(6, 6));
		m.add(new Peca(4, 5));
		
		Jogada j = est.decideJogada(mesa, m);
		
		assertEquals(TipoJogada.NA_ESQUERDA, j.getTipo());
		assertEquals(4, j.getPeca().getNumEsquerdo());
		assertEquals(5, j.getPeca().getNumDireito());
	}
	
	@Test
	public void testaSemPecasNaMesa() {
		Mesa mes = new Mesa();
		List<Peca> m = new ArrayList();
		m.add(new Peca(6, 5));
		m.add(new Peca(6, 6));
		m.add(new Peca(6, 2));
		m.add(new Peca(6, 3));
		m.add(new Peca(4, 4));
		
		Jogada j = est.decideJogada(mes, m);
		
		assertEquals(TipoJogada.NA_DIREITA, j.getTipo());
		assertEquals(6, j.getPeca().getNumEsquerdo());
		assertEquals(6, j.getPeca().getNumDireito());
	}
}
