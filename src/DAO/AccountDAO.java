package DAO;

import Database.DatabaseManager;
import Utility.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
    public void insert(Account a){
        String sql = "INSERT INTO account (username,password,cfOperatore) VALUES (?,?,?)" ;
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,a.getUsername());
            stmt.setString(2,a.getPassword());
            stmt.setString(3,a.getCfOperatore());
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Errore nell'inserimento dell'account",e);
        }
    }

    public void updatePassword(Account a){
        String sql = "UPDATE account SET password = ? WHERE username=?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(2,a.getUsername());
            stmt.setString(1,a.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Errore nel aggiornamento della password",e);
        }
    }

    public void updateUsername(String vecchioUsername, String nuovoUsername){
        String sql= "UPDATE account SET username = ? WHERE username = ?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,nuovoUsername);
            stmt.setString(2, vecchioUsername);
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Errore nel aggiornamento dell'username",e);
        }
    }

    public void delete(Account a ){
        String sql = "DELETE FROM account WHERE username = ?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,a.getUsername());
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Errore nell'inserimento dell'account",e);
        }
    }

    public Account findByUsername(String username){
        String sql = "SELECT * FROM account WHERE username = ?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String password = rs.getString("password");
                String cfOperatore = rs.getString("cfOperatore");
                return new Account(username,password,cfOperatore);
            }
        } catch (SQLException e){
            throw new RuntimeException("Errore nell'inserimento dell'account",e);
        }
        return null;
    }
}
