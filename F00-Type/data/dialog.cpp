#include "dialog.h"


dialog::dialog(QWidget *parent)
    : QWidget(parent)
{


    info_string;

    vbox = new QVBoxLayout(this);
    ok =  new QPushButton("Start på nytt!");
    info = new QLabel;

    vbox->addWidget(info);
    vbox->addWidget(ok);
    setMinimumSize(150,125);

}

dialog::~dialog()

{
    qDebug("deleted");
}
