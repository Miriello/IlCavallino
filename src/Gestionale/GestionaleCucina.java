package Gestionale;

import Item.Prodotto;
import Persone.Persona;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionaleCucina extends JFrame {

    public GestionaleCucina(Persona utente) {
        setTitle("Gestionale Cucina — " + utente.getNome() + " " + utente.getCognome());
        setSize(750, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Menu del Giorno", creaPanelMenu());
        tabs.addTab("Ingredienti in Magazzino", creaPanelIngredienti());

        add(tabs);
        setVisible(true);
    }

    private JPanel creaPanelMenu() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] col = {"Piatto", "Prezzo", "Ingredienti"};
        DefaultTableModel model = new DefaultTableModel(col, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        for (Prodotto p : AppData.MENU.getProdotti()) {
            model.addRow(new Object[]{p.getNome(), String.format("€ %.2f", p.getPrezzo()), p.getIngredienti()});
        }

        JLabel nota = new JLabel("  Visualizzazione sola lettura — modifiche dal pannello Socio");
        nota.setForeground(Color.GRAY);

        panel.add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
        panel.add(nota, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel creaPanelIngredienti() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] col = {"Ingrediente", "Tipo"};
        DefaultTableModel model = new DefaultTableModel(col, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        for (Articolo a : AppData.SCORTE.getMagazzino()) {
            model.addRow(new Object[]{a.getNome(), a.getClass().getSimpleName()});
        }
        panel.add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
        return panel;
    }
}
