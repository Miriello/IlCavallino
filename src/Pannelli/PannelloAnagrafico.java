package Pannelli;

import DAO.AccountDAO;
import DAO.PersonaDAO;
import DAO.RuoloDAO;
import Persone.Persona;
import Utility.Account;
import Utility.Ruolo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PannelloAnagrafico extends JPanel {

    private AccountDAO accountDAO;
    private RuoloDAO ruoloDAO;
    private PersonaDAO personaDAO;
    private DefaultTableModel model;
    private JTable table;

    public PannelloAnagrafico(){
        personaDAO = new PersonaDAO();
        accountDAO = new AccountDAO();
        ruoloDAO = new RuoloDAO();

        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        String[] col = {"Nome", "Cognome", "Codice Fiscale", "Ruolo" };
        model = new DefaultTableModel(col,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        add(new JScrollPane(table),BorderLayout.CENTER);
        aggiornaTabella();

        JPanel gestionePanel = new JPanel(new BorderLayout(5,5));
        JPanel form = new JPanel(new GridLayout(3,4,8,8));
        JPanel pulsanti = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton aggiungi = new JButton("Aggiungi");
        pulsanti.add(aggiungi);
        gestionePanel.add(form,BorderLayout.CENTER);
        gestionePanel.add(pulsanti,BorderLayout.SOUTH);
        add(gestionePanel,BorderLayout.SOUTH);

        aggiungi.addActionListener(e -> {
            aggiungiPersona();
        });

    }

    public void aggiungiPersona(){
        JTextField nomeField = new JTextField();
        JTextField cognomeField = new JTextField();
        JTextField cfField = new JTextField();
        JComboBox<Ruolo> ruoloCombo = new JComboBox<>();
        JTextField usernameField = new JTextField();
        JTextField passwordField = new JTextField();

        for (Ruolo ruolo : ruoloDAO.findAll()){
            ruoloCombo.addItem(ruolo);
        }
        JPanel panel = new JPanel(new GridLayout(6,2,5,5));
        panel.add(new JLabel("Nome: "));
        panel.add(nomeField);
        panel.add(new JLabel("Cognome: "));
        panel.add(cognomeField);
        panel.add(new JLabel("CF: "));
        panel.add(cfField);
        panel.add(new JLabel("Ruolo: "));
        panel.add(ruoloCombo);
        panel.add(new JLabel("Username iniziale: "));
        panel.add(usernameField);
        panel.add(new JLabel("Password iniziale: "));
        panel.add(passwordField);
        int scelta = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Aggiungi persona",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );
        if (scelta != JOptionPane.OK_OPTION) {
            return;
        }

        String nome = nomeField.getText();
        String cognome = cognomeField.getText();
        String cf = cfField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        Ruolo ruolo = (Ruolo) ruoloCombo.getSelectedItem();

        if(nome.isBlank()|| cognome.isBlank() || cf.isBlank() || username.isBlank() || password.isBlank() || ruolo == null){
            JOptionPane.showMessageDialog(this,"Compila tutti i campi");
            return;
        }
        Persona p = new Persona(nome,cognome,cf,ruolo);
        Account a = new Account(username,password,cf);
        personaDAO.insert(p);
        accountDAO.insert(a);
        aggiornaTabella();
        JOptionPane.showMessageDialog(this,"Persona aggiunta correttamente");
    }

    public void aggiornaTabella(){
        model.setRowCount(0);
        for (Persona p : personaDAO.findAll()){
            model.addRow(new Object[]{
                    p.getNome(),
                    p.getCognome(),
                    p.getCodiceFiscale(),
                    p.getRuolo().toString()
            });
        }
    }

}
