package GUI;

import Database.DatabaseManager;
import Persone.Addetto;
import Persone.Persona;
import Persone.Socio;
import Utility.Account;
import Utility.Ruolo;

import java.util.ArrayList;
import java.util.List;

public class Autenticazione {

    public static Persona login(Account a) {
        Persona fromDb = DatabaseManager.autenticaUtente(a.username, a.password);
        if (fromDb != null)
            return fromDb;

        if (u.getUsername().equals(a.username) && u.getPassword().equals(a.password)) {
            return u;
        }
    }
    return null;
}

