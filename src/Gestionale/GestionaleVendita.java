package Gestionale;

import Cose.Prodotto;
import Persone.Persona;
import Registri.Vendita;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionaleVendita extends JFrame {

    private JComboBox<String> prodottoCombo;
    private JSpinner quantitaSpinner;
    private JLabel prezzoLabel;
    private JLabel totaleLabel;
    private DefaultTableModel storicoModel;

    public GestionaleVendita(Persona utente) {
        setTitle("Gestionale Vendita — " + utente.getNome() + " " + utente.getCognome());
        setSize(750, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Nuova Vendita",      creaPanelNuovaVendita());
        tabs.addTab("Storico Giornaliero", creaPanelStorico());

        add(tabs);
        setVisible(true);
    }

    private JPanel creaPanelNuovaVendita() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        JPanel form = new JPanel(new GridLayout(5, 2, 8, 12));

        prodottoCombo = new JComboBox<>();
        for (Prodotto p : AppData.MENU.getProdotti()) prodottoCombo.addItem(p.getNome());

        quantitaSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));
        prezzoLabel     = new JLabel("€ 0.00");
        totaleLabel     = new JLabel("€ 0.00");
        totaleLabel.setFont(totaleLabel.getFont().deriveFont(Font.BOLD, 14f));

        prodottoCombo.addActionListener(e -> aggiornaCalcolo());
        quantitaSpinner.addChangeListener(e -> aggiornaCalcolo());
        aggiornaCalcolo();

        JButton registraBtn = new JButton("Registra Vendita");
        registraBtn.setBackground(new Color(46, 139, 87));
        registraBtn.setForeground(Color.WHITE);
        registraBtn.setFont(registraBtn.getFont().deriveFont(Font.BOLD, 13f));
        registraBtn.addActionListener(e -> registraVendita());

        form.add(new JLabel("Prodotto:"));        form.add(prodottoCombo);
        form.add(new JLabel("Quantità:"));        form.add(quantitaSpinner);
        form.add(new JLabel("Prezzo unitario:")); form.add(prezzoLabel);
        form.add(new JLabel("Totale:"));          form.add(totaleLabel);
        form.add(new JLabel(""));                 form.add(registraBtn);

        panel.add(form, BorderLayout.NORTH);
        return panel;
    }

    private JPanel creaPanelStorico() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] col = {"Prodotto", "Quantità", "Prezzo Unit.", "Totale"};
        storicoModel = new DefaultTableModel(col, 0);
        for (Vendita v : AppData.VENDITE.getVendite()) {
            storicoModel.addRow(new Object[]{v.getProdotto().getNome(), v.getQuantita(),
                    String.format("€ %.2f", v.getProdotto().getPrezzo()),
                    String.format("€ %.2f", v.prezzoTotale())});
        }

        JLabel incassoLabel = new JLabel(String.format("  Incasso: € %.2f", AppData.VENDITE.totaleVendite()));
        incassoLabel.setFont(incassoLabel.getFont().deriveFont(Font.BOLD));

        panel.add(new JScrollPane(new JTable(storicoModel)), BorderLayout.CENTER);
        panel.add(incassoLabel, BorderLayout.SOUTH);
        return panel;
    }

    private void aggiornaCalcolo() {
        String nome = (String) prodottoCombo.getSelectedItem();
        if (nome == null) return;
        Prodotto p = AppData.MENU.cercaProdotto(nome);
        if (p != null) {
            int qty = (int) quantitaSpinner.getValue();
            prezzoLabel.setText(String.format("€ %.2f", p.getPrezzo()));
            totaleLabel.setText(String.format("€ %.2f", p.getPrezzo() * qty));
        }
    }

    private void registraVendita() {
        String nome = (String) prodottoCombo.getSelectedItem();
        if (nome == null) return;
        Prodotto p = AppData.MENU.cercaProdotto(nome);
        if (p == null) { JOptionPane.showMessageDialog(this, "Prodotto non trovato."); return; }
        int qty = (int) quantitaSpinner.getValue();
        Vendita v = new Vendita(p, qty);
        AppData.VENDITE.aggiungiVendita(v);
        storicoModel.addRow(new Object[]{p.getNome(), qty,
                String.format("€ %.2f", p.getPrezzo()),
                String.format("€ %.2f", v.prezzoTotale())});
        JOptionPane.showMessageDialog(this, "Vendita registrata:\n" + v);
    }
}
