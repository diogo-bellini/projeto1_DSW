package controller;

import dao.ProjetoDAO;
import domain.Projeto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet("/cadastrar-projeto")
public class ProjetoController  extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");

        Projeto projeto = new Projeto(nome, descricao);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestesBD", "admin", "1234")) {
            ProjetoDAO projetoDAO = new ProjetoDAO(conn);
            dao.cadastrar(projeto);
            resp.sendRedirect(req.getContextPath() + "/projetos/listar.jsp?sucesso=true");
        } catch (Exception e) {
            req.setAttribute("erro", "Erro ao cadastrar projeto: " + e.getMessage());
            req.getRequestDispatcher("/projetos/cadastrar.jsp").forward((ServletRequest) req, resp);
        }
    }
}
