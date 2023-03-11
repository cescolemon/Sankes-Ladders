package factoryMethod;

import java.util.Random;

import element.CasellaCarte;
import element.CasellaDadi;
import element.Element;
import element.Locanda;
import element.Molla;
import element.Panchina;
import element.Scale;
import element.Serpenti;
import mappa.HashMappa;

public class ConcreteCreator extends Creator { //design pattern
	
	private void createSerpenti(int[]... s) {
		for(int[] a : s) {
			if(a.length!=2)throw new IllegalArgumentException("Servono 2 valori!");
			Element e = new Serpenti();
			e.elementPosition(occupato, a);
			HashMappa.INSTANCE.aggiungiElement(e);
		}
		
	}

	private void createScale(int[]... s) {
		for(int[] a : s) {
			if(a.length!=2)throw new IllegalArgumentException("Servono 2 valori!");
			Element e = new Scale();
			e.elementPosition(occupato, a);
			HashMappa.INSTANCE.aggiungiElement(e);
		}
	}
	private void createLocanda(int... pos) {
		for(int i : pos) {
			Element e = Locanda.INSTANCE;
			e.elementPosition(occupato, i);
			HashMappa.INSTANCE.aggiungiElement(e);
		}	
	}
	
	private void createPanchina(int... pos) {
		for(int i : pos) {
			Element e = Panchina.INSTANCE;
			e.elementPosition(occupato, i);
			HashMappa.INSTANCE.aggiungiElement(e);
		}
	}
	
	private void createMolla(int... pos) {
		for(int i : pos) {
			Element e = Molla.INSTANCE;
			e.elementPosition(occupato, i);
			HashMappa.INSTANCE.aggiungiElement(e);
		}
	}
	
	private void createCasellaDadi(int... pos) {
		for(int i : pos) {
			Element e = CasellaDadi.INSTANCE;
			e.elementPosition(occupato, i);
			HashMappa.INSTANCE.aggiungiElement(e);
		}
	}
	
	private void createCasellaCarte(int... pos) {
		for(int i : pos) {
			Element e = CasellaCarte.INSTANCE;
			e.elementPosition(occupato, i);
			HashMappa.INSTANCE.aggiungiElement(e);
		}
	}
	

	private void createSerpentiRandom(int N) {
		Random r = new Random();
		for(int i=0; i<N; i++) {
			int sup, inf;
			do {
				sup=r.nextInt(ris); inf=r.nextInt(ris)+1;
			}while(sup<=inf || occupato.contains(sup) || occupato.contains(inf));
			int[] posizione= {inf,sup};
			createSerpenti(posizione);
		}
		
	}
	
	private void createScaleRandom(int N) {
		Random r = new Random();
		for(int i=0; i<N; i++) {
			int sup, inf;
			do {
				sup=r.nextInt(ris)+1; inf=r.nextInt(ris-1)+2;
			}while(sup<=inf || occupato.contains(sup) || occupato.contains(inf));
			int[] posizione= {inf,sup};
			createScale(posizione);
		}
	}
	
	private void createLocandaRandom(int N) {
		Random r = new Random();
		for(int i=0; i<N; i++) {
			int pos;
			do {
				pos=r.nextInt(ris-1)+1; 
			}while(occupato.contains(pos));
			createLocanda(pos);
		}
	}
	
	private void createPanchinaRandom(int N) {
		Random r = new Random();
		for(int i=0; i<N; i++) {
			int pos;
			do {
				pos=r.nextInt(ris-1)+1; 
			}while(occupato.contains(pos));
			createPanchina(pos);
		}
	}
	
	private void createMollaRandom(int N) {
		Random r = new Random();
		for(int i=0; i<N; i++) {
			int pos;
			do {
				pos=r.nextInt(ris-1)+1; 
			}while(occupato.contains(pos));
			createMolla(pos);
		}
	}
	
	private void createCasellaDadiRandom(int N) {
		Random r = new Random();
		for(int i=0; i<N; i++) {
			int pos;
			do {
				pos=r.nextInt(ris-1)+1; 
			}while(occupato.contains(pos));
			createCasellaDadi(pos);
		}
	}
	
	private void createCasellaCarteRandom(int N) {
		Random r = new Random();
		for(int i=0; i<N; i++) {
			int pos;
			do {
				pos=r.nextInt(ris-1)+1; 
			}while(occupato.contains(pos));
			createCasellaDadi(pos);
		}
	}
	
	public void factoryMov(String element, int[]... pos) {
		if(element==null) throw new IllegalArgumentException("inserire tipologia elemento");
		if(element.equalsIgnoreCase("serpente")) {
			createSerpenti(pos);
		}
		if(element.equalsIgnoreCase("scala")) {
			createScale(pos);
		}
	}

	@Override
	public void factoryStab(String element, int... pos) {
		if(element==null) throw new IllegalArgumentException("inserire tipologia elemento");
		if(element.equalsIgnoreCase("locanda")) {
			createLocanda(pos);
		}
		if(element.equalsIgnoreCase("panchina")) {
			createPanchina(pos);
		}
		if(element.equalsIgnoreCase("molla")) {
			createMolla(pos);
		}
		if(element.equalsIgnoreCase("dadi")) {
			createCasellaDadi(pos);
		}
		if(element.equalsIgnoreCase("carte")) {
			createCasellaCarte(pos);
		}
		
	}

	@Override
	public void factoryRandom(String element, int N) {
		if(element==null) throw new IllegalArgumentException("inserire tipologia elemento");
		if(element.equalsIgnoreCase("locanda")) {
			createLocandaRandom(N);
		}
		if(element.equalsIgnoreCase("panchina")) {
			createPanchinaRandom(N);
		}
		if(element.equalsIgnoreCase("molla")) {
			createMollaRandom(N);
		}
		if(element.equalsIgnoreCase("dadi")) {
			createCasellaDadiRandom(N);
		}
		if(element.equalsIgnoreCase("carte")) {
			createCasellaCarteRandom(N);
		}
		if(element.equalsIgnoreCase("serpente")) {
			createSerpentiRandom(N);
		}
		if(element.equalsIgnoreCase("scala")) {
			createScaleRandom(N);
		}
		
	}
		
		

}
