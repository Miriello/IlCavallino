package Persone;

import Utility.Ruolo;

public class Persona {
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private Ruolo ruolo;

    public Persona(String nome, String cognome, String codiceFiscale, Ruolo ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.ruolo=ruolo;
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

    public Ruolo getRuolo(){
        return ruolo;
    }
}
