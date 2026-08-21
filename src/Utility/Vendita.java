package Utility;

import Item.Piatto;

import java.util.List;
import java.util.ArrayList;

public class Vendita {

    private int id;
    private List<Piatto> prodotti;

    public Vendita(List<Piatto> prodotti, int id) {
        this.prodotti = new ArrayList<>(prodotti);
        this.id=id;
    }

    public double prezzoTotale() {
        double totale = 0;
        for (Piatto p : prodotti) {
            totale += p.getPrezzo();
        }
        return totale;
    }

    public List<Piatto> getProdotti() {
        return new ArrayList<>(prodotti);
    }

    public long getId(){
        return id;
    }


}
