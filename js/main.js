$(document).ready( function() {

	// year
	$("span[name='myAge']").html(getAge());

	var userLang = navigator.language || navigator.userLanguage; 
	if(userLang == "fr" || userLang == "en")
 		showLanguage(userLang);
 	else
 		showLanguage("en");
 		
 	//lang change
 	$("#langChooser,#flagLangChooser").click(function(){
 		var currentLang = $('#currentLang').val();
 		if(currentLang == "en")
 			showLanguage("fr");
 		else
 			showLanguage("en");
 	});
 
	// Logo
	var $logo 	= $('#logo');
	 if (location.href.indexOf("#") != -1) {
        if(location.href.substr(location.href.indexOf("#"))!='#about'){
        	$logo.show();
        }
    }
    
	// Show logo 
	$('#tab-container .tab a').click(function() {
	  $logo.slideDown('slow');
	});
	// Hide logo
	$('#tab-about').click(function() {
	  $logo.slideUp('slow');
	});	
	
	function animMeter(){
    $(".meter > span").each(function() {
                $(this)
                    .data("origWidth", $(this).width())
                    .width(0)
                    .animate({
                        width: $(this).data("origWidth")
                    }, 1200);
            });
}
animMeter();

      $('#tab-container').easytabs({
        animate			: true,
        updateHash		: true,
        transitionIn	: 'slideDown',
        transitionOut	: 'slideUp',
        animationSpeed	: 600,
        tabActiveClass	: 'active'}).bind('easytabs:midTransition', function(event, $clicked, $targetPanel){
            if($targetPanel.selector=='#resume'){
                    animMeter();
            }
        });
    });
 
 /**
  * Display text in current language
  */   
function showLanguage(lang){
	$("*[lang]").hide();
	$("*[lang='"+lang+"']").show();
	$('#currentLang').val(lang);
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

