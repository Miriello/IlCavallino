package Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Piatto implements Articolo {

    private String nome;
    private int id;
    private Map<Ingrediente, Double> ingredienti;

    public Piatto(String nome, int id, Map<Ingrediente,Double> ingredienti) {
        this.nome = nome;
        this.id=id;
        this.ingredienti = new HashMap<>(ingredienti);
    }

    public Piatto(Piatto p) {
        this.nome = p.nome;
        this.id=p.id;
        this.ingredienti = new HashMap<>(p.ingredienti);
    }

    public String getNome() {
        return nome;
    }

    public int getId(){
        return id;
    }


    public List<Allergene> getAllergeni() {
        List<Allergene> listaAllergeni = new ArrayList<>();
         for(Ingrediente i : ingredienti.keySet()){
             for(Allergene a : i.getAllergeni()){
                 listaAllergeni.add(a);
             }
        }
         return listaAllergeni;
    }

    public Map<Ingrediente, Double> getIngredienti() {
        return new HashMap<>(ingredienti);
    }

    @Override
    public String toString() {
        return nome;
    }

    public boolean equals(Object o){
        if(o==null) return false;
        if(!(o instanceof Piatto))
            return false;
        if(o==this) return true;
        Piatto p = (Piatto) o;
        return p.nome.equals(this.nome);
    }

    public int hashCode(){
        return Integer.hashCode(id);
    }
}
