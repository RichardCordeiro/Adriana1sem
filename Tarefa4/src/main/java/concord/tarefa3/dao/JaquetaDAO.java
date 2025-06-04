package concord.tarefa3.dao;

import concord.tarefa3.database.Database;
import concord.tarefa3.models.Jaqueta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JaquetaDAO {
    
    public void criar(Jaqueta jaqueta) {
        String sql = "INSERT INTO jaqueta (cor, tamanho, tecido, marca) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jaqueta.getCor());
            stmt.setString(2, jaqueta.getTamanho());
            stmt.setString(3, jaqueta.getTecido());
            stmt.setString(4, jaqueta.getMarca());
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    jaqueta.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Jaqueta> listarTodos() {
        List<Jaqueta> jaquetas = new ArrayList<>();
        String sql = "SELECT * FROM jaqueta";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Jaqueta jaqueta = new Jaqueta();
                jaqueta.setId(rs.getInt("id"));
                jaqueta.setCor(rs.getString("cor"));
                jaqueta.setTamanho(rs.getString("tamanho"));
                jaqueta.setTecido(rs.getString("tecido"));
                jaqueta.setMarca(rs.getString("marca"));
                jaquetas.add(jaqueta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jaquetas;
    }

    public void atualizar(Jaqueta jaqueta) {
        String sql = "UPDATE jaqueta SET cor = ?, tamanho = ?, tecido = ?, marca = ? WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jaqueta.getCor());
            stmt.setString(2, jaqueta.getTamanho());
            stmt.setString(3, jaqueta.getTecido());
            stmt.setString(4, jaqueta.getMarca());
            stmt.setInt(5, jaqueta.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM jaqueta WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 