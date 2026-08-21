package Persone;

import Utility.Ruolo;

public abstract class Persona {
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private int id;

    public Persona(String nome, String cognome, String codiceFiscale, int id) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.id=id;
    }

    public Persona(Persona p){
        this.nome=p.nome;
        this.cognome=p.cognome;
        this.codiceFiscale= p.codiceFiscale;
        this.id=p.id;
    }

    public abstract Ruolo getRuolo();

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

}
