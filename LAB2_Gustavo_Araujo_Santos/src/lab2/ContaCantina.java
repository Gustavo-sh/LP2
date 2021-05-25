package lab2;
/**
 * ContaCantina é a classe responsável por administrar as
 * ações feitas numa determinada cantina.
 * 
 * @author Gustavo Araujo Santos - 119210741
 *
 */
public class ContaCantina {
	private String nomeDaCantina;
	private int qtdItens;
	private int divida;// pode aumentar ou diminuir
	private int valorTotal; // apenas aumenta
	private String[] detalhes;
	private int detalhesRegistrados; // quantidade de detalhes para conferência

	/**
	 * Construtor único de ContaCantina. Inicializa todos os atributos
	 * da classe.
	 * 
	 * @param nomeDaCantina nome da cantina a ser inicializada.
	 */
	public ContaCantina(String nomeDaCantina)
	{
		this.nomeDaCantina = nomeDaCantina;
		this.detalhes = new String[1000];
		this.qtdItens = 0;
		this.divida = 0;
		this.valorTotal = 0;
		this.detalhesRegistrados = 0;
	}
	/**
	 * Método que cadastra um lanche a partir da quantidade de ítens e
	 * do valor total dos ítens.
	 * 
	 * @param qtdItens a quantidade total de ítens.
	 * @param valorCent o valor total dos ítens.
	 */
	public void cadastraLanche(int qtdItens, int valorCent)
	{
		this.qtdItens += qtdItens;
		this.divida += valorCent;
		this.valorTotal += valorCent;
	}
	/**
	 * Métodoq que subtrai um determinado valor da dívida atual 
	 * na cantina.
	 * 
	 * @param valorCent o valor a ser pago.
	 */
	public void pagaConta(int valorCent)
	{
		if (valorCent <= divida) {
			this.divida -= valorCent;
		}
	}
	/**
	 * @return quanto o aluno deve atualmente na cantina.
	 */
	public int getFaltaPagar()
	{
		return divida;
	}
	/**
	 * retorna uma representação String da cantina.
	 * Todo o valor gasto até agora é mostrado na
	 * chamada do método, independete do valor
	 * pago.
	 */
	@Override
	public String toString()
	{
		return (this.nomeDaCantina + " " + this.qtdItens + " " + this.valorTotal);
	}
	/**
	 * @return o nome da cantina.
	 */
	public String getNome()
	{
		return this.nomeDaCantina;
	}
	/**
	 * Método que cadastra um lanche com um descrição do mesmo.
	 * 
	 * @param qtdItens
	 * @param valorCent
	 * @param detalhes a descrição do lanche comprado.
	 */
	public void cadastraLanche(int qtdItens, int valorCent, String detalhes) {
		this.qtdItens += qtdItens;
		this.divida += valorCent;
		this.valorTotal += valorCent;
		this.detalhes[this.detalhesRegistrados] = detalhes;
		this.detalhesRegistrados += 1;
	}
	/**
	 * Método que lista os 5 ultimos detalhes cadastrados.
	 * 
	 * @return os 5 ultimos detalhes caso tenha 5 ou mais detalhes cadastrados, todos
	 * os detalhes caso tenha menos de 5 detalhes cadastrados.
	 */
	public String listarDetalhes() {
		String finn = "";
		if (this.detalhesRegistrados == 0) {
			return "Nenhum detalhe cadastrado.";
		}
		if (this.detalhesRegistrados > 5) {
			for (int i = this.detalhesRegistrados - 1; i > this.detalhesRegistrados - 6; i--) {
				finn += detalhes[i] + "\n";
			}
		} else if (this.detalhesRegistrados == 5) {
			for (int i = 4; i >= 0; i--) {
				finn += detalhes[i] + "\n";
			}
		} else {
			for (int i = this.detalhesRegistrados - 1; i >= 0; i--) {
				finn += detalhes[i] + "\n";
			}
		}
		return finn.substring(0, finn.length() - 1);
	}
}
