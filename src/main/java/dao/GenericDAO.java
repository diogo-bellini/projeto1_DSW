package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract public class GenericDAO {

    public GenericDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/TestesBD?useSSL=false&serverTimezone=America/Sao_Paulo";
        String user = "root";
        String password = "root";

        return DriverManager.getConnection(url, user, password);
    }
}

class TesteConexao {
    public static void main(String[] args) {
        GenericDAO dao = new GenericDAO() {};
        try (Connection conn = dao.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Conexão bem-sucedida!");
            } else {
                System.out.println("Falha na conexão.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao tentar conectar ao banco:");
            e.printStackTrace();
        }
    }
}
