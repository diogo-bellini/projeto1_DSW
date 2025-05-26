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
        <p><strong>Descrição:</strong> ${sessao.descricao}</p>
        <p><strong>Data:</strong> ${sessao.dataCriacao}</p>
        <p><strong>Tempo:</strong> ${sessao.tempo} minutos</p>
        <p><strong>Status:</strong> ${sessao.status}</p>
        <a href="${pageContext.request.contextPath}/logado/testador/sessaoTeste/executarSessaoTeste?idSessao=${sessao.idSessao}">Executar</a>
        <hr>
    </div>
</c:forEach>
</body>
</html>
