package concord.tarefa3.dao;

import concord.tarefa3.database.Database;
import concord.tarefa3.models.Mochila;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MochilaDAO {
    
    public void criar(Mochila mochila) {
        String sql = "INSERT INTO mochila (marca, litros, cor, tem_compartimento_notebook) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, mochila.getMarca());
            stmt.setInt(2, mochila.getLitros());
            stmt.setString(3, mochila.getCor());
            stmt.setBoolean(4, mochila.isTemCompartimentoNotebook());
            
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    mochila.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Mochila> listarTodos() {
        List<Mochila> mochilas = new ArrayList<>();
        String sql = "SELECT * FROM mochila";
        
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Mochila mochila = new Mochila();
                mochila.setId(rs.getInt("id"));
                mochila.setMarca(rs.getString("marca"));
                mochila.setLitros(rs.getInt("litros"));
                mochila.setCor(rs.getString("cor"));
                mochila.setTemCompartimentoNotebook(rs.getBoolean("tem_compartimento_notebook"));
                mochilas.add(mochila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return mochilas;
    }
    
    public void atualizar(Mochila mochila) {
        String sql = "UPDATE mochila SET marca = ?, litros = ?, cor = ?, tem_compartimento_notebook = ? WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mochila.getMarca());
            stmt.setInt(2, mochila.getLitros());
            stmt.setString(3, mochila.getCor());
            stmt.setBoolean(4, mochila.isTemCompartimentoNotebook());
            stmt.setInt(5, mochila.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM mochila WHERE id = ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 