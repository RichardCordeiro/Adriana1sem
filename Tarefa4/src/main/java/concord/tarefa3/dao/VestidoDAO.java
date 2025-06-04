package concord.tarefa3.dao;

import concord.tarefa3.database.Database;
import concord.tarefa3.models.Vestido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VestidoDAO {

    public void criar(Vestido vestido) {
        String sql = "INSERT INTO vestido (cor, tamanho, tecido, marca) VALUES ( ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vestido.getCor());
            stmt.setString(2, vestido.getTamanho());
            stmt.setString(3, vestido.getTecido());
            stmt.setString(4, vestido.getMarca());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    vestido.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Vestido> listarTodos() {
        List<Vestido> vestidos = new ArrayList<>();
        String sql = "SELECT * FROM vestido";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Vestido vestido = new Vestido();
                vestido.setId(rs.getInt("id"));
                vestido.setCor(rs.getString("cor"));
                vestido.setTamanho(rs.getString("tamanho"));
                vestido.setTecido(rs.getString("tecido"));
                vestido.setMarca(rs.getString("marca"));
                vestidos.add(vestido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vestidos;
    }

    public void atualizar(Vestido vestido) {
        String sql = "UPDATE vestido SET cor = ?, tamanho = ?, tecido = ?, marca = ? WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vestido.getCor());
            stmt.setString(2, vestido.getTamanho());
            stmt.setString(3, vestido.getTecido());
            stmt.setString(4, vestido.getMarca());
            stmt.setInt(5, vestido.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM vestido WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 