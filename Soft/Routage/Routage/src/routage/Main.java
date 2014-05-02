package routage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class permettant de choisir le type de saisie de reseau
 * (Graphique ou fichier)
 * @author David Ducatel
 *
 */
@SuppressWarnings("serial")
public class Main extends JFrame{

	/**
	 * Bouton permettant de rejoindre l'interface
	 * de saisie du reseau par fichier
	 */
	private JButton fichier;
	
	/**
	 * Bouton permettant de rejoindre l'interface
	 * de saisie du reseau par Interface graphique
	 */
	private JButton graphique;
	
	
	/**
	 * Constructeur de l'ihm de depart
	 */
	public Main(){
		super("Routage Calculator -- David Ducatel");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
				
		/**************** HAUT **********************/
		
		JLabel lab=new JLabel("Veuillez choisir le type de saisie de réseau");
		lab.setHorizontalAlignment(JLabel.CENTER);
		add(lab,BorderLayout.NORTH);
		
		/******************* CENTRE ****************/
		JPanel pan=new JPanel();
		fichier=new JButton("Construction par fichier");
		fichier.addActionListener(new EcouteurBouton());
		pan.add(fichier);
		
		
		graphique=new JButton("Construction par interface graphique");
		graphique.addActionListener(new EcouteurBouton());
		pan.add(graphique);
		
		add(pan);
		
		/*************************************************/
		pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}
	
	/**
	 * Methode d'entre du programme
	 */
	public static void main(String[] args) {
		
		new Main();

		
		/*************Graph de test******************/
		/*
		DefaultGraph g=new DefaultGraph("1");
		//g.addAttribute("ui.quality");
		g.addAttribute("ui.antialias");
		
		g.addNode("C1").addAttribute("label", "C1");
		g.addNode("C2").addAttribute("label", "C2");
		g.addNode("C3").addAttribute("label", "C3");
		g.addNode("C4").addAttribute("label", "C4");
		g.addNode("C5").addAttribute("label", "C5");
		g.addNode("C6").addAttribute("label", "C6");
		
		g.getNode("C1").addAttribute("type", "routeur");
		g.getNode("C2").addAttribute("type", "routeur");
		g.getNode("C3").addAttribute("type", "routeur");
		g.getNode("C4").addAttribute("type", "routeur");
		g.getNode("C5").addAttribute("type", "routeur");
		g.getNode("C6").addAttribute("type", "routeur");
		
		g.getNode("C1").addAttribute("ui.style", "text-size:20px;");
		g.getNode("C2").addAttribute("ui.style", "text-size:20px;");
		g.getNode("C3").addAttribute("ui.style", "text-size:20px;");
		g.getNode("C4").addAttribute("ui.style", "text-size:20px;");
		g.getNode("C5").addAttribute("ui.style", "text-size:20px;");
		g.getNode("C6").addAttribute("ui.style", "text-size:20px;");
		
		
		g.addNode("A").addAttribute("label", "A");
		g.addNode("B").addAttribute("label", "B");
		g.addNode("C").addAttribute("label", "C");
		g.addNode("D").addAttribute("label", "D");
		g.addNode("E").addAttribute("label", "E");
		g.addNode("F").addAttribute("label", "F");
		g.addNode("G").addAttribute("label", "G");
		
		g.getNode("A").addAttribute("ui.style", "fill-color:blue;text-size:20px;");
		g.getNode("B").addAttribute("ui.style", "fill-color:blue;text-size:20px;");
		g.getNode("C").addAttribute("ui.style", "fill-color:blue;text-size:20px;");
		g.getNode("D").addAttribute("ui.style", "fill-color:blue;text-size:20px;");
		g.getNode("E").addAttribute("ui.style", "fill-color:blue;text-size:20px;");
		g.getNode("F").addAttribute("ui.style", "fill-color:blue;text-size:20px;");
		g.getNode("G").addAttribute("ui.style", "fill-color:blue;text-size:20px;");

		g.getNode("A").addAttribute("type", "ordi");
		g.getNode("B").addAttribute("type", "ordi");
		g.getNode("C").addAttribute("type", "ordi");
		g.getNode("D").addAttribute("type", "ordi");
		g.getNode("E").addAttribute("type", "ordi");
		g.getNode("F").addAttribute("type", "ordi");
		g.getNode("G").addAttribute("type", "ordi");
		
		
		/////////////////////////////////////////////////////////////////
		
		g.addEdge("C1C2","C1","C2").addAttribute("label", "2"); 
		g.addEdge("C1C5","C1","C5").addAttribute("label", "2");
		g.addEdge("C1C6","C1","C6").addAttribute("label", "4");
		g.addEdge("C2C3","C2","C3").addAttribute("label", "1");
		g.addEdge("C3C4","C3","C4").addAttribute("label", "1");
		g.addEdge("C5C3","C5","C3").addAttribute("label", "3");
		g.addEdge("C5C6","C5","C6").addAttribute("label", "1");
		g.addEdge("C6C4","C6","C4").addAttribute("label", "1");
		
		g.getEdge("C1C2").addAttribute("poids", 2); 
		g.getEdge("C1C5").addAttribute("poids", 2);
		g.getEdge("C1C6").addAttribute("poids", 4);
		g.getEdge("C2C3").addAttribute("poids", 1);
		g.getEdge("C3C4").addAttribute("poids", 1);
		g.getEdge("C5C3").addAttribute("poids", 3);
		g.getEdge("C5C6").addAttribute("poids", 1);
		g.getEdge("C6C4").addAttribute("poids", 1);
		
		g.getEdge("C1C2").addAttribute("ui.style", "text-size:20px;"); 
		g.getEdge("C1C5").addAttribute("ui.style", "text-size:20px;");
		g.getEdge("C1C6").addAttribute("ui.style", "text-size:20px;");
		g.getEdge("C2C3").addAttribute("ui.style", "text-size:20px;");
		g.getEdge("C3C4").addAttribute("ui.style", "text-size:20px;");
		g.getEdge("C5C3").addAttribute("ui.style", "text-size:20px;");
		g.getEdge("C5C6").addAttribute("ui.style", "text-size:20px;");
		g.getEdge("C6C4").addAttribute("ui.style", "text-size:20px;");
		
		/////////////////////////////////////////////////////////////////////
		
		g.addEdge("AC1","A","C1").addAttribute("poids", 0); 
		g.addEdge("BC2","B","C2").addAttribute("poids", 0); 
		g.addEdge("CC2","C","C2").addAttribute("poids", 0); 
		g.addEdge("DC3","D","C3").addAttribute("poids", 0); 
		g.addEdge("EC4","E","C4").addAttribute("poids", 0);
		g.addEdge("FC4","F","C4").addAttribute("poids", 0);
		g.addEdge("GC6","G","C6").addAttribute("poids", 0); 
		
		
		
		

		
		g.display(true);
		try {
			g.write("test.dgs");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		*/
		
	}

	
	/**
	 * Class permettant de géré l'appui sur les boutons fichier et graphique
	 * @author David Ducatel
	 *
	 */
	private class EcouteurBouton implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// Choix de l'interface a lancer
			if(e.getSource()==fichier)
				new SaisieFichier();
			else
				new GestionNode();
			
			Main.this.dispose();
			
		}
		
	}
}
