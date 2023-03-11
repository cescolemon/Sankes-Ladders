package element;

import java.util.Set;

public enum CasellaDadi implements Element {
	INSTANCE;
	
	private int pos;

	@Override
	public int azione(Giocatore g, StringBuilder sb) {
		int scelta= g.lancioDadi();
		int pos= scelta+g.getCurrPos();
		sb.append("Il giocatore lancia i dadi nuovamente ed ottiene "+scelta+'\n');
		return pos;
	}

	@Override
	public void elementPosition(Set<Integer> set, int... pos) {
		if(pos.length>1)
			throw new IllegalArgumentException("La casella dadi può avere una sola posizione");
		if(set.contains(pos[0]))
			throw new IllegalArgumentException("Posizione già utilizzata!");
		this.pos=pos[0];
		set.add(pos[0]);
	}

	@Override
	public int getPosAzione() {
		return pos;
	}

}
