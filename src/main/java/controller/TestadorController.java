package controller;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import domain.Usuario;
import domain.Papel;
import util.Erro;

@WebServlet(urlPatterns = "/logado/testador/*")
public class TestadorController extends HttpServlet {

    private boolean isAutorizado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return false;
        }

        if (usuario.getPapel() != Papel.testador && usuario.getPapel() != Papel.administrador) {
            Erro erros = new Erro();
            erros.add("Acesso não autorizado!");
            erros.add("Apenas Papel [testador] ou [administrador] têm acesso a essa página");
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
            rd.forward(request, response);
            return false;
        }

        return true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isAutorizado(request, response)) return;

        String action = request.getPathInfo();
        if (action == null || action.equals("/") || action.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/logado/testador/index.jsp");
            return;
        }

        if (action.startsWith("/sessaoTeste")) {
            RequestDispatcher rd = request.getRequestDispatcher("/sessaoTeste" + action.substring("/sessaoTeste".length()));
            rd.forward(request, response);
            return;
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/logado/admin/index.jsp");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher("");
//        dispatcher.forward(request, response);
    }
}
