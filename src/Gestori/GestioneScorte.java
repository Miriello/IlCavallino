package Gestori;

import Item.Ingrediente;

import java.util.HashMap;
import java.util.Map;

public class GestioneScorte {
    private Map<Ingrediente,Integer> magazzino = new HashMap<>();

    public void aggiungiArticolo(Ingrediente i, int q){
        if (q <=0)
            throw new IllegalArgumentException("La quantità non può essere negativa");
        if(!magazzino.containsKey(i)){
            magazzino.put(i, q);
        }
        int vecchiaQ = magazzino.get(i);
        magazzino.put(i, q+vecchiaQ);
        System.out.println("Quantità aggiornata");
    }

    public void rimuoviArticolo(Ingrediente i){
        if(!magazzino.containsKey(i)){
            throw new IllegalArgumentException("Ingrediente non presente in magazzino");
        }
        magazzino.remove(i);
    }

    public Map<Ingrediente, Integer> getMagazzino() {
        return new HashMap<>(magazzino);
    }

}
