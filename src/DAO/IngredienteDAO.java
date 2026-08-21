package DAO;

import Database.DatabaseManager;
import Item.Allergene;
import Item.Ingrediente;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IngredienteDAO {

    public List<Ingrediente> findAll (){

        List<Ingrediente> ingredienti = new ArrayList<>();

        String sql = "SELECT * FROM ingredienti";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String nome = rs.getString("nome");
                LocalDate scadenza = rs.getDate("scadenza").toLocalDate();
                int id = rs.getInt("id");
                List<Allergene> allergeni = AllergeneDAO.findByIngrediente(id);
                Ingrediente ingrediente = new Ingrediente (nome, scadenza, allergeni);
                ingredienti.add(ingrediente);
            }
        } catch (SQLException e ){
            throw new RuntimeException( " Errore durante il caricamento ", e);
        }
        return ingredienti;
    }

    public void insert(Ingrediente ingrediente){
        String sql = "INSERT INTO ingredienti (nome,scadenza,allergeni) VALUES (?,?,?)";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ingrediente.getNome());
            stmt.setDate(2, java.sql.Date.valueOf(ingrediente.getScadenza());

            stmt.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Errore nel caricamento dell'ingrediente",e);
        }
    }

    public void delete(Ingrediente ingrediente){
        String sql = "DELETE FROM ingredienti WHERE id=?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,ingrediente.getId());
            stmt.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Errore nella cancellazione dell'ingrediente",e);
        }
    }

    public void update(Ingrediente ingrediente){
        String sql = "UPDATE ingredienti" + "SET nome = ?, scadenza = ?, allergeni=? " + "WHERE id=?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(4,ingrediente.getId());
            stmt.executeUpdate();
            stmt.setString(1,ingrediente.getNome());
            stmt.setDate(2, java.sql.Date.valueOf(ingrediente.getScadenza()));
        }catch(SQLException e){
            throw new RuntimeException("Errore nell'aggiornamento dell'ingrediente",e);
        }
    }


    public void  findById(int id){
        String sql = "SELECT * FROM ingredienti WHERE id = ?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                String nome = rs.getString("nome");
                LocalDate scadenza = rs.getDate("scadenza").toLocalDate();

            }
        } catch(SQLException e){
            throw new RuntimeException("Errore nella ricerca dell'ingrediente");
        }
    }

    public static List<Ingrediente> findByPiatto(int idPiatto){
        List<Ingrediente> ingredienti = new ArrayList<>();
        String sql = "SELECT i FROM ingredienti_piatto ip JOIN ingredienti i ON ip.idIngrediente=i.idIngrediente WHERE ip.idPiatto= ?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,idPiatto);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String nome = rs.getString("nome");
                LocalDate scadenza = rs.getDate("scadenza").toLocalDate();
                int id = rs.getInt("id");
                List<Allergene> allergeni = AllergeneDAO.findByIngrediente(id);
                ingredienti.add(new Ingrediente(nome, scadenza, allergeni,id));
            }
        } catch(SQLException e){
            throw new RuntimeException("Caricamento degli ingredienti non riuscito", e);
        }
        return ingredienti;
    }

    public static List<Ingrediente> findByFornitore(String partitaIva){
        List<Ingrediente> ingredienti = new ArrayList<>();
        String sql = "SELECT * FROM ingrediente i JOIN ingredienti_fornitore if ON i.id = if.idIngrediente WHERE if.partitaIvaFornitore = ?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, partitaIva);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String nome = rs.getString("nome");
                LocalDate scadenza = rs.getDate("scadenza").toLocalDate();
                int id = rs.getInt("id");
                List<Allergene> allergeni = AllergeneDAO.findByIngrediente(id);
                ingredienti.add(new Ingrediente(nome, scadenza, allergeni,id));
            }
        }catch(SQLException e) {
            throw new RuntimeException("Errore nel caricamento degli ingredienti", e);
        }
        return ingredienti;
    }

}
