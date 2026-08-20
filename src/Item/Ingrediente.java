package Item;

import Utility.Data;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

public class Ingrediente implements Articolo {

    private String nome;
    private Data scadenza;
    private List<Allergene> allergeni;

    public Ingrediente(String nome, Data scadenza, List<Allergene> allergeni) {
        this.nome = nome;
        this.scadenza = scadenza;
        this.allergeni= new ArrayList<>(allergeni);
    }

    public Ingrediente(Ingrediente i) {
        this.nome = i.nome;
        this.scadenza = i.scadenza;
        this.allergeni=new ArrayList<>(i.allergeni);
    }

    public String getNome() {
        return nome;
    }
    public Data getScadenza() {
        return scadenza;
    }

    public List<Allergene> getAllergeni() {
        return new ArrayList<>(allergeni);
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
}
