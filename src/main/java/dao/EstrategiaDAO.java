package dao;

import domain.Estrategia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstrategiaDAO {

    private Connection conexao;

    public EstrategiaDAO(Connection conexao) {

        this.conexao = conexao;
    }

    public void cadastro(Estrategia estrategia) throws SQLException {

        String sql = "INSERT INTO Estrategia (nome, descricao, exemplo, dica) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, estrategia.getNome());
            stmt.setString(2, estrategia.getDescricao());
            stmt.setString(3, estrategia.getExemplo());
            stmt.setString(4, estrategia.getDica());
            stmt.executeUpdate();
        }
    }

    public List<Estrategia> listarTodas() throws SQLException {
        List<Estrategia> lista = new ArrayList<>();
        String sql = "SELECT * FROM Estrategia";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Estrategia e = new Estrategia();
                e.setid_estrategia(rs.getLong("id_estrategia"));
                e.setNome(rs.getString("nome"));
                e.setDescricao(rs.getString("descricao"));
                e.setExemplo(rs.getString("exemplo"));
                e.setDica(rs.getString("dica"));
                lista.add(e);
            }
        }
        return lista;
    }
}
