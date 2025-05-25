package controller;

import dao.SessaoTesteDAO;
import domain.SessaoTeste;
import domain.Usuario;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/logado/testador/sessaoTeste/*"})
public class SessaoTesteController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastroSessaoTeste":
                    insere(request, response);
                    break;
                default:
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
            int usuarioId = usuario.getId_usuario();
            String nomeTestador = usuario.getNome();

            SessaoTeste sessao = new SessaoTeste();
            sessao.setDescricaoSessao(request.getParameter("descricao"));
            sessao.setTempo(Integer.parseInt(request.getParameter("tempo")));
            sessao.setEstrategiaId(Long.parseLong(request.getParameter("estrategiaId")));
            sessao.setProjetoId(Long.parseLong(request.getParameter("projetoId")));
            sessao.setUsuarioId(Math.toIntExact(usuarioId));
            sessao.setNomeTestador(nomeTestador);

            SessaoTesteDAO sessao_teste_dao = new SessaoTesteDAO();
            Long sessaoId = sessao_teste_dao.cadastrar(sessao);

            response.sendRedirect("listarSessoes.jsp?projetoId=" + sessao.getProjetoId());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar sessão.");
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }
}
