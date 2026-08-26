package Main;

import Database.DatabaseManager;
import Item.Allergene;
import Servizi.PaginaLogin;
import DAO.AllergeneDAO;
import javax.swing.*;
import java.awt.*;

public class main {
    public static void main(String[] args) {
        if(!DatabaseManager.connetti()) {
            System.err.println("Impossibile inviare l'applicazione, connessione al DB fallita.");
            return;
        }
        UIManager.put("Button.foreground", Color.BLACK);
        inizializzaAllergeni();
        SwingUtilities.invokeLater(PaginaLogin::new);
    }

    private static void inizializzaAllergeni() {

        AllergeneDAO allergeneDAO = new AllergeneDAO();

        if (!allergeneDAO.findAll().isEmpty()) {
            return;
        }

        allergeneDAO.insert(new Allergene("Glutine", 1));
        allergeneDAO.insert(new Allergene("Crostacei", 2));
        allergeneDAO.insert(new Allergene("Uova", 3));
        allergeneDAO.insert(new Allergene("Pesce", 4));
        allergeneDAO.insert(new Allergene("Arachidi", 5));
        allergeneDAO.insert(new Allergene("Soia", 6));
        allergeneDAO.insert(new Allergene("Latte", 7));
        allergeneDAO.insert(new Allergene("Frutta a guscio", 8));
        allergeneDAO.insert(new Allergene("Sedano", 9));
        allergeneDAO.insert(new Allergene("Senape", 10));
        allergeneDAO.insert(new Allergene("Sesamo", 11));
        allergeneDAO.insert(new Allergene("Solfiti", 12));
        allergeneDAO.insert(new Allergene("Lupini", 13));
        allergeneDAO.insert(new Allergene("Molluschi", 14));
    }

}
