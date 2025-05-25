<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
  HttpSession sessao = request.getSession(false);
  if (sessao == null || sessao.getAttribute("perfil") == null) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
  }
%>

<html>
<head>
  <title>Projetos</title>
</head>
<body>
<h2>Projetos Cadastrados</h2>

Ordenar por:
<a href="?ordem=nome">Nome</a> |
<a href="?ordem=data">Data de Criação</a>

<c:forEach var="projeto" items="${projetos}">
  <div>
    <strong>${projeto.nome}</strong><br>
      ${projeto.descricao}<br>
    Criado em: ${projeto.dataCriacao}<br><br>
  </div>
</c:forEach>
</body>
</html>
