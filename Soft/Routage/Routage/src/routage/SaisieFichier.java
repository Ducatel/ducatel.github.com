package routage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


/**
 * Class permettant de faire la saisie du fichier contenant le reseau
 * @author David Ducatel
 *
 */
@SuppressWarnings("serial")
public class SaisieFichier extends JFrame {
	
	/**
	 * Selecteur de fichier
	 */
	public JFileChooser fc;
	
	/**
	 * JLabel contenant le chemin vers le fichier choisie
	 */
	public JLabel path;
	
	/**
	 * Bouton permettant de lancer le selecteur de fichier
	 */
	public JButton choisir;
	
	/**
	 * Bouton permettant de lancer la visualisation du reseau
	 */
	public JButton visu;
	
	/**
	 * Fichier contenant le reseau a construire
	 */
	public File fichier;
	
	/**
	 * Class permettant la gestion du choix du fichier d'entree
	 */
	public SaisieFichier(){
		super("Routage Calculator -- David Ducatel");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
				
		/**************** HAUT **********************/
		
		JLabel lab=new JLabel("Veuillez choisir votre fichier .dgs");
		lab.setHorizontalAlignment(JLabel.CENTER);
		add(lab,BorderLayout.NORTH);
		
		/******************* CENTRE ****************/
		
		JPanel pan=new JPanel();
		TitledBorder titleBorder = new TitledBorder(new LineBorder(new Color(0, 153, 0),
				1), "Choix du fichier");
		pan.setBorder(titleBorder);
		
		pan.add(new JLabel("Fichier du reseau: "));
		
		path=new JLabel("Aucun fichier choisi !! ");
		pan.add(path);
		
		//selecteur de fichier
		fc = new JFileChooser();
		//selection que des fichiers
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		//creation du filtre de fichier
		FiltreFichier dgs = new FiltreFichier("Fichiers Reseau .dgs",".dgs");

		//ajout du filtre
		fc.addChoosableFileFilter(dgs);
		fc.setFileFilter(dgs);

		add(pan);
		
		/******************** BAS *********************/
		JPanel panBas=new JPanel();
		
		choisir=new JButton("Choisir fichier");
		choisir.addActionListener(new EcouteurBouton());
		panBas.add(choisir);
		
		
		visu=new JButton("Visualiser");
		visu.setEnabled(false);
		visu.addActionListener(new EcouteurBouton());
		panBas.add(visu);
		
		add(panBas,BorderLayout.SOUTH);
		
		/*******************************************/
		pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}
	
	/**
	 * Class permettant de géré l'appui sur les boutons choisir et visu
	 * @author David Ducatel
	 *
	 */
	private class EcouteurBouton implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			//choix suivant le bouton presse
			if(e.getSource()==choisir){
				
				//on verifie qu'un fichier a été choisi
				int returnVal = fc.showOpenDialog(SaisieFichier.this);
				
				//si un fichier a bien ete choisi
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					
					//on recupere le fichier
					fichier = fc.getSelectedFile();
					
					//on affiche le chemin
					path.setText(fichier.getAbsolutePath());
					
					//on debloque le bouton calcul
					visu.setEnabled(true);
					
					//on recalcule la taille de la fenetre
					pack();
				}
			}
			else{
				
				//lancement de la visualisation
				new Visu(fichier);
				
				SaisieFichier.this.dispose();
			}
		}
		
	}
}
