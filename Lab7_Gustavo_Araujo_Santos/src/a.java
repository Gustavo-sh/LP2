public class a {

	public static void main(String args[]) {
		I1 x = new E();
		System.out.println(x instanceof B);
		System.out.println(x instanceof C);
		System.out.println(x instanceof A);
		System.out.println(x instanceof I1);
		System.out.println(x instanceof I2);
		
		Conta con = new Conta("sla");
		Conta c = new Poupanca("iok");
		Poupanca p1 = (Poupanca) c;
		
		//p = con;
		//System.out.println(p.getNome());
	}
}
