package controller;

import dao.ProjetoDAO;
import dao.UsuarioDAO;
import dao.UsuarioProjetoDAO;
import domain.Projeto;
import domain.Usuario;
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
                mostrarFormCadastro(request, response);
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

    private void mostrarFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            List<Usuario> usuarios = usuarioDAO.getAll();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/WEB-INF/views/logado/admin/projeto/cadastroProjeto.jsp")
                    .forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Erro ao carregar formulário de cadastro", e);
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

            ProjetoDAO projetoDAO = new ProjetoDAO();
            Long projetoId = projetoDAO.cadastrar(projeto);

            // Associar usuários selecionados ao projeto
            String[] usuariosIds = request.getParameterValues("usuarios");
            if (usuariosIds != null) {
                UsuarioProjetoDAO usuarioProjetoDAO = new UsuarioProjetoDAO();
                for (String usuarioId : usuariosIds) {
                    usuarioProjetoDAO.associarUsuarioProjeto(Long.parseLong(usuarioId), projetoId);
                }
            }

            response.sendRedirect(request.getContextPath() + "/logado/admin/projeto/listarProjetos");
        } catch (SQLException e) {
            throw new ServletException("Erro ao cadastrar projeto", e);
        }
    }

    private void apresentarFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            ProjetoDAO projetoDAO = new ProjetoDAO();
            Projeto projeto = projetoDAO.buscarPorId(id);

            if (projeto == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            List<Usuario> usuarios = usuarioDAO.getAll();

            UsuarioProjetoDAO usuarioProjetoDAO = new UsuarioProjetoDAO();
            List<Long> usuariosAssociados = usuarioProjetoDAO.getUsuariosAssociados(id);

            request.setAttribute("projeto", projeto);
            request.setAttribute("usuarios", usuarios);
            request.setAttribute("usuariosAssociados", usuariosAssociados);
            request.getRequestDispatcher("/WEB-INF/views/logado/admin/projeto/editarProjeto.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erro ao buscar projeto", e);
        }
    }

    private void atualizarProjeto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long projetoId = Long.parseLong(request.getParameter("id"));
            Projeto projeto = new Projeto();
            projeto.setId_projeto(projetoId);
            projeto.setNome(request.getParameter("nome"));
            projeto.setDescricao(request.getParameter("descricao"));

            ProjetoDAO projetoDAO = new ProjetoDAO();
            projetoDAO.atualizar(projeto);

            // Atualizar associações com usuários
            UsuarioProjetoDAO usuarioProjetoDAO = new UsuarioProjetoDAO();
            usuarioProjetoDAO.removerAssociacoesProjeto(projetoId);

            String[] usuariosIds = request.getParameterValues("usuarios");
            if (usuariosIds != null) {
                for (String usuarioId : usuariosIds) {
                    usuarioProjetoDAO.associarUsuarioProjeto(Long.parseLong(usuarioId), projetoId);
                }
            }

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