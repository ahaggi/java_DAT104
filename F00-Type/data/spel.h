#ifndef SPEL_H
#define SPEL_H

#include <QObject>
#include <QDebug>
#include <ctime>


using namespace std;

class spel : public QObject
{
    Q_OBJECT

public:
    spel();
    ~spel();
    int rett=-1;
    int antall_forsoek=0;
signals:
    void sendSignTilGui_sjk(int , int, int);
    void sendSignTilGui_spn();
public slots:
    void startPaaNytt();
    void sjekk(int verdi);
};


#endif // SPEL_H
