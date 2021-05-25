package ufcg.ccc.domino;

/**
 * Uma peça de dominó com dois lados.
 *
 */
public class Peca implements Comparable<Peca>{

	private int numEsquerdo;
	private int numDireito;

	/**
	 * Cria uma peça.
	 * 
	 * @param numEsquerdo Número do lado esquerdo.
	 * @param numDireito  Número do lado direito.
	 */
	public Peca(int numEsquerdo, int numDireito) {
		this.numEsquerdo = numEsquerdo;
		this.numDireito = numDireito;
	}

	/**
	 * Inverte os lados dos números na peça.
	 */
	public void gira() {
		int tmp = numEsquerdo;
		numEsquerdo = numDireito;
		numDireito = tmp;
	}

	/**
	 * 
	 * @return O número da direita.
	 */
	public int getNumDireito() {
		return numDireito;
	}

	/**
	 * 
	 * @return O número da esquerda.
	 */
	public int getNumEsquerdo() {
		return numEsquerdo;
	}

	@Override
	public String toString() {
		return this.getNumEsquerdo() + ":" + this.getNumDireito();
	}

	/**
	 * Testa se a peça encaixa com um número.
	 * 
	 * @param numero O número a testar.
	 * @return true se um dos lados ao menos combinar com o númer.
	 */
	public boolean encaixa(int numero) {
		return this.numDireito == numero || this.numEsquerdo == numero;
	}

	/**
	 * Verifica se uma peça é carroção.
	 * 
	 * @return true caso seja. false, caso não.
	 */
	public boolean isCarrocao() {
		return this.numDireito == this.numEsquerdo;
	}
	
	/**
	 * Verifica se a soma dos lados de uma peça é maior
	 * que a soma dos lados de uma peça passada como
	 * parâmetro.
	 * 
	 * @param p a peça passada como parâmetro
	 * @return true caso esta peça seja maior. false, caso não.
	 */
	public boolean isMaior(Peca p) {
		if (p.getNumDireito() + p.getNumEsquerdo() < this.numDireito + this.numEsquerdo) {
			return true;
		}
		return false;
	}
	
	@Override
	public int compareTo(Peca p) {
		int som = this.numDireito + this.numEsquerdo;
		int somPecaExtern = p.getNumDireito() + p.getNumEsquerdo();
		if (som < somPecaExtern) { return 1; }
		else if(som > somPecaExtern) { return -1;}
		else { return 0; }
	}
}
