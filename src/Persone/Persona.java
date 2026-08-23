package Persone;

import Utility.Ruolo;

public class Persona {
    private String nome;
    private String cognome;
    private String codiceFiscale;

    public Persona(String nome, String cognome, String codiceFiscale) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
    }

    public Persona(Persona p){
        this.nome=p.nome;
        this.cognome=p.cognome;
        this.codiceFiscale= p.codiceFiscale;
    }


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
