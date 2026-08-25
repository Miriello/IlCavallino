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
        model = new DefaultTableModel(col, 0);
        fornitoreDAO = new FornitoreDAO();
        for (Fornitore f : fornitoreDAO.findAll()) {
            model.addRow(new Object[]{
                    f.getPartitaIva(),
                    f.getRagioneSociale(),
                    f.getEmail()});
            for (Map.Entry<Ingrediente, Double> entry : f.getBeniForniti().entrySet()) {
                Ingrediente i = entry.getKey();
                double costo = entry.getValue();
                model.addRow(new Object[]{
                        i.getNome(),
                        costo
                });
            }
        }
    }
}
