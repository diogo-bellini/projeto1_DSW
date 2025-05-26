package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioProjetoDAO extends GenericDAO {

    public void associarUsuarioProjeto(Long usuarioId, Long projetoId) throws SQLException {
        String sql = "INSERT INTO UsuarioProjeto (usuario_id, projeto_id) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, usuarioId);
            stmt.setLong(2, projetoId);
            stmt.executeUpdate();
        }
    }

    public void removerAssociacoesProjeto(Long projetoId) throws SQLException {
        String sql = "DELETE FROM UsuarioProjeto WHERE projeto_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, projetoId);
            stmt.executeUpdate();
        }
    }

    public List<Long> getUsuariosAssociados(Long projetoId) throws SQLException {
        List<Long> usuariosIds = new ArrayList<>();
        String sql = "SELECT usuario_id FROM UsuarioProjeto WHERE projeto_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, projetoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    usuariosIds.add(rs.getLong("usuario_id"));
                }
            }
        }
        return usuariosIds;
    }

    public List<Long> getProjetosAssociados(Long usuarioId) throws SQLException {
        List<Long> projetosIds = new ArrayList<>();
        String sql = "SELECT projeto_id FROM UsuarioProjeto WHERE usuario_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    projetosIds.add(rs.getLong("projeto_id"));
                }
            }
        }
        return projetosIds;
    }
}