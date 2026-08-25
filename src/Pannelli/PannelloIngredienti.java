package Pannelli;

import DAO.IngredienteDAO;
import Item.Allergene;
import Item.Ingrediente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PannelloIngredienti extends JPanel{

    private DefaultTableModel model;

    public PannelloIngredienti() {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] col = {"ID","Nome","Scadenza","Allergeni",};
        model = new DefaultTableModel(col, 0) {
            public boolean isCellEditable(int r, int c) {
                return false; }
        };
        aggiornaTabella();
        add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
    }

    public void aggiornaTabella(){
        model.setRowCount(0);
        IngredienteDAO ingredienteDAO= new IngredienteDAO();
        for (Ingrediente i : ingredienteDAO.findAll()) {
            StringBuilder sb = new StringBuilder();
            for (Allergene a : i.getAllergeni()) {
                if (!sb.isEmpty()) {
                    sb.append(", ");
                }
                sb.append(a.getNome());
                sb.append(")");
            }
            model.addRow(new Object[]{
                    i.getNome(),
                    i.getScadenza(),
                    sb.toString(),
                    i.getId()});
        }
    }
}
