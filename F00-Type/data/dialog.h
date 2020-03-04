#ifndef DIALOG_H
#define DIALOG_H


#include <QWidget>
#include<QVBoxLayout>
#include<QPushButton>
#include <QMessageBox>
#include <QDebug>
#include <QSignalMapper>
#include <QLabel>

using namespace std;

class dialog : public QWidget{
    Q_OBJECT

public:
    dialog(QWidget *parent = 0);
    QVBoxLayout *vbox;

    QPushButton *ok;
    QLabel *info;
    QString info_string="";

    ~dialog();
};


#endif // DIALOG_H
