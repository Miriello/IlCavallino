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
        //TODO
    }
    public void update(Allergene a) {
        //TODO
    }
    public void delete (Allergene a){
        //TODO
    }
    public void findById(int id){
        //TODO
    }

    public static List<Allergene> findByIngrediente(int idIngrediente) {
        List<Allergene> allergeni = new ArrayList<>();
        String sql = "SELECT * FROM allergeni a JOIN ingredienti_allergeni ia ON a.id = ia.id_allergene WHERE ia.id_ingrediente = ?";
        try {
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String nome = rs.getString("nome");
                int codice = rs.getInt("codiceAllergene");
                allergeni.add(new Allergene(nome,codice));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Errore nel caricamento degli allergeni", e);
        }
        return allergeni;
    }
}
