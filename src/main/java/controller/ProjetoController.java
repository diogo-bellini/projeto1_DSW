package controller;

import dao.ProjetoDAO;
import domain.Projeto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import jakarta.servlet.*;

@WebServlet("/projetos")
public class ProjetoController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ordem = request.getParameter("ordem"); // nome ou data

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestesBD", "admin", "1234")) {
            ProjetoDAO projetoDAO = new ProjetoDAO(conn);
            List<Projeto> projetos = projetoDAO.listarOrdenado(ordem);
            request.setAttribute("projetos", projetos);
            request.getRequestDispatcher("/logado/testador/projeto/listarProjeto.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("erro", "Erro ao listar projetos: " + e.getMessage());
            request.getRequestDispatcher("/erro.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");

        Projeto projeto = new Projeto(nome, descricao);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestesBD", "admin", "1234")) {
            ProjetoDAO projetoDAO = new ProjetoDAO(conn);
            projetoDAO.cadastrar(projeto);
            response.sendRedirect(request.getContextPath() + "/projetos?sucesso=true");
        } catch (Exception e) {
            request.setAttribute("erro", "Erro ao cadastrar projeto: " + e.getMessage());
            request.getRequestDispatcher("/logado/admin/projeto/cadastrarProjeto.jsp").forward(request, response);
        }
    }
}
