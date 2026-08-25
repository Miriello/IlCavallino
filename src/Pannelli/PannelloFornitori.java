package Pannelli;

import DAO.FornitoreDAO;
import Item.Ingrediente;
import Persone.Fornitore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class PannelloFornitori extends JPanel{

    private FornitoreDAO fornitoreDAO;
    private DefaultTableModel model;

    public PannelloFornitori() {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] col = {"P.IVA", "Ragione Sociale", "Email", "Ingredienti Forniti" };
        model = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        fornitoreDAO = new FornitoreDAO();
        for (Fornitore f : fornitoreDAO.findAll()) {
            StringBuilder sb = new StringBuilder();

            for (Map.Entry<Ingrediente, Double> entry : f.getBeniForniti().entrySet()) {
                if (!sb.isEmpty()) {
                    sb.append(", ");
                    sb.append(entry.getKey().getNome());
                    sb.append(String.format("€ %.2f",entry.getValue()));
                    sb.append(")");
                }
            }
            model.addRow(new Object[]{
                    f.getPartitaIva(),
                    f.getRagioneSociale(),
                    f.getEmail(),
                    sb.toString()});
        }
        add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
    }
}
