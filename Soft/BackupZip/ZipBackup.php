<?php
/**
 * Class permettant de faire des archives
 * @author David Ducatel
 */
class ZipBackup extends ZipArchive { 

	/**
	 * Constructeur de la class
	 * $nom = nom de l'archive
	 * $flag = flag d'ouverture de l'archive
	 * $commentaire = commentaire sur l'archive
	 */
	public function __construct($nom, $flag = ZIPARCHIVE::CREATE, $commentaire = ""){

		// Si le flag nous indique que l'archive doit etre créé (elle n'existe pas)
		if($flag == ZIPARCHIVE::CREATE)
		{
			if ($this->open($nom, ZIPARCHIVE::CREATE) !== TRUE)
				throw new Exception("L'archive ne peut être créée");
		}
		else if($flag == ZIPARCHIVE::OVERWRITE) //si le flag nous indique que nous ecrasons l'archive
		{
			if(!file_exists($nom)) //elle n'existe pas, on la créer
			{
				if ($this->open($nom, ZIPARCHIVE::CREATE) !== TRUE)
					throw new Exception("L'archive ne peut être créée");
			}
			else
			{
				if (!unlink($nom))
					throw new Exception("L'archive ne peut être écrasé");
					
				if ($this->open($nom, ZIPARCHIVE::CREATE) !== TRUE)
					throw new Exception("L'archive ne peut être créée");
			}
		}
		
		if ($commentaire != "")
			$this->setArchiveComment($commentaire);
	}
    
	
	/**
	 * Methode qui permet d'enregistrer une sous arborescence.
	 * $chemin = chemin vers le dossier a sauvé
	 * $prefixe = argument pour la récursivité
	 */
	public function addRecursive($chemin, $prefixe = '')
    {
		// Creation d'un chemin absolu et ajout du séparateur de fichier
        $chemin = realpath($chemin) . DIRECTORY_SEPARATOR;

		// Si le chemin ne mene sur rien
        if (!file_exists($chemin)) 
            throw new Exception("Le chemin '$chemin' n'existe pas");

		// si le chemin ne mene pas sur un dossier
        if (!is_dir($chemin)) 
            throw new Exception("Le chemin '$chemin' existe mais n'est pas un répertoire");
        
		// Si il est impossible d'ouvrir le fichier
        if (!($dh = opendir($chemin))) 
            throw new Exception("Le répertoire '$chemin' n'est pas accessible");
        

		// on parcour le dossier
        while (($fichier = readdir($dh)) !== FALSE) {
		
			// si le ce n'est pas le lien . ou ..
            if ($fichier != '.' && $fichier != '..') {
			
				// Si c'est un dossier
				if (is_dir($chemin . $fichier)) {
					
					// on creer un dossier vide 
					$this->addEmptyDir($prefixe . $fichier); 
					
					// on lance la récursivité
					$this->addRecursive($chemin . $fichier . DIRECTORY_SEPARATOR, $prefixe . $fichier . DIRECTORY_SEPARATOR);
					
				} else { // ici un fichier
					
					// on ajoute le fichier
					if (!$this->addFile($chemin . $fichier, $prefixe . $fichier)) 
						throw new Exception("Le fichier '$chemin/$fichier' n'a pu être ajouté à l'archive");
				}
				
			}
        }
		
		// on ferme le pointeur
        closedir($dh);
    }
}

?>