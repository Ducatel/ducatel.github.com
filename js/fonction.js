
$(function(){
	$('#ageCourantTitre').html(getAge());
});

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