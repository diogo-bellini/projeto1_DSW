package dao;

import java.sql.*;

public class UsuarioSessaoDAO extends GenericDAO{
    public UsuarioSessaoDAO() {
        super();
    }

    public void cadastrar(long idUsuario, long idSessao) {
        String sql = "INSERT INTO UsuarioSessao(usuario_id, sessao_id, status) VALUES (?,?,?)";

        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setLong(1, idUsuario);
            stmt.setLong(2, idSessao);
            stmt.setString(3, "criado");

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
