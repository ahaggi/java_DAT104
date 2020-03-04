#include "spel_gui.h"
#include "spel.h"
#include <QApplication>
#include <QSignalMapper>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    spel_gui gui;

    spel sp;

    QObject::connect(gui.mapper , SIGNAL(mapped(int)) , &sp , SLOT(sjekk(int)));
    QObject::connect(gui.mapper , SIGNAL(mapped(QString)) , &sp , SLOT(startPaaNytt()) );

    QObject::connect(gui.wd.ok , SIGNAL(clicked(bool)) , &sp , SLOT(startPaaNytt()));
    QObject::connect(gui.wd.ok, SIGNAL(clicked(bool)), &(gui.wd), SLOT(close()));

    QObject::connect(&sp, SIGNAL(sendSignTilGui_sjk(int,int,int)), &gui, SLOT(stillOppKnapper(int,int,int)) );
    QObject::connect(&sp, SIGNAL(sendSignTilGui_spn()), &gui ,SLOT(startPaaNytt()));

    gui.show();

    return a.exec();
}
