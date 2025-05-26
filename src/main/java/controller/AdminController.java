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
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualiza(request, response);
                    break;
                case "/lista-admins":
                    listaAdmins(request, response);
                    break;
                default:
                    request.getRequestDispatcher("/WEB-INF/views/index-admin.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listaAdmins(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> listaAdmins = usuarioDAO.getAll();
        listaAdmins.removeIf(u1 -> u1.getPapel() != Papel.administrador);
        request.setAttribute("listaAdmins", listaAdmins);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/listaAdmin.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/logado/admin/formAdmin.jsp").forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id_usuario"));
        Usuario admin = usuarioDAO.getbyID(id);
        request.setAttribute("admin", admin);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/formAdmin.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        Papel papel = Papel.valueOf(request.getParameter("papel"));
        Usuario admin = new Usuario(nome, email, senha, papel);
        usuarioDAO.insert(admin);
        response.sendRedirect(request.getContextPath() + "/logado/admin/listaAdmin.jsp");
    }

    private void atualiza(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id_usuario"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        Papel papel = Papel.valueOf(request.getParameter("papel"));
        Usuario admin = new Usuario(id, nome, email, senha, papel);
        usuarioDAO.update(admin);
        response.sendRedirect(request.getContextPath() + "/logado/admin/listaAdmin.jsp");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id_usuario"));
        Usuario admin = new Usuario(id);
        usuarioDAO.delete(admin);
        response.sendRedirect(request.getContextPath() + "/logado/admin/listaAdmin.jsp");
    }
}
