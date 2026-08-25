package Pannelli;

import DAO.PersonaDAO;
import Persone.Persona;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PannelloAnagrafico extends JPanel {

    private PersonaDAO personaDAO;
    private DefaultTableModel model;

    public PannelloAnagrafico(){
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        String[] col = {"Nome", "Cognome", "Codice Fiscale", "Ruolo" };
        model = new DefaultTableModel(col,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        personaDAO = new PersonaDAO();
        for (Persona p : personaDAO.findAll()){
            model.addRow(new Object[]{
                    p.getNome(),
                    p.getCognome(),
                    p.getCodiceFiscale(),
                    p.getRuolo().getNomeRuolo()
            });
        }
        add(new JScrollPane(new JTable(model)),BorderLayout.CENTER);
    }
}
