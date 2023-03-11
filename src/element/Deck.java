package element;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public enum Deck {
	
	INSTANCE;
	
	private int N=50;
	
	private List<Carte> deck=new LinkedList<>();
	
	public void setDeck(List<Carte> deck) {
		this.deck=deck;
	}
	public List<Carte> getDeck(){
		return deck;
	}
	
	public void generaDeck() {
		for(int i=0; i<5; i++)
			for(int j=0; j<N/5; j++) {
				switch(i) {
				case 0 : 
					deck.add(Carte.PANCHINA); break;
				case 1:
					deck.add(Carte.LOCANDA); break;
				case 2:
					deck.add(Carte.MOLLA); break;
				case 3:
					deck.add(Carte.DADI); break;
				case 4:
					deck.add(Carte.NOSTOP); break;
				}
			}
		shuffle();
	}
	
	void shuffle() {
		Collections.shuffle(deck);
	}
	
	public Carte prendiCarta() {
		Carte c= deck.remove(0);
		return c;
	}
	
	public void mettiSotto(Carte c) {
		deck.add(deck.size(), c);
	}
	
	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append("Numero totale carte: "+deck.size()+'\n');
		for(Carte c : deck) sb.append(c.toString()+'\n');
		return sb.toString();
	}

}
