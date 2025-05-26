<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
  <title>Lista de Projetos</title>
</head>
<body>
<h1>Projetos Cadastrados</h1>

<c:if test="${not empty erro}">
  <p style="color: red;">${erro}</p>
</c:if>

<div>
  <a href="${contextPath}/logado/admin/projeto/cadastroProjeto" class="btn btn-primary">Novo Projeto</a>
</div>

<div class="project-list">
  <c:forEach var="projeto" items="${projetos}">
    <div class="project-card">
      <h3>${projeto.nome}</h3>
      <p>${projeto.descricao}</p>
      <div class="project-actions">
        <a href="${contextPath}/logado/admin/projeto/editarProjeto?id=${projeto.id_projeto}"
           class="btn btn-warning">Editar</a>
        <form action="${contextPath}/logado/admin/projeto/removerProjeto" method="post">
          <input type="hidden" name="id" value="${projeto.id_projeto}">
          <button type="submit" class="btn btn-danger"
                  onclick="return confirm('Tem certeza que deseja remover este projeto?')">
            Remover
          </button>
        </form>
      </div>
    </div>
  </c:forEach>
</div>
</body>
</html>