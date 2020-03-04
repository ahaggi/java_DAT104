#ifndef SPEL_GUI_H
#define SPEL_GUI_H

#include<dialog.h>
#include <QWidget>
#include<QVBoxLayout>
#include<QPushButton>
#include <QMessageBox>
#include <QDebug>
#include <QSignalMapper>
#include <vector>


using namespace std;
class spel_gui : public QWidget
{
    Q_OBJECT

public:
    spel_gui(QWidget *parent = 0);
    QHBoxLayout *hbox;
    vector<QPushButton*> buttons;

    QSignalMapper *mapper;

    dialog wd;
    ~spel_gui();
signals:
    void mySignal();

public slots:
    void stillOppKnapper(int verdi, int rett, int antall_forsoek);
    void startPaaNytt();

};


#endif // SPEL_GUI_H
