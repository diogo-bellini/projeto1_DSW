package controller;

import dao.EstrategiaDAO;
import domain.Estrategia;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/cadastrar-estrategia")
public class CadastroEstrategiaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Object tipoUsuario = request.getSession().getAttribute("tipo");
        if (tipoUsuario == null || !"admin".equals(tipoUsuario.toString())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Apenas administradores podem cadastrar.");
            return;
        }

        // Pega os parâmetros do formulário
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String exemplo = request.getParameter("exemplo");
        String dica = request.getParameter("dica");

        Estrategia estrategia = new Estrategia(nome, descricao, exemplo ,dica);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TesteDB", "admin", "admin")) {
            EstrategiaDAO dao = new EstrategiaDAO(conn);
            dao.cadastro(estrategia);
            response.sendRedirect(request.getContextPath() + "/estrategias/cadastro.jsp?sucesso=true");
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("erro", "Não foi possível cadastrar a estratégia");
            request.getRequestDispatcher("/estrategias/cadastro.jsp").forward(request, response);
        }
    }
}
