package lab2;
/**
 * Saude é a classe que guarda os estados de saude fisica e mental
 * do Aluno. Saude guarda também um emoji que representa o sentimento
 * do aluno. Esse emoji não é mostrado no status caso o aluno
 * cadastre um novo estado físico ou mental.
 * 
 * @author Gustavo Araujo Santos - 119210741
 *
 */
public class Saude {
	private String saudeFisica;
	private String saudeMental;
	private boolean haEmoji;
	private String emoji;

	/**
	 * Construtor de Saude. A saude por padrão é inicializada
	 * como "boa".
	 */
	public Saude() 
	{
		this.saudeFisica = "boa";
		this.saudeMental = "boa";
		this.haEmoji = false;
	}
	/**
	 * Método que define a saude mental do aluno e cancela a existência do emoji.
	 * Opções: boa e fraca.
	 */
	public void defineSaudeMental(String saude)
	{
		this.saudeMental = saude;
		this.haEmoji = false;
	}
	/**
	 * Método que define a saude física do aluno e cancela a existência do emoji.
	 * Opções: boa e fraca.
	 */
	public void defineSaudeFisica(String saude)
	{
		this.saudeFisica = saude;
		this.haEmoji = false;
	}
	/**
	 * Método que gera uma representação String do status de saúde do aluno.
	 * Esse método pode retornar também um emoji junto com o status caso
	 * um emoji tenha sido cadastrado pelo aluno e nenhum estado de saude tenha
	 * sido cadastrado.
	 * 
	 * @return o status de saude com ou sem o emoji.
	 */
	public String getStatusGeral()
	{
		if(this.saudeFisica.equals("boa") && this.saudeMental.equals("boa") && haEmoji){
			return "boa" + " " + this.emoji;
		}
		else if(this.saudeFisica.equals("boa") && this.saudeMental.equals("fraca") && haEmoji){
			return "ok" + " " + this.emoji;
		}
		else if(this.saudeFisica.equals("fraca") && this.saudeMental.equals("boa") && haEmoji){
			return "ok" + " " + this.emoji;
		}
		else if (this.saudeFisica.equals("fraca") && this.saudeMental.equals("fraca") && haEmoji){
		    return "fraca" + " " + this.emoji;
		}
		else if(this.saudeFisica.equals("boa") && this.saudeMental.equals("boa")){
			return "boa";
		}
		else if(this.saudeFisica.equals("boa") && this.saudeMental.equals("fraca")){
			return "ok";
		}
		else if(this.saudeFisica.equals("fraca") && this.saudeMental.equals("boa")){
			return "ok";
		}
		else if (this.saudeFisica.equals("fraca") && this.saudeMental.equals("fraca")) {
			return "fraca";
		}
		return "";
	}
	/**
	 * Método que define um emoji. Quando um emoji é definido, a existência
	 * de emojis é mudada para true.
	 * 
	 * @param emoji o emoji a ser definido.
	 */
	public void defineEmoji(String emoji) {
		this.emoji = emoji;
		this.haEmoji = true;
	}
}