package concord.tarefa3.dao;

import concord.tarefa3.database.Database;
import concord.tarefa3.models.Camiseta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CamisetaDAO {
    
    public void criar(Camiseta camiseta) {
        String sql = "INSERT INTO camiseta (cor, tamanho, tecido, marca) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, camiseta.getCor());
            stmt.setString(2, camiseta.getTamanho());
            stmt.setString(3, camiseta.getTecido());
            stmt.setString(4, camiseta.getMarca());
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    camiseta.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Camiseta> listarTodos() {
        List<Camiseta> camisetas = new ArrayList<>();
        String sql = "SELECT * FROM camiseta";
        
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Camiseta camiseta = new Camiseta();
                camiseta.setId(rs.getInt("id"));
                camiseta.setCor(rs.getString("cor"));
                camiseta.setTamanho(rs.getString("tamanho"));
                camiseta.setTecido(rs.getString("tecido"));
                camiseta.setMarca(rs.getString("marca"));
                camisetas.add(camiseta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return camisetas;
    }
    
    public void atualizar(Camiseta camiseta) {
        String sql = "UPDATE camiseta SET cor = ?, tamanho = ?, tecido = ?, marca = ? WHERE id = ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, camiseta.getCor());
            stmt.setString(2, camiseta.getTamanho());
            stmt.setString(3, camiseta.getTecido());
            stmt.setString(4, camiseta.getMarca());
            stmt.setInt(5, camiseta.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deletar(int id) {
        String sql = "DELETE FROM camiseta WHERE id = ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 