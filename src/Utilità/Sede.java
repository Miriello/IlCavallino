package Utilità;

public class Sede {
    private String citta;
    private String regione;
    private String cap;

    public Sede(String citta, String regione, String cap) {
        this.citta = citta;
        this.regione = regione;
        this.cap = cap;
    }

    public String getCitta() { return citta; }
    public String getRegione() { return regione; }
    public String getCap() { return cap; }

    @Override
    public String toString() {
        return citta + " (" + regione + ") " + cap;
    }
}
