package controller;

import dao.SessaoTesteDAO;
import domain.SessaoTeste;
import domain.Status;
import domain.Usuario;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/SessaoTesteController"})
public class SessaoTesteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado");

        SessaoTesteDAO sessao_teste_dao = new SessaoTesteDAO();

        List<SessaoTeste> sessoes = sessao_teste_dao.getListaSessaoUsuario(usuario.getId_usuario());

        req.setAttribute("sessoes", sessoes);
        req.getRequestDispatcher("${pageContext.request.contextPath}/logado/testador/sessaoTeste/executarSessaoTeste.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastrarSessao":
                    insere(request, response);
                    break;
                case "/executarSessao":
                    executar(request, response);
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

            response.sendRedirect("listarSessoes.jsp?projetoId=" + sessao.getProjetoId());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar sessão.");
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }


    private void executar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
}
