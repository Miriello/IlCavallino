package Gestionale;

import Item.Ingrediente;
import Persone.Fornitore;
import Persone.Persona;
import Utilità.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionaleMagazzino extends JFrame {

    public GestionaleMagazzino(Persona utente) {
        setTitle("Gestionale Magazzino — " + utente.getNome() + " " + utente.getCognome());
        setSize(800, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Scorte Magazzino",   creaPanelScorte());
        tabs.addTab("Fornitori & Ordini", creaPanelFornitori());

        add(tabs);
        setVisible(true);
    }

    private JPanel creaPanelScorte() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] col = {"Articolo", "Tipo"};
        DefaultTableModel model = new DefaultTableModel(col, 0);
        for (Articolo a : AppData.SCORTE.getMagazzino()) {
            model.addRow(new Object[]{a.getNome(), a.getClass().getSimpleName()});
        }
        JTable table = new JTable(model);

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 4));
        JTextField nomeF  = new JTextField(14);
        JButton addBtn    = new JButton("Aggiungi");
        JButton removeBtn = new JButton("Rimuovi selezionato");

        addBtn.addActionListener(e -> {
            String nome = nomeF.getText().trim();
            if (nome.isEmpty()) return;
            Ingrediente ing = new Ingrediente(nome, new Data(31, 12, 2026));
            AppData.SCORTE.aggiungiArticolo(ing);
            model.addRow(new Object[]{nome, "Ingrediente"});
            nomeF.setText("");
        });

        removeBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) model.removeRow(row);
        });

        form.add(new JLabel("Articolo:")); form.add(nomeF);
        form.add(addBtn); form.add(removeBtn);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(form, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel creaPanelFornitori() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] col = {"Fornitore", "P.IVA", "Email", "Città"};
        DefaultTableModel model = new DefaultTableModel(col, 0);
        for (Fornitore f : AppData.FORNITORI.getLista()) {
            model.addRow(new Object[]{f.getRagioneSociale(), f.getPartitaIva(),
                    f.getEmail(), f.getSede() != null ? f.getSede().getCitta() : ""});
        }
        JTable table = new JTable(model);

        JButton ordinaBtn = new JButton("Invia Ordine al Fornitore selezionato");
        ordinaBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) { JOptionPane.showMessageDialog(this, "Seleziona un fornitore."); return; }
            String nome = (String) model.getValueAt(row, 0);
            String email = (String) model.getValueAt(row, 2);
            JOptionPane.showMessageDialog(this,
                    "Ordine inviato a: " + nome + "\nEmail: " + email +
                    "\n\n(In produzione: query INSERT su tabella ordini + invio email via SMTP)");
        });

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(ordinaBtn, BorderLayout.SOUTH);
        return panel;
    }
}
