package DAO;

import Database.DatabaseManager;
import Gestori.Menu;
import Item.Piatto;

import javax.print.attribute.standard.RequestingUserName;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuDAO {

    public List<Menu> findAll(){
        String sql = "SELECT * FROM menu";
        List<Menu> risultato = new ArrayList<>();
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                LocalDate data = rs.getDate("dataMenu").toLocalDate();
                Map<Piatto,Double> piatti = PiattoDAO.findByMenu(id);
                risultato.add(new Menu(id,piatti, data));
            }
            return risultato;
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
        String sql = "UPDATE menu SET dataMenu = ? WHERE id = ?";
        String sql1 = "DELETE FROM piatti_menu WHERE idMenu=?";
        String sql2 = "INSERT INTO piatti_menu (idMenu,idPiatto,prezzoVendita) VALUES (?,?,?)";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1,java.sql.Date.valueOf(m.getData()));
            stmt.setInt(2,m.getId());
            stmt.executeUpdate();
            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            stmt1.setInt(1,m.getId());
            stmt1.executeUpdate();
            for (Map.Entry<Piatto,Double> entry : m.getProdotti().entrySet()){
                PreparedStatement stmt2 = conn.prepareStatement(sql2);
                stmt2.setInt(1, m.getId());
                stmt2.setInt(2,entry.getKey().getId());
                stmt2.setDouble(3,entry.getValue());
                stmt2.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nell'aggiornamento del menu",e);
        }
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

    public Menu findByData(LocalDate data){
        String sql = "SELECT * FROM menu WHERE dataMenu = ?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                Menu m = findById(id);
                return m;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel caricamento del menu",e);
        }
        return null;
    }

}
