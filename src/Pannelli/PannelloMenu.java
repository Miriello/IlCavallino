package Pannelli;

import DAO.MenuDAO;
import DAO.PiattoDAO;
import Gestori.Menu;
import Item.Piatto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;
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
        aggiornaTabella();
        add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
        JButton aggiungi = new JButton("Crea Menu");
        aggiungi.addActionListener(e -> creaMenu());
        JPanel pulsanti = new JPanel();
        add(pulsanti, BorderLayout.SOUTH);
    }

    public void creaMenu(){
        PiattoDAO piattoDAO = new PiattoDAO();
        Map<Piatto,Double> piattiMenu = new HashMap<>();
        JComboBox<Piatto> piattoJComboBox= new JComboBox<>();
        JTextField prezzoVendita = new JTextField();

        for(Piatto p : piattoDAO.findAll()){
            piattoJComboBox.addItem(p);
        }
        DefaultListModel<String> piattiModel = new DefaultListModel<>();
        JList<String> listaPiatti = new JList<>(piattiModel);
        JButton aggiungiPiatto = new JButton("Aggiungi Piatto");
        aggiungiPiatto.addActionListener(e -> {
            Piatto p = (Piatto) piattoJComboBox.getSelectedItem();
            String prezzo = prezzoVendita.getText().trim();
            if (p == null || prezzo.isBlank()) {
                JOptionPane.showMessageDialog(this, "Selezionare un piatto e inserire un prezzo di vendita");
                return;
            }
            double prezzoD = Double.parseDouble(prezzo);
            piattiMenu.put(p,prezzoD);
            piattiModel.addElement(p.getNome()+String.format("€ %.2f",prezzoD));
        });
        JPanel panel = new JPanel(new GridLayout(3,2,5,5));
        panel.add(new JLabel("Piatto: "));
        panel.add(piattoJComboBox);
        panel.add(new JLabel("Prezzo di vendita: "));
        panel.add(prezzoVendita);
        panel.add(aggiungiPiatto);
        panel.add(new JScrollPane(listaPiatti));
        int scelta =
                JOptionPane.showConfirmDialog(this, panel, "Crea Menu del Giorno", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
                );

        if (scelta != JOptionPane.OK_OPTION) {
            return;
        }
        if(piattiMenu.isEmpty()){
            JOptionPane.showMessageDialog(this,"Aggiungi almeno un piatto");
            return;
        }
        LocalDate oggi = LocalDate.now();
        Menu menu = new Menu(0,piattiMenu,oggi);
        menuDAO.insert(menu);
        aggiornaTabella();
        JOptionPane.showMessageDialog(this,"Menu creato correttamente");
    }


    public void aggiornaTabella(){
        model.setRowCount(0);
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
    }
}
