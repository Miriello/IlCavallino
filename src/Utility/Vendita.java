package Utility;

import Item.Articolo;
import Item.Piatto;

import java.util.List;
import java.util.ArrayList;

public class Vendita {

    private static long contatore=0;
    private final long id;
    private List<Articolo> prodotti;

    public Vendita(List<Piatto> prodotti) {
        this.prodotti = new ArrayList<>(prodotti);
        id = contatore+1;
        contatore++;
    }

    public double prezzoTotale() {
        double totale = 0;
        for (Articolo a : prodotti) {
            totale += a.getPrezzo();
        }
        return totale;
    }

    public List<Articolo> getProdotti() {
        return new ArrayList<>(prodotti);
    }

    public long getId(){
        return id;
    }


}
