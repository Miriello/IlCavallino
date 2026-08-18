package Persone;

import Utility.Account;
import Utility.Ruolo;

public abstract class Persona {
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private Account account;

    public Persona(String nome, String cognome, String codiceFiscale) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
    }

    public Persona(String nome, String cognome, String codiceFiscale, Account a){
        this.nome=nome;
        this.cognome=cognome;
        this.codiceFiscale= codiceFiscale;
        this.account=a;
    }

    public abstract Ruolo getRuolo();

    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public String getCodiceFiscale() { return codiceFiscale; }

}
