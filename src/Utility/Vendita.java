package Utility;

import Item.Piatto;
import Persone.Persona;

import java.util.List;
import java.util.ArrayList;

public class Vendita {

    private int id;
    private String cfoperatore;
    private List<Piatto> prodotti;
    private int quantita;

    public Vendita(int id ,String cfoperatore, List<Piatto> prodotti,int quantita) {
        this.id=id;
        this.cfoperatore=cfoperatore;
        this.prodotti = new ArrayList<>(prodotti);
        this.quantita=quantita;
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

    public int getQuantita(){
        return quantita;
    }
}
