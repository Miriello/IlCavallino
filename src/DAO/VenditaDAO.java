package DAO;

import Database.DatabaseManager;
import Item.Piatto;
import Utility.Vendita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static DAO.PiattoDAO.findByVendita;

public class VenditaDAO {

    public List<Vendita> findAll(){
        List<Vendita> vendite = new ArrayList<>();
        String sql = "SELECT * FROM vendite";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                List<Piatto> piatti = findByVendita(id);
                vendite.add(new Vendita(id, piatti));
            }
            return vendite;
        }catch (SQLException e ){
            throw new RuntimeException("Errore nel caricamento delle vendite",e);
        }
    }


    public void insert (Vendita v ){
        String sql = "INSERT INTO vendite () VALUES (?,?,?)";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
        } catch(SQLException e ){
            throw new RuntimeException("Errore nel caricamento della vendita",e);
        }
    }
}
