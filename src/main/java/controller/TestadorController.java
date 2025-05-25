package controller;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import domain.Usuario;
import util.Erro;

@WebServlet(urlPatterns = "/testador/*")
public class TestadorController extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        Erro erros = new Erro();
        if (usuario == null) {
            response.sendRedirect(request.getContextPath());
        } else if (usuario.getPapel().toString().equals("testador") || usuario.getPapel().toString().equals("administrador")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/testador/index.jsp");
            dispatcher.forward(request, response);
        } else {
            erros.add("Acesso não autorizado!");
            erros.add("Apenas Papel [testador] tem acesso a essa página");
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

