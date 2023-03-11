package element;

import java.util.Set;

public enum CasellaCarte implements Element {
	
	INSTANCE;
	
	private int pos;

	@Override
	public int azione(Giocatore g, StringBuilder sb) {
		Carte c= Deck.INSTANCE.prendiCarta();
		sb.append("Il giocatore pesca una carta: "+c+'\n');
		switch (c) {
		case PANCHINA: 
			Deck.INSTANCE.mettiSotto(c);
			return Panchina.INSTANCE.azione(g, sb);
		case LOCANDA:
			Deck.INSTANCE.mettiSotto(c);
			return Locanda.INSTANCE.azione(g, sb);
		case MOLLA:
			Deck.INSTANCE.mettiSotto(c);
			return Molla.INSTANCE.azione(g, sb);
		case DADI:
			Deck.INSTANCE.mettiSotto(c);
			return CasellaDadi.INSTANCE.azione(g, sb);
		case NOSTOP: 
			if(g.hasNoStopCard()) Deck.INSTANCE.mettiSotto(c);
			else
				g.noStopCardTaken();
			return g.getCurrPos();
		default:
			throw new IllegalStateException("Nessun'altro caso specificato!");
		}
	}

	@Override
	public void elementPosition(Set<Integer> set, int... pos) {
		if(pos.length!=1)
			throw new IllegalStateException("La casella delle carte può avere una sola posizione");
		int p=pos[0];
		if(set.contains(p))
			throw new IllegalArgumentException("Posizione già occupata, scegline una differente.");
		this.pos=p;
		set.add(p);
	}

	@Override
	public int getPosAzione() {
		return pos;
	}

}
