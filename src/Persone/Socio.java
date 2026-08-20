package Persone;

import Utility.Account;
import Utility.Ruolo;

public class Socio extends Persona {

    public Socio(String nome, String cognome, String codiceFiscale) {
        super(nome, cognome, codiceFiscale);
    }

    @Override
    public Ruolo getRuolo() {
        return Ruolo.SOCIO; }
}
