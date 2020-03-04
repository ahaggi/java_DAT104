#ifndef SPILL_H
#define SPILL_H
#include <QObject>
#include <QSignalMapper>
#include <QDebug>


using namespace std;

class spill : public QObject
{
    Q_OBJECT

public:
    spill();
    ~spill();
    QSignalMapper *mapper;
    int rett=-1;
    int antall_forsoek=0;
signals:
    void startPaaNytt1();
    void sjekk(int verdi);
    void sendSignTilGui(int verdi, int rett, int antall_forsoek);
    void sendSignTilGui_spn();
};

#endif // SPILL_H
