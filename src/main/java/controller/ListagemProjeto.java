package controller;

import dao.ProjetoDAO;
import domain.Projeto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@WebServlet("/listar-projetos")
public class ListagemProjeto extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestesBD", "admin", "1234")) {
            ProjetoDAO dao = new ProjetoDAO(conn);
            List<Projeto> projetos = dao.listar();
            req.setAttribute("projetos", projetos);
            req.getRequestDispatcher("/projetos/listar.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("erro", "Erro ao listar projetos: " + e.getMessage());
            req.getRequestDispatcher("/erro.jsp").forward(req, resp);
        }
    }
}
