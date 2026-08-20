package DAO;

import Database.DatabaseManager;
import Item.Allergene;
import Item.Ingrediente;
import Item.Piatto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PiattoDAO {

    public List<Piatto> findAll() {

        List<Piatto> piatti = new ArrayList<>();

        String sql = "SELECT * FROM piatto JOIN ";
        try {
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                int prezzo = rs.getInt("prezzo");
                int id = rs.getInt("id");
                List<Ingrediente> ingredienti = IngredienteDAO.findByPiatto(id);
                piatti.add(new Piatto(nome, prezzo, ingredienti));
            }
        } catch (SQLException e) {
            throw new RuntimeException(" Errore durante il caricamento ", e);
        }
        return piatti;
    }
}