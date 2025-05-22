<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="pt_BR" />
<fmt:setBundle basename="mensagens.messages" />

<html>
<head>
    <title><fmt:message key="titulo.cadastro.estrategia"/></title>
</head>
<body>
<h1><fmt:message key="titulo.cadastro.estrategia"/></h1>

<c:if test="${param.sucesso == 'true'}">
    <p style="color: green"><fmt:message key="mensagem.sucesso"/></p>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/estrategias/cadastrar">
    <label><fmt:message key="campo.nome" />: <input type="text" name="nome" required></label><br>
    <label><fmt:message key="campo.descricao" />:<br><textarea name="descricao" required></textarea></label><br>
    <label><fmt:message key="campo.exemplo" />:<br><textarea name="exemplo"></textarea></label><br>
    <label><fmt:message key="campo.dica" />:<br><textarea name="dica"></textarea></label><br>
    <button type="submit"><fmt:message key="botao.enviar"/></button>
</form>

<c:if test="${not empty erro}">
    <p style="color:red">${erro}</p>
</c:if>
</body>
</html>
