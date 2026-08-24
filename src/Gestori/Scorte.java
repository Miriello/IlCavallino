package Gestori;

import Item.Ingrediente;
import java.util.HashMap;
import java.util.Map;

public class Scorte {

    private Map<Ingrediente,Double> scorte = new HashMap<>();

    public void aggiungiArticolo(Ingrediente i, double q){
        if (q <=0)
            throw new IllegalArgumentException("La quantità non può essere negativa");
        if(!scorte.containsKey(i)){
            scorte.put(i, q);
        }
        else {
            Double vecchiaQ = scorte.get(i);
            scorte.put(i, q + vecchiaQ);
            System.out.println("Quantità aggiornata");
        }
    }

    public void rimuoviArticolo(Ingrediente i){
        if(!scorte.containsKey(i)){
            throw new IllegalArgumentException("Ingrediente non presente in magazzino");
        }
        scorte.remove(i);
    }

    public void prendiArticolo(Ingrediente i, Double qntRichiesta){
        if(qntRichiesta<=0){
            throw new IllegalArgumentException("La quantità richiesta non può essere negativa");
        }
        if(scorte.containsKey(i)) {
            Double qntMagazzino = scorte.get(i);
            if (qntMagazzino < qntRichiesta) {
                throw new IllegalArgumentException("La quantità richiesta eccede le scorte");
            }
            scorte.put(i, qntMagazzino - qntRichiesta);
        }
        else{
            System.out.println("Il prodotto non è presente in magazzino");
        }
    }

    public Map<Ingrediente, Double> getMagazzino() {
        return new HashMap<>(scorte);
    }

}
