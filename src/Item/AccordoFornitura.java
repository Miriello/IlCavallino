package Item;

import Persone.Fornitore;
import Persone.Socio;

public class AccordoFornitura {
    private Fornitore fornitore;
    private Socio socio;
    private Data stipulaAccordo;
    private int durata;

    public AccordoFornitura(Fornitore f, Socio s, Data dat, int d){
        this.fornitore=f;
        this.socio=s;
        this.stipulaAccordo=dat;
        this.durata=d;
    }

    public AccordoFornitura(AccordoFornitura af){
        this.fornitore=af.fornitore;
        this.socio=af.socio;
        this.stipulaAccordo=af.stipulaAccordo;
        this.durata=durata;
    }

    public String toString(){
        return ("L'accordo di fornitura tra il fornitore "+fornitore+ " e il socio "+socio+ "è stato stipulato in data" + stipulaAccordo + "per la durata di" +durata+" giorni");
    }
}
