package dao;

import domain.Bug;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BugDAO extends GenericDAO{
    public BugDAO() {
        super();
    }

    public Long cadastrar(Bug bug) {
        String sql = "INSERT INTO Bug (descricao, sessao_id) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, bug.getDescricao());
            stmt.setLong(2, bug.getSessaoId());
            stmt.executeUpdate();


            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long id = generatedKeys.getLong(1);
                    bug.setIdBug(id);
                    return id;
                } else {
                    throw new SQLException("Falha ao obter ID gerado do bug.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Bug> listarPorSessao(Long sessaoId) throws SQLException {
        List<Bug> bugs = new ArrayList<>();
        String sql = "SELECT * FROM Bug WHERE sessao_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, sessaoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Bug bug = new Bug();
                bug.setIdBug(rs.getLong("id_bug"));
                bug.setDescricao(rs.getString("descricao"));
                bug.setDataCriacao(rs.getTimestamp("data_criacao"));
                bug.setSessaoId(rs.getLong("sessao_id"));
                bugs.add(bug);
            }
        }
        return bugs;
    }
}
