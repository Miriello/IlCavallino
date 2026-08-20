package DAO;

import Database.DatabaseManager;
import Item.Allergene;
import Item.Ingrediente;
import Utility.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredienteDAO extends ArticoloDAO {

    public List<Ingrediente> findAll (){

        List<Ingrediente> ingredienti = new ArrayList<>();

        String sql = "SELECT * FROM ingredienti";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String nome = rs.getString("nome");
                Date dataSql = rs.getDate("scadenza");
                Data scadenza = new Data(dataSql.toLocalDate().getDayOfMonth(),dataSql.toLocalDate().getMonthValue(),dataSql.toLocalDate().getYear());
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
        //TODO
    }

    public void delete(Ingrediente ingrediente){
        //TODO
    }

    public void update(Ingrediente ingrediente){
        //TODO
    }

    public void  findById(int id){
        //TODO
    }

    public static List<Ingrediente> findByPiatto(int idPiatto){
        List<Ingrediente> ingredienti = new ArrayList<>();
        String sql = "SELECT i FROM ingredienti_piatto ip JOIN ingredienti i ON ip.idIngrediente=i.idIngrediente WHERE ip.idIngrediente = ?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String nome = rs.getString("nome");
                Date dataSql = rs.getDate("scadenza");
                Data scadenza = new Data(dataSql.toLocalDate().getDayOfMonth(),dataSql.toLocalDate().getMonthValue(),dataSql.toLocalDate().getYear());
                int id = rs.getInt("id");
                List<Allergene> allergeni = AllergeneDAO.findByIngrediente(id);
                ingredienti.add(new Ingrediente(nome, scadenza, allergeni));
            }

        } catch(SQLException e){
            throw new RuntimeException("Caricamento degli ingredienti non riuscito", e);
        }
        return ingredienti;
    }

    public static List<Ingrediente> findByFornitore(int idFornitore){
        List<Ingrediente> ingredienti = new ArrayList<>();
        return ingredienti;
    }

}
