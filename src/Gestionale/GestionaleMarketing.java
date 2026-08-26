package Gestionale;

import Pannelli.PannelloMenu;
import Pannelli.PannelloStorico;
import Persone.Persona;
import javax.swing.*;

public class GestionaleMarketing extends JFrame {

    public GestionaleMarketing(Persona utente) {
        setTitle("Gestionale Marketing — " + utente.getNome() + " " + utente.getCognome());
        setSize(800, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Report Vendite", new PannelloStorico());
        tabs.addTab("Menu", new PannelloMenu(false));

        add(tabs);
        setVisible(true);
    }
}