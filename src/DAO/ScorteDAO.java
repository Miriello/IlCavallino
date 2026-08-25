package DAO;

import Database.DatabaseManager;
import Item.Ingrediente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScorteDAO {

    public Map<Ingrediente,Double> findAll(){
        String sql = "SELECT * FROM scorte";
        Map<Ingrediente,Double> scorte = new HashMap<>();
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
               int idIngrediente = rs.getInt("idIngrediente");
               Double quantita = rs.getDouble("quantita");
               Ingrediente i = IngredienteDAO.findById(idIngrediente);
               scorte.put(i,quantita);
            }
            return scorte;
        }catch (SQLException e){
            throw new RuntimeException("Errore nel caricamento delle scorte",e);
        }
    }

    public void insert(Ingrediente i, Double quantita, Double sogliaMinima){
        String sql = "INSERT INTO scorte (idIngrediente,quantita,sogliaMinima) VALUES (?,?,?)";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,i.getId());
            stmt.setDouble(2,quantita);
            stmt.setDouble(3,sogliaMinima);
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Errore nell'inserimento della scorta",e);
        }
    }

    public void updateQuantita(int idIngrediente, Double nuovaQuantita){
        String sql = "UPDATE scorte SET quantita = ? WHERE idIngrediente = ?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1,nuovaQuantita);
            stmt.setInt(2,idIngrediente);
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Errore nell'aggiornamento della quantita della scorta",e);
        }
    }

    public void updateSoglia(int idIngrediente, Double nuovaSoglia){
        String sql = "UPDATE scorte SET sogliaMinima = ? WHERE idIngrediente = ?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1,nuovaSoglia);
            stmt.setInt(2,idIngrediente);
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Errore nell'aggiornamento della soglia minima della scorta",e);
        }
    }

    public Double findQuantitaIngrediente(int idIngrediente){
        String sql = "SELECT s.quantita FROM scorte s WHERE idIngrediente =?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,idIngrediente);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Double quantita = rs.getDouble("quantita");
                return quantita;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella ricerca della quantità dell'ingrediente",e);
        }
        return null;
    }
    public List<Ingrediente> findSottoSoglia(){
        String sql = "SELECT * FROM scorte WHERE quantita <= sogliaMinima";
        List<Ingrediente> ingredienti = new ArrayList<>() ;
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int idIngrediente = rs.getInt("idIngrediente");
                ingredienti.add(IngredienteDAO.findById(idIngrediente));
            }
            return ingredienti;
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel calcolo degli ingredienti sotto la soglia minima",e);
        }
    }
}
