package DAO;

import Database.DatabaseManager;
import Utility.Pagamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {

    public List<Pagamento> findAll(){
        String sql = "SELECT * FROM pagamenti";
        List<Pagamento> pagamenti = new ArrayList<>();
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int idVendita = rs.getInt("idVendita");
                double importo = rs.getDouble("importo");
                String metodoPagamento = rs.getString("metodoPagamento");
                pagamenti.add(new Pagamento(idVendita, importo, metodoPagamento));
            }
            return pagamenti;
        } catch (SQLException e){
            throw new RuntimeException("Errore nel caricamento dei pagamenti");
        }
    }

    public int insert(Pagamento p){
        String sql = "INSERT INTO pagamenti (idVendita, importo, metodoPagamento) VALUES (?,?,?)";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,p.getIdVendita());
            stmt.setDouble(2,p.getImporto());
            stmt.setString(3,p.getMetodoPagamento());
            stmt.executeUpdate();
        } catch (SQLException e ){
            throw new RuntimeException("Errore nell'inserimento del pagamento",e);
        }
        return p.getIdVendita();
    }

    public void update(Pagamento p){
        String sql = "UPDATE pagamenti SET importo =? metodoPagamento=? WHERE idVendita=?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1,p.getImporto());
            stmt.setString(2,p.getMetodoPagamento());
            stmt.setInt(3,p.getIdVendita());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errore nell'aggiornamento del pagamento",e);
        }
    }

    public void delete(Pagamento p){
        String sql = "DELETE FROM pagamenti WHERE idVendita=?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,p.getIdVendita());
            stmt.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Errore nella cancellazione del pagamento",e);
        }
    }

    public Pagamento findByVendita(int idVendita){
        String sql = "SELECT * FROM pagamenti WHERE idVendita =?";
        try{
            Connection conn = DatabaseManager.getConnessione();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,idVendita);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                double importo = rs.getDouble("importo");
                String metodoPagamento = rs.getString("metodoPagamento");
                return new Pagamento(idVendita, importo, metodoPagamento);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella ricerca del pagamento", e);
        }
        return null;
    }

}
