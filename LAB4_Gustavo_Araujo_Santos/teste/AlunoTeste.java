import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import lab4.*;

public class AlunoTeste {

	@Test
	public void testaAllInAluno() {
		Aluno aluno = new Aluno("119210741", "Gustavo", "CC");
		assertEquals("119210741", aluno.getMatricula());
		assertEquals("Gustavo", aluno.getNome());
		assertEquals("CC", aluno.getCurso());
		try {
			Aluno aluno2 = new Aluno("", "Gustavo", "CC");
		} catch(IllegalArgumentException nexc) {
			assertEquals("Matrícula, nome ou curso vazios!", nexc.getMessage());
		}
		Aluno aluno3 = new Aluno("119210741", "Gustavo", "CC");
		assertTrue(aluno3.equals(aluno));
		assertEquals(aluno3.hashCode(), aluno.hashCode());
		Aluno aluno4 = new Aluno("119210742", "Gustavo", "CC");
		assertFalse(aluno3.equals(aluno4));
		assertNotEquals(aluno3.hashCode(), aluno4.hashCode());
		assertEquals("119210741 - Gustavo - CC", aluno.toString());
		try {
			Aluno aluno5 = new Aluno("null", "Gustavo", "CC");
		} catch(NullPointerException nexc) {
			assertEquals("Matrícula, nome ou curso nulos!", nexc.getMessage());
		}
		try {
			Aluno aluno6 = new Aluno("    ", "Gustavo", "CC");
		} catch(IllegalArgumentException nexc) {
			assertEquals("Matrícula, nome ou curso formado(s) apenas por espaço(s)!", nexc.getMessage());
		}
	}
}
