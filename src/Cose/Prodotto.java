package Cose;

import Utilità.Data;
import java.util.ArrayList;
import java.util.List;

public class Prodotto implements Articolo {
    private String nome;
    private double prezzo;
    private List<String> ingredienti;
    private Data scadenza;

    public Prodotto(String nome, double prezzo, List<String> ingredienti, Data scadenza) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.ingredienti = new ArrayList<>(ingredienti);
        this.scadenza = new Data(scadenza);
    }

    public String getNome() { return nome; }
    public double getPrezzo() { return prezzo; }
    public List<String> getIngredienti() { return ingredienti; }
    public Data getScadenza() { return scadenza; }

    @Override
    public String toString() {
        return nome + " €" + String.format("%.2f", prezzo) + " - " + ingredienti;
    }
}
