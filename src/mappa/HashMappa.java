package mappa;

import java.util.HashMap;
import java.util.Map;

import element.CasellaCarte;
import element.CasellaDadi;
import element.Dadi;
import element.Deck;
import element.Element;
import element.Giocatore;
import element.Locanda;
import element.Molla;
import element.Panchina;
import element.Scale;
import element.Serpenti;


public enum HashMappa implements Mappa{
	
	INSTANCE;
	
	private int N, nRighe, nCol, ultimoG=-1, 
			nDadi, nPartita=1, actG=0, risDadi;
	

	public void setnRighe(int nRighe) {
		this.nRighe = nRighe;
	}

	public void setnCol(int nCol) {
		this.nCol = nCol;
	}

	private boolean doppioSei=false;

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	public int getUltimoG() {
		return ultimoG;
	}

	public void setUltimoG(int ultimoG) {
		this.ultimoG = ultimoG;
	}

	public void setnDadi(int nDadi) {
		this.nDadi = nDadi;
	}

	public int getnRound() {
		return nPartita;
	}

	public void setnRound(int nRound) {
		this.nPartita = nRound;
	}

	public int getActG() {
		return actG;
	}

	public void setActG(int actG) {
		this.actG = actG;
	}

	public Map<Integer, Element> getElements() {
		return elements;
	}

	public void setElements(Map<Integer, Element> elements) {
		this.elements = elements;
	}

	public Giocatore[] getGiocatori() {
		return giocatori;
	}

	public void setGiocatori(Giocatore[] giocatori) {
		this.giocatori = giocatori;
	}

	private Map<Integer, Element> elements= new HashMap<>();
	
	private Giocatore[]  giocatori;

	@Override
	public String getElement(int pos) {
		Element e = elements.get(pos);
		if(e==null)return "";
		if(e instanceof Serpenti) {
			Serpenti s= (Serpenti) e;
			return "SERPENTE "+ s.getInf();
		}
		else if(e instanceof Scale) {
			Scale l= (Scale) e;
			return "SCALA "+l.getSup();
		}
		else if(e instanceof Locanda ) return "LOCANDA";
		else if(e instanceof Panchina) return "PANCHINA";
		else if(e instanceof CasellaDadi) return "DADI";
		else if(e instanceof Molla) return "MOLLA";
		else if(e instanceof CasellaCarte) return "CARTE";
		else return "";
	}
	
	public void aggiungiElement(Element e) {
		int pos=e.getPosAzione();
		if(elements.containsKey(pos))throw new IllegalArgumentException("Non è possibile aggiungere elementi in questa posizione");
		elements.put(pos, e);
	}
	
	private String giocaUnaPartitaP(int giocatore) {
		StringBuilder sb= new StringBuilder();
		Giocatore g=giocatori[giocatore];
		ultimoG=giocatore;
		int x=giocatore+1;
		sb.append("Tocca al giocatore "+x+" in posizione "+g.getCurrPos()+'\n');
		if(g.getRoundsToWait()!=0) {
			sb.append(g+" deve attendere ancora "+g.getRoundsToWait()+" giri per giocare."+'\n');
			g.setRoundsToWait(g.getRoundsToWait()-1);
			return sb.toString();
		}
		int ris=g.lancioDadi();
		risDadi=ris;
		sb.append("Il giocatore "+x+" ha ottenuto "+ris+'\n');
		int nPos;
		if(g.getCurrPos()+ris>N) {
			int margin=g.getCurrPos()+ris-N;
			nPos=N-margin;
		}
		else{
			nPos=g.getCurrPos()+ris;
		}
		insGiocatore(g,nPos, sb);
		if(g.getLastScore()==12 && doppioSei) {
			sb.append("Doppio 6, il giocatore rilancia."+'\n');
			sb.append(giocaUnaPartitaP(g.getCardinal()-1));
		}
		return sb.toString();
	}
	

	@Override
	public String giocaUnaPartita() {
		if(actG==giocatori.length-1)nPartita++;
		String s=giocaUnaPartitaP(actG);
		ultimoG=actG;
		actG=(actG+1)%giocatori.length;
		return s;
	}

	@Override
	public int getPosGiocatore(int g) {
		return giocatori[g].getCurrPos();
	}

	@Override
	public int getRisDadi() {
		return risDadi;
	}

	@Override
	public boolean giocatoreBloccato(int g) {
		return giocatori[g].getRoundsToWait()!=0;
	}

	@Override
	public void restart() {
		for(Giocatore g : giocatori) g.reset();
		
	}

	@Override
	public int getNumGiocatori() {
		return giocatori.length;
	}

	@Override
	public int getNumRighe() {
		return nRighe;
	}

	@Override
	public int getNumColonne() {
		return nCol;
	}

	@Override
	public int getNumDadi() {
		return nDadi;
	}

	@Override
	public int getPartita() {
		return nPartita;
	}

	@Override
	public int getActGiocatore() {
		return actG;
	}
	
	private int insGiocatore(Giocatore g, int pos, StringBuilder sb) {
		//sposta i giocatori, in base all'elemento trovato sulla mappa, nella posizione "pos"
		Element e=elements.get(pos);
		g.moveTo(pos);
		if (e==null) {
			return g.getCurrPos(); 
		}
		int nPos=e.azione(g, sb);
		if(nPos>N) {
			int margine=nPos-N;
			nPos=N-margine;
		}
		else if(nPos==g.getCurrPos()) {
			return nPos;
		}
		return insGiocatore(g, nPos, sb);
	}

	public void attivaDoppioSei() {
		setDoppioSei(true);
	}
	
	@Override
	public boolean doppioSei() {
		return doppioSei;
	}

	@Override
	public StatoMappa save() {
		return new StatoMappa(N, elements, doppioSei, giocatori, risDadi, nDadi,
				nRighe, nCol, nPartita, ultimoG, actG );
	}

	@Override
	public void restore(StatoMappa sm) {
		this.N=sm.getN();
		this.elements=sm.getElements();
		this.doppioSei=sm.doppioSei();
		this.giocatori=sm.getGiocatori();
		this.nDadi=sm.getnDadi();
		this.ultimoG=sm.getUltimoG();
		this.actG=sm.getActGiocatore();
		this.risDadi=sm.getRisDadi();
		this.nPartita=sm.getPartite();
		this.nRighe=sm.getnRighe();
		this.nCol=sm.getnCol();
		Dadi.INSTANCE.setNumDadi(nDadi);
		Deck.INSTANCE.setDeck(sm.getCarte());
	}

	public void setRisDadi(int risDadi) {
		this.risDadi = risDadi;
	}

	public boolean isDoppioSei() {
		return doppioSei;
	}

	public void setDoppioSei(boolean doppioSei) {
		this.doppioSei = doppioSei;
	}

}
