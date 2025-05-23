package servlet;

import domain.SessaoTeste;
import dao.SessaoTesteDAO;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CadastroSessaoTesteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long usuarioId = (Long) request.getSession().getAttribute("usuarioId");
            String nomeTestador = (String) request.getSession().getAttribute("nome");

            SessaoTeste sessao = new SessaoTeste();
            sessao.setDescricaoSessao(request.getParameter("descricaoSessao"));
            sessao.setTempo(Integer.parseInt(request.getParameter("tempo")));
            sessao.setEstrategiaId(Long.parseLong(request.getParameter("estrategiaId")));
            sessao.setProjetoId(Long.parseLong(request.getParameter("projetoId")));
            sessao.setUsuarioId(Math.toIntExact(usuarioId));
            sessao.setNomeTestador(nomeTestador);

            SessaoTesteDAO dao = new SessaoTesteDAO();
            Long sessaoId = dao.cadastrarSessao(sessao);
            dao.criarStatusUsuarioSessao(usuarioId, sessaoId);

            response.sendRedirect("listarSessoes.jsp?projetoId=" + sessao.getProjetoId());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar sessão.");
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }
}
