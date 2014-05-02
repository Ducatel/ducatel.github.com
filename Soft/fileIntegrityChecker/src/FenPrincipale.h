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

#ifndef HEADER_FENPRINCIPALE
#define HEADER_FENPRINCIPALE

#include <QtGui>

class FenPrincipale : public QWidget
{
	Q_OBJECT
    public:
        FenPrincipale();


    private slots:
		void choixFichier();
		void calculerHash();
		void compareHash(QString hash);

    private:
        QLineEdit* nomFichier;
        QLineEdit* hashDonee;
        QLineEdit* hashCalculer;

        QComboBox* choixHash;

        QPushButton* ouvir;
        QPushButton* calculer;
        QPushButton* quitter;

        QLabel* resultat;

       void dragEnterEvent(QDragEnterEvent *event);
       void dropEvent(QDropEvent *event);


};

#endif
