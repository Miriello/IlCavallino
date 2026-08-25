package Gestionale;

import Pannelli.*;
import Persone.Persona;
import javax.swing.*;
import java.awt.*;

public class GestionaleSocio extends JFrame {

    public GestionaleSocio(Persona utente) {
        setTitle("Gestionale Socio — " + utente.getNome() + " " + utente.getCognome());
        setSize(950, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Vendite", new PannelloVendite(utente));
        tabs.addTab("Menu", new PannelloMenu());
        tabs.addTab("Magazzino", new PannelloScorte());
        tabs.addTab("Fornitori", new PannelloFornitori());
        tabs.addTab("Storico", new PannelloStorico());

        add(tabs);
        setVisible(true);
    }
}