package controller;

import dao.ProjetoDAO;
import domain.Projeto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/listar-projetos")
public class ListagemProjeto extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ProjetoDAO dao = new ProjetoDAO(); // Remove a passagem de Connection
            List<Projeto> projetos = dao.listar();
            req.setAttribute("projetos", projetos);
            req.getRequestDispatcher("/projetos/listar.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("erro", "Erro ao listar projetos: " + e.getMessage());
            req.getRequestDispatcher("/erro.jsp").forward(req, resp);
        }
    }
}