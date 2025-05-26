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

    <div>
        <h3>Usuários Associados</h3>
        <c:forEach var="usuario" items="${usuarios}">
            <div>
                <input type="checkbox" id="usuario_${usuario.id_usuario}" name="usuarios"
                       value="${usuario.id_usuario}"
                       <c:if test="${usuariosAssociados.contains(usuario.id_usuario)}">checked</c:if>>
                <label for="usuario_${usuario.id_usuario}">${usuario.nome} (${usuario.email}) - ${usuario.papel}</label>
            </div>
        </c:forEach>
    </div>

    <button type="submit">Atualizar</button>
    <a href="${contextPath}/logado/admin/projeto/listarProjetos">Cancelar</a>
</form>
</body>
</html>