package Pannelli;

import DAO.MenuDAO;
import Gestori.Menu;
import Item.Piatto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.Map;

public class PannelloMenu extends JPanel {
    private MenuDAO menuDAO;
    private DefaultTableModel model;

    public PannelloMenu() {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] col = {"Piatto", "Prezzo", "Ingredienti"};
        model = new DefaultTableModel(col, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        menuDAO = new MenuDAO();
        Menu menuDelGiorno = menuDAO.findByData(LocalDate.now());
        if(menuDelGiorno != null ){
            for(Map.Entry<Piatto,Double> entry: menuDelGiorno.getProdotti().entrySet()){
                Piatto p = entry.getKey();
                double prezzo = entry.getValue();
                model.addRow(new Object[]{
                        p.getNome(),String.format("€ %.2f",prezzo), p.getIngredienti()
                });
            }
        }
        add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
    }
}
