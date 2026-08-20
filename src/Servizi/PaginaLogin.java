package Servizi;

import Gestionale.*;
import Persone.Persona;

import javax.swing.*;
import java.awt.*;

public class PaginaLogin extends JFrame {

    public PaginaLogin() {
        setTitle("Login — Il Cavallino");
        setSize(360, 230);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Accedi");
        JLabel statusLabel = new JLabel(" ");
        statusLabel.setForeground(Color.RED);

        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 15, 25));
        panel.add(new JLabel("Username:"));  panel.add(usernameField);
        panel.add(new JLabel("Password:"));  panel.add(passwordField);
        panel.add(new JLabel(""));           panel.add(loginButton);
        panel.add(statusLabel);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            Persona u = Autenticazione.login(username, password);
            if (u == null) {
                statusLabel.setText("Credenziali non valide.");
            } else {
                apriGestionale(u);
                dispose();
            }
        });

        passwordField.addActionListener(loginButton.getActionListeners()[0]);

        add(panel);
        setVisible(true);
    }

    private void apriGestionale(Persona u) {
        switch (u.getRuolo()) {
            case SOCIO     -> new GestionaleSocio(u);
            case CUCINA    -> new GestionaleCucina(u);
            case VENDITA   -> new GestionaleVendita(u);
            case MAGAZZINO -> new GestionaleMagazzino(u);
            case MARKETING -> new GestionaleMarketing(u);
        }
    }
}
