package Persone;

import Utilità.Ruolo;

public class Addetto extends Persona {
    private Ruolo ruolo;

    public Addetto(String nome, String cognome, String codiceFiscale, String username, String password, Ruolo ruolo) {
        super(nome, cognome, codiceFiscale, username, password);
        this.ruolo = ruolo;
    }

    @Override
    public Ruolo getRuolo() {
        return ruolo;
    }
}
