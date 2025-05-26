package controller;

import dao.ProjetoDAO;
import domain.Projeto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/logado/admin/projeto/*")
public class ProjetoController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        if (path == null || path.equals("/")) {
            listarProjetos(request, response);
            return;
        }

        switch (path) {
            case "/cadastroProjeto":
                request.getRequestDispatcher("/WEB-INF/views/logado/admin/projeto/cadastroProjeto.jsp")
                        .forward(request, response);
                return;

            case "/listarProjetos":
                listarProjetos(request, response);
                return;

            case "/editarProjeto":
                apresentarFormEdicao(request, response);
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
                case "/cadastrarProjeto":
                    cadastrarProjeto(request, response);
                    break;

                case "/atualizarProjeto":
                    atualizarProjeto(request, response);
                    break;

                case "/removerProjeto":
                    removerProjeto(request, response);
                    break;

                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listarProjetos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String ordem = request.getParameter("ordem");
            ProjetoDAO dao = new ProjetoDAO();
            List<Projeto> projetos = (ordem != null) ? dao.listarOrdenado(ordem) : dao.listar();

            request.setAttribute("projetos", projetos);
            request.getRequestDispatcher("/WEB-INF/views/logado/admin/projeto/listarProjetos.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erro ao listar projetos", e);
        }
    }

    private void cadastrarProjeto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Projeto projeto = new Projeto();
            projeto.setNome(request.getParameter("nome"));
            projeto.setDescricao(request.getParameter("descricao"));

            ProjetoDAO dao = new ProjetoDAO();
            dao.cadastrar(projeto);

            response.sendRedirect(request.getContextPath() + "/logado/admin/projeto/listarProjetos");
        } catch (SQLException e) {
            throw new ServletException("Erro ao cadastrar projeto", e);
        }
    }

    private void apresentarFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            ProjetoDAO dao = new ProjetoDAO();
            Projeto projeto = dao.buscarPorId(id);

            if (projeto == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            request.setAttribute("projeto", projeto);
            request.getRequestDispatcher("/WEB-INF/views/logado/admin/projeto/editarProjeto.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erro ao buscar projeto", e);
        }
    }

    private void atualizarProjeto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Projeto projeto = new Projeto();
            projeto.setId_projeto(Long.parseLong(request.getParameter("id")));
            projeto.setNome(request.getParameter("nome"));
            projeto.setDescricao(request.getParameter("descricao"));

            ProjetoDAO dao = new ProjetoDAO();
            dao.atualizar(projeto);

            response.sendRedirect(request.getContextPath() + "/logado/admin/projeto/listarProjetos");
        } catch (SQLException e) {
            throw new ServletException("Erro ao atualizar projeto", e);
        }
    }

    private void removerProjeto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            ProjetoDAO dao = new ProjetoDAO();
            dao.remover(id);

            response.sendRedirect(request.getContextPath() + "/logado/admin/projeto/listarProjetos");
        } catch (SQLException e) {
            throw new ServletException("Erro ao remover projeto", e);
        }
    }
}