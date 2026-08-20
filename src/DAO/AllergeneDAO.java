package DAO;

import Database.DatabaseManager;
import Item.Allergene;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AllergeneDAO {

    public List<Allergene> findAll() {
        List<Allergene> allergeni = new ArrayList<>();

        String sql ="SELECT * FROM allergeni";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String nome = rs.getString("nome");
                int codice = rs.getInt("codiceAllergene");
                allergeni.add(new Allergene(nome,codice));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel caricamento degli allergeni", e);
        }
        return allergeni;
    }
    public void insert(Allergene a){

    }
    public void update(Allergene a) {

    }
    public void delete (Allergene a){

    }
    public Allergene findById(int id){
        return new Allergene();
    }



}
