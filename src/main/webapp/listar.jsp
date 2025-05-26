<%--
  Created by IntelliJ IDEA.
  User: pvini
  Date: 25/05/2025
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: pvini
  Date: 25/05/2025
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="pt_BR"/>
<fmt:setBundle basename="mensagens.messages" />

<html>
<head>
    <title><fmt:message key="titulo.listagem.estrategias"/></title>
</head>
<body>
<h1><fmt:message key="titulo.listagem.estrategias"/></h1>

<table border="1">
    <tr>
        <th><fmt:message key="campo.nome"/></th>
        <th><fmt:message key="campo.descricao"/></th>
        <th><fmt:message key="campo.exemplo"/></th>
        <th><fmt:message key="campo.dica"/></th>
    </tr>
    <c:forEach var="e" items="${estrategias}">
        <tr>
            <td>${e.nome}</td>
            <td>${e.descricao}</td>
            <td>${e.exemplo}</td>
            <td>${e.dica}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>