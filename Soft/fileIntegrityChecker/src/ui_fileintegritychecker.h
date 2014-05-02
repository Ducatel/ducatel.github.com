/********************************************************************************
** Form generated from reading UI file 'fileintegritychecker.ui'
**
** Created: Fri Jul 15 23:52:38 2011
**      by: Qt User Interface Compiler version 4.7.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_FILEINTEGRITYCHECKER_H
#define UI_FILEINTEGRITYCHECKER_H

#include <QtCore/QVariant>
#include <QtGui/QAction>
#include <QtGui/QApplication>
#include <QtGui/QButtonGroup>
#include <QtGui/QHeaderView>
#include <QtGui/QWidget>

QT_BEGIN_NAMESPACE

class Ui_FileIntegrityCheckerClass
{
public:

    void setupUi(QWidget *FileIntegrityCheckerClass)
    {
        if (FileIntegrityCheckerClass->objectName().isEmpty())
            FileIntegrityCheckerClass->setObjectName(QString::fromUtf8("FileIntegrityCheckerClass"));
        FileIntegrityCheckerClass->resize(400, 300);

        retranslateUi(FileIntegrityCheckerClass);

        QMetaObject::connectSlotsByName(FileIntegrityCheckerClass);
    } // setupUi

    void retranslateUi(QWidget *FileIntegrityCheckerClass)
    {
        FileIntegrityCheckerClass->setWindowTitle(QApplication::translate("FileIntegrityCheckerClass", "FileIntegrityChecker", 0, QApplication::UnicodeUTF8));
    } // retranslateUi

};

namespace Ui {
    class FileIntegrityCheckerClass: public Ui_FileIntegrityCheckerClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_FILEINTEGRITYCHECKER_H
