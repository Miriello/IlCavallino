package Persone;

import Utility.Account;
import Utility.Ruolo;

public class Addetto extends Persona {
    private Ruolo ruolo;

    public Addetto(String nome, String cognome, String codiceFiscale, Ruolo ruolo) {
        super(nome, cognome, codiceFiscale);
        this.ruolo = ruolo;
    }

    public Addetto(String nome, String cognome, String codiceFiscale, Ruolo ruolo, Account a) {
        super(nome, cognome, codiceFiscale, a);
        this.ruolo = ruolo;
    }

    @Override
    public Ruolo getRuolo() {
        return ruolo;
    }
}
