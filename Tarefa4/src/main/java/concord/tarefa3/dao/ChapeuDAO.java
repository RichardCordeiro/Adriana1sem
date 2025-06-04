package concord.tarefa3.dao;

import concord.tarefa3.database.Database;
import concord.tarefa3.models.Chapeu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChapeuDAO {

    public void criar(Chapeu chapeu) {
        String sql = "INSERT INTO chapeu (cor, tamanho, material, marca) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, chapeu.getCor());
            stmt.setString(2, chapeu.getTamanho());
            stmt.setString(3, chapeu.getMaterial());
            stmt.setString(4, chapeu.getMarca());
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    chapeu.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Chapeu> listarTodos() {
        List<Chapeu> chapeus = new ArrayList<>();
        String sql = "SELECT * FROM chapeu";
        
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Chapeu chapeu = new Chapeu();
                chapeu.setId(rs.getInt("id"));
                chapeu.setCor(rs.getString("cor"));
                chapeu.setTamanho(rs.getString("tamanho"));
                chapeu.setMaterial(rs.getString("material"));
                chapeu.setMarca(rs.getString("marca"));
                chapeus.add(chapeu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return chapeus;
    }

    public void atualizar(Chapeu chapeu) {
        String sql = "UPDATE chapeu SET cor = ?, tamanho = ?, material = ?, marca = ? WHERE id = ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, chapeu.getCor());
            stmt.setString(2, chapeu.getTamanho());
            stmt.setString(3, chapeu.getMaterial());
            stmt.setString(4, chapeu.getMarca());
            stmt.setInt(5, chapeu.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM chapeu WHERE id = ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 