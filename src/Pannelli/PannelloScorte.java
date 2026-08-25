package Pannelli;

import DAO.IngredienteDAO;
import DAO.ScorteDAO;
import Item.Ingrediente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class PannelloScorte extends JPanel {

    private ScorteDAO scorteDAO;
    private DefaultTableModel model;

    public  PannelloScorte() {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] col = {"Ingrediente", "Quantità"};
        model = new DefaultTableModel(col, 0);
        scorteDAO = new ScorteDAO();
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
        add(form, BorderLayout.SOUTH);
    }
}
