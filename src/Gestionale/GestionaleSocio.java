package Gestionale;

import Item.Ingrediente;
import Item.Piatto;
import Persone.Fornitore;
import Persone.Persona;
import Utility.Vendita;
import Utility.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GestionaleSocio extends JFrame {

    public GestionaleSocio(Persona utente) {
        setTitle("Gestionale Socio — " + utente.getNome() + " " + utente.getCognome());
        setSize(950, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Vendite",    creaPanelVendite());
        tabs.addTab("Menu",       creaPanelMenu());
        tabs.addTab("Magazzino",  creaPanelMagazzino());
        tabs.addTab("Fornitori",  creaPanelFornitori());
        tabs.addTab("Report",     creaPanelReport());

        add(tabs);
        setVisible(true);
    }

    private JPanel creaPanelVendite() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] col = {"Prodotto", "Quantità", "Totale"};
        DefaultTableModel model = new DefaultTableModel(col, 0);
        for (Vendita v : AppData.VENDITE.getVendite()) {
            model.addRow(new Object[]{v.getProdotti(),
                    String.format("€ %.2f", v.prezzoTotale())});
        }
        JTable table = new JTable(model);

        JLabel totLabel = new JLabel(String.format("  Incasso totale: € %.2f", AppData.VENDITE.totaleVendite()));
        totLabel.setFont(totLabel.getFont().deriveFont(Font.BOLD, 14f));

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(totLabel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel creaPanelMenu() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] col = {"Nome", "Prezzo", "Ingredienti"};
        DefaultTableModel model = new DefaultTableModel(col, 0);
        for (Piatto p : AppData.MENU.getProdotti()) {
            model.addRow(new Object[]{p.getNome(), String.format("€ %.2f", p.getPrezzo()), p.getIngredienti()});
        }
        JTable table = new JTable(model);

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 4));
        JTextField nomeF    = new JTextField(10);
        JTextField prezzoF  = new JTextField(5);
        JTextField ingF     = new JTextField(18);
        JButton addBtn      = new JButton("Aggiungi");
        JButton removeBtn   = new JButton("Rimuovi selezionato");

        addBtn.addActionListener(e -> {
            try {
                String nome = nomeF.getText().trim();
                if (nome.isEmpty()) return;
                double prezzo = Double.parseDouble(prezzoF.getText().trim());
                List<String> ing = Arrays.stream(ingF.getText().split(","))
                        .map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.toList());
                LocalDate now = LocalDate.now();
                Piatto p = new Piatto(nome, prezzo, ing, new Data(now.getDayOfMonth(), now.getMonthValue(), now.getYear()));
                AppData.MENU.aggiungiProdotto(p);
                model.addRow(new Object[]{nome, String.format("€ %.2f", prezzo), ing});
                nomeF.setText(""); prezzoF.setText(""); ingF.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Prezzo non valido.");
            }
        });

        removeBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) return;
            String nome = (String) model.getValueAt(row, 0);
            Piatto p = AppData.MENU.cercaProdotto(nome);
            if (p != null) AppData.MENU.rimuoviProdotto(p);
            model.removeRow(row);
        });

        form.add(new JLabel("Nome:")); form.add(nomeF);
        form.add(new JLabel("€")); form.add(prezzoF);
        form.add(new JLabel("Ingredienti (virgola):")); form.add(ingF);
        form.add(addBtn); form.add(removeBtn);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(form, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel creaPanelMagazzino() {
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
        JButton addBtn    = new JButton("Aggiungi ingrediente");
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

        form.add(new JLabel("Nome:")); form.add(nomeF);
        form.add(addBtn); form.add(removeBtn);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(form, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel creaPanelFornitori() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] col = {"Ragione Sociale", "P.IVA", "Email", "Città"};
        DefaultTableModel model = new DefaultTableModel(col, 0);
        for (Fornitore f : AppData.FORNITORI.getLista()) {
            model.addRow(new Object[]{f.getRagioneSociale(), f.getPartitaIva(),
                    f.getEmail(), f.getSede() != null ? f.getSede().getCitta() : ""});
        }
        panel.add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
        return panel;
    }

    private JPanel creaPanelReport() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

        JButton refreshBtn = new JButton("Aggiorna");
        refreshBtn.addActionListener(e -> area.setText(buildReport()));

        area.setText(buildReport());
        panel.add(new JScrollPane(area), BorderLayout.CENTER);
        panel.add(refreshBtn, BorderLayout.SOUTH);
        return panel;
    }

    private String buildReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== REPORT GIORNALIERO ===\n\n");
        sb.append(String.format("Vendite registrate : %d%n", AppData.VENDITE.getVendite().size()));
        sb.append(String.format("Incasso totale     : € %.2f%n%n", AppData.VENDITE.totaleVendite()));
        sb.append("--- Dettaglio vendite ---\n");
        for (Vendita v : AppData.VENDITE.getVendite()) sb.append("  ").append(v).append("\n");
        sb.append("\n--- Magazzino ---\n");
        sb.append(String.format("Articoli presenti  : %d%n", AppData.SCORTE.getMagazzino().size()));
        sb.append("\n--- Fornitori ---\n");
        for (Fornitore f : AppData.FORNITORI.getLista()) sb.append("  ").append(f).append("\n");
        return sb.toString();
    }
}
