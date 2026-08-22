package Item;

import java.util.ArrayList;
import java.util.List;

public class Piatto implements Articolo {

    private String nome;
    private int id;
    private double prezzo;
    private List<Ingrediente> ingredienti;

    public Piatto(String nome, int id, double prezzo, List<Ingrediente> ingredienti) {
        this.nome = nome;
        this.id=id;
        this.prezzo = prezzo;
        this.ingredienti = new ArrayList<>(ingredienti);
    }

    public Piatto(Piatto p) {
        this.nome = p.nome;
        this.id=p.id;
        this.prezzo = p.prezzo;
        this.ingredienti = new ArrayList<>(p.ingredienti);
    }

    public String getNome() {
        return nome;
    }

    public int getId(){
        return id;
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
