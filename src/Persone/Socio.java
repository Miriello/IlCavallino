package Persone;

import Utility.Account;
import Utility.Ruolo;

public class Socio extends Persona {

    public Socio(String nome, String cognome, String codiceFiscale, Account account) {
        super(nome, cognome, codiceFiscale, account);
    }

    @Override
    public Ruolo getRuolo() {
        return Ruolo.SOCIO; }
}
