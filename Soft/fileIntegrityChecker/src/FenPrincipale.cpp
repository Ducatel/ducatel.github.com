/*
Auteur: David Ducatel
Date de creation: ven. juil. 15 2011

Rôle:
Fenetre principale de l'application

Licence:
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see http://www.gnu.org/licenses

*/

#include "FenPrincipale.h"
FenPrincipale::FenPrincipale() : QWidget(){

	QVBoxLayout *layoutPrinc=new QVBoxLayout();

	/************** Choix du fichier *******************/

	QHBoxLayout *layoutChoixFichier=new QHBoxLayout();
	layoutPrinc->addLayout(layoutChoixFichier);

	layoutChoixFichier->addWidget(new QLabel("Fichier à tester: "));

	nomFichier=new QLineEdit(this);
	nomFichier->setAcceptDrops(false);
	layoutChoixFichier->addWidget(nomFichier);

	ouvir=new QPushButton("Choisir fichier",this);
	layoutChoixFichier->addWidget(ouvir);

	/****************** Hash *****************************/

	QFormLayout *layoutHash=new QFormLayout();
	layoutPrinc->addLayout(layoutHash);

	hashCalculer=new QLineEdit();
	hashCalculer->setReadOnly(true);
	layoutHash->addRow("Hash calculé: ",hashCalculer);

	hashDonee=new QLineEdit();
	hashDonee->setAcceptDrops(false);
	layoutHash->addRow("Votre Hash: ",hashDonee);

	choixHash=new QComboBox();
	choixHash->addItem("MD5");
	choixHash->addItem("MD4");
	choixHash->addItem("SHA1");
	layoutHash->addRow("Type de hash: ",choixHash);

	/*************** Resultat *******************/

	resultat=new QLabel("Etat: Indeterminé");
	resultat->setAlignment(Qt::AlignCenter);
	resultat->setFont(QFont("Comic Sans MS", 20));
	layoutPrinc->addWidget(resultat);


	/***************** Bouton *******************/

	QHBoxLayout *layoutBouton=new QHBoxLayout();
	layoutPrinc->addLayout(layoutBouton);

	calculer=new QPushButton("Calculer",this);
	layoutBouton->addWidget(calculer);

	quitter=new QPushButton("Quitter",this);
	layoutBouton->addWidget(quitter);

	/*****************************************************/

	connect(quitter,SIGNAL(clicked()),qApp,SLOT(quit()));
	connect(ouvir,SIGNAL(clicked()),this,SLOT(choixFichier()));
	connect(calculer,SIGNAL(clicked()),this,SLOT(calculerHash()));
    connect(hashDonee,SIGNAL(textEdited(QString)),this,SLOT(compareHash(QString)));


    setWindowIcon(QIcon(QCoreApplication::applicationDirPath() + "/icone.png"));
    setAcceptDrops(true);
	setLayout(layoutPrinc);
	setWindowTitle("File Integrity Checker");
}

void FenPrincipale::compareHash(QString hash){

	if(!hashCalculer->text().isEmpty()){
		if(hash == hashCalculer->text())
			resultat->setText("Etat: <font color=\"#00DD00\">Fichier correct</font>");
		else if(!hash.isEmpty())
			resultat->setText("Etat: <font color=\"#FF0000\">Fichier corrompu</font>");
		else
			resultat->setText("Etat: Indeterminé");
	}


}

void FenPrincipale::choixFichier(){
    QString fichier = QFileDialog::getOpenFileName(this, "Ouvrir un fichier", QString());
    if(!fichier.isEmpty())
    	nomFichier->setText(fichier);
}

void FenPrincipale::calculerHash(){
	QFile *file = new QFile(nomFichier->text());

	if(!file->exists()){
		QMessageBox::critical(this, "Erreur", "Le chemin vers le fichier est vide ou invalide");
		return;
	}

	// lecture du fichier
	file->open(QIODevice::ReadOnly);
	QByteArray data = file->readAll();

	// choix du type de hash et calcul du hash

	QByteArray hash;
	if(choixHash->currentText()=="MD4")
		hash=QCryptographicHash::hash(data,QCryptographicHash::Md4).toHex();
	else if(choixHash->currentText()=="MD5")
		hash=QCryptographicHash::hash(data,QCryptographicHash::Md5).toHex();
	else
		hash=QCryptographicHash::hash(data,QCryptographicHash::Sha1).toHex();

	file->flush();
	file->close();

	// on affiche le hash
	hashCalculer->setText(hash);

	if(!hashDonee->text().isEmpty()){
		if(hashDonee->text() == hash)
			resultat->setText("Etat: <font color=\"#00DD00\">Fichier correct</font>");
		else
			resultat->setText("Etat: <font color=\"#FF0000\">Fichier corrompu</font>");

	}


}

void FenPrincipale::dragEnterEvent(QDragEnterEvent *event)
{
	// On vérifie que c'est bien un fichier qui est utilisé pour le glisser-déposer
   if(event->mimeData()->hasUrls()){
		   // On accepte le glisser-déposer
		   event->acceptProposedAction();
   }
}

void FenPrincipale::dropEvent(QDropEvent *event){

   // On récupère tout les fichiers sélectionnés par l'utilisateur pour le glisser-déposer
   QList<QUrl> urls = event->mimeData()->urls();
   if (urls.isEmpty())
	   return;

   // On récupère le chemin du premier fichier selectionner
   QString fileName = urls.first().toLocalFile();
   if (fileName.isEmpty())
	   return;

   //on affiche ne nom du fichier
   nomFichier->setText(fileName);
}

