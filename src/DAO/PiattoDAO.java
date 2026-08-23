package DAO;

import Database.DatabaseManager;
import Item.Ingrediente;
import Item.Piatto;

import javax.swing.event.InternalFrameEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static DAO.IngredienteDAO.findByPiatto;

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
                List<Ingrediente> ingredienti = findByPiatto(id);
                piatti.add(new Piatto(nome, id, prezzo, ingredienti));
            }
        } catch (SQLException e) {
            throw new RuntimeException(" Errore durante il caricamento ", e);
        }
        return piatti;
    }

    public void insert (Piatto p ){
        String sql = "INSERT INTO piatti (nome, prezzo) VALUES (?,?)";
        String sql1= "INSERT INTO ingredienti_piatto (idIngrediente, idPiatto) VALUES (?,?)";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPrezzo());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                int idPiatto = rs.getInt(1);
                for (Ingrediente i : p.getIngredienti()) {
                    PreparedStatement stmt1 = conn.prepareStatement(sql1);
                    stmt1.setInt(1, i.getId());
                    stmt1.setInt(2, idPiatto);
                    stmt1.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel caricamento del piatto", e);
        }
    }

    public void update(Piatto p){
        String sql = "UPDATE piatti SET nome = ?, prezzo = ? WHERE id= ?";
        String sql1 = "DELETE from ingredienti_piatto WHERE idPiatto = ?";
        String sql2 = "INSERT INTO ingredienti_piatto (idIngrediente, idPiatto) VALUES (?,?)";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,p.getNome());
            stmt.setDouble(2,p.getPrezzo());
            stmt.setInt(3,p.getId());
            stmt.executeUpdate();
            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            stmt1.setInt(1,p.getId());
            stmt1.executeUpdate();
            for(Ingrediente i : p.getIngredienti()){
                PreparedStatement stmt2 = conn.prepareStatement(sql2);
                stmt2.setInt(1,i.getId());
                stmt2.setInt(2,p.getId());
                stmt2.executeUpdate();
            }
        }catch(SQLException e){
            throw new RuntimeException("Errore nell'aggiornamento del piatto");
        }
    }

    public void delete (Piatto p){
        String sql = "DELETE FROM piatti WHERE id = ? ";
        try {
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,p.getId());
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Errore nella cancellazione del piatto");
        }
    }

    public Piatto findById(int idPiatto){
        String sql = "SELECT * FROM piatti WHERE id = ?";
        try {
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,idPiatto);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                String nome = rs.getString("nome");
                Double prezzo = rs.getDouble("prezzo");
                List<Ingrediente> ingredienti = findByPiatto(idPiatto);
                return new Piatto(nome,idPiatto,prezzo,ingredienti);
            }
        } catch(SQLException e){
            throw new RuntimeException("Errore nel caricamento del piatto");
        }
        return null;
    }
    public static Map<Piatto,Integer> findByVendita(int idVendita) {
        String sql = "SELECT p.nome, p.id, p.prezzo vp.quantita FROM piatti p JOIN vendita_piatto vp ON p.id=vp.idPiatto WHERE idVendita=?";
        Map<Piatto, Integer> risultato = new HashMap<>();
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,idVendita);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String nome = rs.getString("nome");
                int idPiatto = rs.getInt("id");
                Double prezzo = rs.getDouble("prezzo");
                int quantita = rs.getInt("quantita");
                List<Ingrediente> ingredienti = findByPiatto(idPiatto);
                risultato.put((new Piatto(nome,idPiatto,prezzo,ingredienti)),quantita);
            }
            return risultato;
        } catch(SQLException e){
            throw new RuntimeException("Errore nella ricarca dei Piatti",e);
        }
    }
}