package Gestori;
import Cose.Prodotto;

import java.util.ArrayList;
import java.util.List;

public class GestioneMenu {
    private List<Prodotto> menu = new ArrayList<>();
    public void addProdotto(Prodotto p) {
        menu.add(p);
    }
    public void rimuoviProdotto(Prodotto p) {
        if (!menu.contains(p)){
            throw new IllegalArgumentException("Prodotto non ancora aggiunto");
        }
        menu.remove(p);
    }
    public void stampaMenu() {
        System.out.println("MENU DEL GIORNO : ");
        for (Prodotto p : menu) {
            System.out.println(p);
        }
    }
    public Prodotto cercaProdotto(String nome){
        for (Prodotto p : menu) {
            if (p.getNome().equals(nome)){
                return p;
            }
        }
        return null;
    }

    public List<Prodotto> getProdotti() {
        return new ArrayList<>(menu);
    }

}
