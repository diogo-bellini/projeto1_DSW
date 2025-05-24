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

@WebServlet("/estrategia/*")
public class EstrategiaController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String exemplo = request.getParameter("exemplo");
        String dica = request.getParameter("dica");
        Estrategia estrategia = new Estrategia();
        estrategia.setNome(nome);
        estrategia.setDescricao(descricao);
        estrategia.setExemplo(exemplo);
        estrategia.setDica(dica);

        Estrategia e = new Estrategia(nome, descricao, exemplo ,dica);

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/create.sql", "root", "root")){
            EstrategiaDAO dao = new EstrategiaDAO();
            dao.cadastro(e);
            response.sendRedirect(request.getContextPath() + "/estrategias/cadastroEstrategia.jsp?sucesso=true");
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("erro", "Não foi possivel cadastrar a estrategia");
            request.getRequestDispatcher("/estrategias/cadastrar.jsp").forward(request, response);
        }
    }
}
