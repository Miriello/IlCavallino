package DAO;

import Database.DatabaseManager;
import Item.Piatto;
import Utility.Vendita;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static DAO.PiattoDAO.findByVendita;

public class VenditaDAO {

    public List<Vendita> findAll(){
        List<Vendita> vendite = new ArrayList();
        String sql = "SELECT * FROM vendite";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String cfoperatore =rs.getString("cfOperatore");
                Map<Piatto,Integer> prodotti = findByVendita(id);
                vendite.add(new Vendita(id, cfoperatore, prodotti));
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
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, v.getOperatore());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()) {
                int idVendita = rs.getInt(1);
                for (Map.Entry<Piatto, Integer> entry :
                        v.getProdotti().entrySet()) {
                    PreparedStatement stmt1 = conn.prepareStatement(sql1);
                    stmt1.setInt(1, idVendita);
                    stmt1.setInt(2, entry.getKey().getId());
                    stmt1.setInt(3, entry.getValue());
                    stmt1.executeUpdate();
                }
            }
        } catch(SQLException e ){
            throw new RuntimeException("Errore nel caricamento della vendita",e);
        }
    }


    public void delete(Vendita v){
        String sql = "DELETE FROM vendite WHERE id=?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,v.getId());
            stmt.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Errore nell'eliminazione della vendita",e);
        }
    }



    public Vendita findById(int idVendita){
        String sql = "SELECT * FROM vendite WHERE id = ?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,idVendita);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String cfoperatore = rs.getString("cfoperatore");
                Map<Piatto, Integer> prodotti = findByVendita(idVendita);
                return new Vendita(idVendita,cfoperatore,prodotti);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella ricerca della vendita",e);
        }
        return null;
    }
}
