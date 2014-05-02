import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JPanel;

/**
 * Class de codage de la donnee binaire
 * @author David Ducatel
 *
 */
@SuppressWarnings("serial")
public class Dessin extends JPanel{
	
	/**
	 * Variable qui contient le mot binaire a coder
	 */
	private String mot;
	
	/**
	 * Variable qui contient la methode de codage a utiliser
	 */
	private int methode;
	
	/**
	 * Variable qui contient la "hauteur" du courant pour un byte a 1
	 */
	private int vPos;
	
	/**
	 * Variable qui contient la "hauteur" du courant pour un byte a 0
	 */
	private int vNeg;
	
	/**
	 * Variable qui contient l'espacement entre chaque byte
	 */
	private int spaceH;
	
	/**
	 * Constructeur de la zone de l'affichage
	 */
	public Dessin(){
		this.mot=null;
		this.methode=-1;
	}
	
	/**
	 * Fonction qui permet de definir les donnees utilent a la creation du codage
	 * @param mot mot binaire a coder
	 * @param methode methode de codage a utilise
	 */
	public void SetArgs(String mot,int methode){
		
		// Enregistrement des valeurs utilent a la creation du codage
		this.mot=mot;
		this.methode=methode;
		
		//calcul de valeur utile a la construction du codage
		vPos=this.getHeight()/4;
		vNeg=this.getHeight()/4+this.getHeight()/2;
		spaceH=this.getWidth()/mot.length();
		
		// Affichage de la solution
		repaint();
	}
	
	/**
	 * Fonction qui va tracer les codages
	 */
	public void paintComponent(Graphics g){
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
				
		//si on a les parametres pour dessiner le codage
		if(mot!=null || methode!=-1){
			dessineGrille(g);
			
			//on code suivante la methode choisie
			switch(methode){
			case 0:
				//ici methode NRZ
				NRZ(mot,g);
				break;
			case 1:
				//ici methode NRZI
				NRZI(g);
				break;
				
			case 2:
				//ici methode manchester
				manchester(g);
				break;
				
			case 3:
				//ici methode manchester differentiel
				manchesterDiff(g);
				break;
				
			case 4:
				//ici methode Miller
				miller(g);
				break;
			}
		}
	}
	
