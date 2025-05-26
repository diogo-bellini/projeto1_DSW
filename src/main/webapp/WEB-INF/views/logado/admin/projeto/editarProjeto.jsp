<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>Editar Projeto</title>
</head>
<body>
<h1>Editar Projeto</h1>

<c:if test="${not empty erro}">
    <p style="color: red;">${erro}</p>
</c:if>

<form method="post" action="${contextPath}/logado/admin/projeto/atualizarProjeto">
    <input type="hidden" name="id" value="${projeto.id_projeto}">

    <div>
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" value="${projeto.nome}" required>
    </div>

    <div>
        <label for="descricao">Descrição:</label>
        <textarea id="descricao" name="descricao" required>${projeto.descricao}</textarea>
    </div>

    <button type="submit">Atualizar</button>
    <a href="${contextPath}/logado/admin/projeto/listarProjetos">Cancelar</a>
</form>
</body>
</html>