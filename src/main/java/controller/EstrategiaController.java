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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/logado/admin/estrategia/*")
public class EstrategiaController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastroEstrategia":
                    insere(request, response);
                    break;
                default:
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Estrategia estrategia = new Estrategia();
            estrategia.setNome(request.getParameter("nome"));
            estrategia.setDescricao(request.getParameter("descricao"));
            estrategia.setExemplo(request.getParameter("exemplo"));
            estrategia.setDica(request.getParameter("dica"));

            EstrategiaDAO estrategia_dao = new EstrategiaDAO();
            estrategia_dao.cadastro(estrategia);

            List<Estrategia> listaEstrategias = estrategia_dao.buscarTodas();
            getServletContext().setAttribute("listaEstrategias", listaEstrategias);

            response.sendRedirect(request.getContextPath() + "/logado/admin/estrategia/cadastroEstrategia.jsp?sucesso=true");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar estratégia.");
            request.getRequestDispatcher("/logado/admin/estrategia/cadastroEstrategia.jsp").forward(request, response);
        }
    }
}
