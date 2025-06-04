package concord.tarefa3.dao;

import concord.tarefa3.database.Database;
import concord.tarefa3.models.Calca;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalcaDAO {
    
    public void criar(Calca calca) {
        String sql = "INSERT INTO calca (tamanho, cor, tecido, marca) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, calca.getTamanho());
            stmt.setString(2, calca.getCor());
            stmt.setString(3, calca.getTecido());
            stmt.setString(4, calca.getMarca());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    calca.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Calca> listarTodos() {
        List<Calca> calcas = new ArrayList<>();
        String sql = "SELECT * FROM calca";
        
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Calca calca = new Calca(
                    rs.getString("cor"),
                    rs.getString("tamanho"),
                    rs.getString("tecido"),
                    rs.getString("marca")
                );
                calca.setId(rs.getInt("id"));
                calcas.add(calca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return calcas;
    }
    
    public void atualizar(Calca calca) {
        String sql = "UPDATE calca SET tamanho = ?, cor = ?, tecido = ?, marca = ? WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, calca.getTamanho());
            stmt.setString(2, calca.getCor());
            stmt.setString(3, calca.getTecido());
            stmt.setString(4, calca.getMarca());
            stmt.setInt(5, calca.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deletar(int id) {
        String sql = "DELETE FROM calca WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 