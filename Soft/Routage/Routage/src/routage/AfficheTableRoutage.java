package routage;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Class permettant de generer la fenetre qui affiche les 
 * tables de routage
 * @author David Ducatel
 *
 */
@SuppressWarnings("serial")
public class AfficheTableRoutage extends JDialog {
	
	/**
	 * Constructeur permettant de creer une fenetre
	 * contenant les tables de routage
	 * @param visu objet de l'ihm principale permettant a la fenetre d'etre modal
	 * @param resultat ArrayListe contenant chaque table de routage
	 */
	public AfficheTableRoutage(Visu visu,ArrayList<String> resultat){
		super(visu,"Table de routage du reseau",true);
		
		JPanel p=new JPanel(new GridLayout(1, resultat.size(),10,10));
		
		for(String s: resultat)
			p.add(new JLabel(s));
		
		JScrollPane jsp=new JScrollPane(p);
		add(jsp);
		
		pack();
		setResizable(false);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

}
