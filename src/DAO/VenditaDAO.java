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
                String cfoperatore =rs.getString("cfOperatore");
                int quantita = rs.getInt("quantita");
                List<Piatto> piatti = findByVendita(id);
                vendite.add(new Vendita(id, cfoperatore, piatti, quantita));
            }
            return vendite;
        }catch (SQLException e ){
            throw new RuntimeException("Errore nel caricamento delle vendite",e);
        }
    }


    public void insert (Vendita v ){
        String sql = "INSERT INTO vendite (cfOperatore) VALUES (?)";
        String sql1 = "INSERT INTO vendita_piatto (idVendita, idPiatto, quantita) VALUES (?,?,?)";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, v.getOperatore());
            stmt.executeUpdate();
            for (Piatto p : v.getProdotti()){
                PreparedStatement stmt1 = conn.prepareStatement(sql1);
                stmt1.setInt(1,v.getId());
                stmt1.setInt(2,p.getId());
                stmt1.setInt(3,v.getQuantita());
                stmt1.executeUpdate();
            }
        } catch(SQLException e ){
            throw new RuntimeException("Errore nel caricamento della vendita",e);
        }
    }

    public void update (Vendita v){
        String sql = "UPDATE FROM vendite WHERE id = ?";
        String sql1 = "DELETE FROM vendita_piatto WHERE idPiatto = ?";
        String sql2 = "INSERT INTO vendita_piatto (id";
        try {
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,v.getId());
            stmt.executeUpdate();
        } catch (SQLException e ){
            throw new RuntimeException("Errore nell'aggiornamento della vendita");
        }
    }

    public void delete(Vendita v){

    }



    public Vendita findById(int idVendita){
        return null;
    }
}
