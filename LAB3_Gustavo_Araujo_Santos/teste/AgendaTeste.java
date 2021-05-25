import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import lab3.*;

public class AgendaTeste {

	private Agenda agenda; 
	
	@BeforeEach
	public void initAgenda() {
		
		this.agenda = new Agenda();
		
	}
	
	@Test
	public void testaCadastraContato() {
		
		String msg = "";
		
		try {
			agenda.cadastraContato(1, "", "san", "007", "008", "009", "1", "2", "3");
		} catch (IllegalArgumentException nexc) {
			msg = nexc.getMessage();
		}
		assertEquals("Nome vazio!", msg);
		
		try {
			agenda.cadastraContato(2, "    ", "san", "007", "008", "009", "1", "2", "3");
		} catch (IllegalArgumentException nexc) {
			msg = nexc.getMessage();
		}
		assertEquals("Nome formado apenas por espaços!", msg);
		
		try {
			agenda.cadastraContato(1, "null", "san", "007", "008", "009", "1", "2", "3");
		} catch (NullPointerException nexc) {
			msg = nexc.getMessage();
		}
		assertEquals("Nome nulo!", msg);
		
		assertEquals(0, agenda.cadastraContato(0, "gus", "san", "", "", "", "", "", ""));
		assertEquals(1, agenda.cadastraContato(1, "gus", "san", "", "", "", "", "", ""));
		assertEquals(1, agenda.cadastraContato(99, "gus", "san", "999", "888", "777", "1", "3", "6"));
		assertEquals(1, agenda.cadastraContato(87, "gus", "san", "123", "321", "444", "2", "1", "5"));
		assertEquals(1, agenda.cadastraContato(16, "gus", "san", "12107", "00876 8", "444555", "3", "2", "1"));
		assertEquals(1, agenda.cadastraContato(18, "gus", "san", "", "", "", "1", "2", "3"));
		assertEquals(1, agenda.cadastraContato(34, "gus", "san", "111", "222", "333", "1", "1", "3"));
		
	}
	
	@Test
	public void testaExibeContato() {
		agenda.cadastraContato(1, "gus", "san", "", "", "", "", "", "");
		assertEquals("gus san", agenda.exibeContato("1"));
		agenda.cadastraContato(99, "gus", "san", "999", "888", "777", "1", "3", "6");
		assertEquals("gus san\n999 (prioritario)\n888\n777 (zap)", agenda.exibeContato("99"));
		agenda.cadastraContato(87, "gus", "san", "123", "321", "", "1", "1", "5");
		assertEquals("gus san\n123 (prioritario) (zap)\n321", agenda.exibeContato("87"));
		agenda.cadastraContato(19, "gus", "san", "", "321", "123", "2", "2", "5");
		assertEquals("gus san\n321 (prioritario) (zap)\n123", agenda.exibeContato("19"));
		agenda.cadastraContato(22, "gus", "san", "", "", "123", "3", "1", "5");
		assertEquals("gus san\n123 (prioritario)", agenda.exibeContato("22"));
		agenda.cadastraContato(25, "gus", "san", "", "", "", "3", "1", "34");
		assertEquals("gus san", agenda.exibeContato("25"));
		assertEquals("Posição inválida!", agenda.exibeContato("101"));
		assertEquals("Nenhum contato cadastrado nessa posição!", agenda.exibeContato("100"));
	}
	
	@Test
	public void testaListaContatos() {
		assertEquals("Nenhum contato cadastrado!", agenda.listaContatos());
		agenda.cadastraContato(1, "gus", "san", "", "", "", "", "", "");
		agenda.cadastraContato(99, "gus", "san", "999", "888", "777", "1", "3", "6");
		assertEquals("1 - gus san\n99 - gus san\n", agenda.listaContatos());
	}
	
	@Test
	public void testaListaContatosPriori() {
		assertEquals("Nenhum contato prioritário cadastrado!", agenda.listaContatosPriori());
		agenda.cadastraContato(1, "gus", "san", "111", "222", "333", "2", "1", "");
		agenda.cadastraContato(99, "gus", "san", "999", "888", "777", "1", "3", "6");
		assertEquals("gus san - 222\ngus san - 999\n", agenda.listaContatosPriori());
		agenda.cadastraContato(1, "gus", "san", "111", "222", "333", "3", "1", "");
		agenda.cadastraContato(99, "gus", "san", "999", "888", "777", "", "3", "6");
		assertEquals("gus san - 333\ngus san - Não tem\n", agenda.listaContatosPriori());
	}
	
	@Test
	public void testaListaContatosZap() {
		assertEquals("Nenhum contato zap cadastrado!", agenda.listaContatosZap());
		agenda.cadastraContato(1, "gus", "san", "111", "222", "333", "2", "1", "");
		agenda.cadastraContato(99, "gus", "san", "999", "888", "777", "1", "3", "6");
		assertEquals("gus san - 111\ngus san - 777", agenda.listaContatosZap());
		agenda.cadastraContato(1, "gus", "san", "111", "222", "333", "3", "1", "");
		agenda.cadastraContato(99, "gus", "san", "999", "888", "777", "", "", "6");
		assertEquals("gus san - 111\ngus san - Não tem", agenda.listaContatosZap());
	}
	
	@Test
	public void testaConsultaPorNome() {
		agenda.cadastraContato(1, "gus", "san", "111", "222", "333", "2", "1", "");
		agenda.cadastraContato(99, "gus", "san", "999", "888", "777", "1", "3", "6");
		assertEquals("1 - gus san\n99 - gus san", agenda.consultaPorNome("gus"));
		assertEquals("Nenhum contato com esse nome!", agenda.consultaPorNome("samanto"));
	}
	
	@Test
	public void testaConultaPorNvAmizade() {
		agenda.cadastraContato(1, "gus", "san", "", "", "", "", "", "");
		assertEquals("1 - gus san", agenda.consultaPorNvAmizade("1"));
		agenda.cadastraContato(99, "gus", "san", "999", "888", "777", "1", "3", "6");
		assertEquals("1 - gus san\n99 - gus san", agenda.consultaPorNvAmizade("1"));
		assertEquals("Nenhum contato com esse nível de amizade!", agenda.consultaPorNvAmizade("101"));
		assertEquals("Digite um número correspondente ao nível de amizade!", agenda.consultaPorNvAmizade("seu TTT do TTT"));
	}
	
	@Test
	public void testaQuantNvAmizade() {
		assertEquals(0, agenda.quantNvAmizade("1"));
		agenda.cadastraContato(1, "gus", "san", "111", "222", "333", "2", "1", "");
		agenda.cadastraContato(99, "gus", "san", "999", "888", "777", "1", "3", "6");
		assertEquals(2, agenda.quantNvAmizade("1"));
	}
	
	@Test
	public void testaMediaAmizades() {
		assertEquals(0.0, agenda.mediaAmizades());
		agenda.cadastraContato(1, "gus", "san", "111", "222", "333", "2", "1", "");
		agenda.cadastraContato(99, "gus", "san", "999", "888", "777", "1", "3", "6");
		assertEquals(1.0, agenda.mediaAmizades());
	}
}
