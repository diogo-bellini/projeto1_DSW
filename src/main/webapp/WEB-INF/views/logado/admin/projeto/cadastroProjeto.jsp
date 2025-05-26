<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>Cadastrar Projeto</title>
</head>
<body>
<h1>Cadastrar Novo Projeto</h1>

<c:if test="${not empty erro}">
    <p style="color: red;">${erro}</p>
</c:if>

<form method="post" action="${contextPath}/logado/admin/projeto/cadastrarProjeto">
    <div>
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required>
    </div>

    <div>
        <label for="descricao">Descrição:</label>
        <textarea id="descricao" name="descricao" required></textarea>
    </div>

    <div>
        <h3>Usuários Associados</h3>
        <c:forEach var="usuario" items="${usuarios}">
            <div>
                <input type="checkbox" id="usuario_${usuario.id_usuario}" name="usuarios" value="${usuario.id_usuario}">
                <label for="usuario_${usuario.id_usuario}">${usuario.nome} (${usuario.email}) - ${usuario.papel}</label>
            </div>
        </c:forEach>
    </div>

    <button type="submit">Cadastrar</button>
    <a href="${contextPath}/logado/admin/projeto/listarProjetos">Exibir projetos</a>
</form>
</body>
</html>