#include "spel_gui.h"


spel_gui::spel_gui(QWidget *parent)
    : QWidget(parent)
{
    buttons.assign(11,NULL);
    mapper = new QSignalMapper(this);
    hbox = new QHBoxLayout(this);

    for (int i = 0; i < 10; ++i) {
        buttons[i] = new QPushButton(QString::number(i), this);
        buttons[i]->setDisabled(true);
        hbox->addWidget(buttons[i]);
        connect(buttons[i] ,SIGNAL(clicked(bool)), mapper, SLOT(map()) );
        mapper->setMapping(buttons[i],i);
    }

    buttons[10] = new QPushButton( "Nytt spill" , this);
    hbox->addWidget(buttons[10]);
    connect(buttons[10], SIGNAL(clicked(bool)), mapper , SLOT(map()));
    mapper->setMapping(buttons[10]," ");


}

void spel_gui::stillOppKnapper(int verdi, int rett, int antall_forsoek){
    if (verdi > rett)
        for (int i = verdi; i < 10; ++i){
            buttons[i]->setStyleSheet("background-color: red;");
            buttons[i]->setDisabled(true);
        }
    else if (verdi < rett)
        for (int i = 0; i <= verdi; ++i){
            buttons[i]->setStyleSheet("background-color: red;");
            buttons[i]->setDisabled(true);
        }
    else{
        buttons[verdi]->setStyleSheet("background-color: green;");
        for (int i = 0; i < 10; ++i)
            buttons[i]->setDisabled(true);
        wd.info->setText("<b>Gratulerer</b>!<br/>  Antall forsøk: "+ QString::number(antall_forsoek));
        wd.show();

        
      
    
//       int x = QMessageBox::information(this, "Message","Antall forsøk: " + QString::number(antall_forsoek), QMessageBox::Ok);

//        QMessageBox qmsg;
//        qmsg.setText("Gratulerer!");
//        qmsg.setInformativeText("Antall forsøk: " + QString::number(antall_forsoek));
//        qmsg.setStandardButtons(QMessageBox::Ok);
//        int x = qmsg.exec();


//        if (x==QMessageBox::Ok) {
//            startPaaNytt();
//        }

     }

}


void spel_gui::startPaaNytt(){
    for (int i = 0; i < 10; ++i) {
        buttons[i]->setDisabled(false);
        buttons[i]->setStyleSheet("background-color: none;");
    }
}

spel_gui::~spel_gui()
{
    vector<QPushButton*>::iterator it = buttons.begin();
    while(it!=buttons.end()){
        delete *it;
        buttons.erase(it);
    }
}
