
$(function(){
	createBanniere();
	
	
});

/**
 * Fonction qui permet de generer la banniere
 * canvas HTML5
 */
function createBanniere(){
	//creation de la balise canvas
	$('#div_banniere').append($('<canvas/>').attr({
		'id':'banniere',
		'height':100,
		'width':$(window).width()-2
	}));
	
	
	var canvas=document.getElementById('banniere');
	var ctx=canvas.getContext('2d');
	
	// nombre de ligne dans le canvas
	var nbLigne = 5;
	
	// compteur du nombre de ligne
	var cpt = 0;
	
	
	// fonction qui va definir un cycle de mouvement des courbes
	var cycle = function() {
	
		// on vide le canvas
		ctx.clearRect(0,0,canvas.width,canvas.height);
		
		
		// reglage de l'ecriture
		ctx.fillStyle = "hsl(204,100%,50%)";
		ctx.font = "20pt Arial";
		
		// on ecrie les infos perso
		ctx.fillText('David Ducatel', 10, 25);
		ctx.fillText('Master informatique', 10, 90);
		
		var text='1 Juillet 1988 -- '+getAge()+' ans';
		var taille=ctx.measureText(text);
		
		ctx.fillText(text, canvas.width-taille.width-10, 25);
		
		var text='Le Havre';
		var taille=ctx.measureText(text);
		ctx.fillText(text, canvas.width-taille.width-10, 90);
		
		/////////////////////// TEST /////////////////////////////
		
		
		/////////////////////////////////////////////////////////
		
		// pour chaque ligne que nous voulons tracer
		for(var j=0; j<nbLigne; ++j) {
		
			// calcule d'une marge entre chaque ligne
			var offset = (cpt+j*10)/20;
			
			// calcul de l'épaisseur de la ligne
			ctx.lineWidth = 1+2*(nbLigne-j); 
			
			// calcul de l'opacité de la ligne
			ctx.strokeStyle = 'hsla(204,100%,50%,'+(j/5+0.1)+')'; 
			
			// calcul des coordonées de la ligne
			var y = (Math.sin(offset)+1)*canvas.height/2;
			var cpy1 = (Math.cos(offset)+0.5)*canvas.height;
			var cpy2 = canvas.height - cpy1;
			ctx.beginPath();
			ctx.moveTo(0, y);
			ctx.bezierCurveTo(canvas.width/3, cpy1, 2*canvas.width/3, cpy2, canvas.width, y);
			ctx.stroke();
		}
		cpt++;
	};
	setInterval(cycle, 30);
}

/**
 * Fonction qui calcul mon age courant
 * @return mon age
 */
function getAge(){
	actu=new Date();
	var moisNaiss='07';
	var jourNaiss='01';
	var anneeNaiss='1988';
	if((actu.getMonth()+1)>=moisNaiss){
		if((actu.getMonth()+1)==moisNaiss){
			if(actu.getDate()>=jourNaiss){
				mois=(actu.getMonth()+1)-moisNaiss;
				ans=actu.getFullYear()-anneeNaiss;
			}
			else
			{
				mois=(12-moisNaiss)+(actu.getMonth()+1);
				ans=actu.getFullYear()-anneeNaiss-1;
			}
		}
		else
		{
			mois=(actu.getMonth()+1)-moisNaiss;
			ans=actu.getFullYear()-anneeNaiss;
		}
	}
	else{
		mois=(12-moisNaiss)+(actu.getMonth()+1);
		ans=actu.getFullYear()-anneeNaiss-1;
	}
	if(actu.getDate()>jourNaiss)
	jours=actu.getDate()-jourNaiss;
	else

	jours=(30-jourNaiss)+(actu.getDate());

	while(jours>30){
	jours-=30;
	mois+=1;
	}
	while(mois>12){
	mois-=12;
	ans+=1;
	}
	
	return ans;
}