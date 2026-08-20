package Persone;

import Utility.Ruolo;

public class Addetto extends Persona {
    private Ruolo ruolo;

    public Addetto(String nome, String cognome, String codiceFiscale, Ruolo ruolo) {
        super(nome, cognome, codiceFiscale);
        this.ruolo = ruolo;
    }

    public Addetto(Addetto a) {
        super(a.getNome(),a.getCognome(),a.getCodiceFiscale());
        this.ruolo=a.getRuolo();
    }

    @Override
    public Ruolo getRuolo() {
        return ruolo;
    }
}
