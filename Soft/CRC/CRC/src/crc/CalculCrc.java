package crc;


/**
 * Class qui implemente les calculs de CRC
 * @author David Ducatel
 *
 */
public class CalculCrc {
	
	/**
	 * Objet de l'ihm permettant d'afficher l'avancement de l'execution
	 */
	private Visu ihm;
	
	/**
	 * Tableau contenant les valeurs binaires des polynomes
	 */
	private String[] listPoly={"10110","1100000001111","11000000000000101","10001000000100001","1000001001100000100011101101100101"};

	/**
	 * Constructeur du calcul
	 * Permet de preparer le calcule
	 * @param mot mot binaire a calculer
	 * @param typeCalcul type de calcul a effectuer 
	 * @param poly polynome a utiliser pour le calcul
	 * @param visu objet de l'ihm 
	 */
	public CalculCrc(String mot,int typeCalcul,int poly,Visu visu){
		ihm=visu;
		visu.resultat.setText("");

		//lancement du calcul suivant la valeur de la JComboBox
		if(typeCalcul==0)
			calculEmission(listPoly[poly],mot);
		else{
			if(calculReception(listPoly[poly],mot))
				visu.resultat.setText(visu.resultat.getText()+"Le mot est correct");
			else
				visu.resultat.setText(visu.resultat.getText()+"Le mot est corrompu");
		}
	}
	
	/**
	 * Fonction qui permet de calculer pour l'emission du message
	 * @param poly polynome a utiliser pour le calcul
	 * @param mot mot a utilisé pour le calcul
	 */
	private void calculEmission(String poly,String mot){

		//sauvegarde du mot utile pour le message final
		String save=mot;
		
		// Ajout des zero supplementaire au bout du mot
		for(int i=0;i<(poly.length()-1);i++)
			mot=mot+"0";
		
		// Pour bouclé sur toute la longueur du mot
		while(poly.length()<=mot.length()){
			
			//Application du polynome
			String calcul="";
			for(int i=0;i<poly.length();i++)
				calcul+=mot.charAt(i)^poly.charAt(i);
			
			//pour la presentation
			String tiret="";
			
			//reconstitution du mot
			String tmp="";
			
			for(int i=0;i<mot.length();i++){
				if(i<calcul.length())
					tmp+=calcul.charAt(i);
				else
					tmp+=mot.charAt(i);
				
				tiret+="--";
			}
			
			//Affichage des differentes etape de calcul
			ihm.resultat.setText(ihm.resultat.getText()+mot+"\n"+poly+"\n"+tiret+"\n"+tmp+"\n\n");
			mot=tmp;
			
			//suppression des zero est debut de mot
			mot=mot.split("^0+")[1];
		}
		
		//affichage de fin
		ihm.resultat.setText(ihm.resultat.getText()+"Mot a envoyer: "+save+mot);
		
	}
	
	/**
	 * Fonction qui permet de calculer pour la reception du message
	 * @param poly polynome a utiliser pour le calcul
	 * @param mot mot a utilisé pour le calcul
	 */
	private boolean calculReception(String poly,String mot){
				
		// Pour bouclé sur toute la longueur du mot
		while(poly.length()<=mot.length()){
			
			//Application du polynome
			String calcul="";
			for(int i=0;i<poly.length();i++)
				calcul+=mot.charAt(i)^poly.charAt(i);
			
			//pour la presentation
			String tiret="";
			
			//reconstitution du mot
			String tmp="";
			
			for(int i=0;i<mot.length();i++){
				if(i<calcul.length())
					tmp+=calcul.charAt(i);
				else
					tmp+=mot.charAt(i);
				
				tiret+="--";
			}
			
			//Affichage des differentes etape de calcul
			ihm.resultat.setText(ihm.resultat.getText()+mot+"\n"+poly+"\n"+tiret+"\n"+tmp+"\n\n");
			mot=tmp;
			
			//Si il reste des 1, on supprime les zeros au debut du mot, sinon on a finit
			if(mot.contains("1"))
				mot=mot.split("^0+")[1];
			else
				return true;
		}
		
		return false;

	}
	
}
