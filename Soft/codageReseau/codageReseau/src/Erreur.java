import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * Class de la popup d'erreur
 * @author David Ducatel
 *
 */
@SuppressWarnings("serial")
public class Erreur extends JDialog{

	/**
	 * Constructeur de la popup d'erreur
	 */
	public Erreur(){
		setTitle("Erreur");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(300, 80);
		
		JLabel lab=new JLabel("Votre mot d'entree n'est pas binaire !");
		lab.setHorizontalAlignment(JLabel.CENTER);
		lab.setVerticalAlignment(JLabel.CENTER);
		add(lab);
		
		
		setVisible(true);
	}
}
