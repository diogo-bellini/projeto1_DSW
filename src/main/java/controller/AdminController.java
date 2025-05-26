package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.UsuarioDAO;
import domain.Usuario;
import domain.Papel;
import util.Erro;

@WebServlet(urlPatterns = "/logado/admin/*")
public class AdminController extends HttpServlet{
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    private boolean isAutorizado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return false;
        }

        if (usuario.getPapel() != Papel.administrador) {
            Erro erros = new Erro();
            erros.add("Acesso não autorizado!");
            erros.add("Apenas Papel [administrador] tem acesso a essa página");
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
            rd.forward(request, response);
            return false;
        }

        return true;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isAutorizado(request, response)) return;

        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isAutorizado(request, response)) return;

        String action = request.getPathInfo();
        if (action == null || action.equals("/") || action.isEmpty()) {
            request.getRequestDispatcher("/WEB-INF/views/index-admin.jsp").forward(request, response);
            return;
        }

        if (action.startsWith("/estrategia")) {
            RequestDispatcher rd = request.getRequestDispatcher("/estrategia" + action.substring("/estrategia".length()));
            rd.forward(request, response);
            return;
        }

        try {
            switch (action) {
                case "/cadastro-admin":
                    apresentaFormCadastro(request, response, "administrador");
                    break;
                case "/cadastro-testador":
                    apresentaFormCadastro(request, response, "testador");
                    break;
                case "/insercao-admin":
                    insere(request, response, "administrador");
                    break;
                case "/insercao-testador":
                    insere(request, response, "testador");
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao-admin":
                    atualiza(request, response, "administrador");
                    break;
                case "/atualizacao-testador":
                    atualiza(request, response, "testador");
                    break;
                case "/lista-admin":
                    listaUser(request, response, "administrador");
                    break;
                case "/lista-testador":
                    listaUser(request, response, "testador");
                    break;
                default:
                    request.getRequestDispatcher("/WEB-INF/views/index-admin.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listaUser(HttpServletRequest request, HttpServletResponse response, String tipoUser) throws ServletException, IOException {
        List<Usuario> listaUser = usuarioDAO.getAll();
        if (tipoUser.equals("administrador")) {
            listaUser.removeIf(u1 -> u1.getPapel() != Papel.administrador);
        } else if (tipoUser.equals("testador")) {
            listaUser.removeIf(u1 -> u1.getPapel() != Papel.testador);
        }
        request.setAttribute("tipoUser", tipoUser);
        request.setAttribute("listaUser", listaUser);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/logado/admin/listaUser.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response, String tipoUser) throws ServletException, IOException {
        request.setAttribute("tipoUser", tipoUser);
        request.getRequestDispatcher("/WEB-INF/views/logado/admin/formUser.jsp").forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Usuario user = usuarioDAO.getbyID(id);
        String tipoUser = user.getPapel() == Papel.administrador ? "administrador" : "testador";
        request.setAttribute("user", user);
        request.setAttribute("tipoUser", tipoUser);
        request.getRequestDispatcher("/WEB-INF/views/logado/admin/formUser.jsp").forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response, String tipoUser) throws IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        Papel papel = Papel.valueOf(tipoUser);
        Usuario user = new Usuario(nome, email, senha, papel);
        usuarioDAO.insert(user);
        if (user.getPapel() == Papel.administrador) {
            response.sendRedirect(request.getContextPath() + "/logado/admin/lista-admin");
        } else {
            response.sendRedirect(request.getContextPath() + "/logado/admin/lista-testador");
        }
    }

    private void atualiza(HttpServletRequest request, HttpServletResponse response, String tipoUser) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        Papel papel = Papel.valueOf(tipoUser);
        Usuario user = new Usuario(id, nome, email, senha, papel);
        usuarioDAO.update(user);
        if (tipoUser.equals("administrador")) {
            response.sendRedirect(request.getContextPath() + "/logado/admin/lista-admin");
        } else {
            response.sendRedirect(request.getContextPath() + "/logado/admin/lista-testador");
        }
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Usuario user = new Usuario(id);

        if (usuarioDAO.getbyID(id).getPapel() == Papel.administrador) {
            response.sendRedirect(request.getContextPath() + "/logado/admin/lista-admin");
        } else {
            response.sendRedirect(request.getContextPath() + "/logado/admin/lista-testador");
        }
        usuarioDAO.delete(user);
    }
}
