<?php
/**
 * Generateur de captcha
 * Author David Ducatel
 */
 
 
session_start(); 
 
// Definition du chemin absolu
if ( !defined('ABSPATH') ) define('ABSPATH', dirname(__FILE__) . '/');
 
/*
 * Fonction qui va permettre de generer une suite aleatoire de caractere
 */
function rdmCode($length) {
  $chars = '23456789ABCDEFGHJKLMNPQRSTUVWXYZ'; // au supprime 1,I,0,O afin d'eviter les risque de confusion par l'utilisateur
  $rand_str = '';
  for ($i=0; $i<$length; $i++)
    $rand_str .= $chars{ mt_rand( 0, strlen($chars)-1 ) };
  return $rand_str;
}

/**
 * Fonction qui retourne un entre aleatoire d'un tableau
 */
function rdmTab($tab) {
  return $tab[array_rand($tab)];
}
 
// generation d'un code
$code = rdmCode(5);
 
// cryptage du code en md5 et stockage dans session pour vérification ultérieure
$_SESSION['captcha'] = md5($code);
 
// recuperation de chaque symbole du code
$char1 = substr($code,0,1);
$char2 = substr($code,1,1);
$char3 = substr($code,2,1);
$char4 = substr($code,3,1);
$char5 = substr($code,4,1);
 
// recuperation des fonts que nous voulons utiliser
$tabPolice = glob('fonts/*.ttf');
 
// Creation de l'image 
$image = imagecreatefrompng('captcha.png');
 
// Tableau qui va contenir les couleur des symboles
$tabCouleur=array (	imagecolorallocate($image, 131,154,255),
                imagecolorallocate($image,  89,186,255),
                imagecolorallocate($image, 155,190,214),
                imagecolorallocate($image, 255,128,234),
                imagecolorallocate($image, 255,123,123) );
 

// ecriture des lettre sur l'image
imagettftext($image, 28, -10,   0, 37, rdmTab($tabCouleur), ABSPATH .'/'. rdmTab($tabPolice), $char1);
imagettftext($image, 28,  20,  37, 37, rdmTab($tabCouleur), ABSPATH .'/'. rdmTab($tabPolice), $char2);
imagettftext($image, 28, -35,  55, 37, rdmTab($tabCouleur), ABSPATH .'/'. rdmTab($tabPolice), $char3);
imagettftext($image, 28,  25, 100, 37, rdmTab($tabCouleur), ABSPATH .'/'. rdmTab($tabPolice), $char4);
imagettftext($image, 28, -15, 120, 37, rdmTab($tabCouleur), ABSPATH .'/'. rdmTab($tabPolice), $char5);
 
 
// Envoi de l'image
header('Content-Type: image/png');
imagepng($image);
imagedestroy($image);
?>