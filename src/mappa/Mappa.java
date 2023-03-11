package mappa;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import element.Carte;
import element.Deck;
import element.Element;
import element.Giocatore;


public interface Mappa {
	
	public String getElement(int pos);
	
	public String giocaUnaPartita();
	
	public int getPosGiocatore(int g); //posizione giocatore g
	
	public int getRisDadi(); //risultato dadi
	
	public boolean giocatoreBloccato(int k); 
	
	public void restart();
	
	public int getNumGiocatori();
	
	public int getNumRighe();
	
	public int getNumColonne();
	
	public int getNumDadi();
	
	public int getPartita();
	
	public int getActGiocatore();
	
	public boolean doppioSei(); //se attivo TRUE
	
	public StatoMappa save();
	
	public void restore(StatoMappa sm);
	
	class StatoMappa implements Serializable{
		
		private static final long serialVersionUID = 2904483145041459228L;
		private int N, nRighe, nCol, rounds , nDadi,risDadi, ultimoG, actG;
		private Map<Integer, Element> elements;
		
		private List<Carte> deck;

		private boolean doppioSei;
		private Giocatore[] giocatori;
		
		
		public StatoMappa(int N, Map<Integer, Element> elements, boolean doppioSei, Giocatore[] giocatori, int risDadi, int nDadi
				,int righe, int col, int rounds, int ultimoG, int actG) {
			this.N=N; this.risDadi=risDadi;
			this.elements=elements; this.doppioSei=doppioSei;
			this.giocatori=giocatori;
			this.nCol=col; this.nRighe=righe; this.rounds=rounds;
			this.nDadi=nDadi;
			this.deck=Deck.INSTANCE.getDeck();
			this.ultimoG=ultimoG;
			this.actG=actG;
		}

		public int getN() {
			return N;
		}
		public Map<Integer, Element> getElements() {
			return elements;
		}

		public List<Carte> getCarte() {
			return deck;
		}
		
		public void setElements(Map<Integer, Element> elements) {
			this.elements = elements;
		}

		public boolean doppioSei() {
			return doppioSei;
		}

		public Giocatore[] getGiocatori() {
			return giocatori;
		}

		public int getRisDadi() {
			return risDadi;
		}

		public int getnRighe() {
			return nRighe;
		}

		public int getPartite() {
			return rounds;
		}

		public int getnCol() {
			return nCol;
		}
		public int getnDadi() {
			return nDadi;
		}

		public int getUltimoG() {
			return ultimoG;
		}

		public int getActGiocatore() {
			return actG;
		}
			
	}

}
