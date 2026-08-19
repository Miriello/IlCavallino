package Item;

import java.util.ArrayList;
import java.util.List;

public class Piatto implements Articolo {

    private String nome;
    private double prezzo;
    private List<Ingrediente> ingredienti;

    public Piatto(String nome, double prezzo, List<Ingrediente> ingredienti) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.ingredienti = new ArrayList<>(ingredienti);
    }

    public Piatto(Piatto p) {
        this.nome = p.nome;
        this.prezzo = p.prezzo;
        this.ingredienti = new ArrayList<>(p.ingredienti);
    }

    public String getNome() {
        return nome;
    }
    public double getPrezzo() {
        return prezzo;
    }
    public List<Ingrediente> getIngredienti() {
        return new ArrayList<>(ingredienti);
    }

    public List<Allergene> getAllergeni() {
        List<Allergene> listaAllergeni = new ArrayList<>();
         for(Ingrediente i : ingredienti){
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

    public boolean equals(Object o){
        if(o==null) return false;
        if(!(o instanceof Piatto))
            return false;
        if(o==this) return true;
        Piatto p = (Piatto) o;
        return p.nome == this.nome;
    }
}
