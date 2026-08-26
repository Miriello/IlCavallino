package Item;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ingrediente implements Articolo {

    private String nome;
    private LocalDate scadenza;
    private List<Allergene> allergeni;
    private int id;

    public Ingrediente(String nome, LocalDate scadenza, List<Allergene> allergeni, int id) {
        this.nome = nome;
        this.scadenza = scadenza;
        this.allergeni= new ArrayList<>(allergeni);
        this.id=id;
    }

    public Ingrediente(Ingrediente i) {
        this.nome = i.nome;
        this.scadenza = i.scadenza;
        this.allergeni=new ArrayList<>(i.allergeni);
        this.id=i.id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getScadenza() {
        return scadenza;
    }

    public List<Allergene> getAllergeni() {
        return new ArrayList<>(allergeni);
    }

    public int getId(){
        return id;
    }

    @Override
    public int hashCode() {
        int M = 17;
        M = 19 * M + nome.hashCode();
        M = 19 * M + scadenza.hashCode();
        return M;
    }

    public boolean equals(Object o){
        if (o==null) return false;
        if(!(o instanceof Ingrediente))
          return false;
        if(o==this)
          return true;
        Ingrediente c = (Item.Ingrediente) o;
        return c.nome.equals(this.nome) && c.scadenza.equals(this.scadenza);
    }

    public String toString(){
        return nome;
    }

}
