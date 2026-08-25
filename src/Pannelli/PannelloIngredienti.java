package Pannelli;

import DAO.IngredienteDAO;
import Item.Allergene;
import Item.Ingrediente;
import Persone.Fornitore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
            }
            model.addRow(new Object[]{
                    i.getId(),
                    i.getNome(),
                    i.getScadenza(),
                    sb.toString()});
        }
    }

    public void aggiungiIngrediente(){
        IngredienteDAO ingredienteDAO = new IngredienteDAO();
        JTextField nomeField= new JTextField();
        JPanel panel = new JPanel(new GridLayout(6,2,5,5));
        panel.add(new JLabel("Nome: "));
        panel.add(nomeField);
        int scelta = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Aggiungi Ingrediente",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );
        if (scelta != JOptionPane.OK_OPTION) {
            return;
        }
        String nomeIngrediente = nomeField.getText();

        if(nomeIngrediente.isBlank()){
            JOptionPane.showMessageDialog(this,"Compila tutti i campi");
            return;
        }
        Map<Ingrediente,Double> beniForniti = new HashMap<>();
        Ingrediente i = new Ingrediente(nomeIngrediente, new LocalDate scadenza ,new List<Allergeni> allergeni,0);;
        ingredienteDAO.insert(i);
        aggiornaTabella();
        JOptionPane.showMessageDialog(this,"Ingrediente aggiunto correttamente");
    }
}
