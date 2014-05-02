package crc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Class de gestion de l'IHM
 * @author David Ducatel
 *
 */
@SuppressWarnings("serial")
public class Visu extends JFrame{

	/**
	 * Permet la saisie du mot binaire a traiter.
	 */
	private JTextField motIn;
	
	/**
	 * Permet de choisir le polynome a utiliser pour le calcul
	 */
	private JComboBox poly;
	
	/**
	 * Permet de choisir le type de calcul (Emission ou reception)
	 */
	private JComboBox emRec;
	
	/**
	 * Permet de lancer le calcul
	 */
	private JButton calculer;
	
	/**
	 * Va contenir le détail du calcul et le resultat final
	 */
	public JTextArea resultat;
	
	public Visu(){
		super("CRC Calculator -- David Ducatel");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		/************ HAUT ****************/
		JPanel panH=new JPanel();
		
		// Ajout du champ de saisie du mot binaire
		panH.add(new JLabel("Mot binaire:"));
		motIn=new JTextField(10);
		panH.add(motIn);
		
		// Ajout de la JComboBox de choix du polynome
		poly=new JComboBox();
		poly.addItem("<html><body>x<sup>4</sup>+x<sup>2</sup>+x+1</body></html>");
		poly.addItem("<html><body>x<sup>12</sup>+x<sup>11</sup>+x<sup>3</sup>+x<sup>2</sup>+x+1</body></html>");
		poly.addItem("<html><body>x<sup>16</sup>+x<sup>15</sup>+x<sup>2</sup>+1</body></html>");
		poly.addItem("<html><body>x<sup>16</sup>+x<sup>12</sup>+x<sup>5</sup>+1</body></html>");
		//poly.addItem("<html><body>x<sup>32</sup>+x<sup>26</sup>+x<sup>23</sup>+x<sup>22</sup>+x<sup>16</sup>+x<sup>12</sup>+x<sup>11</sup>+x<sup>10</sup>+x<sup>8</sup>+x<sup>7</sup>+x<sup>5</sup>+x<sup>4</sup>+x+1</body></html>");
		poly.addItem("CRC-32");
		panH.add(poly);
		
		// Ajout de la JComboBox de choix du type de calcul (emission reception)
		emRec=new JComboBox();
		emRec.addItem("Emission");
		emRec.addItem("Réception");
		panH.add(emRec);
		
		// Ajout du bouton calculer
		calculer=new JButton("Calculer");
		calculer.addActionListener(new EcouteurBouton());
		panH.add(calculer);
		
		add(panH,BorderLayout.NORTH);
		
		
		/************** CENTRE *************/
		resultat=new JTextArea("");
		JScrollPane scrollPane = new JScrollPane(resultat,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		scrollPane.setPreferredSize(new Dimension(this.getWidth(),200));
		add(scrollPane);
	
		
		/************************************/
		pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
		
	}

	/**
	 * Point d'entre du programme
	 */
	public static void main(String[] args) {
		new Visu();
	}
	
	/**
	 * Class permettant de gérer l'appui sur le bouton calculer
	 * @author David Ducatel
	 *
	 */
	private class EcouteurBouton implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			// Recuperation du mot saisi par l'utilisateur
			String mot=motIn.getText();
			
			//on test si le mot d'entree est correct (si il ne contient que des 0 et des 1)
			if(!mot.matches("[01]+")){
				
				// Detection d'un erreur, donc on declenche une popup et on vide la zone de saisi
				new Erreur();
				motIn.setText("");
			}
			else{
				
				// Pas d'erreur, donc on lance le calcul
				new CalculCrc(mot, emRec.getSelectedIndex(), poly.getSelectedIndex(),Visu.this);
				
			}
		}
		
	}

}
