<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Execução da Sessão de Teste</title>
</head>
<body>
<h1>Página de Execução de Teste</h1>
<h2>Executando Sessão: ${sessao.descricao}</h2>
<p><strong>Status atual:</strong> ${sessao.status}</p>

<h3>Bugs encontrados nesta sessão:</h3>
<c:choose>
  <c:when test="${not empty bugs}">
    <ul>
      <c:forEach var="bug" items="${bugs}">
        <li>${bug.descricao} (Registrado em: ${bug.dataCriacao})</li>
      </c:forEach>
    </ul>
  </c:when>
  <c:otherwise>
    <p>Não há bugs registrados.</p>
  </c:otherwise>
</c:choose>

<h3>Adicionar novo bug</h3>
<form action=""${pageContext.request.contextPath}/logado/testador/sessaoTeste/adicionarBug" method="post">
  <input type="hidden" name="sessaoId" value="${sessao.id_sessao}"/>
  <textarea name="descricao" rows="4" cols="50" placeholder="Descreva o bug aqui..." required></textarea><br/>
  <button type="submit">Adicionar Bug</button>
</form>
</body>
</html>
