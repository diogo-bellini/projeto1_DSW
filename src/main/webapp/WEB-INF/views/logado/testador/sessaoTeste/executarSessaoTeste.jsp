<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
  <title>Execução da Sessão de Teste</title>
</head>
<body>
<h1>Página de Execução de Teste</h1>
<h2>Executando Sessão: ${sessao.descricao}</h2>
<p><strong>Status atual:</strong> ${sessao.status}</p>

<h3>Adicionar novo bug</h3>
<form method="post" action="${pageContext.request.contextPath}/logado/testador/sessaoTeste/adicionarBug">
  <input type="hidden" name="sessaoId" value="${sessao.idSessao}"/>
  <textarea name="descricao" rows="4" cols="50" placeholder="Descreva o bug aqui..." required></textarea><br/>
  <button type="submit">Adicionar Bug</button>
</form>

<c:if test="${not empty mensagemSucesso}">
  <div style="color: green; margin-top: 10px;">${mensagemSucesso}</div>
</c:if>

<ul id="listaBugs">
  <c:forEach var="bug" items="${bugs}">
    <li>
        ${bug.descricao} (Registrado em:
      <fmt:formatDate value="${bug.dataCriacao}" pattern="yyyy-MM-dd HH:mm:ss" />)
    </li>
  </c:forEach>
</ul>

</body>
</html>
