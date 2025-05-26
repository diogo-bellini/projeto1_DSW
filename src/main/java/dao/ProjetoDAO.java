package dao;

import domain.Projeto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO extends GenericDAO {

    public ProjetoDAO() {
        super();
    }

    public Long cadastrar(Projeto projeto) throws SQLException {
        String sql = "INSERT INTO Projeto (nome, descricao, data_criacao) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Falha ao criar projeto, nenhuma linha afetada.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Falha ao obter ID do projeto criado.");
                }
            }
        }
    }

    public List<Projeto> listar() throws SQLException {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM Projeto";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setId_projeto(rs.getLong("id_projeto"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDescricao(rs.getString("descricao"));
                projeto.setDataCriacao(rs.getTimestamp("data_criacao"));
                projetos.add(projeto);
            }
        }
        return projetos;
    }

    public List<Projeto> listarOrdenado(String ordem) throws SQLException {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM Projeto ORDER BY ";

        switch (ordem) {
            case "nome":
                sql += "nome";
                break;
            case "data":
            default:
                sql += "data_criacao DESC";
                break;
        }

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setId_projeto(rs.getLong("id_projeto"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDescricao(rs.getString("descricao"));
                projeto.setDataCriacao(rs.getTimestamp("data_criacao"));
                projetos.add(projeto);
            }
        }
        return projetos;
    }

    public Projeto buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM Projeto WHERE id_projeto = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Projeto projeto = new Projeto();
                    projeto.setId_projeto(rs.getLong("id_projeto"));
                    projeto.setNome(rs.getString("nome"));
                    projeto.setDescricao(rs.getString("descricao"));
                    projeto.setDataCriacao(rs.getTimestamp("data_criacao"));
                    return projeto;
                }
            }
        }
        return null;
    }

    public void atualizar(Projeto projeto) throws SQLException {
        String sql = "UPDATE Projeto SET nome = ?, descricao = ? WHERE id_projeto = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setLong(3, projeto.getId_projeto());
            stmt.executeUpdate();
        }
    }

    public void remover(Long id) throws SQLException {
        String sql = "DELETE FROM Projeto WHERE id_projeto = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}