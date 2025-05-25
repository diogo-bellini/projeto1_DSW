package controller;

import domain.SessaoTeste;
import dao.SessaoTesteDAO;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.sql.Timestamp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static java.lang.Math.toIntExact;


public class CadastroSessaoTesteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long usuarioId = (Long) request.getSession().getAttribute("usuarioId");
            String nomeTestador = (String) request.getSession().getAttribute("nome");

            // Verificar se usuário está logado
            if (usuarioId == null || nomeTestador == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            // Verificar se o usuário tem perfil de testador

            SessaoTeste sessao = new SessaoTeste();
            sessao.setDescricaoSessao(request.getParameter("descricao")); //
            sessao.setTempo(Integer.parseInt(request.getParameter("tempo")));
            sessao.setEstrategiaId(Long.parseLong(request.getParameter("estrategiaId")));
            sessao.setProjetoId(Long.parseLong(request.getParameter("projetoId")));
            sessao.setUsuarioId(toIntExact(usuarioId));
            sessao.setNomeTestador(nomeTestador);
            sessao.setDataSessao(new Timestamp(System.currentTimeMillis()));

            SessaoTesteDAO dao = new SessaoTesteDAO();
            Long sessaoId = dao.cadastrarSessao(sessao);
            dao.criarStatusUsuarioSessao(usuarioId, sessaoId);

            response.sendRedirect("listarSessoes.jsp?projetoId=" + sessao.getProjetoId());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar sessão: " + e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }
}
