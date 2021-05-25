import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import lab4.*;

public class GrupoDeEstudosTeste {

	@Test
	public void testaEquals() {
		GrupoDeEstudos grupo1 = new GrupoDeEstudos("fiestalokona");
		GrupoDeEstudos grupo2 = new GrupoDeEstudos("fiestaloka");
		assertFalse(grupo1.equals(grupo2));
		GrupoDeEstudos grupo3 = new GrupoDeEstudos("fiestaloka");
		assertTrue(grupo2.equals(grupo3));
	}

	@Test
	public void testaHashCode() {
		GrupoDeEstudos grupo1 = new GrupoDeEstudos("fiestalokona");
		GrupoDeEstudos grupo2 = new GrupoDeEstudos("fiestaloka");
		assertNotEquals(grupo1.hashCode(), grupo2.hashCode());
		GrupoDeEstudos grupo3 = new GrupoDeEstudos("fiestaloka");
		assertEquals(grupo2.hashCode(), grupo3.hashCode());
	}
}
