
#include <QtGui>
#include "FenPrincipale.h"

int main(int argc, char *argv[])
{
    QApplication app(argc, argv);

    // langue
	QString locale = QLocale::system().name().section('_', 0, 0);
	QTranslator translator;
	translator.load(QString("qt_") + locale, QLibraryInfo::location(QLibraryInfo::TranslationsPath));
	app.installTranslator(&translator);

	// support des accents et autre
	QTextCodec::setCodecForCStrings(QTextCodec::codecForName("UTF-8"));


    FenPrincipale f;
    f.show();
    return app.exec();
}
