
/**
 * Fonction qui permet d'afficher ou de cacher une div
 */
function afficheCacheDiv(idDiv){
	if(document.getElementById(idDiv).style.display!='none'){ // si la div n'est pas caché
		Effect.BlindUp(idDiv);
	}
	else{ // si la div est caché
		Effect.BlindDown(idDiv);
	}
}

function getWindowHeight() {
	var windowHeight = 0;
	if (typeof(window.innerHeight) == 'number') {
		windowHeight = window.innerHeight;
	}
	else {
		if (document.documentElement && document.documentElement.clientHeight) {
			windowHeight = document.documentElement.clientHeight;
		}
		else {
			if (document.body && document.body.clientHeight) {
				windowHeight = document.body.clientHeight;
			}
		}
	}
	return windowHeight;
}


function setFooter() {
	if (document.getElementById) {
		var windowHeight = getWindowHeight();
		if (windowHeight > 0) {
			var contentHeight = document.getElementById('centre').offsetHeight;
			var footerElement = document.getElementById('footer');
			var footerHeight  = footerElement.offsetHeight;
			if (windowHeight - (contentHeight + footerHeight) >= 0) {
				footerElement.style.position = 'relative';
				footerElement.style.top = (windowHeight - (contentHeight + footerHeight)) + 'px';
			}
			else {
				footerElement.style.position = 'static';
			}
		}
	}
}

