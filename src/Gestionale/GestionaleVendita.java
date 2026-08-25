package Gestionale;

import DAO.MenuDAO;
import Item.Piatto;
import Persone.Persona;
import Utility.Vendita;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class GestionaleVendita extends JFrame {

    private JComboBox<String> prodottoCombo;
    private JSpinner quantitaSpinner;
    private JLabel prezzoLabel;
    private JLabel totaleLabel;
    private DefaultTableModel storicoModel;
    private DefaultTableModel carrelloModel;
    private Persona utente;
    private Gestori.Menu menuDelGiorno;
    private Map<Piatto,Integer> carrello = new HashMap<>();


    public GestionaleVendita(Persona utente) {
        this.utente = utente;

        MenuDAO menuDAO = new MenuDAO();
        this.menuDelGiorno = menuDAO.findByData(LocalDate.now()) ;
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
        String [] colonne = {
                "Piatto", "Quantità", "Prezzo","Totale"
        };

        carrelloModel = new DefaultTableModel(colonne,0){
            @Override
            public boolean isCellEditable(int r, int c){
                return false;
            }
        };

        JTable tabella = new JTable(carrelloModel);
        panel.add(new JScrollPane(tabella),BorderLayout.CENTER);

        prodottoCombo = new JComboBox<>();
        if(menuDelGiorno!=null){
            for(Piatto p : menuDelGiorno.getProdotti().keySet()){
                prodottoCombo.addItem(p.getNome());
            }
        }

        quantitaSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));
        prezzoLabel     = new JLabel("€ 0.00");
        totaleLabel     = new JLabel("€ 0.00");
        totaleLabel.setFont(totaleLabel.getFont().deriveFont(Font.BOLD, 14f));

        prodottoCombo.addActionListener(e -> aggiornaCalcolo());
        quantitaSpinner.addChangeListener(e -> aggiornaCalcolo());
        aggiornaCalcolo();

        JButton registraBtn = new JButton("Aggiungi al carrello");
        registraBtn.setBackground(new Color(46, 139, 87));
        registraBtn.setForeground(Color.WHITE);
        registraBtn.setFont(registraBtn.getFont().deriveFont(Font.BOLD, 13f));
        registraBtn.addActionListener(e -> aggiungiAllOrdine());

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
        if (nome == null || menuDelGiorno == null) return;
        for (Map.Entry<Piatto,Double> entry : menuDelGiorno.getProdotti().entrySet()){
            Piatto p = entry.getKey();
            if(p.getNome().equals(nome)){
                double prezzo = entry.getValue();
                int quantita = (int) quantitaSpinner.getValue();
                prezzoLabel.setText(String.format("€ %2.f", prezzo));
                totaleLabel.setText(String.format("€ %2.f", prezzo * quantita));
                return;
            }
        }
    }

    private void aggiornaCarrello(){
        carrelloModel.setRowCount(0);
        for(Map.Entry<Piatto,Integer> entry ; menuDelGiorno.getProdotti().entrySet()){
            Piatto p = entry.getKey();
            int quantita = entry.getValue();
            Double prezzo = menuDelGiorno.getProdotti().get(p);
            double subtotale = prezzo * quantita;
            carrelloModel.addRow(new Object[]{
                    p.getNome(),
                    quantita,
                    String.format("€ %2.f", prezzo),
                    String.format("€ %2.f", subtotale)
            });
        }
    }

    private void aggiungiAllOrdine() {
        String nome = (String) prodottoCombo.getSelectedItem();
        if (nome == null || menuDelGiorno == null) return;
        int quantita = (int) quantitaSpinner.getValue();
        for (Piatto p : menuDelGiorno.getProdotti().keySet()) {
            if (p.getNome().equals(nome)) {
                carrello.put(p, carrello.getOrDefault(p, 0) + quantita);
                JOptionPane.showMessageDialog(this, "Aggiunto all'ordine: " + quantita + "x" + p.getNome());

            }
            aggiornaCarrello();
        }
    }
}
