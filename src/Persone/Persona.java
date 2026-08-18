package Persone;

import Utilità.Ruolo;

public abstract class Persona {
    protected String nome;
    protected String cognome;
    protected String codiceFiscale;
    private String username;
    private String password;

    public Persona(String nome, String cognome, String codiceFiscale, String username, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.username = username;
        this.password = password;
    }

    public abstract Ruolo getRuolo();

    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public String getCodiceFiscale() { return codiceFiscale; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
