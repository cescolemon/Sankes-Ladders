package grafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class Casella extends JPanel {

	private static final long serialVersionUID = 885835231352822043L;

	
	private JPanel num, giocatore, element;
	private JLabel[] giocatori;
	private int cardinal;
	
	
	public Casella(int nGiocatori) { //grafica singola casella
		super();
		this.setSize(10, 10);
		this.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		this.setLayout(new GridLayout(3,1,0,0));
		giocatori=new JLabel[nGiocatori];
		num=new JPanel();
		element=new JPanel();
		giocatore=new JPanel();
		this.add(num); this.add(giocatore); this.add(element);
		for(int i=0; i< nGiocatori; i++) {
			int x=i+1;
			JLabel nome=new JLabel("G"+x);
			nome.setFont(new Font("Papyrus", Font.BOLD, 12));
			giocatori[i]=nome;
			giocatore.add(giocatori[i]);
			giocatori[i].setVisible(false);
		}
	}

	public int getCardinal() {
		return cardinal;
	}

	public void setCardinal(int cardinal) {
		this.cardinal = cardinal;
		JLabel numero= new JLabel(""+cardinal);
		numero.setFont(new Font("Papyrus", Font.BOLD, 12));
		num.add(numero);
	}
	
	
	public void addGiocatore(int i) { //aggiunge n giocatori
		giocatori[i].setVisible(true);
		giocatore.revalidate();
	}
	
	public void clearGiocatori(int i) {
		giocatori[i].setVisible(false);
		giocatore.revalidate();
	}
	
	public void addElement(String txt) { //aggiunge un elemento da una stringa
		JLabel nome= new JLabel(txt);
		nome.setFont(new Font("Mono",Font.BOLD,10));
		element.add(nome);
	}
	
	public void removeElement() {
		element.removeAll();
	}
	
	public void addGiocatori() {
		for(JLabel player : giocatori)
			player.setVisible(true);
		giocatore.revalidate();
	}

}
