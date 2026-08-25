package Pannelli;


import DAO.IngredienteDAO;
import DAO.PiattoDAO;
import Item.Allergene;
import Item.Ingrediente;
import Item.Piatto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class PannelloPiatti extends JPanel {

    private DefaultTableModel model;

    public PannelloPiatti(){
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] col = {"ID","Nome","Ingredienti","Allergeni",};
        model = new DefaultTableModel(col, 0) {
            public boolean isCellEditable(int r, int c) {
                return false; }
        };
        aggiornaTabella();
        add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
        JButton aggiungi = new JButton("Aggiungi Piatto");
        aggiungi.addActionListener(e ->aggiungiPiatto());
        JPanel pulsanti = new JPanel();
        pulsanti.add(aggiungi);
        add(pulsanti,BorderLayout.SOUTH);
    }

    public void aggiungiPiatto(){
        Map<Ingrediente,Double> ingredientiPiatto = new HashMap<>();
        PiattoDAO piattoDAO = new PiattoDAO();
        IngredienteDAO ingredienteDAO = new IngredienteDAO();
        JTextField nomePiattoField = new JTextField();
        JTextField quantitaField = new JTextField();
        JComboBox<Ingrediente> ingredienteJComboBox = new JComboBox<>();
        DefaultListModel<String> ingredientiModel = new DefaultListModel<>();
        JList<String> listaIngredienti = new JList<>(ingredientiModel);

        for (Ingrediente i : IngredienteDAO.findAll()){
            ingredienteJComboBox.addItem(i);
        }
        JButton aggiungiIngrediente = new JButton("Aggiungi Ingrediente");
        aggiungiIngrediente.addActionListener(e -> {
            Ingrediente i = (Ingrediente) ingredienteJComboBox.getSelectedItem();
            String quantita = quantitaField.getText().trim();
            if(i==null||quantita.isBlank()){
                JOptionPane.showMessageDialog(this,"Selezionare ingrediente e quantità");
                return;
            }
            double quantitaD = Double.parseDouble(quantita);
            ingredientiPiatto.put(i,quantitaD);
            ingredientiModel.addElement(i.getNome()+" x "+ quantitaD);
        });

        JPanel panel = new JPanel(new GridLayout(4,2,5,5));
        panel.add(new JLabel("Nome piatto: "));
        panel.add(nomePiattoField);
        panel.add(new JLabel("Ingrediente: "));
        panel.add(ingredienteJComboBox);
        panel.add(new JLabel("Quantità: "));
        panel.add(quantitaField);
        panel.add(aggiungiIngrediente);
        panel.add(new JScrollPane(listaIngredienti));
        int scelta = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Aggiungi Piatto",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );
        if (scelta != JOptionPane.OK_OPTION) {
            return;
        }
        String nomePiatto = nomePiattoField.getText();
        if(nomePiatto.isBlank()){
            JOptionPane.showMessageDialog(this,"Compila tutti i campi");
            return;
        }
        Piatto p = new Piatto(nomePiatto,0,ingredientiPiatto);
        piattoDAO.insert(p);
        aggiornaTabella();
        JOptionPane.showMessageDialog(this,"Piatto aggiunto correttamente");
    }

    public void aggiornaTabella(){
        model.setRowCount(0);
        PiattoDAO piattoDAO= new PiattoDAO();
        for (Piatto p : piattoDAO.findAll()) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sbA = new StringBuilder();
            for (Map.Entry<Ingrediente, Double> entry : p.getIngredienti().entrySet()) {
                Ingrediente i = entry.getKey();
                if (!sb.isEmpty()) {
                    sb.append(", ");
                }
                sb.append(i.getNome());
                for (Allergene a : i.getAllergeni()){
                    if(!sbA.isEmpty()) {
                        sbA.append(", ");
                    }
                    sbA.append(a.getCodiceAllergene());
            }
            model.addRow(new Object[]{
                    p.getId(),
                    p.getNome(),
                    sb.toString(),
                    sbA.toString()});
        }
    }
    }
}
