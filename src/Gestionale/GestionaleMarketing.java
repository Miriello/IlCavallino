package Gestionale;

import Cose.Prodotto;
import Persone.Persona;
import Registri.Vendita;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class GestionaleMarketing extends JFrame {

    public GestionaleMarketing(Persona utente) {
        setTitle("Gestionale Marketing — " + utente.getNome() + " " + utente.getCognome());
        setSize(800, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Report Vendite", creaPanelReport());
        tabs.addTab("Analisi Menu",   creaPanelMenu());

        add(tabs);
        setVisible(true);
    }

    private JPanel creaPanelReport() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] col = {"Prodotto", "Qty Venduta", "Incasso"};
        DefaultTableModel model = new DefaultTableModel(col, 0);

        Map<String, double[]> aggregato = new LinkedHashMap<>();
        for (Vendita v : AppData.VENDITE.getVendite()) {
            aggregato.computeIfAbsent(v.getProdotto().getNome(), k -> new double[]{0, 0});
            aggregato.get(v.getProdotto().getNome())[0] += v.getQuantita();
            aggregato.get(v.getProdotto().getNome())[1] += v.prezzoTotale();
        }
        for (Map.Entry<String, double[]> entry : aggregato.entrySet()) {
            model.addRow(new Object[]{entry.getKey(),
                    (int) entry.getValue()[0],
                    String.format("€ %.2f", entry.getValue()[1])});
        }

        JLabel totLabel = new JLabel(String.format(
                "  Incasso totale: € %.2f  |  Vendite: %d",
                AppData.VENDITE.totaleVendite(), AppData.VENDITE.getVendite().size()));
        totLabel.setFont(totLabel.getFont().deriveFont(Font.BOLD, 13f));

        JButton refreshBtn = new JButton("Aggiorna");
        refreshBtn.addActionListener(e -> {
            model.setRowCount(0);
            Map<String, double[]> ag = new LinkedHashMap<>();
            for (Vendita v : AppData.VENDITE.getVendite()) {
                ag.computeIfAbsent(v.getProdotto().getNome(), k -> new double[]{0, 0});
                ag.get(v.getProdotto().getNome())[0] += v.getQuantita();
                ag.get(v.getProdotto().getNome())[1] += v.prezzoTotale();
            }
            for (Map.Entry<String, double[]> en : ag.entrySet()) {
                model.addRow(new Object[]{en.getKey(), (int) en.getValue()[0],
                        String.format("€ %.2f", en.getValue()[1])});
            }
            totLabel.setText(String.format("  Incasso totale: € %.2f  |  Vendite: %d",
                    AppData.VENDITE.totaleVendite(), AppData.VENDITE.getVendite().size()));
        });

        JPanel south = new JPanel(new BorderLayout());
        south.add(totLabel, BorderLayout.WEST);
        south.add(refreshBtn, BorderLayout.EAST);

        panel.add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
        panel.add(south, BorderLayout.SOUTH);
        return panel;
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

        JLabel nota = new JLabel("  Analisi menu — sola lettura");
        nota.setForeground(Color.GRAY);

        panel.add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
        panel.add(nota, BorderLayout.SOUTH);
        return panel;
    }
}
