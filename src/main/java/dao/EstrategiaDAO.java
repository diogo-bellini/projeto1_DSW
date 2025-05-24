package dao;

import domain.Estrategia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EstrategiaDAO extends GenericDAO {

    private Connection conexao;

    public EstrategiaDAO() {
        super();
    }

    public void cadastro(Estrategia estrategia) throws SQLException {

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
}
