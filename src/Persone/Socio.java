package Persone;

import Utilità.Ruolo;

public class Socio extends Persona {

    public Socio(String nome, String cognome, String codiceFiscale, String username, String password) {
        super(nome, cognome, codiceFiscale, username, password);
    }

    @Override
    public Ruolo getRuolo() { return Ruolo.SOCIO; }
}
