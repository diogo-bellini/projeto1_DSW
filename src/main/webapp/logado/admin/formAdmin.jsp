<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <title>Administração</title>
</head>
<body>
<div align="center">
  <h1>Gerenciamento de Administradores</h1>
  <h2>
    <a href="lista-admins">Lista de Administradores</a>
  </h2>
</div>
<div align="center">
  <c:choose>
    <c:when test="${admin != null}">
      <form action="atualizacao" method="get">
        <%@include file="camposAdmin.jsp"%>
      </form>
    </c:when>
    <c:otherwise>
      <form action="insercao" method="get">
        <%@include file="camposAdmin.jsp"%>
      </form>
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
