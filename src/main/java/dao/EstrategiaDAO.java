package dao;

import domain.Estrategia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstrategiaDAO extends GenericDAO {

    public EstrategiaDAO() {
        super();
    }

    public void cadastrar(Estrategia estrategia) throws SQLException {
        String sql = "INSERT INTO Estrategia (nome, descricao, exemplo, dica) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estrategia.getNome());
            stmt.setString(2, estrategia.getDescricao());
            stmt.setString(3, estrategia.getExemplo());
            stmt.setString(4, estrategia.getDica());

            stmt.executeUpdate();
        }
    }

    public List<Estrategia> buscarTodas() throws SQLException {
        List<Estrategia> estrategias = new ArrayList<>();
        String sql = "SELECT * FROM Estrategia";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Estrategia e = new Estrategia();
                e.setId_estrategia(rs.getLong("id_estrategia"));
                e.setNome(rs.getString("nome"));
                e.setDescricao(rs.getString("descricao"));
                e.setExemplo(rs.getString("exemplo"));
                e.setDica(rs.getString("dica"));

                estrategias.add(e);
            }
        }
        return estrategias;
    }
}