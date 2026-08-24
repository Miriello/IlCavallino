package DAO;

import Database.DatabaseManager;
import Item.Ingrediente;
import Persone.Fornitore;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static DAO.IngredienteDAO.findByFornitore;

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
                Map<Ingrediente,Double> articoli = findByFornitore(pIva);
                fornitori.add(new Fornitore(pIva, rS, email, articoli));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel caricamento dei fornitori", e);
        }
        return fornitori;
    }

    public void insert (Fornitore f){
        String sql = "INSERT INTO fornitori (partitaIva, ragioneSociale, email) VALUES (?,?,?)";
        String sql1 = "INSERT INTO ingredienti_fornitore (idIngrediente, partitaIvaFornitore, costoUnitario) VALUES (?,?,?)";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, f.getPartitaIva());
            stmt.setString(2, f.getRagioneSociale());
            stmt.setString(3, f.getEmail());
            stmt.executeUpdate();
            for(Map.Entry<Ingrediente,Double> entry : f.getBeniForniti().entrySet()){
                Ingrediente i = entry.getKey();
                double costoUnitario =entry.getValue();
                PreparedStatement stmt1= conn.prepareStatement(sql1);
                stmt1.setInt(1,i.getId());
                stmt1.setString(2,f.getPartitaIva());
                stmt1.setDouble(3,costoUnitario);
                stmt1.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nell'inserimento del fornitore", e);
        }
    }

    public void update (Fornitore f){
        String sql = "UPDATE fornitori SET ragioneSociale = ?, email=? WHERE partitaIva=?";
        String sql1= "DELETE FROM ingredienti_fornitore " + "WHERE partitaIvaFornitore= ?";
        String sql2 = "INSERT INTO ingredienti_fornitore (idIngrediente, partitaIvaFornitore,costoUnitario) VALUES (?,?,?)";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(3,f.getPartitaIva());
            stmt.setString(1, f.getRagioneSociale());
            stmt.setString(2,f.getEmail());
            stmt.executeUpdate();
            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            stmt1.setString(1,f.getPartitaIva());
            stmt1.executeUpdate();
            for(Map.Entry<Ingrediente, Double> entry : f.getBeniForniti().entrySet()){
                Ingrediente i = entry.getKey();
                Double costoUnitario = entry.getValue();
                PreparedStatement stmt2 = conn.prepareStatement(sql2);
                stmt2.setInt(1,i.getId());
                stmt2.setString(2,f.getPartitaIva());
                stmt2.setDouble(3,costoUnitario);
                stmt2.executeUpdate();
            }
        }catch(SQLException e){
            throw new RuntimeException("Errore nell'aggiornamento del fornitore",e);
        }
    }

    public void delete(Fornitore f){
        String sql = "DELETE FROM fornitori WHERE partitaIva = ? ";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,f.getPartitaIva());
            stmt.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Errore nell'eliminazione del fornitore",e);
        }
    }

    public Fornitore findByPartitaIva(String pIva){
        String sql = "SELECT * FROM fornitori WHERE partitaIva = ?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,pIva);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                String ragioneSociale = rs.getString("ragioneSociale");
                String email = rs.getString("email");
                Map<Ingrediente,Double> articoli = findByFornitore(pIva);
                return new Fornitore(pIva, ragioneSociale, email, articoli);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Errore nella ricerca del fornitore",e);
        }
        return null;
    }
}
