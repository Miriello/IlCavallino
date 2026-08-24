package Database;

import Persone.Addetto;
import Persone.Persona;
import Persone.Socio;
import Utility.Ruolo;

import java.sql.*;

public class DatabaseManager {
    private static Connection connessione;

    private static final String DB_URL      = System.getProperty("db.url",      "jdbc:mysql://localhost:3306/ilcavallino");
    private static final String DB_USER     = System.getProperty("db.user",     "root");
    private static final String DB_PASSWORD = System.getProperty("db.password", "");

    public static boolean connetti() {
        try {
            connessione = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("DB Connessione stabilita: " + DB_URL);
            return true;
        } catch (SQLException e) {
            System.out.println("Connessione al DB non disponibile (" + e.getMessage() + ")");
            return false;
        }
    }

    public static Persona autenticaUtente(String username, String password) {
        if (connessione == null) return null;
        String sql = "SELECT * FROM utenti WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nome     = rs.getString("nome");
                String cognome  = rs.getString("cognome");
                String cf       = rs.getString("codice_fiscale");
                Ruolo  ruolo    = Ruolo.valueOf(rs.getString("ruolo"));
                if (ruolo == Ruolo.SOCIO) {
                    return new Socio(nome, cognome, cf);
                } else {
                    return new Addetto(nome, cognome, cf, ruolo);
                }
            }
        } catch (SQLException e) {
            System.err.println("DB Errore autenticazione: " + e.getMessage());
        }
        return null;
    }

    public static Connection getConnessione() {
        return connessione; }

    public static void chiudi() {
        try {
            if (connessione != null && !connessione.isClosed()) connessione.close();
        } catch (SQLException e) {
            System.err.println("DB Errore chiusura: " + e.getMessage());
        }
    }
}
