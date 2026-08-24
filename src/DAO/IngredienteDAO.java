package DAO;

import Database.DatabaseManager;
import Item.Allergene;
import Item.Ingrediente;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static DAO.AllergeneDAO.findByIngrediente;

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
                List<Allergene> allergeni = findByIngrediente(id);
                Ingrediente ingrediente = new Ingrediente (nome, scadenza, allergeni,id);
                ingredienti.add(ingrediente);
            }
        } catch (SQLException e ){
            throw new RuntimeException( " Errore durante il caricamento ", e);
        }
        return ingredienti;
    }

    public void insert(Ingrediente ingrediente){
        String sql = "INSERT INTO ingredienti (nome,scadenza) VALUES (?,?)";
        String sql1 = "INSERT INTO allergeni_ingrediente (codiceAllergene, idIngrediente) VALUES (?,?)";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,ingrediente.getNome());
            stmt.setDate(2, java.sql.Date.valueOf(ingrediente.getScadenza()));
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                int idIngrediente = rs.getInt(1);
                for(Allergene a : ingrediente.getAllergeni()){
                    PreparedStatement stmt1 = conn.prepareStatement(sql1);
                    stmt1.setInt(1,a.getCodiceAllergene());
                    stmt1.setInt(2,idIngrediente);
                    stmt1.executeUpdate();
                }
            }
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
        String sql = "UPDATE ingredienti " + "SET nome = ?, scadenza = ? " + "WHERE id=?";
        String sql1 = "DELETE FROM allergeni_ingrediente WHERE idIngrediente = ?";
        String sql2 = "INSERT INTO allergeni_ingrediente (codiceAllergene, idIngrediente) VALUES (?,?)";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,ingrediente.getNome());
            stmt.setDate(2,java.sql.Date.valueOf(ingrediente.getScadenza()));
            stmt.setInt(3,ingrediente.getId());
            stmt.executeUpdate();
            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            stmt1.setInt(1,ingrediente.getId());
            stmt1.executeUpdate();
            for(Allergene a: ingrediente.getAllergeni()){
                PreparedStatement stmt2=conn.prepareStatement(sql2);
                stmt2.setInt(1,a.getCodiceAllergene());
                stmt2.setInt(2,ingrediente.getId());
                stmt2.executeUpdate();
            }
        }catch(SQLException e){
            throw new RuntimeException("Errore nell'aggiornamento dell'ingrediente",e);
        }
    }


    public Ingrediente findById(int id){
        String sql = "SELECT * FROM ingredienti WHERE id = ?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                String nome = rs.getString("nome");
                LocalDate scadenza = rs.getDate("scadenza").toLocalDate();
                List<Allergene> allergeni = findByIngrediente(id);
                return new Ingrediente(nome,scadenza,allergeni,id);
            }
        } catch(SQLException e){
            throw new RuntimeException("Errore nella ricerca dell'ingrediente");
        }
        return null;
    }

    public static Map<Ingrediente,Double> findByPiatto(int idPiatto){
        Map<Ingrediente,Double> ingredienti = new HashMap<>();
        String sql = "SELECT * FROM ingredienti_piatto ip JOIN ingredienti i ON ip.idIngrediente=i.id WHERE ip.idPiatto= ?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,idPiatto);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String nome = rs.getString("nome");
                LocalDate scadenza = rs.getDate("scadenza").toLocalDate();
                int id = rs.getInt("id");
                Double quantita = rs.getDouble("quantita");
                List<Allergene> allergeni = findByIngrediente(id);
                ingredienti.put(new Ingrediente(nome, scadenza, allergeni,id),quantita);
            }
        } catch(SQLException e){
            throw new RuntimeException("Caricamento degli ingredienti non riuscito", e);
        }
        return ingredienti;
    }

    public static List<Ingrediente> findByFornitore(String partitaIva){
        List<Ingrediente> ingredienti = new ArrayList<>();
        String sql = "SELECT * FROM ingredienti i JOIN ingredienti_fornitore if ON i.id = if.idIngrediente WHERE if.partitaIvaFornitore = ?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, partitaIva);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String nome = rs.getString("nome");
                LocalDate scadenza = rs.getDate("scadenza").toLocalDate();
                int id = rs.getInt("id");
                List<Allergene> allergeni = findByIngrediente(id);
                ingredienti.add(new Ingrediente(nome, scadenza, allergeni,id));
            }
        }catch(SQLException e) {
            throw new RuntimeException("Errore nel caricamento degli ingredienti", e);
        }
        return ingredienti;
    }

}
