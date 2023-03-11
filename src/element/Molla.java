package element;

import java.util.Set;

public enum Molla implements Element {
	
	INSTANCE;
	
	private int pos;

	@Override
	public int azione(Giocatore g, StringBuilder sb) {
		int scelta= g.getLastScore()+g.getCurrPos();
		sb.append("Il giocatore ha preso una molla e si sposta nuovamente di "+g.getLastScore()+'\n');
		return scelta;
	}

	@Override
	public void elementPosition(Set<Integer> set, int... pos) {
		if(pos.length>1)
			throw new IllegalArgumentException("La molla funziona con una sola posizione");
		if(set.contains(pos[0]))
			throw new IllegalArgumentException("Posizione già utilizzata");
		this.pos=pos[0];
		set.add(pos[0]);
	}

	@Override
	public int getPosAzione() {
		return pos;
	}
	

}
