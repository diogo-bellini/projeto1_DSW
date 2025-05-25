package controller;

import dao.EstrategiaDAO;
import domain.Estrategia;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet("/estrategias/listar")
public class ListarEstrategiasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TesteDB", "admin", "admin")) {
            EstrategiaDAO dao = new EstrategiaDAO(conn);
            List<Estrategia> estrategias = dao.listarTodas();
            request.setAttribute("estrategias", estrategias);
            request.getRequestDispatcher("/estrategias/listar.jsp").forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao carregar estratégias.");
        }
    }
}
