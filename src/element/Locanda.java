package element;

import java.util.Set;

public enum Locanda implements Element{
	INSTANCE;
	
	private int pos;

	@Override
	public int azione(Giocatore g, StringBuilder sb) {
		if(g.hasNoStopCard()) {
			g.noStopCardConsumed();
			Deck.INSTANCE.mettiSotto(Carte.NOSTOP);
			sb.append("Il giocatore ha la carta No-Stop e continua a giocare."+'\n');
		}else {
			g.setRoundsToWait(3);
			sb.append("Il giocatore deve attendere 3 turni prima di giocare nuovamente."+'\n');
		}
		return g.getCurrPos();
	}

	@Override
	public void elementPosition(Set<Integer> set, int... pos) {
		if(pos.length>1)
			throw new IllegalArgumentException("La locanda può essere utilizzata in una sola posizione");
		if(set.contains(pos[0]))
			throw new IllegalArgumentException("Non è possibile inserirlo in questa posizione.");
		this.pos=pos[0];
		set.add(pos[0]);
	}

	@Override
	public int getPosAzione() {
		return pos;
	}

}
