package Gestori;

import Item.Piatto;

import java.util.*;

public class Menu {
    private int id;
    private Map<Piatto,Double> piatti = new HashMap<>();
    private Date data;

    public Menu(int id,Map<Piatto,Double> piatti, Date data){
        this.id=id;
        this.piatti=new HashMap<>(piatti);
        this.data=data;
    }

    public void aggiungiProdotto(Piatto p, Double prezzo) {
        piatti.put(p,prezzo);
    }

    public void rimuoviProdotto(Piatto p) {
        if (!piatti.containsKey(p)){
            throw new IllegalArgumentException("Prodotto non ancora aggiunto");
        }
        piatti.remove(p);
    }


    public Map<Piatto,Double> getProdotti() {
        return new HashMap<>(piatti);
    }

    public int getId(){
        return id;
    }

    public Date getData(){
        return data;
    }

}
