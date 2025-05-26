<%--
  Created by IntelliJ IDEA.
  User: diogobellini
  Date: 26/05/25
  Time: 00:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Lista de Estratégias</h2>
<ul>
  <c:forEach var="e" items="${applicationScope.listaEstrategias}">
    <li>
      <strong>${e.nome}</strong>: ${e.descricao}
    </li>
  </c:forEach>
</ul>
</body>
</html>
