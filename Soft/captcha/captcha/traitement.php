<?php
session_start();

// si l'utilisateur a bien tappe un code
if (!empty($_POST['userCode'])){

	//Convertion du texte est majuscule
	$userCode = strtoupper($_POST['userCode']);

	// Comparaison du code
	if( md5($userCode) == $_SESSION['captcha'] )
	  echo '<h2 class="correct">Correct !</h2>'; // Le code est bon
	else 
		echo '<h2 class="incorrect">Incorrect recommencez !</h2>'; // Le code est erroné
}
else 
	echo '<h2>Veuillez entrer le code</h2>'; // ici aucun code detecte
	

?>