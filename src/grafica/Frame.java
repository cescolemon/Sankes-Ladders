package grafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;



import factoryMethod.ConcreteCreator;
import factoryMethod.Creator;
import mappa.Mappa;
import java.awt.Color;

public class Frame extends JFrame {

	private static final long serialVersionUID = -5199636951321272244L;
	private JFrame frame;
	private JTextField textField_giocatori;
	private JTextField textField_serpenti;
	private JTextField textField_scale;
	private JTextField textField_colonne;
	private JTextField textField_righe;
	private JTextField textField_molle;
	private JTextField textField_panchine;
	private JTextField textField_dadi;
	private JTextField textField_carte;
	private JTextField textField_locande;
	private  Creator creator;
	private int nRighe, nCol, giocatori;
	private JCheckBox serpentiRandom, scaleRandom, locandeRandom, molleRandom, 
					  dadiRandom, panchineRandom, carteRandom, doppioSei;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_numDadi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame() {
		initialize();
	}

	private void avviaFramePrincipale(Mappa gm) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePrincipale mf= new FramePrincipale(gm);
					mf.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		this.dispose();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Scale&Serpenti");
		frame.getContentPane().setFont(new Font("Thames", Font.PLAIN, 13));
		frame.setBounds(100, 100, 579, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		serpentiRandom = new JCheckBox("Genera Serpenti Random");
		serpentiRandom.setSelected(true);
		serpentiRandom.setFont(new Font("Thames", Font.BOLD, 12));
		serpentiRandom.setBounds(25, 29, 163, 21);
		frame.getContentPane().add(serpentiRandom);
		
		scaleRandom = new JCheckBox("Genera Scale Random");
		scaleRandom.setSelected(true);
		scaleRandom.setFont(new Font("Thames", Font.BOLD, 12));
		scaleRandom.setBounds(25, 52, 148, 21);
		frame.getContentPane().add(scaleRandom);
		
		locandeRandom = new JCheckBox("Genera Locande Random");
		locandeRandom.setSelected(true);
		locandeRandom.setFont(new Font("Thames", Font.BOLD, 12));
		locandeRandom.setBounds(25, 75, 163, 21);
		frame.getContentPane().add(locandeRandom);
		
		molleRandom = new JCheckBox("Genera Molle Random");
		molleRandom.setSelected(true);
		molleRandom.setFont(new Font("Thames", Font.BOLD, 12));
		molleRandom.setBounds(25, 98, 148, 21);
		frame.getContentPane().add(molleRandom);
		
		dadiRandom = new JCheckBox("Genera Dadi Random");
		dadiRandom.setSelected(true);
		dadiRandom.setFont(new Font("Thames", Font.BOLD, 12));
		dadiRandom.setBounds(25, 121, 148, 21);
		frame.getContentPane().add(dadiRandom);
		
		panchineRandom = new JCheckBox("Genera Panchine Random");
		panchineRandom.setSelected(true);
		panchineRandom.setFont(new Font("Thames", Font.BOLD, 12));
		panchineRandom.setBounds(25, 144, 163, 21);
		frame.getContentPane().add(panchineRandom);
		
		carteRandom = new JCheckBox("Genera Carte Random");
		carteRandom.setSelected(true);
		carteRandom.setFont(new Font("Thames", Font.BOLD, 12));
		carteRandom.setBounds(25, 166, 148, 21);
		frame.getContentPane().add(carteRandom);
		
		doppioSei = new JCheckBox("Doppio sei");
		doppioSei.setFont(new Font("Thames", Font.BOLD, 12));
		doppioSei.setBounds(25, 192, 93, 20);
		frame.getContentPane().add(doppioSei);
		
		textField_giocatori = new JTextField("4");
		textField_giocatori.setBounds(478, 29, 60, 19);
		frame.getContentPane().add(textField_giocatori);
		textField_giocatori.setColumns(10);
		
		textField_serpenti = new JTextField("5");
		textField_serpenti.setBounds(478, 53, 60, 19);
		frame.getContentPane().add(textField_serpenti);
		textField_serpenti.setColumns(10);
		
		textField_scale = new JTextField("5");
		textField_scale.setBounds(478, 76, 60, 19);
		frame.getContentPane().add(textField_scale);
		textField_scale.setColumns(10);
		
		textField_colonne = new JTextField("10");
		textField_colonne.setBounds(478, 99, 60, 19);
		frame.getContentPane().add(textField_colonne);
		textField_colonne.setColumns(10);
		
		textField_righe = new JTextField("10");
		textField_righe.setBounds(478, 122, 60, 19);
		frame.getContentPane().add(textField_righe);
		textField_righe.setColumns(10);
		
		textField_molle = new JTextField("5");
		textField_molle.setBounds(478, 145, 60, 19);
		frame.getContentPane().add(textField_molle);
		textField_molle.setColumns(10);
		
		textField_panchine = new JTextField("5");
		textField_panchine.setBounds(478, 168, 60, 19);
		frame.getContentPane().add(textField_panchine);
		textField_panchine.setColumns(10);
		
		textField_dadi = new JTextField("5");
		textField_dadi.setBounds(478, 191, 60, 19);
		frame.getContentPane().add(textField_dadi);
		textField_dadi.setColumns(10);
		
		textField_carte = new JTextField("5");
		textField_carte.setBounds(478, 215, 60, 19);
		frame.getContentPane().add(textField_carte);
		textField_carte.setColumns(10);
		
		JTextArea txtrBenvenutiNellaSimulazione = new JTextArea();
		txtrBenvenutiNellaSimulazione.setFont(new Font("Enigmatic Unicode", Font.BOLD, 13));
		txtrBenvenutiNellaSimulazione.setForeground(new Color(0, 0, 0));
		txtrBenvenutiNellaSimulazione.setBackground(Color.LIGHT_GRAY);
		txtrBenvenutiNellaSimulazione.setWrapStyleWord(true);
		txtrBenvenutiNellaSimulazione.setText("   \r\n           BENVENUTI NELLA \r\n       SIMULAZIONE DEL GIOCO\r\n            SCALE&SERPENTI");
		txtrBenvenutiNellaSimulazione.setEditable(false);
		txtrBenvenutiNellaSimulazione.setBounds(192, 74, 187, 71);
		frame.getContentPane().add(txtrBenvenutiNellaSimulazione);
		
		JLabel lbl_giocatori = new JLabel("Giocatori");
		lbl_giocatori.setFont(new Font("Thames", Font.BOLD, 12));
		lbl_giocatori.setBounds(396, 33, 60, 13);
		frame.getContentPane().add(lbl_giocatori);
		
		JLabel lbl_serpenti = new JLabel("Serpenti");
		lbl_serpenti.setFont(new Font("Thames", Font.BOLD, 12));
		lbl_serpenti.setBounds(396, 55, 45, 13);
		frame.getContentPane().add(lbl_serpenti);
		
		JLabel lbl_scale = new JLabel("Scale");
		lbl_scale.setFont(new Font("Thames", Font.BOLD, 12));
		lbl_scale.setBounds(396, 78, 45, 13);
		frame.getContentPane().add(lbl_scale);
		
		JLabel lbl_col = new JLabel("Colonne");
		lbl_col.setFont(new Font("Thames", Font.BOLD, 12));
		lbl_col.setBounds(396, 101, 45, 13);
		frame.getContentPane().add(lbl_col);
		
		JLabel lbl_righe = new JLabel("Righe");
		lbl_righe.setFont(new Font("Thames", Font.BOLD, 12));
		lbl_righe.setBounds(396, 124, 45, 13);
		frame.getContentPane().add(lbl_righe);
		
		JLabel lbl_molle = new JLabel("Caselle Molla");
		lbl_molle.setFont(new Font("Thames", Font.BOLD, 12));
		lbl_molle.setBounds(396, 147, 72, 13);
		frame.getContentPane().add(lbl_molle);
		
		JLabel lbl_panchine = new JLabel("Caselle Panchina");
		lbl_panchine.setFont(new Font("Thames", Font.BOLD, 11));
		lbl_panchine.setBounds(396, 169, 82, 18);
		frame.getContentPane().add(lbl_panchine);
		
		JLabel lbl_dadi = new JLabel("Caselle Dadi");
		lbl_dadi.setFont(new Font("Thames", Font.BOLD, 12));
		lbl_dadi.setBounds(396, 195, 68, 13);
		frame.getContentPane().add(lbl_dadi);
		
		JLabel lbl_Carte = new JLabel("Caselle Carte");
		lbl_Carte.setFont(new Font("Thames", Font.BOLD, 12));
		lbl_Carte.setBounds(396, 218, 82, 13);
		frame.getContentPane().add(lbl_Carte);
		
		textField_locande = new JTextField();
		textField_locande.setText("10");
		textField_locande.setBounds(478, 238, 60, 19);
		frame.getContentPane().add(textField_locande);
		textField_locande.setColumns(10);
		
		JLabel lbl_locande = new JLabel("Caselle Locanda");
		lbl_locande.setFont(new Font("Thames", Font.BOLD, 11));
		lbl_locande.setBounds(396, 241, 82, 13);
		frame.getContentPane().add(lbl_locande);
		
		comboBox_numDadi = new JComboBox();
		comboBox_numDadi.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
		comboBox_numDadi.setBounds(106, 218, 39, 21);
		comboBox_numDadi.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(comboBox_numDadi.getSelectedItem()=="1") doppioSei.setSelected(false);
		}
		});
		frame.getContentPane().add(comboBox_numDadi);
		
		JLabel numDadi = new JLabel("Numero Dadi");
		numDadi.setFont(new Font("Thames", Font.BOLD, 12));
		numDadi.setBounds(25, 218, 82, 21);
		frame.getContentPane().add(numDadi);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.DARK_GRAY);
		frame.setJMenuBar(menuBar);
		
		JButton btnNewButton = new JButton("Avvia Simulazione");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		menuBar.add(btnNewButton);
		btnNewButton.addActionListener(new AvvioSim());
		btnNewButton.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 13));
		
		JButton btnNewButton_1 = new JButton("Carica Partita");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		menuBar.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				FileFilter ff = new FileNameExtensionFilter("Data File", "dat");
				jfc.setFileFilter(ff);
				int result=jfc.showOpenDialog(null);
				if(result==JFileChooser.APPROVE_OPTION) {
					try {
						File f = jfc.getSelectedFile();
						FileInputStream fis= new FileInputStream(f);
						ObjectInputStream ois= new ObjectInputStream(fis);
						Object o=ois.readObject();
						ois.close(); fis.close();
						if(!(o instanceof Mappa.StatoMappa)) throw new IllegalStateException("File corrotto");
						Mappa.StatoMappa sm= (Mappa.StatoMappa) o;
						creator=new ConcreteCreator();
						Mappa m= creator.getMappa();
						m.restore(sm);
						avviaFramePrincipale(m);
					}catch(IOException | ClassNotFoundException ex) {
						ex.printStackTrace();
					}
				}
			}
			
		});
	}
	
	class AvvioSim implements ActionListener{

		int nSerpenti, nScale, nCarte, nMolle,
			nPanchine, nLocande, nCaselleDadi, nDadi;
		int [][] serpenti, scale;
		int[] carte, molle, dadi, panchine, locande;
		
		private int getQuantità(String s) {
			int ris;
			try {
				ris = Integer.parseInt(s.trim());
				if (ris < 0)
					throw new IllegalArgumentException("Non si possono avere quantità negative!");
				return ris;
			} catch (IllegalArgumentException IAE) {
				JOptionPane.showMessageDialog(rootPane, IAE);
			} 
		return 0;
		}
		
		private int[][] posizionaMov(String element, int lenght){
			boolean inError=false;
			do{
				try {
					String s= JOptionPane.showInputDialog("Inserisci le posizioni di "+
				element+" tra apici e separate da uno spazio. ").trim();
					String[] a = s.split(" ");
					if(a.length!=lenght)
						throw new IllegalArgumentException("Errore nel numero elementi");
					Set<Integer> values= new HashSet<>();
					int[][] ris=new int[a.length][2];
					for(int i=0; i<a.length; i++) {
						String [] part= a[i].trim().split(",");
						if(part.length!=2)throw new IllegalArgumentException("Posizione errata");
						for(int j=0; j<2; j++) {
							int val=Integer.parseInt(part[j]);
							if(val<=0 || val> nRighe*nCol)throw new IllegalArgumentException("Inserisci un valore valido");
							ris[i][j]=val;
							if(values.contains(val))throw new IllegalArgumentException("Il valore deve essere univoco");
							values.add(val);
						}
						Arrays.sort(ris[i]);
					}
					inError=false;
					return ris;
				}catch(IllegalArgumentException IAE) {
					JOptionPane.showMessageDialog(rootPane, IAE);
					inError=true;
				}
				
			}while(inError);
			return null;
		}
		
		private int[] posizionaStab(String element, int lenght) {
			boolean inError=false;
			do {
				try {
					String s=JOptionPane.showInputDialog("Inserisci le posizioni di "+element+
							" separate da spazi.").trim();
					String[] a =s.split(" ");
					if(a.length!=lenght)
						throw new IllegalArgumentException("Valore lunghezza errato");
					Set<Integer> values= new HashSet<>();
					int[] pos=new int[lenght];
					for(int i=0; i<lenght; i++) {
						pos[i]=Integer.parseInt(a[i]);
						if(pos[i]<=0 || pos[i]>nRighe*nCol) throw new IllegalArgumentException("Inserisci un valore valido");
						if(values.contains(pos[i]))throw new IllegalArgumentException("Il valore deve essere univoco");
						values.add(pos[i]);
					}
					inError=false;
					return pos;
				}catch(IllegalArgumentException IAE) {
					inError=true;
					JOptionPane.showMessageDialog(rootPane,IAE);
				}
			}while(inError);
			return null;
		}
		
		private void construct() {
			creator=new ConcreteCreator();
			creator.createSpazio(nRighe, nCol);
			creator.createDadi(nDadi);
			creator.createGiocatore(giocatori);
			creator.createDeck();
			
			if(!serpentiRandom.isSelected()) {
				boolean errore=false;
				do {
					try {
						serpenti = posizionaMov("Serpenti", nSerpenti);
						creator.factoryMov("serpente",serpenti);
						errore=false;
					} catch (IllegalArgumentException IAE) {
						errore=true;
						JOptionPane.showMessageDialog(rootPane, IAE);
					}
				} while (errore);
			}
			
			if(!scaleRandom.isSelected()) {
				boolean errore=false;
				do {
					try {
						scale=posizionaMov("Scale", nScale);
						creator.factoryMov("scala",scale);
						errore=false;
					} catch (IllegalArgumentException IAE) {
						errore=true;
						JOptionPane.showMessageDialog(rootPane, IAE);
					}
				} while (errore);
			}
			
			if(!carteRandom.isSelected()) {
				boolean errore=false;
				do {
					try {
						carte=posizionaStab("Carte", nCarte);
						creator.factoryStab("carte",carte);
						errore=false;
					} catch (IllegalArgumentException IAE) {
						errore=true;
						JOptionPane.showMessageDialog(rootPane, IAE);
					}
				} while (errore);
			}
			if(!molleRandom.isSelected()) {
				boolean errore=false;
				do {
					try {
						molle=posizionaStab("Molle", nMolle);
						creator.factoryStab("molla",molle);
						errore=false;
					} catch (IllegalArgumentException IAE) {
						errore=true;
						JOptionPane.showMessageDialog(rootPane, IAE);
					}
				} while (errore);
			}
			if(!dadiRandom.isSelected()) {
				boolean errore=false;
				do {
					try {
						dadi=posizionaStab("Caselle Dadi", nCaselleDadi);
						creator.factoryStab("dadi",dadi);
						errore=false;
					} catch (IllegalArgumentException IAE) {
						errore=true;
						JOptionPane.showMessageDialog(rootPane, IAE);
					}
				} while (errore);
			}
			if(!panchineRandom.isSelected()) {
				boolean errore=false;
				do {
					try {
						panchine=posizionaStab("Panchine", nPanchine);
						creator.factoryStab("panchina",panchine);
						errore=false;
					} catch (IllegalArgumentException IAE) {
						errore=true;
						JOptionPane.showMessageDialog(rootPane, IAE);
					}
				} while (errore);
			}
			if(!locandeRandom.isSelected()) {
				boolean errore=false;
				do {
					try {
						locande=posizionaStab("Locande",nLocande);
						creator.factoryStab("locanda",locande);
						errore=false;
					} catch (IllegalArgumentException IAE) {
						errore=true;
						JOptionPane.showMessageDialog(rootPane, IAE);
					}
				} while (errore);
			}
			
			if(serpentiRandom.isSelected()) creator.factoryRandom("serpente",nSerpenti);
			if(scaleRandom.isSelected()) creator.factoryRandom("scala",nScale);
			if(carteRandom.isSelected()) creator.factoryRandom("carte", nCarte);
			if(molleRandom.isSelected()) creator.factoryRandom("molla", nMolle);
			if(dadiRandom.isSelected()) creator.factoryRandom("dadi", nDadi);
			if(panchineRandom.isSelected()) creator.factoryRandom("panchina", nPanchine);
			if(locandeRandom.isSelected()) creator.factoryRandom("locanda", nLocande);
			
			if(doppioSei.isSelected()) creator.createDoppioSei();
			
			avviaFramePrincipale(creator.getMappa());
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			giocatori=getQuantità(textField_giocatori.getText());
			nRighe=getQuantità(textField_righe.getText());
			nCol=getQuantità(textField_colonne.getText());
			nSerpenti=getQuantità(textField_serpenti.getText());
			nScale=getQuantità(textField_scale.getText());
			nMolle=getQuantità(textField_molle.getText());
			nCarte=getQuantità(textField_carte.getText());
			nPanchine=getQuantità(textField_panchine.getText());
			nLocande=getQuantità(textField_locande.getText());
			nCaselleDadi=getQuantità(textField_dadi.getText());
			
			nDadi=Integer.parseInt((String)comboBox_numDadi.getSelectedItem());
			
			int elementsNumber=(nSerpenti+nScale)*2+nMolle+nCarte+nPanchine+nLocande+nCaselleDadi;
			
			if(elementsNumber> nRighe*nCol) {
				JOptionPane.showMessageDialog(rootPane, "Numero elementi non supportato dalla mappa");
				return;
			}
			
			construct();
		}
		
	}
		
}
