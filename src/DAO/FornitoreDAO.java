package DAO;

import Database.DatabaseManager;
import Item.Articolo;
import Item.Ingrediente;
import Persone.Fornitore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornitoreDAO {
    public List<Fornitore> findAll(){
        List<Fornitore> fornitori = new ArrayList<>();
        String sql = "SELECT * FROM fornitori";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String pIva = rs.getString("partitaIva");
                String rS = rs.getString("ragioneSociale");
                String email = rs.getString("email");
                int id = rs.getInt("id");
                List<Ingrediente> articoli = IngredienteDAO.findByFornitore(id);
                fornitori.add(new Fornitore(pIva, rS, email, articoli));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel caricamento dei fornitori", e);
        }
        return fornitori;
    }
}
