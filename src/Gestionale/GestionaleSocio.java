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
        tabs.addTab("Registra Vendita", new PannelloVendite(utente));
        tabs.addTab("Visualizza Menu", new PannelloMenu());
        tabs.addTab("Visualizza Magazzino", new PannelloScorte());
        tabs.addTab("Visualizza Fornitori", new PannelloFornitori());
        tabs.addTab("Visualizza Storico", new PannelloStorico());

        add(tabs);
        setVisible(true);
    }
}