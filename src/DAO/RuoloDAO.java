package DAO;

import Database.DatabaseManager;
import Utility.Ruolo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RuoloDAO {

    public List<Ruolo> findAll(){
        List<Ruolo> ruoli = new ArrayList<>();
        String sql = "SELECT * FROM ruoli";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int idRuolo = rs.getInt("id");
                String nomeRuolo = rs.getString("nomeRuolo");
                ruoli.add(new Ruolo(idRuolo,nomeRuolo));
            }
            return ruoli;
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel caricamento dei ruoli", e);
        }
    }
    public Ruolo findById(int idRuolo){
        String sql = "SELECT * FROM ruoli WHERE id=?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,idRuolo);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String nomeRuolo = rs.getString("nomeRuolo");
                return new Ruolo(idRuolo, nomeRuolo);
            }
        } catch (SQLException e){
            throw new RuntimeException("Errone nella ricerca del ruolo",e);
        }
        return null;
    }
}
