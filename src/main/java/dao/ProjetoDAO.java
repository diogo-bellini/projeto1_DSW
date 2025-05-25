package dao;

import domain.Projeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {
    private Connection conexao;

    public ProjetoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void cadastrar(Projeto projeto) throws SQLException {
        String sql = "INSERT INTO Projeto (nome, descricao) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.executeUpdate();
        }
    }

    public List<Projeto> listar() throws SQLException {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM Projeto ORDER BY nome";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Projeto p = new Projeto();
                p.setId(rs.getLong("id_projeto"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                projetos.add(p);
            }
        }

        return projetos;
    }
}
