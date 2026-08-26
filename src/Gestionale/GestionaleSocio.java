package Gestionale;

import Pannelli.*;
import Persone.Persona;
import javax.swing.*;

public class GestionaleSocio extends JFrame {

    public GestionaleSocio(Persona utente) {
        setTitle("Gestionale Socio — " + utente.getNome() + " " + utente.getCognome());
        setSize(950, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Ingredienti", new PannelloIngredienti());
        tabs.addTab("Piatti", new PannelloPiatti());
        tabs.addTab("Menu", new PannelloMenu(true));
        tabs.addTab("Magazzino", new PannelloScorte());
        tabs.addTab("Nuova Vendita", new PannelloVendite(utente));
        tabs.addTab("Storico Vendite", new PannelloStorico());
        tabs.addTab("Fornitori", new PannelloFornitori(true));
        tabs.addTab("Visualizza Anagrafica",new PannelloAnagrafico());
        add(tabs);
        setVisible(true);
    }
}