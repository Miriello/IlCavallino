package Pannelli;

import DAO.MenuDAO;
import DAO.PagamentoDAO;
import DAO.VenditaDAO;
import Item.Piatto;
import Persone.Persona;
import Utility.Pagamento;
import Utility.Vendita;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PannelloVendite extends JPanel{

    private JComboBox<String> prodottoCombo;
    private JSpinner quantitaSpinner;
    private DefaultTableModel carrelloModel;
    private JLabel prezzoLabel;
    private JLabel totaleLabel;
    private Gestori.Menu menuDelGiorno;
    private Map<Piatto,Integer> carrello = new HashMap<>();

    public PannelloVendite(Persona utente) {
        MenuDAO menuDAO = new MenuDAO();
        this.menuDelGiorno = menuDAO.findByData(LocalDate.now()) ;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        JPanel form = new JPanel(new GridLayout(6, 2, 8, 12));
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
        add(new JScrollPane(tabella),BorderLayout.CENTER);

        prodottoCombo = new JComboBox<>();
        if(menuDelGiorno!=null){
            for(Piatto p : menuDelGiorno.getProdotti().keySet()){
                prodottoCombo.addItem(p.getNome());
            }
        }
        else {
            JOptionPane.showMessageDialog(this,"Nessun piatto disponibile alla vendita oggi");
        }

        quantitaSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));
        prezzoLabel     = new JLabel("€ 0.00");
        totaleLabel     = new JLabel("€ 0.00");
        totaleLabel.setFont(totaleLabel.getFont().deriveFont(Font.BOLD, 14f));

        prodottoCombo.addActionListener(e -> aggiornaCalcolo());
        quantitaSpinner.addChangeListener(e -> aggiornaCalcolo());
        aggiornaCalcolo();

        JButton registraPiatto = new JButton("Aggiungi al carrello");
        registraPiatto.addActionListener(e -> aggiungiAllOrdine());

        JButton registraVendita = new JButton ("Registra vendita");
        registraVendita.addActionListener(e-> registraVendita(utente));

        form.add(new JLabel("Prodotto:"));        form.add(prodottoCombo);
        form.add(new JLabel("Quantità:"));        form.add(quantitaSpinner);
        form.add(new JLabel("Prezzo unitario:")); form.add(prezzoLabel);
        form.add(new JLabel("Totale:"));          form.add(totaleLabel);
        form.add(new JLabel(""));                 form.add(registraPiatto);
        form.add(new JLabel(""));                 form.add(registraVendita);
        add(form, BorderLayout.NORTH);
    }

    private void aggiornaCalcolo() {
        String nome = (String) prodottoCombo.getSelectedItem();
        if (nome == null || menuDelGiorno == null) return;
        for (Map.Entry<Piatto,Double> entry : menuDelGiorno.getProdotti().entrySet()){
            Piatto p = entry.getKey();
            if(p.getNome().equals(nome)){
                double prezzo = entry.getValue();
                int quantita = (int) quantitaSpinner.getValue();
                prezzoLabel.setText(String.format("€ %.2f", prezzo));
                totaleLabel.setText(String.format("€ %.2f", prezzo * quantita));
                return;
            }
        }
    }

    private void aggiornaCarrello(){
        carrelloModel.setRowCount(0);
        for(Map.Entry<Piatto,Integer> entry : carrello.entrySet()){
            Piatto p = entry.getKey();
            int quantita = entry.getValue();
            Double prezzo = menuDelGiorno.getProdotti().get(p);
            double subtotale = prezzo * quantita;
            carrelloModel.addRow(new Object[]{
                    p.getNome(),
                    quantita,
                    String.format("€ %.2f", prezzo),
                    String.format("€ %.2f", subtotale)
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
                break;
            }

        }
        aggiornaCarrello();
    }

    private void registraVendita(Persona utente){
        if(carrello.isEmpty()){
            JOptionPane.showMessageDialog(this,"Il carrello è vuoto");
            return;
        }
        double totale = calcolaTotale();

        String [] metodoPagamento = {"CONTANTI", "CARTA"};
        String metodo = (String) JOptionPane.showInputDialog(this,"Seleziona il metodo di pagamento: ",
                "Pagamento",
                JOptionPane.QUESTION_MESSAGE,
                null,
                metodoPagamento,
                metodoPagamento[0]);
        if (metodo == null){
            return ;
        }
        Vendita v = new Vendita(0, utente.getCodiceFiscale(),carrello);
        VenditaDAO venditaDAO = new VenditaDAO();
        int idVendita = venditaDAO.insert(v);
        Pagamento pagamento = new Pagamento(idVendita,totale,metodo);
        PagamentoDAO pagamentoDAO = new PagamentoDAO();
        pagamentoDAO.insert(pagamento);
        JOptionPane.showMessageDialog(this,"La vendita è stata registrata");
        carrello.clear();
        aggiornaCarrello();
    }

    private double calcolaTotale(){
        double totale = 0;
        for (Map.Entry<Piatto,Integer> entry : carrello.entrySet()){
            Piatto p = entry.getKey();
            int quantita = entry.getValue();
            double prezzo = menuDelGiorno.getProdotti().get(p);
            totale += prezzo * quantita;
        }
        return totale;
    }


}
