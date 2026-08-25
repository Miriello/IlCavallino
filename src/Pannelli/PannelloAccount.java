package Pannelli;

import DAO.AccountDAO;
import DAO.PersonaDAO;
import DAO.RuoloDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class PannelloAccount extends JPanel {

    private PersonaDAO personaDAO;
    private RuoloDAO ruoloDAO;
    private AccountDAO accountDAO;
    private DefaultTableModel model;

    public PannelloAccount(){
        setLayout(new BorderLayout(5,5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


    }
}
