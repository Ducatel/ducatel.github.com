<?php
include('ZipBackup.php');

//le chemin vers l'arborescence
$dossier="./dossier";

//Nom de l'archive
$archive=date('d-m-Y').".zip";

// envoie des header pour le telechargement de l'archove
header('Content-Type: application/x-zip');
header("Content-Disposition: attachment; filename=".$archive);

// creation de l'objet archive
$zip=new ZipBackup($archive);

// Compression du dossier
$zip->addRecursive($dossier);

// Fermeture de l'archive
$zip->close();

// envoie de l'archive
readfile($archive);

?>