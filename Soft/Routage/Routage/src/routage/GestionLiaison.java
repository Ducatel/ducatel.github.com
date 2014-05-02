package routage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.DefaultGraph;

/**
 * Class permettant de gerer la creation des
 * liaison du reseau
 * @author David Ducatel
 *
 */
@SuppressWarnings("serial")
public class GestionLiaison extends JFrame{
	
	/**
	 * Jlist contenant la liste des liaisons du reseau
	 */
	public JList listEdge;
	
	/**
	 * Bouton permettant de lancer la creation d'une liaison
	 */
	public JButton creer;
	
	/**
	 * Bouton permettant de supprimé une liaison 
	 */
	public JButton supp;
	
	/**
	 * Bouton permettant de passer a l'etape de visualisation
	 */
	public JButton visu;

	/**
	 * Graph qui represente le reseau
	 */
	public DefaultGraph reseau;
	
	/**
	 * Constructeur du gestionnaire de liaison
	 * @param reseau graph contenant les composants du reseau
	 */
	public GestionLiaison(DefaultGraph reseau) {
		super("Routage Calculator -- David Ducatel");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		this.reseau=reseau;
		
		/***************** CENTRE *****************/
		listEdge=new JList();
		listEdge.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listEdge.setVisibleRowCount(5);
		JScrollPane scrollPane = new JScrollPane(listEdge);		
		
		add(scrollPane);
		
		/***************** BAS ***********************/
		
		JPanel panB=new JPanel();
		
		creer=new JButton("Creer liaison");
		creer.addActionListener(new EcouteurBouton());
		panB.add(creer);
		
		supp=new JButton("Supprimer liaison");
		supp.addActionListener(new EcouteurBouton());
		panB.add(supp);
		
		visu=new JButton("Visualiser");
		visu.addActionListener(new EcouteurBouton());
		panB.add(visu);
		
		add(panB,BorderLayout.SOUTH);
		
		
		/*************************************/
		pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}

	/**
	 * Class permettant de géré l'appui sur les boutons 
	 * d'ajout de suppression et de passage a l'etape suivante
	 * @author David Ducatel
	 *
	 */
	private class EcouteurBouton implements ActionListener{

		public void actionPerformed(ActionEvent e) {
		
			if(e.getSource()==creer){
				new CreationLiaison(GestionLiaison.this, reseau);
			}
			else if(e.getSource()==supp){
				//on test si il y a bien une liaison de selectionner
				if(!listEdge.isSelectionEmpty()){
				
					//recupération de l'id du composant a supprime
					String tmp=(String) listEdge.getSelectedValue();
					String tab[]=tmp.split("id: ");
					tab=tab[1].split(" ");
					
					//suppression au sein du reseau
					reseau.removeEdge(tab[0]);
					
					//suppression au sein de la liste
					//ajout a la liste
					listEdge.removeAll();
					DefaultListModel listModel = new DefaultListModel();
					for(Edge e2:reseau.getEachEdge()){
						tmp="id: "+e2.getId()+" Composant: ( "+e2.getNode0()+" , "+e2.getNode1()+" ) poids: "+e2.getLabel("poids");
						listModel.addElement(tmp);
					}
					
					listEdge.setModel(listModel);
				}
				else
					JOptionPane.showMessageDialog(null, "Suppression impossible, aucune liaison selectionné", "Erreur", JOptionPane.ERROR_MESSAGE);

			}
			else{
				new Visu(reseau);
				GestionLiaison.this.dispose();
			}
		}
	}
}
