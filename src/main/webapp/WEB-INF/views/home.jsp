<%--
  Created by IntelliJ IDEA.
  User: diogobellini
  Date: 25/05/25
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Title</title>
</head>
<body>
<h1>Usuário Logado</h1>
<a href="${pageContext.request.contextPath}/logado/testador">Página do Testador</a><br>
<a href="${pageContext.request.contextPath}/logado/admin">Página do Admin</a><br><br>
Email:${sessionScope.usuarioLogado.email}
Senha:${sessionScope.usuarioLogado.senha}
Papel:${sessionScope.usuarioLogado.papel}

<ul>
  <c:forEach var="estrategia" items="${applicationScope.listaEstrategias}">
    <li>${estrategia.nome}</li>
  </c:forEach>
</ul>
</body>
</html>