package Pannelli;

import DAO.ScorteDAO;
import Item.Ingrediente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class PannelloIngredienti extends JPanel{

    private ScorteDAO scorteDAO;
    private DefaultTableModel model;

    public PannelloIngredienti() {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] col = {"Ingrediente", "Quantita disponibile"};
        model = new DefaultTableModel(col, 0) {
            public boolean isCellEditable(int r, int c) {
                return false; }
        };
        scorteDAO = new ScorteDAO();
        Map<Ingrediente,Double> scorte = scorteDAO.findAll();
        for(Map.Entry<Ingrediente,Double> entry :scorte.entrySet()){
            Ingrediente i = entry.getKey();
            Double quantita = entry.getValue();
            model.addRow(new Object[]{
                    i.getNome(),quantita
            });
        }
        add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
    }
}
