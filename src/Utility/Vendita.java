package Utility;

import Item.Piatto;

import java.util.List;

public class Vendita {
    private static long contatore=0;
    private final long id;
    private List<Piatto> prodotti;

    public Vendita(List<Piatto> prodotti) {
        this.prodotti = prodotti;
        id = contatore+1;
    }

    public double prezzoTotale() {
        int totale = 0;
        for (Piatto p : prodotti) {
            totale += p.getPrezzo();
        }
        return totale;
    }

    public List<Piatto> getProdotti() {
        return prodotti;
    }

    public long getId(){
        return id;
    }

}
