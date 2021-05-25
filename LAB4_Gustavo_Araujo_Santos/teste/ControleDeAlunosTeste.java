import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import lab4.*;

public class ControleDeAlunosTeste {
	
	private ControleDeAlunos controle;
	
    @BeforeEach
    public void initControleDeAlunos() {
    	controle = new ControleDeAlunos();
    }
    
	@Test
	public void testaCadastraAluno() {
		assertEquals(controle.cadastraAluno("119210741", "Gustavo", "CC"), "CADASTRO REALIZADO!");
		try {
			controle.cadastraAluno("", "Gustavo", "CC");
		} catch(IllegalArgumentException nexc) {
			assertEquals("Matrícula, nome ou curso vazios!", nexc.getMessage());
		}
		try {
			controle.cadastraAluno("      ", "Gustavo", "CC");
		} catch(IllegalArgumentException nexc) {
			assertEquals("Matrícula, nome ou curso formado(s) apenas por espaço(s)!", nexc.getMessage());
		}
		try {
			controle.cadastraAluno("119210741", "null", "CC");
		} catch(NullPointerException nexc) {
			assertEquals("Matrícula, nome ou curso nulos!", nexc.getMessage());
		}
		assertEquals(controle.cadastraAluno("119210741", "Gustavo", "CC"), "MATRÍCULA JÁ CADASTRADA!");
	}
	
	@Test
	public void testaConsultaAluno() {
		try {
			controle.consultaAluno("");
		} catch(IllegalArgumentException nexc) {
			assertEquals("Matrícula vazia!", nexc.getMessage());
		}
		try {
			controle.consultaAluno("     ");
		} catch(IllegalArgumentException nexc) {
			assertEquals("Matrícula formada apenas por espaços!", nexc.getMessage());
		}
		try {
			controle.consultaAluno("null");
		} catch(NullPointerException nexc) {
			assertEquals("Matrícula nula!", nexc.getMessage());
		}
		controle.cadastraAluno("119210741", "Gustavo", "CC");
		assertEquals("Aluno: 119210741 - Gustavo - CC", controle.consultaAluno("119210741"));
		assertEquals("Aluno não cadastrado.", controle.consultaAluno("123"));
	}
	
	@Test
	public void testaCadastraGrupo() {
		try {
			controle.cadastraGrupo("");
		} catch(IllegalArgumentException nexc) {
			assertEquals("Grupo vazio!", nexc.getMessage());
		}
		try {
			controle.cadastraGrupo("      ");
		} catch(IllegalArgumentException nexc) {
			assertEquals("Nome do Grupo formado apenas por espaços!", nexc.getMessage());
		}
		try {
			controle.cadastraGrupo("null");
		} catch(NullPointerException nexc) {
			assertEquals("Grupo nulo!", nexc.getMessage());
		}
		assertEquals("CADASTRO REALIZADO!", controle.cadastraGrupo("FiestaLOka"));
		assertEquals("GRUPO JÁ CADASTRADO!", controle.cadastraGrupo("Fiestaloka"));
	}
	
	@Test
	public void testaAlocaAluno() {
		controle.cadastraAluno("119210741", "Gustavo", "CC");
		controle.cadastraGrupo("FiestaLOka");
		try {
			controle.alocaAluno("", "fiestaloka");
		} catch(IllegalArgumentException nexc) {
			assertEquals("Matrícula/Grupo vazio(s)!", nexc.getMessage());
		}
		try {
			controle.alocaAluno("     ", "fiestaloka");
		} catch(IllegalArgumentException nexc) {
			assertEquals("Matrícula/Grupo formado(s) apenas por espaço(s)!", nexc.getMessage());
		}
		try {
			controle.alocaAluno("119210741", "null");
		} catch(NullPointerException nexc) {
			assertEquals("Matrícula/Grupo nulo(s)!", nexc.getMessage());
		}
		assertEquals("ALUNO ALOCADO!", controle.alocaAluno("119210741", "FIESTALOKA"));
		assertEquals("Aluno não cadastrado.\nGrupo não cadastrado.", controle.alocaAluno("11921074", "fiestaloka"));
		assertEquals("Aluno não cadastrado.\nGrupo não cadastrado.", controle.alocaAluno("119210741", "fiestalok"));
	}
	
	@Test
	public void testaImprimeGrupo() {
		controle.cadastraAluno("119210741", "Gustavo", "CC");
		controle.cadastraGrupo("FiestaLOka");
		assertEquals("Alunos do grupo FiestaLOka:", controle.imprimeGrupo("fiestaloka"));
		controle.alocaAluno("119210741", "FiesTaLoKa");
		try {
			controle.imprimeGrupo("");
		} catch(IllegalArgumentException nexc) {
			assertEquals("Grupo vazio!", nexc.getMessage());
		}
		try {
			controle.imprimeGrupo("     ");
		} catch(IllegalArgumentException nexc) {
			assertEquals("Nome do Grupo formado apenas por espaços!", nexc.getMessage());
		}
		try {
			controle.imprimeGrupo("null");
		} catch(NullPointerException nexc) {
			assertEquals("Grupo nulo!", nexc.getMessage());
		}
		assertEquals("Grupo não cadastrado.", controle.imprimeGrupo("fiesta"));
		assertEquals("Alunos do grupo FiestaLOka:\n* 119210741 - Gustavo - CC", controle.imprimeGrupo("fiestaloka"));
	}
	
	@Test
	public void testaCadastraAlunoRespondeuQuadro() {
		controle.cadastraAluno("119210741", "Gustavo", "CC");
		try {
			controle.cadastraAlunoRespondeuQuadro("");
		} catch(IllegalArgumentException nexc) {
			assertEquals("Matrícula vazia!", nexc.getMessage());
		}
		try {
			controle.cadastraAlunoRespondeuQuadro("      ");
		} catch(IllegalArgumentException nexc) {
			assertEquals("Matrícula formada apenas por espaços!", nexc.getMessage());
		}
		try {
			controle.cadastraAlunoRespondeuQuadro("null");
		} catch(NullPointerException nexc) {
			assertEquals("Matrícula nula!", nexc.getMessage());
		}
		assertEquals("Aluno não cadastrado.", controle.cadastraAlunoRespondeuQuadro("11921074"));
		assertEquals("ALUNO REGISTRADO!", controle.cadastraAlunoRespondeuQuadro("119210741"));
	}
	
	@Test
	public void testaImprimeAlunosResponderam() {
		assertEquals("Nenhum aluno cadastrado.", controle.imprimeAlunosQueResponderam()); 
		controle.cadastraAluno("119210741", "Gustavo", "CC");
		controle.cadastraAluno("111222333", "ChicoLopes", "CC");
		controle.cadastraAlunoRespondeuQuadro("119210741");
		controle.cadastraAlunoRespondeuQuadro("111222333");
		assertEquals("Alunos:\n1. 119210741 - Gustavo - CC\n2. 111222333 - ChicoLopes - CC", controle.imprimeAlunosQueResponderam());
	}
}
