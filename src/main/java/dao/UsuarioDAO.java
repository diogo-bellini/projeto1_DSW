package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Papel;
import domain.Usuario;

public class UsuarioDAO extends GenericDAO {
    public void insert(Usuario usuario) {
        String sql = "INSERT INTO Usuario (email, nome, senha, papel) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;
            statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getNome());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, usuario.getPapel().toString());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> getAll() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * from Usuario u";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id_usuario");
                String nome = resultSet.getString("nome");
                String login = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papelStr = resultSet.getString("papel");
                Papel papel = Papel.valueOf(papelStr);
                Usuario usuario = new Usuario(id, nome, login, senha, papel);
                listaUsuarios.add(usuario);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

    public void delete(Usuario usuario) {
        String sql = "DELETE FROM Usuario where id_usuario = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, usuario.getId_usuario());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Usuario usuario) {
        String sql = "UPDATE Usuario SET nome = ?, email = ?, senha = ?, papel = ? WHERE id_usuario = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getLogin());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, usuario.getPapel().toString());
            statement.setLong(5, usuario.getId_usuario());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario getbyID(Long id) {
        Usuario usuario = null;
        String sql = "SELECT * from Usuario WHERE id_usuario = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String login = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papelStr = resultSet.getString("papel");
                Papel papel = Papel.valueOf(papelStr);
                usuario = new Usuario(id, nome, login, senha, papel);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public Usuario getbyLogin(String login) {
        Usuario usuario = null;
        String sql = "SELECT * from Usuario WHERE email = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id_usuario");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String papelStr = resultSet.getString("papel");
                Papel papel = Papel.valueOf(papelStr);
                usuario = new Usuario(id, nome, login, senha, papel);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
}
