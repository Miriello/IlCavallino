package Pannelli;


import DAO.PiattoDAO;
import Item.Allergene;
import Item.Ingrediente;
import Item.Piatto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


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
        PiattoDAO piattoDAO = new PiattoDAO();
        JTextField nomePiattoField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(6,2,5,5));
        panel.add(new JLabel("Nome piatto: "));
        panel.add(nomePiattoField);
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
        Piatto p = new Piatto(nomePiatto);
        piattoDAO.insert(p);
        aggiornaTabella();
        JOptionPane.showMessageDialog(this,"Fornitore aggiunto correttamente");
    }

    public void aggiornaTabella(){
        model.setRowCount(0);
        PiattoDAO piattoDAO= new PiattoDAO();
        for (Piatto p : piattoDAO.findAll()) {
            StringBuilder sb = new StringBuilder();
            for (Ingrediente i : p.getIngredienti()) {
                StringBuilder sbA = new StringBuilder();
                if (!sb.isEmpty()) {
                    sb.append(", ");
                }
                sb.append(p.getNome());
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
