package dao;

import domain.SessaoTeste;
import domain.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessaoTesteDAO extends GenericDAO {

    public SessaoTesteDAO() {
        super();
    }

    public Long cadastrar(SessaoTeste sessao) throws SQLException {
        String sql = "INSERT INTO SessaoTeste(descricao, nome_testador, estrategia_id, tempo, projeto_id, usuario_id, status, data_criacao) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, sessao.getDescricao());
            stmt.setString(2, sessao.getNomeTestador());
            stmt.setLong(3, sessao.getEstrategiaId());
            stmt.setInt(4, sessao.getTempo());
            stmt.setLong(5, sessao.getProjetoId());
            stmt.setLong(6, sessao.getUsuarioId());
            stmt.setString(7, sessao.getStatus().toString());
            stmt.setTimestamp(8, sessao.getDataCriacao());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }

            return null;
        }
    }

    public List<SessaoTeste> getListaSessaoUsuario(Long idUsuario) {
        String sql = "SELECT * FROM SessaoTeste WHERE usuario_id = ?";

        List<SessaoTeste> sessoes = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                SessaoTeste sessao = new SessaoTeste();

                sessao.setIdSessao(rs.getLong("id_sessao"));
                sessao.setDescricao(rs.getString("descricao"));
                sessao.setNomeTestador(rs.getString("nome_testador"));
                sessao.setEstrategiaId(rs.getLong("estrategia_id"));
                sessao.setTempo(rs.getInt("tempo"));
                sessao.setProjetoId(rs.getLong("projeto_id"));
                sessao.setUsuarioId(rs.getLong("usuario_id"));
                sessao.setStatus(Status.fromString(rs.getString("status")));
                sessao.setDataCriacao(rs.getTimestamp("data_criacao"));

                sessoes.add(sessao);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sessoes;
    }
}
