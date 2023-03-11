package grafica;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mappa.Mappa;

public class PanelMap extends JPanel {

	private static final long serialVersionUID = -4041482310870193964L;
	private Casella[] caselle;
	private int giocatori, N;
	
	public PanelMap(Mappa map) {
		super();
		if(map==null)throw new IllegalArgumentException("La mappa non può essere null");
		int rig=map.getNumRighe(); int col=map.getNumColonne(); int giocatori=map.getNumGiocatori();
		if(rig>12 || col >12 || giocatori >5) {
			/*
			 * Se la mappa di dimensioni elevate non viene rappresentata.
			 * Allo stesso tempo viene caricata un'immagine sostitutiva.
			 */
			try {
				BufferedImage img= ImageIO.read(this.getClass().getResource("/mappa.jpg"));
				JLabel imglbl=new JLabel(new ImageIcon(img.getScaledInstance(350, 350, Image.SCALE_SMOOTH)));
				this.add(imglbl);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		setSize(2,2);
		setLayout(new GridLayout(rig, col, 0, 0));
		N=rig*col;
		caselle=new Casella[N+1];
		for(int i=1; i<=N; i++) {
			caselle[i]=new Casella(giocatori);
			caselle[i].setCardinal(i);
			this.add(caselle[i]);
		}
	}
	
	public void addGiocatore(int pos, int giocatore) {
		if(giocatore>=giocatori)throw new IllegalArgumentException("Nessun giocatore");
		caselle[pos].addGiocatore(giocatore);
	}
	
	public void removeGiocatore(int pos, int giocatore) {
		caselle[pos].clearGiocatori(giocatore);
	}
	
	public void setGiocatori(int giocatori) {
		this.giocatori = giocatori;
	}
	
	public Casella[] getCaselle() {
		return caselle;
	}

}
