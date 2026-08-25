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
