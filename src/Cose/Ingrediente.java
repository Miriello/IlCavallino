package Cose;

import Utilità.Data;

import java.util.List;

public class Ingrediente implements Articolo {

    private String nome;
    private Data scadenza;
    private List<Allergene> allergeni;

    public Ingrediente(String nome, Data scadenza, List<Allergene> allergeni) {
        this.nome = nome;
        this.scadenza = new Data(scadenza);
        this.allergeni= allergeni;

    }

    public Ingrediente(Ingrediente i) {
        this.nome = i.nome;
        this.scadenza = scadenza;
        this.allergeni=i.allergeni;
    }

    public String getNome() {
        return nome;
    }
    public Data getScadenza() {
        return scadenza;
    }

    public List<Allergene> getAllergeni() {
        return allergeni;
    }
}
