package Registri;

import Cose.Prodotto;

import java.util.List;

public class Vendita {
    private long id;
    private List<Prodotto> prodotti;

    public Vendita(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public double prezzoTotale(Vendita v) {
        int totale = 0;
        for (Prodotto p : v.getProdotti()) {
            totale += p.getPrezzo();
        }
        return totale;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

}
