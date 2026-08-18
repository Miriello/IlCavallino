package GUI;

import Database.DatabaseManager;
import Persone.Addetto;
import Persone.Persona;
import Persone.Socio;
import Utility.Ruolo;

import java.util.ArrayList;
import java.util.List;

public class Autenticazione {

    private static final List<Persona> utentiDemo = new ArrayList<>();

    static {
        utentiDemo.add(new Socio("Mario",  "Rossi",   "RSSMRA80A01H501Z", "admin",      "admin123"));
        utentiDemo.add(new Addetto("Luigi", "Bianchi", "BNCLGU90B02H501Y", "cucina1",    "pass", Ruolo.CUCINA));
        utentiDemo.add(new Addetto("Anna",  "Verdi",   "VRDNNA85C03H501X", "vendita1",   "pass", Ruolo.VENDITA));
        utentiDemo.add(new Addetto("Marco", "Neri",    "NRIMRC75D04H501W", "magazzino1", "pass", Ruolo.MAGAZZINO));
        utentiDemo.add(new Addetto("Sara",  "Blu",     "BLUSRA70E05H501V", "marketing1", "pass", Ruolo.MARKETING));
    }

    public static Persona login(String username, String password) {
        Persona fromDb = DatabaseManager.autenticaUtente(username, password);
        if (fromDb != null) return fromDb;

        for (Persona u : utentiDemo) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}
