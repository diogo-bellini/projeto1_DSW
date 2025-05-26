package controller;

import dao.*;
import domain.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(urlPatterns = {"/logado/testador/sessaoTeste/*"})
public class SessaoTesteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo();
        if (path == null) path = "/";

        try {
            switch (path) {
                case "/cadastroSessaoTeste":
                    carregarFormularioCadastro(request, response);
                    break;

                case "/listarSessaoTeste":
                    listarSessoes(request, response);
                    request.getRequestDispatcher("/WEB-INF/views/logado/testador/sessaoTeste/listarSessaoTeste.jsp")
                            .forward(request, response);
                    break;

                case "/executarSessaoTeste":
                    if (prepararExecucaoSessao(request, response)) {
                        request.getRequestDispatcher("/WEB-INF/views/logado/testador/sessaoTeste/executarSessaoTeste.jsp")
                                .forward(request, response);
                    }
                    break;

                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new ServletException("Erro no controller de sessão de teste", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();
        if (action == null) action = "/";

        try {
            switch (action) {
                case "/cadastrarSessao":
                    cadastrarSessao(request, response);
                    break;

                case "/adicionarBug":
                    adicionarBug(request, response);
                    break;

                case "/finalizarSessao":
                    finalizarSessao(request, response);
                    break;

                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new ServletException("Erro no processamento POST", e);
        }
    }

    private void carregarFormularioCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        ProjetoDAO projetoDAO = new ProjetoDAO();
        EstrategiaDAO estrategiaDAO = new EstrategiaDAO();

        List<Projeto> projetos = projetoDAO.listarPorUsuario(usuario.getId_usuario());
        List<Estrategia> estrategias = estrategiaDAO.buscarTodas();

        request.setAttribute("projetos", projetos);
        request.setAttribute("listaEstrategias", estrategias);

        request.getRequestDispatcher("/WEB-INF/views/logado/testador/sessaoTeste/cadastroSessaoTeste.jsp")
                .forward(request, response);
    }

    private void cadastrarSessao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

        try {
            SessaoTeste sessao = new SessaoTeste();
            sessao.setDescricao(request.getParameter("descricao"));
            sessao.setTempo(Integer.parseInt(request.getParameter("tempo")));
            sessao.setEstrategiaId(Long.parseLong(request.getParameter("estrategiaId")));
            sessao.setProjetoId(Long.parseLong(request.getParameter("projetoId")));
            sessao.setUsuarioId(usuario.getId_usuario());
            sessao.setNomeTestador(usuario.getNome());
            sessao.setStatus(Status.criado);
            sessao.setDataCriacao(new Timestamp(System.currentTimeMillis()));

            SessaoTesteDAO sessaoDAO = new SessaoTesteDAO();
            sessaoDAO.cadastrar(sessao);

            response.sendRedirect(request.getContextPath() + "/logado/testador/sessaoTeste/listarSessaoTeste");

        } catch (Exception e) {
            request.setAttribute("erro", "Erro ao cadastrar sessão: " + e.getMessage());
            carregarFormularioCadastro(request, response);
        }
    }

    private void listarSessoes(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        SessaoTesteDAO sessaoDAO = new SessaoTesteDAO();
        List<SessaoTeste> sessoes = sessaoDAO.getListaSessaoUsuario(usuario.getId_usuario());
        request.setAttribute("sessoes", sessoes);
    }

    private boolean prepararExecucaoSessao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        Long idSessao = Long.parseLong(request.getParameter("idSessao"));
        SessaoTesteDAO sessaoDAO = new SessaoTesteDAO();
        BugDAO bugDAO = new BugDAO();

        SessaoTeste sessao = sessaoDAO.getById(idSessao);

        if (sessao.getStatus() == Status.finalizado) {
            request.getSession().setAttribute("erro", "Sessão já finalizada");
            response.sendRedirect(request.getContextPath() + "/logado/testador/sessaoTeste/listarSessaoTeste");
            return false;
        }

        if (sessao.getStatus() == Status.criado) {
            sessaoDAO.atualizarStatus(idSessao, Status.em_execucao);
            sessao.setStatus(Status.em_execucao);
        }

        List<Bug> bugs = bugDAO.listarPorSessao(idSessao);
        request.setAttribute("sessao", sessao);
        request.setAttribute("bugs", bugs);

        return true;
    }

    private void adicionarBug(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {

        Long sessaoId = Long.parseLong(request.getParameter("sessaoId"));
        String descricao = request.getParameter("descricao");

        Bug bug = new Bug();
        bug.setDescricao(descricao);
        bug.setSessaoId(sessaoId);
        bug.setDataCriacao(new Timestamp(System.currentTimeMillis()));

        BugDAO bugDAO = new BugDAO();
        bugDAO.cadastrar(bug);

        response.sendRedirect(request.getContextPath() +
                "/logado/testador/sessaoTeste/executarSessaoTeste?idSessao=" + sessaoId);
    }

    private void finalizarSessao(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {

        Long sessaoId = Long.parseLong(request.getParameter("idSessao"));
        SessaoTesteDAO sessaoDAO = new SessaoTesteDAO();
        sessaoDAO.atualizarStatus(sessaoId, Status.finalizado);

        response.sendRedirect(request.getContextPath() + "/logado/testador/sessaoTeste/listarSessaoTeste");
    }
}