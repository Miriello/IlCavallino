package Gestori;

import Item.Articolo;

import java.util.ArrayList;
import java.util.List;

public class GestioneScorte {
    private List<Articolo> magazzino = new ArrayList<>();

    public void aggiungiArticolo(Articolo a){
        magazzino.add(a);
    }
    public void rimuoviArticolo(Articolo a){
        if(!magazzino.contains(a)){
            throw new IllegalArgumentException("Articolo non presente in magazzino");
        }
        magazzino.remove(a);
    }

    public List<Articolo> getMagazzino() {
        return new ArrayList<>(magazzino);
    }
}
