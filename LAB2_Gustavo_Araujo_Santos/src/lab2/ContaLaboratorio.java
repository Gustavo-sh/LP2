package lab2;
/**
 * ContaLaboratório é a classe que trabalha em cima das ações
 * feitas por Aluno na escolha "LABORATORIO".
 * 
 * @author Gustavo Araujo Santos - 119210741
 *
 */
public class ContaLaboratorio {
	private String nomeLab;
	private int cota;
	private int novaCota;

	public ContaLaboratorio(String nomeLab)
	{
		this.nomeLab = nomeLab;
	}
	
	public ContaLaboratorio(String nomeLab, int cota)
	{
		this.nomeLab= nomeLab;
		this.novaCota = cota;
	}
	
	/**
	 * Método que libera espaço reduzindo a quantidade atual da cota.
	 * 
	 * @param mbytes a quantidade de MB a ser liberada.
	 */
	public void liberaEspaco(int mbytes)
	{
		this.cota -= mbytes;
	}
	/**
	 * Método que consome espaço aumentando a quantidade atual da cota.
	 * 
	 * @param mbytes a quantidade de MB a ser liberada.
	 */
	public void consomeEspaco(int mbytes)
	{
		this.cota += mbytes;
	}
	/**
	 * Método que verifica se já atingiu a cota ou não. 2000 MB é a 
	 * cota padrão.
	 * 
	 * @return true caso tenha atingido a cota, false caso não.
	 */
	public boolean atingiuCota()
	{
		if(novaCota == 0)
		{
		    return (cota >= 2000);
		}
		else
		{
			return (cota >= novaCota);
		}
	}
	/**
	 * Método que retorna uma representação String do laboratório.
	 * segue o seguinte modelo: "NOMEDOLABORATORIO COTAATUAL COTAPADRAO/NOVACOTA" 
	 */
	@Override
	public String toString()
	{
		if(novaCota == 0)
		{
		    return (this.nomeLab + " " + this.cota + "/2000");
		}
		else
		{
			return (this.nomeLab + " " + this.cota + "/" + this.novaCota);
		}
	}
	/**
	 * Retorna o nome do laboratório.
	 * @return
	 */
	public String getNome()
	{
		return this.nomeLab;
	}
}
