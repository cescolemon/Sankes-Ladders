package element;

import java.util.Set;

public enum Panchina implements Element{
	
	INSTANCE;
	
	private int pos;

	@Override
	public int azione(Giocatore g, StringBuilder sb) {
		if(g.hasNoStopCard()) {
			g.noStopCardConsumed();
			Deck.INSTANCE.mettiSotto(Carte.NOSTOP);
			sb.append("Il giocatore ha una No-Stop card e continua a giocare.+'\n");
		}else {
			g.setRoundsToWait(1);
			sb.append("Il giocatore si ferma per un turno."+'\n');
		}
		return g.getCurrPos();
	}

	@Override
	public void elementPosition(Set<Integer> set, int... pos) {
		if(pos.length>1)
			throw new IllegalArgumentException("La panchina è utilizziabile su una sola posizione.");
		if(set.contains(pos[0]))
			throw new IllegalArgumentException("Posizione già utilizzata.");
		this.pos=pos[0];
		set.add(pos[0]);
		
	}

	@Override
	public int getPosAzione() {
		return pos;
	}

}
