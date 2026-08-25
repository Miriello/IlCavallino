package Pannelli;

import DAO.FornitoreDAO;
import Item.Ingrediente;
import Persone.Fornitore;
import Persone.Persona;
import Utility.Account;
import Utility.Ruolo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
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
        aggiornaTabella();
        add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
    }

    public void aggiungiFornitore(){
        JTextField pIvaField = new JTextField();
        JTextField RagioneSocialeField = new JTextField();
        JTextField EmailField = new JTextField();


        JPanel panel = new JPanel(new GridLayout(6,2,5,5));
        panel.add(new JLabel("P.Iva: "));
        panel.add(pIvaField);
        panel.add(new JLabel("Ragione Sociale: "));
        panel.add(RagioneSocialeField);
        panel.add(new JLabel("Email: "));
        panel.add(EmailField);

        int scelta = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Aggiungi Fornitore",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );
        if (scelta != JOptionPane.OK_OPTION) {
            return;
        }

        String pIva = pIvaField.getText();
        String ragioneSociale = RagioneSocialeField.getText();
        String email = EmailField.getText();

        if(pIva == "" || ragioneSociale == "" || email == ""){
            JOptionPane.showMessageDialog(this,"Compila tutti i campi");
            return;
        }
        Map<Ingrediente,Double> beniForniti = new HashMap<>();
        Fornitore f = new Fornitore(pIva,ragioneSociale,email, beniForniti);
        fornitoreDAO.insert(f);
        aggiornaTabella();
        JOptionPane.showMessageDialog(this,"Persona aggiunta correttamente");
    }

    public void aggiornaTabella(){
        model.setRowCount(0);
        fornitoreDAO = new FornitoreDAO();
        for (Fornitore f : fornitoreDAO.findAll()) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<Ingrediente, Double> entry : f.getBeniForniti().entrySet()) {
                if (!sb.isEmpty()) {
                    sb.append(", ");
                }
                sb.append(entry.getKey().getNome());
                sb.append(String.format("€ %.2f",entry.getValue()));
                sb.append(")");
            }
            model.addRow(new Object[]{
                    f.getPartitaIva(),
                    f.getRagioneSociale(),
                    f.getEmail(),
                    sb.toString()});
        }
    }
}
