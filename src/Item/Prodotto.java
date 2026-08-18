package Item;

import java.util.ArrayList;
import java.util.List;

public class Prodotto implements Articolo {

    private String nome;
    private double prezzo;
    private List<Ingrediente> ingredienti;

    public Prodotto(String nome, double prezzo, List<Ingrediente> ingredienti) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.ingredienti = new ArrayList<>(ingredienti);
    }

    public Prodotto(Prodotto p) {
        this.nome = p.nome;
        this.prezzo = p.prezzo;
        this.ingredienti = p.ingredienti;
    }

    public String getNome() {
        return nome;
    }
    public double getPrezzo() {
        return prezzo;
    }
    public List<Ingrediente> getIngredienti() {
        return ingredienti;
    }

    public List<Allergene> getAllergeni(Prodotto p) {
        List<Allergene> listaAllergeni = new ArrayList<>();
         for(Ingrediente i : p.getIngredienti()){
             for(Allergene a : i.getAllergeni()){
                 listaAllergeni.add(a);
             }
        }
         return listaAllergeni;
    }


    @Override
    public String toString() {
        return nome + " €" + String.format("%.2f", prezzo) + " - " + ingredienti;
    }
}
