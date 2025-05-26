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
<a href="${pageContext.request.contextPath}/logado/testador">Sessão de testes</a><br>
<a href="${pageContext.request.contextPath}/logado/admin">Administração</a><br><br>

<ul>
  <c:forEach var="estrategia" items="${applicationScope.listaEstrategias}">
    <li>${estrategia.nome}</li>
  </c:forEach>
</ul>
</body>
</html>