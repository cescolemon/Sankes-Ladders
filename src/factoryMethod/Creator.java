package factoryMethod;

import java.util.HashSet;
import java.util.Set;

import element.Dadi;
import element.Deck;
import element.Giocatore;
import mappa.HashMappa;
import mappa.Mappa;

public abstract class Creator {
	protected Set<Integer> occupato;
	protected int ris;
	
	public Mappa getMappa() {
		return HashMappa.INSTANCE;
	}
	
	public int createSpazio(int rig, int col) {
		int n = rig*col;
		HashMappa.INSTANCE.setN(n);
		HashMappa.INSTANCE.setnCol(col);
		HashMappa.INSTANCE.setnRighe(rig);
		occupato=new HashSet<>(n);
		ris=n;
		return n;
	}
	
	public abstract void factoryMov(String element, int[]... pos);
	
	public abstract void factoryStab(String element, int... pos);
	
	public abstract void factoryRandom(String element, int N);
	
	public void createDoppioSei() {
		HashMappa.INSTANCE.attivaDoppioSei();
		
	}
	
	public Giocatore[] createGiocatore(int N) {
		if(N<2) throw new IllegalArgumentException("Ci devono essere almeno 2 giocatori");
		Giocatore[] giocatori= new Giocatore[N];
		for(int i=0; i<N; i++) {
			giocatori[i]=new Giocatore();
			giocatori[i].setCardinal(i+1);
		}
		HashMappa.INSTANCE.setGiocatori(giocatori);
		return giocatori;
	}
	
	public Deck createDeck() {
		Deck.INSTANCE.generaDeck();
		return Deck.INSTANCE;
	}
	
	public Dadi createDadi(int N) {
		if(N>2 || N==0) throw new IllegalArgumentException("Ci possono essere massimo 2 dadi");
		Dadi.INSTANCE.setNumDadi(N);
		Dadi.INSTANCE.setDimensioneMappa(ris);
		HashMappa.INSTANCE.setnDadi(N);
		return Dadi.INSTANCE;
	}
	
	public Set<Integer> getSet() {
		return occupato;
	}
	
}
