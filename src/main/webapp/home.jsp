<%--
  Created by IntelliJ IDEA.
  User: diogobellini
  Date: 25/05/25
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
      <title>Home</title>
      <style>
            .estrategia-card {
                  border: 1px solid #ddd;
                  border-radius: 5px;
                  padding: 15px;
                  margin-bottom: 10px;
                  background-color: #f9f9f9;
            }
            .estrategia-nome {
                  font-weight: bold;
                  color: #2c3e50;
            }
      </style>
</head>
<body>
<h1>Usuário Logado</h1>
<a href="${pageContext.request.contextPath}/logado/testador">Página do Testador</a><br>
<a href="${pageContext.request.contextPath}/logado/admin">Página do Admin</a><br><br>

<div>
      Email: ${sessionScope.usuarioLogado.email}<br>
      Papel: ${sessionScope.usuarioLogado.papel}
</div>

<h2>Estratégias Disponíveis</h2>

<c:choose>
      <c:when test="${not empty applicationScope.listaEstrategias}">
            <div class="estrategias-container">
                  <c:forEach var="estrategia" items="${applicationScope.listaEstrategias}">
                        <div class="estrategia-card">
                              <div class="estrategia-nome">${estrategia.nome}</div>
                              <div class="estrategia-descricao">${estrategia.descricao}</div>
                              <div class="estrategia-exemplo"><small>Exemplo: ${estrategia.exemplo}</small></div>
                        </div>
                  </c:forEach>
            </div>
      </c:when>
      <c:otherwise>
            <p>Nenhuma estratégia cadastrada ainda.</p>
      </c:otherwise>
</c:choose>
</body>
</html>