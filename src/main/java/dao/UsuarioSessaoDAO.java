package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioSessaoDAO extends GenericDAO{
    public UsuarioSessaoDAO() {
        super();
    }

    public boolean cadastrar(long idUsuario, long idSessao) {
        String sql = "INSERT INTO UsuarioSessao(usuario_id, sessao_id, status) VALUES (?,?,?)";

        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setLong(1, idUsuario);
            stmt.setLong(2, idSessao);
            stmt.setString(3, "criado");

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
