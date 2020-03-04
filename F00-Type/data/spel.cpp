#include<spel.h>


    spel::spel(){
        qsrand(time(NULL));

    }
    spel::~spel(){

    }


    void spel::sjekk(int verdi){
        qDebug() << verdi;
        ++antall_forsoek;

        emit sendSignTilGui_sjk(verdi, rett , antall_forsoek);
    }

    void spel::startPaaNytt(){
        antall_forsoek = 0;
        rett = qrand() % 10;
        qDebug() << rett;
        emit sendSignTilGui_spn();
    }
