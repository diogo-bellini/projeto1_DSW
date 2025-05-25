<%@ page contentType="text/html;charset=UTF-8" %>
<%
    HttpSession sessao = request.getSession(false);
    String perfil = (sessao != null) ? (String) sessao.getAttribute("perfil") : null;
    if (perfil == null || !perfil.equals("admin")) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<html>
<head><title>Cadastrar Projeto</title></head>
<body>
<h2>Novo Projeto</h2>
<form action="${pageContext.request.contextPath}/cadastrar-projeto" method="post">
    Nome: <input type="text" name="nome" required><br>
    Descrição: <textarea name="descricao" required></textarea><br>
    <input type="submit" value="Cadastrar">
</form>
</body>
</html>
