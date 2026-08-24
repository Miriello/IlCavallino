package Utility;

import Item.Piatto;
import java.util.HashMap;
import java.util.Map;

public class Vendita {

    private int id;
    private String cfoperatore;
    private Map<Piatto, Integer> prodotti;

    public Vendita(int id ,String cfoperatore, Map<Piatto,Integer> prodotti) {
        this.id=id;
        this.cfoperatore=cfoperatore;
        this.prodotti = new HashMap<>(prodotti);
    }

    public double prezzoTotale() {
        double totale = 0;
        for (Map.Entry<Piatto,Integer> entry: prodotti.entrySet()){
            totale+= entry.getKey().getPrezzo()*entry.getValue();
        }
        return totale;
    }

    public Map<Piatto,Integer> getProdotti() {
        return new HashMap<>(prodotti);
    }

    public int getId(){
        return id;
    }

    public String getOperatore(){
        return cfoperatore;
    }

}
