import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import facade.ControlerCentral;

public class US7Teste {

	private ControlerCentral cc;
	
	@BeforeEach
	public void initControlerCentral() {
		this.cc = new ControlerCentral();
	}
	
	@Test
	public void testaTudoUS7() {
		try {
			this.cc.adicionaFornecedor("guga", ".com", "n sei");
			this.cc.adicionaProduto("guga", "banana", "bananinha", 3);
			this.cc.adicionaProduto("guga", "sal", "salzinho", 1);
			this.cc.adicionaCombo("gug", "banana com sal", "bananinha com salzinho", 0.25, "banana - bananinha, sal - salzinho");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro no cadastro de combo: fornecedor nao existe.", nexc.getMessage());
		}
		try {
			this.cc.adicionaFornecedor("gugao", ".com", "n sei");
			this.cc.adicionaProduto("gugao", "bananao", "bananinhao", 3);
			this.cc.adicionaProduto("gugao", "salzao", "salzinhoo", 1);
			this.cc.adicionaCombo("guga", "banana com sal", "bananinha com salzinho", 0.5, "banana - bananinha, sa - salzinho");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro no cadastro de combo: produto nao existe.", nexc.getMessage());
		}
		try {
			this.cc.adicionaCombo("guga", "banana com sal", "bananinha com salzinho", -0.25, "banana - bananinha, sal - salzinho");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro no cadastro de combo: fator invalido.", nexc.getMessage());
		}
		this.cc.adicionaCombo("gugao", "banana com sal", "bananinha com salzinho", 0.5, "bananao - bananinhao, salzao - salzinhoo");
		assertEquals("banana com sal - bananinha com salzinho - R$2,00", this.cc.exibeProduto("banana com sal", "bananinha com salzinho", "gugao"));
		this.cc.removeProduto("banana com sal", "bananinha com salzinho", "gugao");
		try {
			this.cc.exibeProduto("banana com sal", "bananinha com salzinho", "gugao");
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na exibicao de produto: produto nao existe.", nexc.getMessage());
		}
		this.cc.adicionaCombo("gugao", "banana com sal", "bananinha com salzinho", 0.5, "bananao - bananinhao, salzao - salzinhoo");
		this.cc.editaCombo("banana com sal", "bananinha com salzinho", "gugao", 0.25);
		assertEquals("banana com sal - bananinha com salzinho - R$3,00", this.cc.exibeProduto("banana com sal", "bananinha com salzinho", "gugao"));
		try {
			this.cc.editaCombo("banana com sal", "bananinha com salzinho", "gugao", 0);
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na edicao de combo: fator invalido.", nexc.getMessage());
		}
		try {
			this.cc.editaCombo("banana com sal", "bananinha com salzinho", "gugao", 1);
		} catch (IllegalArgumentException nexc) {
			assertEquals("Erro na edicao de combo: fator invalido.", nexc.getMessage());
		}
	}
}
