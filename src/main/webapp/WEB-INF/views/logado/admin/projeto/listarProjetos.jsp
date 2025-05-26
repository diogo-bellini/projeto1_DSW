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
  <a href="${contextPath}/logado/admin/projeto/cadastroProjeto">Novo Projeto</a>

  <c:choose>
    <c:when test="${empty projetos}">
      <span> - Nenhum projeto encontrado</span>
    </c:when>
    <c:when test="${projetos.size() == 1}">
      <span> - 1 projeto encontrado</span>
    </c:when>
    <c:otherwise>
      <span> - ${projetos.size()} projetos encontrados</span>
    </c:otherwise>
  </c:choose>
</div>

<br><br>

<div>
  <form method="get" action="${contextPath}/logado/admin/projeto/listarProjetos" style="display: inline;">
    <label for="ordem">Ordenar por:</label>
    <select name="ordem" id="ordem" onchange="this.form.submit()">
      <option value="data" ${param.ordem == 'data' || empty param.ordem ? 'selected' : ''}>
        Data de Criação (Mais Recente)
      </option>
      <option value="data_asc" ${param.ordem == 'data_asc' ? 'selected' : ''}>
        Data de Criação (Mais Antigo)
      </option>
      <option value="nome" ${param.ordem == 'nome' ? 'selected' : ''}>
        Nome (A-Z)
      </option>
      <option value="nome_desc" ${param.ordem == 'nome_desc' ? 'selected' : ''}>
        Nome (Z-A)
      </option>
    </select>
    <noscript>
      <button type="submit">Aplicar Ordenação</button>
    </noscript>
  </form>

  <a href="${contextPath}/logado/admin/projeto/listarProjetos">Limpar Filtros</a>
</div>

<hr>

<div>
  <c:choose>
    <c:when test="${empty projetos}">
      <p>Nenhum projeto foi cadastrado ainda.</p>
      <p><a href="${contextPath}/logado/admin/projeto/cadastroProjeto">Clique aqui para cadastrar o primeiro projeto</a></p>
    </c:when>
    <c:otherwise>
      <c:forEach var="projeto" items="${projetos}">
        <div>
          <h3>${projeto.nome}</h3>
          <p><strong>Descrição:</strong> ${projeto.descricao}</p>
          <p><strong>Criado em:</strong> <fmt:formatDate value="${projeto.dataCriacao}" pattern="dd/MM/yyyy HH:mm"/></p>

          <p>
            <a href="${contextPath}/logado/admin/projeto/editarProjeto?id=${projeto.id_projeto}">Editar</a>
            |
          <form action="${contextPath}/logado/admin/projeto/removerProjeto" method="post" style="display: inline;">
            <input type="hidden" name="id" value="${projeto.id_projeto}">
            <button type="submit" onclick="return confirm('Tem certeza que deseja remover este projeto?\n\nEsta ação não pode ser desfeita.')" style="background: none; border: none; color: red; text-decoration: underline; cursor: pointer;">
              Remover
            </button>
          </form>
          </p>
          <hr>
        </div>
      </c:forEach>
    </c:otherwise>
  </c:choose>
</div>

<script>
  document.addEventListener('DOMContentLoaded', function() {
    var ordemSelect = document.getElementById('ordem');
    var form = ordemSelect.closest('form');

    var urlParams = new URLSearchParams(window.location.search);
    urlParams.forEach(function(value, key) {
      if (key !== 'ordem') {
        var hiddenInput = document.createElement('input');
        hiddenInput.type = 'hidden';
        hiddenInput.name = key;
        hiddenInput.value = value;
        form.appendChild(hiddenInput);
      }
    });
  });
</script>

</body>
</html>