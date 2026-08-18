package Utility;

import Item.Prodotto;

import java.util.List;

public class Vendita {
    private static long id=0;
    private List<Prodotto> prodotti;

    public Vendita(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
        id+=1;
    }

    public double prezzoTotale() {
        int totale = 0;
        for (Prodotto p : prodotti) {
            totale += p.getPrezzo();
        }
        return totale;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public long getId(){
        return id;
    }

}
