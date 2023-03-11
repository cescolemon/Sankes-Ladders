package grafica;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mappa.Mappa;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

import javax.swing.border.MatteBorder;
import java.awt.Dimension;
import javax.swing.JTextPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;


public class FramePrincipale extends JFrame {

	private static final long serialVersionUID = 599450888378679784L;
	private JPanel contentPane;
	private Casella[] caselle;
	private int righe,col, N, nGiocatori;
	private Mappa mappa;
	private boolean vittoria=false;
	private JButton btnNewButton_4, btnNewButton, btnNewButton_1, btnNewButton_2;
	private JScrollPane scrollPane;
	private JTextArea eventi;
	
	/**
	 * Create the frame.
	 */
	public FramePrincipale(Mappa mappa) {
		setResizable(false);
		if(mappa==null)
			throw new IllegalArgumentException("La mappa non deve essere null");
		righe=mappa.getNumRighe(); System.out.println("righe "+righe);
		col=mappa.getNumColonne(); System.out.println("colonne "+col);
		this.mappa=mappa;
		nGiocatori=mappa.getNumGiocatori(); System.out.println("giocatori "+nGiocatori);
		if(righe<=0 || col <=0)
			throw new IllegalArgumentException("Righe e colonne devono essere >0");
		if(nGiocatori<=0)
			throw new IllegalArgumentException("Numero giocatori >0");
		this.N=righe*col;
		setForeground(SystemColor.textHighlight);
		setTitle("Scale&Serpenti");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1196, 689);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.DARK_GRAY);
		menuBar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setJMenuBar(menuBar);
		
		btnNewButton = new JButton("Simula partita");
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				btnNewButton_4.setEnabled(false);
				simula();
			}
		});
		menuBar.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Salva partita");
		btnNewButton_1.setBackground(SystemColor.inactiveCaption);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				int res = jfc.showSaveDialog(null);
				if(res==JFileChooser.APPROVE_OPTION) {
					File f = jfc.getSelectedFile();
					if(f.exists())
						if(JOptionPane.showConfirmDialog(jfc, "Sovrascrivere il file "+f.getName()+" ?")==JOptionPane.OK_OPTION);
						else return;
					String name= f.getAbsolutePath();
					if(!name.endsWith(".dat")) name+=".dat";
					try {
						FileOutputStream fos= new FileOutputStream(name);
						ObjectOutputStream oos= new ObjectOutputStream(fos);
						oos.writeObject(mappa.save());
						oos.close(); fos.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
						
				}
			}
			
		});
		menuBar.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Riavvia");
		btnNewButton_2.setBackground(SystemColor.inactiveCaption);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				riavvia();
			}
		});
		menuBar.add(btnNewButton_2);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	
		PanelMap mapPanel = new PanelMap(mappa);
		mapPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		
		mapPanel.setBackground(Color.LIGHT_GRAY);
		
		
		JScrollPane scrollEventi= new JScrollPane(eventi, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollEventi.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollEventi.setBackground(Color.BLACK);
		scrollEventi.setForeground(Color.GREEN);
		
		JLabel lblDoppioSei = new JLabel();
		if(mappa.doppioSei()) lblDoppioSei.setText("Doppio Sei: Sì");
		else lblDoppioSei.setText("Doppio Sei: No");
		
		
		btnNewButton_4 = new JButton("=Avanza>>");
		btnNewButton_4.setBackground(SystemColor.activeCaption);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				giocaPartita();
			}
		});
		btnNewButton_4.setFont(new Font("DejaVu Sans", Font.ITALIC, 25));
		
		scrollPane = new JScrollPane(mapPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	
		JLabel lblNewLabel = new JLabel("Numero dadi: "+mappa.getNumDadi());
		lblNewLabel.setFont(new Font("Papyrus", Font.BOLD, 18));
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setFont(new Font("Papyrus", Font.BOLD, 18));
		if(mappa.doppioSei()) lblNewLabel_1.setText("Doppio 6: Sì");
		else lblNewLabel_1.setText("Doppio 6: No");
		
		JLabel lblNewLabel_2 = new JLabel("Progetto esame Ingegneria del Software - Francesco Pignolo.");

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollEventi, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 183, Short.MAX_VALUE)
									.addGap(424))
								.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE)
							.addGap(69))))
				.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1044, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollEventi, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))
		);
		
		eventi = new JTextArea();
		scrollEventi.setViewportView(eventi);
		eventi.setForeground(Color.GREEN);
		eventi.setBackground(Color.DARK_GRAY);
		eventi.setFont(new Font("Papyrus", Font.BOLD | Font.ITALIC, 10));
		
		
		contentPane.setLayout(gl_contentPane);
		caselle=mapPanel.getCaselle();
		if (caselle!=null) {
			for (Casella b : caselle) {
				if (b == null)
					continue;
				aggiornaCasella(b);
			}
			for(int i=0; i<nGiocatori; i++) {
				int pos=mappa.getPosGiocatore(i);
				caselle[pos].addGiocatore(i);
			}
		}
	}
	public void aggiornaCasella(Casella casella) {
		int cardinal=casella.getCardinal();
		String element=mappa.getElement(cardinal);
		casella.addElement(element);
	}
	
	private void giocaPartita() {
		int actG=mappa.getActGiocatore();
		int lastPos=mappa.getPosGiocatore(actG);
		String event=mappa.giocaUnaPartita();
		int x=actG+1;
		eventi.append(event);
		eventi.append(""+'\n');
		int pos=mappa.getPosGiocatore(actG);
		if(caselle!=null)
			caselle[pos].addGiocatore(actG);
		if(pos!=lastPos && caselle!=null)//controllo sul movimento del giocatore
			caselle[lastPos].clearGiocatori(actG);
		if(pos==N) {
			vincita();
			JOptionPane.showMessageDialog(contentPane, "Il giocatore "+x+" ha vinto!");
		}
	}
	
	private void simula() {
		while(!vittoria) {
			giocaPartita();
		}
	}
	
	private void riavvia() {
		mappa.restart();
		vittoria=false;
		btnNewButton_1.setEnabled(true);
		btnNewButton_4.setEnabled(true);
		btnNewButton_2.setEnabled(false);
		eventi.setText("");
	}
	
	private void vincita() {
		vittoria=true;
		btnNewButton_1.setEnabled(false);
		btnNewButton_4.setEnabled(false);
		btnNewButton_2.setEnabled(true);
	}
	
}
