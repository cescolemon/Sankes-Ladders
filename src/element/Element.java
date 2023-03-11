package element;

import java.util.Set;

public interface Element {
	
	int azione(Giocatore g, StringBuilder sb);
	
	void elementPosition(Set<Integer> set, int ... pos);
	
	int getPosAzione();

}
