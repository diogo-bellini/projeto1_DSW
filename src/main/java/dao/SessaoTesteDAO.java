package dao;

import domain.SessaoTeste;
import java.sql.*;

public class SessaoTesteDAO {
    public Long cadastrarSessao(SessaoTeste sessao) throws SQLException {
        String sql = "INSERT INTO SessaoTeste(descricao, nome_testador, estrategia_id, tempo, projeto_id, usuario_id,data_criacao) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/create.sql", "root", "root");
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, sessao.getDescricaoSessao());
            stmt.setString(2, sessao.getNomeTestador());
            stmt.setLong(3, sessao.getEstrategiaId());
            stmt.setInt(4, sessao.getTempo());
            stmt.setLong(5, sessao.getProjetoId());
            stmt.setLong(6, sessao.getUsuarioId());
            stmt.setTimestamp(7, sessao.getDataSessao());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
        }
        return null;
    }
    public void criarStatusUsuarioSessao(Long usuarioId, Long sessaoId) throws SQLException {
        String sql = "INSERT INTO UsuarioSessao(usuario_id, sessao_id, status) VALUES (?, ?, 'criado')";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/create.sql", "root", "root");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, usuarioId);
            stmt.setLong(2, sessaoId);
            stmt.executeUpdate();
        }
    }
}
