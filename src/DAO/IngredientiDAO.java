package DAO;

import Database.DatabaseManager;
import Item.Ingrediente;
import Utility.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientiDAO {

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
                Ingrediente ingrediente = new Ingrediente (nome, scadenza, new ArrayList<>());
                ingredienti.add(ingrediente);
            }
        } catch (SQLException e ){
            throw new RuntimeException( " Errore durante il caricamento ", e);
        }
        return ingredienti;
    }

    public void insert(Ingrediente ingrediente){

    }

    public void delete(Ingrediente ingrediente){

    }

    public void update(Ingrediente ingrediente){

    }

    public Ingrediente findById(int id){
        return new Ingrediente();
    }



}
