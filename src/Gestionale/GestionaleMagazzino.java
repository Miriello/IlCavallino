package Gestionale;

import DAO.FornitoreDAO;
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
        tabs.addTab("Fornitori", creaPanelFornitori());

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
        aggiorna.addActionListener(e-> );

        JSpinner sogliaSpinner = new JSpinner(new SpinnerNumberModel(5,0,10000,1));
        form.add(new JLabel("Ingrediente"));
        form.add(ingredienteJComboBox);
        form.add(new JLabel("Quantita:"));
        form.add(quantitaSpinner);
        form.add(new JLabel("Soglia minima: "));
        form.add(sogliaSpinner);
        form.add(aggiorna);
        panel.add(form, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel creaPanelFornitori() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] col = {"P.IVA", "Ragione Sociale", "Email", "Ingredienti Forniti" };
        DefaultTableModel model = new DefaultTableModel(col, 0);
        FornitoreDAO fornitoreDAO = new FornitoreDAO();
        for (Fornitore f : fornitoreDAO.findAll()) {
            f.getPartitaIva();
            f.getRagioneSociale();
            f.getEmail();
            for (Map.Entry<Ingrediente, Double> entry : f.getBeniForniti().entrySet()) {
                Ingrediente i = entry.getKey();
                double costo = entry.getValue();
            }
        }

        return panel;
    }
}
