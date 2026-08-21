package DAO;

import Database.DatabaseManager;
import Item.Ingrediente;
import Persone.Fornitore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornitoreDAO {
    public List<Fornitore> findAll(){
        List<Fornitore> fornitori = new ArrayList<>();
        String sql = "SELECT * FROM fornitori";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String pIva = rs.getString("partitaIva");
                String rS = rs.getString("ragioneSociale");
                String email = rs.getString("email");
                List<Ingrediente> articoli = IngredienteDAO.findByFornitore(pIva);
                fornitori.add(new Fornitore(pIva, rS, email, articoli));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel caricamento dei fornitori", e);
        }
        return fornitori;
    }

    public void insert (Fornitore f){
        String sql = "INSERT INTO fornitori (partitaIva, ragioneSociale, email) VALUES (?,?,?)";
        String sql1 = "INSERT INTO ingredienti_fornitore (idIngrediente, partitaIvaFornitore) VALUES (?,?)";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, f.getPartitaIva());
            stmt.setString(2, f.getRagioneSociale());
            stmt.setString(3, f.getEmail());
            stmt.executeUpdate();
            for(Ingrediente i: f.getBeniForniti()){
                PreparedStatement stmt1= conn.prepareStatement(sql1);
                stmt1.setInt(1,i.getId());
                stmt1.setString(2,f.getPartitaIva());
                stmt1.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel caricamento del fornitore", e);
        }
    }

    public void update (Fornitore f){
        String sql = "UPDATE fornitori " + "SET ragioneSociale = ?, email=? " + "WHERE partitaIva=?";
        String sql1= "DELETE FROM ingredienti_fornitore " + "WHERE partitaIvaFornitore= ?";
        String sql2 = "INSERT INTO ingredienti_fornitore (idIngrediente, partitaIvaFornitore) VALUES (?,?)";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(3,f.getPartitaIva());
            stmt.setString(1, f.getRagioneSociale());
            stmt.setString(2,f.getEmail());
            stmt.executeUpdate();
            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            stmt1.executeUpdate();
            for(Ingrediente i: f.getBeniForniti()){
                PreparedStatement stmt2 = conn.prepareStatement(sql2);
                stmt2.setInt(1,i.getId());
                stmt2.setString(2,f.getPartitaIva());
            }
        }catch(SQLException e){
            throw new RuntimeException("Errore nell'aggiornamento del fornitore",e);
        }
    }
}
