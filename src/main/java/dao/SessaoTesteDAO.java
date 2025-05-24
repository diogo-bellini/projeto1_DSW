package dao;

import domain.SessaoTeste;
import java.sql.*;

public class SessaoTesteDAO extends GenericDAO{

    public SessaoTesteDAO(){
        super();
    }

    public Long cadastrar(SessaoTeste sessao) throws SQLException {
        String sql = "INSERT INTO SessaoTeste(descricao, nome_testador, estrategia_id, tempo, projeto_id, usuario_id, data_criacao) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
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
            Long idGerado = null;

            if (rs.next()) {
                idGerado = rs.getLong(1);
            }

            if (idGerado != null) {
                UsuarioSessaoDAO usuario_sessao_dao = new UsuarioSessaoDAO();
                usuario_sessao_dao.cadastrar(sessao.getUsuarioId(), idGerado);
            }

            return idGerado;
        }
    }
}
