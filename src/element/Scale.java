package element;

import util.Moves;

public class Scale extends Moves implements Element {

	private static final long serialVersionUID = 8488217695897129299L;

	@Override
	public int azione(Giocatore g, StringBuilder sb) {
		if(g.getCurrPos()!=inf)
			throw new IllegalStateException("La posizione del giocatore non corrisponde a quella della scala");
		sb.append("Il giocatore sale con la scala fino a "+ sup+'\n');
		return sup;
	}

	@Override
	public int getPosAzione() {
		return this.getInf();
	}

}
