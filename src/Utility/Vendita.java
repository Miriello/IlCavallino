package Utility;

import Item.Piatto;

import java.util.HashMap;
import java.util.Map;

public class Vendita {

    private int id;
    private String cfoperatore;
    private Map<Piatto, Integer> prodotti;

    public Vendita(int id ,String cfoperatore, Map<Piatto> prodotti) {
        this.id=id;
        this.cfoperatore=cfoperatore;
        this.prodotti = new HashMap<>(prodotti);
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

    public int getId(){
        return id;
    }

    public String getOperatore(){
        return cfoperatore;
    }

}
