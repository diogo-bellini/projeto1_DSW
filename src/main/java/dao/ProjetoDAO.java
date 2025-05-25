package dao;

import domain.Projeto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {
    private Connection conexao;

    public ProjetoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void cadastrar(Projeto projeto) throws SQLException {
        String sql = "INSERT INTO Projeto (nome, descricao, data_criacao) VALUES (?, ?, NOW())";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.executeUpdate();
        }
    }

    public List<Projeto> listarOrdenado(String ordem) throws SQLException {
        List<Projeto> projetos = new ArrayList<>();

        String sql = "SELECT * FROM Projeto";
        if ("nome".equalsIgnoreCase(ordem)) {
            sql += " ORDER BY nome ASC";
        } else if ("data".equalsIgnoreCase(ordem)) {
            sql += " ORDER BY data_criacao DESC";
        } else {
            sql += " ORDER BY nome ASC";
        }

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Projeto p = new Projeto();
                p.setId(rs.getLong("id_projeto"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));

                try {
                    Timestamp ts = rs.getTimestamp("data_criacao");
                    if (ts != null) {
                        p.setDataCriacao(ts.toLocalDateTime().toLocalDate());
                    }
                } catch (SQLException ignore) {
                }

                projetos.add(p);
            }
        }

        return projetos;
    }

    public List<Projeto> listar() throws SQLException {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM Projeto";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Projeto p = new Projeto();
                p.setId(rs.getLong("id_projeto"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));

                try {
                    Timestamp ts = rs.getTimestamp("data_criacao");
                    if (ts != null) {
                        p.setDataCriacao(ts.toLocalDateTime().toLocalDate());
                    }
                } catch (SQLException ignore) {
                }

                projetos.add(p);
            }
        }

        return projetos;
    }
}
