package Main;

import Database.DatabaseManager;
import GUI.PaginaLogin;

import javax.swing.SwingUtilities;

public class main {
    public static void main(String[] args) {
        DatabaseManager.connetti(); //
        SwingUtilities.invokeLater(PaginaLogin::new);
    }
}
