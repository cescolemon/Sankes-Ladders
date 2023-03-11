package element;

import util.Moves;

public class Serpenti extends Moves implements Element {

	private static final long serialVersionUID = -6784080415372633528L;

	@Override
	public int azione(Giocatore g, StringBuilder sb) {
		if(g.getCurrPos()!=sup)
			throw new IllegalStateException("La posizione del giocatore non corrisponde a quella del serpente");
		sb.append("Il giocatore scende con il serpente fino a "+inf+'\n');
		return inf;
	}

	@Override
	public int getPosAzione() {
		return this.getSup();
	}

}
