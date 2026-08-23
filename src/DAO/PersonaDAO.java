package DAO;

import Database.DatabaseManager;
import Persone.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    public List<Persona> findAll() {
        String sql = "SELECT * FROM persone";
        List<Persona> persone = new ArrayList<>();
        try {
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String cf = rs.getString("cf");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                int idRuolo = rs.getInt("idRuolo");
                persone.add(new Persona(nome,cognome,cf));
            }
            return persone;
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel caricamento delle persone",e);
        }
    }

    public void insert(Persona p){
        String sql = "INSERT INTO persone (cf, nome, cognome) VALUES (?,?,?)";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,p.getCodiceFiscale());
            stmt.setString(2,p.getNome());
            stmt.setString(3,p.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errone nell'inserimento della persona", e);
        }
    }

    public void update(Persona p){
        String sql ="UPDATE persone SET nome = ?, cognome = ? WHERE cf=?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,p.getNome());
            stmt.setString(2,p.getCognome());
            stmt.setString(3,p.getCodiceFiscale());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errore nell'aggiornamento della persona",e);
        }
    }

    public void delete(Persona p){
        String sql = "DELETE FROM persone WHERE cf=?";
        try {
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,p.getCodiceFiscale());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella cancellazione della persona",e);
        }
    }

    public Persona findByCf(String cf){
        String sql = "SELECT * FROM persone WHERE cf =?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,cf);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                return new Persona(nome,cognome,cf);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella ricerca della persona",e);
        }
        return null;
    }
}
