package Gestionale;

import DAO.IngredienteDAO;
import DAO.ScorteDAO;
import Item.Ingrediente;
import Persone.Fornitore;
import Persone.Persona;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

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

        String[] col = {"Ingrediente", "Quantità"};
        DefaultTableModel model = new DefaultTableModel(col, 0);
        ScorteDAO scorteDAO = new ScorteDAO();
        Map<Ingrediente,Double> scorte = scorteDAO.findAll();
        for (Map.Entry<Ingrediente,Double> entry:scorte.entrySet()){
            Ingrediente i = entry.getKey();
            double quantita = entry.getValue();
            model.addRow(new Object[]{
                    i.getNome(),
                    quantita
            });

        }
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT,6,4));
        JComboBox<Ingrediente> ingredienteJComboBox = new JComboBox<>();
        for (Ingrediente i : IngredienteDAO.findAll()){
            ingredienteJComboBox.addItem(i);
        }
        JSpinner quantitaSpinner = new JSpinner(new SpinnerNumberModel(0.0,0.0,10000.0,0.1));
        JButton aggiorna = new JButton("Aggiorna");

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
