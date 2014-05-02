package routage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.DefaultGraph;



/**
 * Class permettant la creation de liaison dans le reseau
 * @author David Ducatel
 *
 */
@SuppressWarnings("serial")
public class CreationLiaison extends JDialog{
	
	/**
	 * Objet de l'ihm de gestion de liaison
	 */
	public GestionLiaison gl;
	
	/**
	 * Graph soutenant le reseau
	 */
	public DefaultGraph reseau;
	
	/**
	 * Bouton permettant de lancer la creation de la liaison
	 */
	public JButton creer;
	
	/**
	 * JComboBox contenant les composants du reseau 
	 * (premier cote de la liaison)
	 */
	public JComboBox nodeIn;
	
	/**
	 * JComboBox contenant les composants du reseau 
	 * (second cote de la liaison)
	 */
	public JComboBox nodeOut;
	
	/**
	 * Champs de texte permettant de definir le poids d'une arrete
	 */
	public JTextField poids;
	
	/**
	 * Constructeur de l'interface de creation de liaison
	 * @param gl objet de l'ihm de gestion qui va permettre de mettre a jour celle-ci
	 * @param reseau graph contenant le reseau
	 */
	public CreationLiaison(GestionLiaison gl,DefaultGraph reseau){
		super(gl,"Creation de liaison",true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		this.reseau=reseau;
		this.gl=gl;
		
		/**************** Centre ******************/
		JPanel panC=new JPanel();
		
		
		nodeIn=new JComboBox();
		nodeOut=new JComboBox();
		
		for(Node n:reseau.getEachNode()){
			nodeIn.addItem(n.getId());
			nodeOut.addItem(n.getId());
		}
		panC.add(new JLabel("Composant 1: "));
		panC.add(nodeIn);
		
		panC.add(new JLabel("Composant 2:"));
		panC.add(nodeOut);
		
		panC.add(new JLabel("Poids de la liaison: "));
		poids=new JTextField(3);
		poids.setToolTipText("Utile pour les liaisons entre routeur");
		
		panC.add(poids);
		
		add(panC);
		
		/**************** BAS ***********************/
		
	    JPanel panB=new JPanel();
	    creer=new JButton("Creer");
	    creer.addActionListener(new EcouteurBouton());
	    panB.add(creer);
	    add(panB,BorderLayout.SOUTH);
	    
		/****************************************/
		pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
		
		
	}
	
	/**
	 * Class permettant de géré la création d'une liaison 
	 * @author David Ducatel
	 *
	 */
	private class EcouteurBouton implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			//recuperation des points de connexion
			Node n1=reseau.getNode(nodeIn.getSelectedItem().toString());
			Node n2=reseau.getNode(nodeOut.getSelectedItem().toString());

			//on verifie si l'arrete existe deja
			if(reseau.getEdge(n1.getId()+n2.getId())==null && reseau.getEdge(n2.getId()+n1.getId())==null){
				
				//si la connexion est entre routeur il faut mettre un poids a l'arrete
				if(n1.getLabel("type").equals("routeur") && n2.getLabel("type").equals("routeur")){
					
					//on regarde si le poids est bien precisez, qu'il ne contient pas d'espace et qu'il est decimal
					if(!poids.getText().isEmpty()&&!poids.getText().matches(" *")&&poids.getText().matches("\\d*"))
					{
						Edge e1=reseau.addEdge(n1.getId()+n2.getId(), n1.getId(), n2.getId());
						e1.addAttribute("label", poids.getText());
						e1.addAttribute("poids", Integer.parseInt(poids.getText()));
						e1.addAttribute("ui.style", "text-size:20px;"); 
						
						
						//ajout a la liste
						gl.listEdge.removeAll();
						DefaultListModel listModel = new DefaultListModel();
						String tmp;
						for(Edge e2:reseau.getEachEdge()){
							tmp="id: "+e2.getId()+" Composant: ( "+e2.getNode0()+" , "+e2.getNode1()+" ) poids: "+e2.getNumber("poids");
							listModel.addElement(tmp);
						}
						
						gl.listEdge.setModel(listModel);
						
						//fermeture de la fenetre
						CreationLiaison.this.dispose();
					}
					else{
						String msg="<html><body>Un poids doit etre saisie pour une liaison entre routeur<br/>" +
								"La valeur doit etre entiere et sans espace</body></html>";
						JOptionPane.showMessageDialog(null, msg, "Erreur", JOptionPane.ERROR_MESSAGE);
						poids.setText("");
					}	
				}
				else{
					//ici connexion entre pc et routeur donc pas de poids sur l'arrete
					Edge e1=reseau.addEdge(n1.getId()+n2.getId(), n1.getId(), n2.getId());
					e1.addAttribute("poids", 0);
					
					
					//ajout a la liste
					gl.listEdge.removeAll();
					DefaultListModel listModel = new DefaultListModel();
					String tmp;
					for(Edge e2:reseau.getEachEdge()){
						tmp="id: "+e2.getId()+" Composant: ( "+e2.getNode0()+" , "+e2.getNode1()+" ) poids: "+e2.getNumber("poids");
						listModel.addElement(tmp);
					}
					
					gl.listEdge.setModel(listModel);
					
					CreationLiaison.this.dispose();
				}
			}
			else{
				//popup d'erreur en cas d'arret deja existante
				String msg="<html><body>L'arrete est déjà créée !</body></html>";
				JOptionPane.showMessageDialog(null, msg, "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
