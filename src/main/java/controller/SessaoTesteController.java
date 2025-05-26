package controller;

import dao.SessaoTesteDAO;
import domain.SessaoTeste;
import domain.Status;
import domain.Usuario;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/sessaoTeste/*"})
public class SessaoTesteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        if (path == null || path.equals("/")) {
            request.getRequestDispatcher("/WEB-INF/views/index-testador.jsp").forward(request, response);
            return;
        }

        switch (path) {
            case "/cadastroSessaoTeste":
                request.getRequestDispatcher("/WEB-INF/views/logado/testador/sessaoTeste/cadastroSessaoTeste.jsp")
                        .forward(request, response);
                return;

            case "/listarSessaoTeste":
                listarSessao(request, response);
                request.getRequestDispatcher("/WEB-INF/views/logado/testador/sessaoTeste/listarSessaoTeste.jsp")
                        .forward(request, response);
                break;

            case "/executarSessaoTeste":
                executarSessao(request, response);
                request.getRequestDispatcher("/WEB-INF/views/logado/testador/sessaoTeste/executarSessaoTeste.jsp")
                        .forward(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String path = request.getPathInfo();
//        response.getWriter().println("SessaoTesteController funcionando! Caminho: " + path);
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastrarSessao":
                    insere(request, response);
                    break;

                case "/adicionarBug":
                    adicionarBug(request, response);
                    break;

                default:
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

            Long usuarioId = usuario.getId_usuario();
            String nomeTestador = usuario.getNome();

            SessaoTeste sessao = new SessaoTeste();
            sessao.setDescricao(request.getParameter("descricao"));
            sessao.setTempo(Integer.parseInt(request.getParameter("tempo")));
            sessao.setEstrategiaId(Long.parseLong(request.getParameter("estrategiaId")));
            sessao.setProjetoId(Long.parseLong(request.getParameter("projetoId")));
            sessao.setUsuarioId(usuarioId);
            sessao.setNomeTestador(nomeTestador);
            sessao.setStatus(Status.criado);
            sessao.setDataCriacao(new Timestamp(System.currentTimeMillis()));

            SessaoTesteDAO sessao_teste_dao = new SessaoTesteDAO();
            Long sessaoId = sessao_teste_dao.cadastrar(sessao);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar sessão.");
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }


    private void listarSessao(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
            Long usuarioId = usuario.getId_usuario();

            SessaoTesteDAO sessao_teste_dao = new SessaoTesteDAO();
            List<SessaoTeste> sessoes = sessao_teste_dao.getListaSessaoUsuario(usuarioId);

            request.setAttribute("sessoes", sessoes);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar sessão.");
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }

    private void executarSessao(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long idSessao = Long.parseLong(request.getParameter("idSessao"));

        SessaoTesteDAO sessao_teste_dao = new SessaoTesteDAO();
        sessao_teste_dao.atualizarStatus(idSessao, Status.em_execucao);
        SessaoTeste sessao = sessao_teste_dao.getById(idSessao);

        request.setAttribute("sessao", sessao);
    }

    public void adicionarBug(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
}
