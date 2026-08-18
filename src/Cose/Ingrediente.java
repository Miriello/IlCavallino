package Cose;

import Utilità.Data;

public class Ingrediente implements Articolo {
    private String nome;
    private Data scadenza;

    public Ingrediente(String nome, Data scadenza) {
        this.nome = nome;
        this.scadenza = new Data(scadenza);
    }

    public Ingrediente(Ingrediente i) {
        this.nome = i.nome;
        this.scadenza = new Data(i.scadenza);
    }

    public String getNome() { return nome; }
    public Data getScadenza() { return scadenza; }
}
