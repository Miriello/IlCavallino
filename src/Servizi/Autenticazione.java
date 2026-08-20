package Servizi;

import Database.DatabaseManager;
import Persone.Persona;

public class Autenticazione {

    public static Persona login (String username, String password) {
        Persona fromDb = DatabaseManager.autenticaUtente(username, password);
        if (fromDb != null)
            return fromDb;
        return null;
    }
}