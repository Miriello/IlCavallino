package Gestionale;


import Pannelli.PannelloFornitori;
import Pannelli.PannelloScorte;
import Persone.Persona;
import javax.swing.*;


public class GestionaleMagazzino extends JFrame {

    public GestionaleMagazzino(Persona utente) {
        setTitle("Gestionale Magazzino — " + utente.getNome() + " " + utente.getCognome());
        setSize(800, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Scorte Magazzino",   new PannelloScorte());
        tabs.addTab("Fornitori", new PannelloFornitori());

        add(tabs);
        setVisible(true);
    }

}
