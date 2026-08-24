package DAO;

import Database.DatabaseManager;
import Item.Ingrediente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScorteDAO {

    public void insert(Ingrediente i, int quantita, int sogliaMinima){
        String sql = "INSERT INTO scorte (idIngrediente,quantita,sogliaMinima) VALUES (?,?,?)";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,i.getId());
            stmt.setInt(2,quantita);
            stmt.setInt(3,sogliaMinima);
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Errore nell'inserimento della scorta",e);
        }
    }

    public void updateQuantita(int idIngrediente, int nuovaQuantita){
        String sql = "UPDATE scorte SET quantita = ? WHERE idIngrediente = ?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,nuovaQuantita);
            stmt.setInt(2,idIngrediente);
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Errore nell'aggiornamento della quantita della scorta",e);
        }
    }

    public void updateSoglia(int idIngrediente, int nuovaSoglia){
        String sql = "UPDATE scorte SET sogliaMinima = ? WHERE idIngrediente = ?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,nuovaSoglia);
            stmt.setInt(2,idIngrediente);
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Errore nell'aggiornamento della soglia minima della scorta",e);
        }
    }

    public Integer findQuantitaIngrediente(int idIngrediente){
        String sql = "SELECT s.quantita FROM scorte s WHERE idIngrediente =?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,idIngrediente);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                int quantita = rs.getInt("quantita");
                return quantita;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella ricerca della quantità dell'ingrediente",e);
        }
        return 0;
    }
    public List<Ingrediente> findSottoSoglia(){
        String sql = "SELECT * FROM scorte WHERE quantita < sogliaMinima";
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
