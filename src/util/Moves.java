package util;

import java.io.Serializable;
import java.util.Set;

import element.Element;


public abstract class Moves implements Element,Serializable {

	private static final long serialVersionUID = 1L;
	protected int sup, inf;

	@Override
	public void elementPosition(Set<Integer> set, int ... pos) {
		sup=pos[1]; inf=pos[0];
		if(sup<=inf) {
			throw new IllegalArgumentException("limite superiore deve essere maggiore del limite inferiore");
		}
		if(set.contains(sup) || set.contains(inf))
			throw new IllegalArgumentException("Posizione già occupata");
		this.setSup(sup); this.setInf(inf);
		set.add(sup); set.add(inf);
		
	}
	
	
	public int getSup() {
		return sup;
	}

	public void setSup(int sup) {
		this.sup = sup;
	}

	public int getInf() {
		return inf;
	}

	public void setInf(int inf) {
		this.inf = inf;
	}
}
