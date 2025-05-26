<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <title>Administração</title>
</head>
<body>
<div align="center">
  <c:choose>
    <c:when test="${tipoUser == 'administrador'}">
      <h1>Gerenciamento de Administradores</h1>
      <h2>
          <a href="lista-admin">Lista de Administradores</a>
      </h2>
    </c:when>
    <c:when test="${tipoUser == 'testador'}">
      <h1>Gerenciamento de Testadores</h1>
      <h2>
        <a href="lista-testador">Lista de Testadores</a>
      </h2>
    </c:when>
  </c:choose>

</div>
<div align="center">
  <c:choose>
    <c:when test="${user != null}">
      <c:choose>
        <c:when test="${tipoUser == 'administrador'}">
          <form action="atualizacao-admin" method="post">
            <%@include file="camposUser.jsp"%>
          </form>
        </c:when>
        <c:when test="${tipoUser == 'testador'}">
          <form action="atualizacao-testador" method="post">
            <%@include file="camposUser.jsp"%>
          </form>
        </c:when>
      </c:choose>
    </c:when>

    <c:otherwise>
      <c:choose>
        <c:when test="${tipoUser == 'administrador'}">
          <form action="insercao-admin" method="post">
            <%@include file="camposUser.jsp"%>
          </form>
        </c:when>
        <c:when test="${tipoUser == 'testador'}">
          <form action="insercao-testador" method="post">
            <%@include file="camposUser.jsp"%>
          </form>
        </c:when>
      </c:choose>
    </c:otherwise>
  </c:choose>
</div>
<c:if test="${!empty requestScope.mensagens}">
  <ul class="erro">
    <c:forEach items="${requestScope.mensagens}" var="mensagem">
      <li>${mensagem}</li>
    </c:forEach>
  </ul>
</c:if>
</body>
</html>