	/**
	 * Fonction qui dessine la grille
	 * @param g objet pour dessiner la grille
	 */
	private void dessineGrille(Graphics g){	
		
		//J'ai besoin d'un objet Graphics2D pour faire des trait pointier
		Graphics2D g2 = (Graphics2D) g;
		
		//on fixe la couleur des traits en bleu
		g2.setColor(Color.CYAN);
		
		// recuperation du mode standard de dessin 
		Stroke stroke = g2.getStroke();
		
		//reglage de la longeur des pointiers 
		float dash1[] = {5.0f};
		
		//application du mode pointier
		g2.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f));
		
		//on trace les traits vertical
		for(int i=0;i<this.getWidth();i=i+spaceH)
			g2.drawLine(i, 0, i, this.getHeight());
		
		// On repasse en mode normale
		g2.setStroke(stroke);
		 
		//on fixe la couleur des traits en rouge
		g2.setColor(Color.RED);
		
		//on trace la ligne du potentiel null
		g2.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
		
	}

	/**
	 * Fonction qui affiche le codage NRZ du mot
	 * @param mot mot a coder
	 * @param g objet pour dessiner le codage
	 */
	private void NRZ(String mot,Graphics g){
		
		//on fixe la couleur des traits en blanc
		g.setColor(Color.white);
		
		// position dans le codage
		int indexPosition=0;
		
		// valeur du bit precedement cod� (-1 si rien avant)
		int valPred=-1;
		
		//codage de la donn�e
		for(int i=0;i<mot.length();i++){
			
			//si la valeur du bit est 0 ou 1
			if(mot.charAt(i)=='0'){
				
				//on trace la liaison verticale avec le precedent byte si il est different de celui en cour de traitement
				if(valPred!=0 && valPred!=-1)
					g.drawLine(indexPosition, vPos, indexPosition,vNeg);
				valPred=0;
				
				//on trace le codage
				g.drawLine(indexPosition, vNeg, (indexPosition+spaceH),vNeg);
				indexPosition+=spaceH;
			
			}
			else{
				//on trace la liaison verticale avec le precedent byte si il est different de celui en cour de traitement
				if(valPred!=1 && valPred!=-1)
					g.drawLine(indexPosition, vPos, indexPosition,vNeg);
				valPred=1;
				
				//on trace le codage
				g.drawLine(indexPosition, vPos, (indexPosition+spaceH),vPos);
				indexPosition+=spaceH;
			}
			
			
		}		
	}
	
	/**
	 * Fonction qui affiche le codage NRZI du mot
	 * @param g objet pour dessiner le codage
	 */
	private void NRZI(Graphics g){
		
		//inversion des bit du mot
		String motNRZI="";
		for(int i=0;i<mot.length();i++){
			if(mot.charAt(i)=='0')
				motNRZI+="1";
			else
				motNRZI+="0";
		}
		
		//appele de la fonction NRZ avec le mot NRZI
		NRZ(motNRZI,g);
	}
	
	/**
	 * Fonction qui affiche le codage de Manchester
	 * @param g objet pour dessiner le codage
	 */
	private void manchester(Graphics g){
		
		//on fixe la couleur des traits en blanc
		g.setColor(Color.white);
		
		// Calcul de la longeur d'un demi espace
		int midSpaceH=spaceH/2;
		
		// position dans le codage
		int indexPosition=0;
		
		// valeur du precedent byte traite
		int pred=-1;
		
		// Parcour de la donnee a coder
		for(int i=0;i<mot.length();i++){
			
			// Choix du trace suivant la valeur du byte
			if(mot.charAt(i)=='0'){
				
				//si c'est le premier byte
				if(i==0){
					
					//par convention je place le premier trait pour le millieu d'horloge en bas si le byte est a zero
					g.drawLine(0, vNeg, midSpaceH, vNeg);
					indexPosition=midSpaceH;
				}
				else {
					if (pred==0){
					
					//on trace le trait qui descent seulement si le precedent byte �tait un zero
					g.drawLine(indexPosition, vPos, indexPosition, vNeg);
					
					}
					
					//on trace le trait pour atteindre le millieu d'horloge	
					g.drawLine(indexPosition, vNeg, indexPosition+midSpaceH,vNeg);
					indexPosition+=midSpaceH;
				}
				
			

				
				//on trace un trait qui monte
				g.setColor(Color.yellow);
				g.drawLine(indexPosition, vNeg, indexPosition,vPos );
				
				// dessin d'un triangle en bout de fleche
				int x[]={indexPosition,indexPosition-5,indexPosition+5};
				int y[]={vPos,vPos+10,vPos+10};
				g.fillPolygon(x,y, 3);

				//on trace le trait pour arriver a l'autre top d'horloge
				g.setColor(Color.white);
				g.drawLine(indexPosition, vPos, indexPosition+midSpaceH, vPos);
				
				indexPosition+=midSpaceH;
				pred=0;
			}
			else{
				if(i==0){
					
					//par convention je place le premier trait pour le millieu d'horloge en haut si le byte est a 1
					g.drawLine(0, vPos, midSpaceH, vPos);
					indexPosition=midSpaceH;
				}
				else{
					if (pred==1){
				
					
					//on trace le trait qui monte seulement si le precedent byte �tait un 1
					g.drawLine(indexPosition, vNeg, indexPosition, vPos);
					}
					
					//on trace le trait pour atteindre li millieu d'horloge
					g.drawLine(indexPosition, vPos, indexPosition+midSpaceH, vPos);
					indexPosition+=midSpaceH;
					
				}
				
				
				//on trace un trait qui descent
				g.setColor(Color.green);
				g.drawLine(indexPosition, vPos, indexPosition, vNeg);
				
				// dessin d'un triangle en bout de fleche
				int x[]={indexPosition,indexPosition-5,indexPosition+5};
				int y[]={vNeg,vNeg-10,vNeg-10};
				g.fillPolygon(x,y, 3);
				
				//on trace le trait pour arriver a l'autre top d'horloge
				g.setColor(Color.white);
				g.drawLine(indexPosition, vNeg, indexPosition+midSpaceH, vNeg);
				
				indexPosition+=midSpaceH;
				
				pred=1;

			}
		}
	}
	
	/**
	 * Fonction qui affiche le codage de Manchester Differentiel
	 * @param g objet pour dessiner le codage
	 */
	private void manchesterDiff(Graphics g){
		
		//on fixe la couleur des traits en blanc
		g.setColor(Color.white);
		
		// Calcul de la longeur d'un demi espace
		int midSpaceH=spaceH/2;
		
		//valeur de voltage du predecesseur
		int vPred=0;
		
		// position dans le codage
		int indexPosition=0;
		
		// Parcour de la donnee a coder
		for(int i=0;i<mot.length();i++){
			
			// si le byte est un 0
			if(mot.charAt(i)=='0'){
				
				
				//si c'est le premier byte
				if(i==0){
					
					//on choisi aleatoirement de commencer a vPos ou vNeg
					double r=Math.random();
					
					if(r<0.5){
						g.drawLine(0, vNeg, 0, vPos);
						
						g.drawLine(0, vPos, midSpaceH, vPos);
						indexPosition=midSpaceH;
						
						g.drawLine(indexPosition, vPos, indexPosition, vNeg);
						vPred=vNeg;
						
						g.drawLine(indexPosition, vNeg, indexPosition+midSpaceH, vNeg);
						
					}
					else{
				
						g.drawLine(0, vPos, 0, vNeg);
						
						g.drawLine(0, vNeg, midSpaceH, vNeg);
						indexPosition=midSpaceH;
						
						g.drawLine(indexPosition, vNeg, indexPosition, vPos);
						vPred=vPos;
						
						g.drawLine(indexPosition, vPos, indexPosition+midSpaceH, vPos);
					}
					indexPosition+=midSpaceH;
				}
				else{ 
					//ici ce n'est pas le premier byte
					
					//suivant la valeur de courant du precedent byte
					if(vPred==vPos){
						g.drawLine(indexPosition, vPos, indexPosition, vNeg);
						
						g.drawLine(indexPosition, vNeg, indexPosition+midSpaceH, vNeg);
						indexPosition+=midSpaceH;
						
						g.drawLine(indexPosition, vNeg, indexPosition, vPos);
						vPred=vPos;
						
						g.drawLine(indexPosition, vPos, indexPosition+midSpaceH, vPos);
						
					}
					else{
						g.drawLine(indexPosition, vNeg, indexPosition,vPos);

						
						g.drawLine(indexPosition, vPos, indexPosition+midSpaceH, vPos);
						indexPosition+=midSpaceH;
						
						g.drawLine(indexPosition, vPos, indexPosition, vNeg);
						vPred=vNeg;
						
						g.drawLine(indexPosition, vNeg, indexPosition+midSpaceH, vNeg);
					}
					indexPosition+=midSpaceH;
				}
			}
			else{ //byte a 1
				
				//si c'est le premier byte
				if(i==0){
					
					//on choisi aleatoirement de commencer a vPos ou vNeg
					double r=Math.random();
					
					if(r<0.5){
						
						g.drawLine(0, vPos, midSpaceH, vPos);
						indexPosition=midSpaceH;
						
						g.drawLine(indexPosition, vPos, indexPosition, vNeg);
						vPred=vNeg;
						
						g.drawLine(indexPosition, vNeg, indexPosition+midSpaceH, vNeg);
						
					}
					else{
						
						g.drawLine(0, vNeg, midSpaceH, vNeg);
						indexPosition=midSpaceH;
						
						g.drawLine(indexPosition, vNeg, indexPosition, vPos);
						vPred=vPos;
						
						g.drawLine(indexPosition, vPos, indexPosition+midSpaceH, vPos);
					}
					indexPosition+=midSpaceH;
				}
				else{ 
					//ici ce n'est pas le premier byte
					
					//suivant la precedente tension
					if(vPred==vPos){
						g.drawLine(indexPosition, vPos, indexPosition+midSpaceH, vPos);
						indexPosition+=midSpaceH;
						
						g.drawLine(indexPosition, vPos, indexPosition, vNeg);
						vPred=vNeg;
						
						g.drawLine(indexPosition, vNeg, indexPosition+midSpaceH, vNeg);
					}
					else{
						
						g.drawLine(indexPosition, vNeg, indexPosition+midSpaceH, vNeg);
						indexPosition+=midSpaceH;
						
						g.drawLine(indexPosition, vNeg, indexPosition, vPos);
						vPred=vPos;
						
						g.drawLine(indexPosition, vPos, indexPosition+midSpaceH, vPos);
					}
					indexPosition+=midSpaceH;					
				}
				
			}
			
		}
		
	}
	
	/**
	 * Fonction qui affiche le codage de Miller
	 * @param g objet pour dessiner le codage
	 */
	private void miller(Graphics g){
		
		//on fixe la couleur des traits en blanc
		g.setColor(Color.white);
		
		// Calcul de la longeur d'un demi espace
		int midSpaceH=spaceH/2;
		
		//valeur de voltage du predecesseur
		int vPred=0;
		
		//valeur du precedent byte
		int pred=-1;
		
		// position dans le codage
		int indexPosition=0;
		
		// Parcour de la donnee a coder
		for(int i=0;i<mot.length();i++){
			
			// si le byte est un 0
			if(mot.charAt(i)=='0'){
				
				//si c'est le premier byte,on choisie aleatoirement de commencer en vPos ou vNeg
				if(i==0){
					
					double r=Math.random();
					if(r<0.5){
						
						//demarrage en vPos
						g.drawLine(indexPosition, vPos, indexPosition+spaceH, vPos);
						vPred=vPos;
					}
					else{
						
						//demarrage en vNeg
						g.drawLine(indexPosition, vNeg, indexPosition+spaceH, vNeg);
						vPred=vNeg;
					}
					
					indexPosition+=spaceH;
					pred=0;
				}
				else{
					
					//si le byte precedent est un 0, on fait une transition sur le temps d'horloge
					if(pred==0){
						
						//si il etait en vPos on va en vNeg et inversement
						if(vPred==vPos){
							g.drawLine(indexPosition, vPos, indexPosition, vNeg);
							g.drawLine(indexPosition, vNeg, indexPosition+spaceH, vNeg);
							
							vPred=vNeg;
						}
						else{
							g.drawLine(indexPosition, vNeg, indexPosition, vPos);
							g.drawLine(indexPosition, vPos, indexPosition+spaceH, vPos);
							
							vPred=vPos;
						}
						
						indexPosition+=spaceH;
						pred=0;
					}else{
						
						//le precedent n'est pas un 0 donc on n'effectue pas de transition
						
						if(vPred==vPos){
							g.drawLine(indexPosition, vPos, indexPosition+spaceH, vPos);
							
							vPred=vPos;
						}
						else{
							g.drawLine(indexPosition, vNeg, indexPosition+spaceH, vNeg);
							
							vPred=vNeg;
						}
						
						indexPosition+=spaceH;
						pred=0;
					}
				
				}
				

			}
			else{ // byte a 1
				
				//si c'est le premier byte, on choisie aleatoirement de commencer en vPos ou vNeg
				if(i==0){
					
					double r=Math.random();
					if(r<0.5){
						//demarrage en vPos
						g.drawLine(0, vPos, midSpaceH, vPos);
						indexPosition=midSpaceH;
						
						g.drawLine(indexPosition, vPos, indexPosition, vNeg);
						
						g.drawLine(indexPosition, vNeg, indexPosition+midSpaceH, vNeg);
						
						vPred=vNeg;
					}
					else{
						
						//demarrage en vNeg
						g.drawLine(0, vNeg, midSpaceH, vNeg);
						indexPosition=midSpaceH;
						
						g.drawLine(indexPosition, vNeg, indexPosition, vPos);
						g.drawLine(indexPosition, vPos, indexPosition+midSpaceH, vPos);
						
						vPred=vPos;
					}
					
					indexPosition+=midSpaceH;
					pred=1;
				}
				else{ //ce n'est pas le premier byte
									
					if(vPred==vPos){
						
						g.drawLine(indexPosition, vPos, indexPosition+midSpaceH, vPos);
						indexPosition+=midSpaceH;
						
						g.drawLine(indexPosition, vPos, indexPosition, vNeg);
						vPred=vNeg;
						
						g.drawLine(indexPosition, vNeg,indexPosition+midSpaceH, vNeg);
						
						
					}
					else{
						g.drawLine(indexPosition, vNeg, indexPosition+midSpaceH, vNeg);
						indexPosition+=midSpaceH;
						
						g.drawLine(indexPosition, vNeg, indexPosition, vPos);
						vPred=vPos;
						
						g.drawLine(indexPosition, vPos,indexPosition+midSpaceH, vPos);
						
					}
					indexPosition+=midSpaceH;
					pred=1;
						
				}
				
			}
			
		}
		
	}
}
