package DAO;

import Database.DatabaseManager;
import Item.Articolo;
import Utility.Vendita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VenditaDAO {

    public List<Vendita> findAll(){
        List<Articolo> vendite = new ArrayList<>();
        String sql = "SELECT * FROM vendite";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement();
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                List<Articolo> articoli =
            }
        }catch (SQLException e ){
            throw new RuntimeException("Errore nel caricamento delle vendite",e);
        }
    }
}
