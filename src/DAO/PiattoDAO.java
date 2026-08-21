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

    public void insert (Piatto p ){
        String sql = "INSERT INTO piatti (nome, prezzo, ingredienti) VALUES (?,?,?)";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPrezzo());
            stmt.setArray(p.getIngredienti());
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel caricamento del piatto", e);
        }
    }
}