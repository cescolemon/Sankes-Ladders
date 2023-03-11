package element;

import java.io.Serializable;
import java.util.Random;

public enum Dadi implements Serializable {
	
	INSTANCE;
	
	private int N; //numero dadi
	private Random r= new Random();
	private int dimensione; 
	
	public void setNumDadi(int n) {
		if(n!=1 && n!=2)
			throw new IllegalArgumentException("Minimo 1 dado e massimo 2!");
		this.N=n;
	}
	
	public int lancioDadi(Giocatore g) {
		if(N!=1 && N!=2) 
			throw new IllegalStateException("Inserire prima il numero dei dadi!");
		int ret;
		if(N==1 || g.getCurrPos()>=(dimensione-6))
			ret= r.nextInt(6)+1;
		else ret= r.nextInt(6)+r.nextInt(6)+2;
		return ret;
	}

	public void setDimensioneMappa(int dimensione) {
		this.dimensione=dimensione;
	}
}
