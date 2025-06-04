package concord.tarefa3.dao;

import concord.tarefa3.database.Database;
import concord.tarefa3.models.Shorts;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShortsDAO {

    public void criar(Shorts shorts) {
        String sql = "INSERT INTO shorts (cor, tamanho, tecido, marca) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, shorts.getCor());
            stmt.setString(2, shorts.getTamanho());
            stmt.setString(3, shorts.getTecido());
            stmt.setString(4, shorts.getMarca());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    shorts.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Shorts> listarTodos() {
        List<Shorts> shortsList = new ArrayList<>();
        String sql = "SELECT * FROM shorts";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Shorts shorts = new Shorts();
                shorts.setId(rs.getInt("id"));
                shorts.setCor(rs.getString("cor"));
                shorts.setTamanho(rs.getString("tamanho"));
                shorts.setTecido(rs.getString("tecido"));
                shorts.setMarca(rs.getString("marca"));
                shortsList.add(shorts);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shortsList;
    }

    public void atualizar(Shorts shorts) {
        String sql = "UPDATE shorts SET cor = ?, tamanho = ?, tecido = ?, marca = ? WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, shorts.getCor());
            stmt.setString(2, shorts.getTamanho());
            stmt.setString(3, shorts.getTecido());
            stmt.setString(4, shorts.getMarca());
            stmt.setInt(5, shorts.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM shorts WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 