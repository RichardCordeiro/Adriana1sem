package concord.tarefa3.dao;

import concord.tarefa3.database.Database;
import concord.tarefa3.models.Tenis;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TenisDAO {
    
    public void criar(Tenis tenis) {
        String sql = "INSERT INTO tenis (cor, tamanho, material, marca, esportivo) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, tenis.getCor());
            stmt.setString(2, tenis.getTamanho());
            stmt.setString(3, tenis.getMaterial());
            stmt.setString(4, tenis.getMarca());
            stmt.setBoolean(5, tenis.isEsportivo());
            
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    tenis.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Tenis> listarTodos() {
        List<Tenis> tenis = new ArrayList<>();
        String sql = "SELECT * FROM tenis";
        
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Tenis tenisAtual = new Tenis();
                tenisAtual.setId(rs.getInt("id"));
                tenisAtual.setCor(rs.getString("cor"));
                tenisAtual.setTamanho(rs.getString("tamanho"));
                tenisAtual.setMaterial(rs.getString("material"));
                tenisAtual.setMarca(rs.getString("marca"));
                tenisAtual.setEsportivo(rs.getBoolean("esportivo"));
                tenis.add(tenisAtual);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tenis;
    }

    public void atualizar(Tenis tenis) {
        String sql = "UPDATE tenis SET cor = ?, tamanho = ?, material = ?, marca = ?, esportivo = ? WHERE id = ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, tenis.getCor());
            stmt.setString(2, tenis.getTamanho());
            stmt.setString(3, tenis.getMaterial());
            stmt.setString(4, tenis.getMarca());
            stmt.setBoolean(5, tenis.isEsportivo());
            stmt.setInt(6, tenis.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM tenis WHERE id = ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 