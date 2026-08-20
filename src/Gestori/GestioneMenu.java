package Gestori;

import Item.Piatto;
import java.util.ArrayList;
import java.util.List;

public class GestioneMenu {
    private List<Piatto> menu = new ArrayList<>();

    public void aggiungiProdotto(Piatto p) {
        menu.add(p);
    }

    public void rimuoviProdotto(Piatto p) {
        if (!menu.contains(p)){
            throw new IllegalArgumentException("Prodotto non ancora aggiunto");
        }
        menu.remove(p);
    }

    public void stampaMenu() {
        System.out.println("MENU DEL GIORNO : ");
        for (Piatto p : menu) {
            System.out.println(p);
        }
    }

    public Piatto cercaProdotto(String nome){
        for (Piatto p : menu) {
            if (p.getNome().equals(nome)){
                return p;
            }
        }
        return null;
    }

    public List<Piatto> getProdotti() {
        return new ArrayList<>(menu);
    }

}
