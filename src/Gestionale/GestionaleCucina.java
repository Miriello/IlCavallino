package Gestionale;

import Pannelli.PannelloIngredienti;
import Pannelli.PannelloMenu;
import Persone.Persona;
import javax.swing.*;

public class GestionaleCucina extends JFrame {

    public GestionaleCucina(Persona utente) {
        setTitle("Gestionale Cucina — " + utente.getNome() + " " + utente.getCognome());
        setSize(750, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Menu del Giorno", new PannelloMenu(true));
        tabs.addTab("Ingredienti in Magazzino", new PannelloIngredienti());

        add(tabs);
        setVisible(true);
    }
}
