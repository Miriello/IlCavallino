package Pannelli;

import DAO.IngredienteDAO;
import DAO.ScorteDAO;
import Item.Ingrediente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class PannelloScorte extends JPanel {

    private final JTable table;
    private ScorteDAO scorteDAO;
    private DefaultTableModel model;

    public  PannelloScorte() {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] col = {"Ingrediente", "Quantità"};
        model = new DefaultTableModel(col, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        add (new JScrollPane(table),BorderLayout.CENTER);
        scorteDAO = new ScorteDAO();
        aggiornaTabella();

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT,6,4));
        JComboBox<Ingrediente> ingredienteJComboBox = new JComboBox<>();
        for (Ingrediente i : IngredienteDAO.findAll()){
            ingredienteJComboBox.addItem(i);
        }
        JSpinner quantitaSpinner = new JSpinner(new SpinnerNumberModel(0.0,0.0,10000.0,0.1));
        JSpinner sogliaSpinner = new JSpinner(new SpinnerNumberModel(5,0,10000,1));

        JButton aggiorna = new JButton("Aggiorna");
        aggiorna.addActionListener(e-> {
            Ingrediente i = (Ingrediente) ingredienteJComboBox.getSelectedItem();
            if (i==null){
                return;
            }
            double quantita = ((Number) quantitaSpinner.getValue()).doubleValue();
            Double soglia = ((Number) sogliaSpinner.getValue()).doubleValue();
            Double quantita_attuale =scorteDAO.findQuantitaIngrediente(i.getId());
            if(quantita_attuale==null){
                scorteDAO.insert(i,quantita,soglia);
            }
            else{
                scorteDAO.updateQuantita(i.getId(),quantita);
                scorteDAO.updateSoglia(i.getId(),soglia);
            }
            aggiornaTabella();
        });

        form.add(new JLabel("Ingrediente"));
        form.add(ingredienteJComboBox);
        form.add(new JLabel("Quantita:"));
        form.add(quantitaSpinner);
        form.add(new JLabel("Soglia minima: "));
        form.add(sogliaSpinner);
        form.add(aggiorna);
        add(form, BorderLayout.SOUTH);
    }

    private void aggiornaTabella(){
        model.setRowCount(0);
        Map<Ingrediente,Double> scorte = scorteDAO.findAll();
        for(Map.Entry<Ingrediente,Double> entry : scorte.entrySet()){
            Ingrediente i = entry.getKey();
            double quantita = entry.getValue();
            model.addRow(new Object[]{
                    i.getNome(),
                    quantita
            });
        }
    }
}
