package DAO;

import Database.DatabaseManager;
import Gestori.Menu;
import Item.Piatto;

import java.sql.*;
import java.time.LocalDate;
import java.util.Map;

public class MenuDAO {

    public void findAll(){
        String sql = "SELECT * FROM menu";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");

            }
        } catch (SQLException e){
            throw new RuntimeException("Errore nel caricamento dei menu",e);
        }
    }

    public void insert(Menu m){
        String sql = "INSERT INTO menu (dataMenu) VALUES (?)";
        String sql1= "INSERT INTO piatti_menu (idMenu, idPiatto,prezzoVendita) VALUES (?,?,?)";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1,java.sql.Date.valueOf(m.getData()));
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                int idMenu = rs.getInt(1);
                PreparedStatement stmt1 = conn.prepareStatement(sql1);
                stmt1.setInt(1,idMenu);
                for(Map.Entry<Piatto,Double> entry : m.getProdotti().entrySet()){
                    Piatto p = entry.getKey();
                    double prezzo = entry.getValue();
                    stmt1.setInt(2,p.getId());
                    stmt1.setDouble(3,prezzo);
                    stmt1.executeUpdate();
                }
            }
        } catch(SQLException e){
            throw new RuntimeException("Errore nell'inserimento del menu",e);
        }
    }

    public void update(Menu m){
        String sql = ""
    }

    public void delete(Menu m){
        String sql = "DELETE FROM menu WHERE id =?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,m.getId());
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Errore nella cancellazione del menu",e);
        }
    }

    public Menu findById(int idMenu){
        String sql = "SELECT * FROM menu WHERE id =?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,idMenu);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                LocalDate data = rs.getDate("dataMenu").toLocalDate();
                Map<Piatto,Double> piatti = PiattoDAO.findByMenu(idMenu);
                return new Menu(idMenu,piatti,data);
            }
        } catch (SQLException e){
            throw new RuntimeException("Errore nella ricerca del menu",e);
        }
        return null;
    }

}
