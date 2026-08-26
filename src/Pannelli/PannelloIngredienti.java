package Pannelli;

import DAO.AllergeneDAO;
import DAO.IngredienteDAO;
import Item.Allergene;
import Item.Ingrediente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;


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
        JButton aggiungi = new JButton("Aggiungi Ingrediente");
        aggiungi.addActionListener(e -> aggiungiIngrediente());
        JPanel pulsanti = new JPanel();
        pulsanti.add(aggiungi);
        add(pulsanti, BorderLayout.SOUTH);
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
                sb.append(a.toString());
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
        AllergeneDAO allergeneDAO = new AllergeneDAO();

        JTextField nomeField= new JTextField();
        JTextField scadenzaField = new JTextField();
        JComboBox<Allergene> allergeneJComboBox = new JComboBox<>();
        ArrayList<Allergene> allergeni = new ArrayList<>();

        for (Allergene a : allergeneDAO.findAll()){
            allergeneJComboBox.addItem(a);
        }

        JButton aggiungiAllergene = new JButton("Aggiungi Allergene");
        DefaultListModel<Allergene> allergeniModel = new DefaultListModel<>();
        JList<Allergene> listaAllergeni = new JList<>(allergeniModel);

        aggiungiAllergene.addActionListener(e -> {
            Allergene a = (Allergene) allergeneJComboBox.getSelectedItem();
            if(a!= null && !allergeni.contains(a)){
                allergeni.add(a);
                allergeniModel.addElement(a);
            }
        });

        JPanel panel = new JPanel(new GridLayout(4,2,5,5));
        panel.add(new JLabel("Nome: "));
        panel.add(nomeField);
        panel.add(new JLabel("Scadenza (AAAA-MM-GG): "));
        panel.add(scadenzaField);
        panel.add(allergeneJComboBox);
        panel.add(aggiungiAllergene);
        panel.add(new JLabel("Allergeni selezionati"));
        panel.add(new JScrollPane(listaAllergeni));
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
        String nomeIngrediente = nomeField.getText().trim();
        String scadenza = scadenzaField.getText().trim();

        if(nomeIngrediente.isBlank()||scadenza.isBlank()){
            JOptionPane.showMessageDialog(this,"Compila tutti i campi");
            return;
        }
        Ingrediente i = new Ingrediente(nomeIngrediente, LocalDate.parse(scadenza) ,allergeni,0);;
        ingredienteDAO.insert(i);
        aggiornaTabella();
        JOptionPane.showMessageDialog(this,"Ingrediente aggiunto correttamente");
    }
}
