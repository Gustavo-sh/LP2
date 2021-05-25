package lab2;
/**
 * Disciplina é a classe responsável pelas ações em uma
 * determinada disciplina iniciada pelo Aluno.
 * 
 * @author Gustavo Araujo Santos - 119210741
 *
 */
public class Disciplina {
	private String nomeDaDisciplina;
	private double[] notas;
	private String[] pesos;
	private int horasEstudadas = 0;
	private double media;
	private int notasCadastradas;
	/**
	 * Construtor de DIsciplina que recebe os pesos das notas 
	 * além do número de notas e do nome da disciplina.
	 * 
	 * @param numNotas número de notas que poderão ser cadastradas.
	 * @param pesos os respectivos pesos das notas no formato String[].
	 * @param nomeDaDisciplina o nome da disciplina que será estudada.
	 */
	public Disciplina(int numNotas, String[] pesos, String nomeDaDisciplina) {
		this.notas = new double[numNotas];
		this.pesos = pesos;
		this.nomeDaDisciplina = nomeDaDisciplina;
	}
	/**
	 * Construtor que inicializa uma Disciplina apenas com a quantidade
	 * de notas e com o nome da disciplina.
	 * 
	 * @param numNotas
	 * @param nomeDaDisciplina
	 */
	public Disciplina(int numNotas, String nomeDaDisciplina) {
		this.notas = new double[numNotas];
		this.pesos = new String[1];
		this.nomeDaDisciplina = nomeDaDisciplina;
	}
	/**
	 * Método que cadastra horas na quantidade total de horas estudadas.
	 * 
	 * @param horas quantidade de horas a adicionar.
	 */
	public void cadastraHoras(int horas)
	{
		this.horasEstudadas += horas;
	}
	/**
	 * Método que cadastra uma nota no array de notas do tipo double.
	 * Esse método trata a posição de cadastro como uma casa a menos
	 * pelo fato de que a nota mais baixa a ser cadastrada é 1 e precisamos
	 * tratar também da posição 0 no array de notas para o calculo da media
	 * com pesos futuramente.
	 * 
	 * @param nota posição do cadastro da nota
	 * @param valorNota valor da nota
	 */
	public void cadastraNota(int nota, double valorNota)
	{
		if(nota <= this.notas.length)
		{
			/*
			 * verifica se a nota não tinha sido cadastrada antes.
			 */
			if (notas[nota - 1] == 0) {
				this.notasCadastradas += 1;
			}
			this.notas[nota - 1] = valorNota;
		}
	}
	/**
	 * Método que verifica se o aluno foi aprovado ou não.
	 * 
	 * @return true caso a média seja maior ou igual a 7 e false
	 * caso a média seja menor que 7.
	 */
	public boolean aprovado() {
		double soma = 0;
		if (this.pesos[0] == null) {
			for(int i = 0; i < notasCadastradas; i++) {
				soma += notas[i]; 
			}
			media = soma / notasCadastradas;
		}else {
			for(int i = 0; i < this.notas.length; i++) {
				soma += notas[i] * Integer.parseInt(pesos[i]); 
			}
			media = soma / 10;
		}
		return (media >= 7);
	}
	/**
	 * retorna uma representação String da Disciplina em questão
	 */
	@Override
	public String toString()
	{
		String finn = "";
		finn += (this.nomeDaDisciplina + " " + this.horasEstudadas + " " + this.media + " " + "[");
		for (int i = 0; i < this.notas.length; i++) {
			finn += notas[i] + ", ";
		}
		finn = finn.substring(0, finn.length() - 2) + "]";
		return finn;
	}
	/**
	 * @return retorna o nome da Disciplina.
	 */
	public String getNome()
	{
		return this.nomeDaDisciplina;
	}
	/**
	 * @return retorna a quantidade de notas cadastradas.
	 */
	public int getNotasCadastradas() {
		return this.notasCadastradas;
	}
}	