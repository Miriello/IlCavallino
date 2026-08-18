package GUI;

import javax.swing.*;

public class GestoreGUI extends JFrame {
    public GestoreGUI() {
        setTitle("Sistema Informativo IL CAVALLINO");
        setSize(850, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inizializzaComponenti();
        setVisible(true);
    }

    public void inizializzaComponenti() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Sistema Informativo");
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }




}
