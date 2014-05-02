import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * Class de gestion de l'applet
 * @author David Ducatel
 *
 */
@SuppressWarnings("serial")
public class Visu extends JApplet{

	/**
	 * Bouton permettant de lancer la convertion
	 */
	private JButton convertir;
	
	/**
	 * Liste permettant de choisir le codage a utiliser
	 */
	private JComboBox codage;
	
	/**
	 * Zone de texte permettant la saisie du mot binaire
	 */
	private JTextField entree;
	
	/**
	 * Zone ou le codage va venir s'afficher
	 */
	private Dessin zoneGraph;
	
	/**
	 * Fonction qui construit l'IHM de l'applet
	 */
	public void init(){
		
		// Definition general de la fenetre
		this.setLayout(new BorderLayout());
		
		/*********** PARTIE HAUTE *************/
		
		// JPanel qui va contenir l'interface de controle
		JPanel panH=new JPanel();
		
		// Ajout  de la zone de saisie du mot binaire
		panH.add(new JLabel("Mot binaire:"));
		entree=new JTextField("1100110101",10);
		panH.add(entree);
		
		// Ajout de la liste de selection du codage
		codage=new JComboBox();
		codage.addItem("NRZ");
		codage.addItem("NRZI");
		codage.addItem("Manchester");
		codage.addItem("Manchester differentiel");
		codage.addItem("Miller");
		
		panH.add(codage);
		
		
		// Ajout du bouton d'execution du codage
		convertir=new JButton("Convertir");
		convertir.addActionListener(new EcouteurBouton());
		panH.add(convertir);
		
		
		add(panH,BorderLayout.NORTH);
		
		
		/*********** PARTIE CENTRALE *************/
		
		// Ajout de la zone d'affichage de la solution
		zoneGraph=new Dessin();
		add(zoneGraph);
	}
	
	
	/**
	 * Class interne permettant de gerer l'appui sur le bouton d'execution
	 * @author David Ducatel
	 *
	 */
	public class EcouteurBouton implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			
			// Recuperation du mot saisi par l'utilisateur
			String mot=entree.getText();
			
			//on test si le mot d'entree est correct (si il ne contient que des 0 et des 1)
			if(!mot.matches("[01]+")){
				
				// Detection d'un erreur, donc on declenche une popup et on vide la zone de saisi
				new Erreur();
				entree.setText("");
			}
			else{
				
				// Pas d'erreur, donc on lance le codage
				zoneGraph.SetArgs(mot,codage.getSelectedIndex());
			}
		}
	}
	
}
