<%--
  Created by IntelliJ IDEA.
  User: diogobellini
  Date: 24/05/25
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Sessões do Usuário</h2>

<c:forEach var="sessao" items="${sessoes}">
    <div>
        <p><strong>Descrição:</strong> ${sessao.descricaoSessao}</p>
        <p><strong>Data:</strong> ${sessao.dataSessao}</p>
        <p><strong>Tempo:</strong> ${sessao.tempo} minutos</p>
        <hr>
    </div>
</c:forEach>
</body>
</html>
