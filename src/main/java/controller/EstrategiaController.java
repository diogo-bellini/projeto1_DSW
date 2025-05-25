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

@WebServlet("/estrategia/*")
public class EstrategiaController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        if (path == null || path.equals("/")) {
            request.getRequestDispatcher("/WEB-INF/views/index-admin.jsp").forward(request, response);
            return;
        }

        switch (path) {
            case "/cadastroEstrategia":
                request.getRequestDispatcher("/WEB-INF/views/logado/admin/estrategia/cadastroEstrategia.jsp")
                        .forward(request, response);
                return;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastrarEstrategia":
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

            List<Estrategia> listaEstrategias = new ArrayList<>();
            listaEstrategias = estrategia_dao.buscarTodas();
            getServletContext().setAttribute("listaEstrategias", listaEstrategias);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar estratégia.");
            request.getRequestDispatcher("/logado/admin/estrategia/cadastroEstrategia.jsp").forward(request, response);
        }
    }
}
