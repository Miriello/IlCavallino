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
        else {
            int vecchiaQ = magazzino.get(i);
            magazzino.put(i, q + vecchiaQ);
            System.out.println("Quantità aggiornata");
        }
    }

    public void rimuoviArticolo(Ingrediente i){
        if(!magazzino.containsKey(i)){
            throw new IllegalArgumentException("Ingrediente non presente in magazzino");
        }
        magazzino.remove(i);
    }

    public void prendiArticolo(Ingrediente i, int qntRichiesta){
        if(qntRichiesta<=0){
            throw new IllegalArgumentException("La quantità richiesta non può essere negativa");
        }
        if(magazzino.containsKey(i)) {
            int qntMagazzino = magazzino.get(i);
            if (qntMagazzino < qntRichiesta) {
                throw new IllegalArgumentException("La quantità richiesta eccede le scorte");
            }
            magazzino.put(i, qntMagazzino - qntRichiesta);
        }
        else{
            System.out.println("Il prodotto non è presente in magazzino");
        }
    }

    public Map<Ingrediente, Integer> getMagazzino() {
        return new HashMap<>(magazzino);
    }

}
