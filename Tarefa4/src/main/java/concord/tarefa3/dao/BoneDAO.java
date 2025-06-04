package concord.tarefa3.dao;

import concord.tarefa3.database.Database;
import concord.tarefa3.models.Bone;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoneDAO {

    public void criar(Bone bone) {
        String sql = "INSERT INTO bone (cor, estilo, tamanho, marca) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bone.getCor());
            stmt.setString(2, bone.getEstilo());
            stmt.setString(3, bone.getTamanho());
            stmt.setString(4, bone.getMarca());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    bone.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Bone> listarTodos() {
        List<Bone> bones = new ArrayList<>();
        String sql = "SELECT * FROM bone";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Bone bone = new Bone();
                bone.setId(rs.getInt("id"));
                bone.setCor(rs.getString("cor"));
                bone.setEstilo(rs.getString("estilo"));
                bone.setTamanho(rs.getString("tamanho"));
                bone.setMarca(rs.getString("marca"));
                bones.add(bone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bones;
    }

    public void atualizar(Bone bone) {
        String sql = "UPDATE bone SET cor = ?, estilo = ?, tamanho = ?, marca = ? WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bone.getCor());
            stmt.setString(2, bone.getEstilo());
            stmt.setString(3, bone.getTamanho());
            stmt.setString(4, bone.getMarca());
            stmt.setInt(5, bone.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM bone WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 