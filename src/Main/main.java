package Main;

import Database.DatabaseManager;
import Servizi.PaginaLogin;

import javax.swing.SwingUtilities;

public class main {
    public static void main(String[] args) {
        if(!DatabaseManager.connetti()) {
            System.err.println("Impossibile inviare l'applicazione, connessione al DB fallita.");
            return;
        }
        SwingUtilities.invokeLater(PaginaLogin::new);
    }
}
