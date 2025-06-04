package concord.tarefa3.dao;

import concord.tarefa3.database.Database;
import concord.tarefa3.models.Saia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaiaDAO {
    
    public void criar(Saia saia) {
        String sql = "INSERT INTO saia (cor, tecido, comprimento, plissada) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, saia.getCor());
            stmt.setString(2, saia.getTecido());
            stmt.setDouble(3, saia.getComprimento());
            stmt.setBoolean(4, saia.isPlissada());
            
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    saia.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Saia> listarTodos() {
        List<Saia> saias = new ArrayList<>();
        String sql = "SELECT * FROM saia";
        
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Saia saia = new Saia();
                saia.setId(rs.getInt("id"));
                saia.setCor(rs.getString("cor"));
                saia.setTecido(rs.getString("tecido"));
                saia.setComprimento(rs.getDouble("comprimento"));
                saia.setPlissada(rs.getBoolean("plissada"));
                saias.add(saia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return saias;
    }
    
    public void atualizar(Saia saia) {
        String sql = "UPDATE saia SET cor = ?, tecido = ?, comprimento = ?, plissada = ? WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, saia.getCor());
            stmt.setString(2, saia.getTecido());
            stmt.setDouble(3, saia.getComprimento());
            stmt.setBoolean(4, saia.isPlissada());
            stmt.setInt(5, saia.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM saia WHERE id = ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 