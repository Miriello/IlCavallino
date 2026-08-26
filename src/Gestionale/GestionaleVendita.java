package Gestionale;


import Pannelli.PannelloStorico;
import Pannelli.PannelloVendite;
import Persone.Persona;
import javax.swing.*;

public class GestionaleVendita extends JFrame {

    private Persona utente;

    public GestionaleVendita(Persona utente) {
        this.utente = utente;
        setTitle("Gestionale Vendita — " + utente.getNome() + " " + utente.getCognome());
        setSize(750, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Nuova Vendita",new PannelloVendite(utente));
        tabs.addTab("Storico Vendite", new PannelloStorico());
        add(tabs);
        setVisible(true);
    }













}
