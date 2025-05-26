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
        <a href="/${requestScope.contextPath}/logado/admin/">Menu Principal</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/logado/admin/cadastro-admin">Adicionar Novo Admin</a>
      </h2>
    </c:when>
    <c:when test="${tipoUser == 'testador'}">
      <h1>Gerenciamento de Testadores</h1>
      <h2>
        <a href="/${requestScope.contextPath}/logado/admin/">Menu Principal</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/logado/admin/cadastro-testador">Adicionar Novo Testador</a>
      </h2>
    </c:when>
  </c:choose>
</div>
<div align="center">
  <table border="1">
    <c:choose>
      <c:when test="${tipoUser == 'administrador'}">
        <caption>Lista de Administradores</caption>
      </c:when>
      <c:when test="${tipoUser == 'testador'}">
        <caption>Lista de Testadores</caption>
      </c:when>
    </c:choose>

    <tr>
      <th>ID</th>
      <th>Nome</th>
      <th>Email</th>
      <th>Ações</th>
    </tr>
    <c:forEach var="user" items="${requestScope.listaUser}">
      <tr>
        <td>${user.id_usuario}</td>
        <td>${user.nome}</td>
        <td>${user.email}</td>
        <td>
          <a href="/${requestScope.contextPath}/logado/admin/edicao?id=${user.id_usuario}">Edição</a>
          &nbsp;&nbsp;&nbsp;
          <a href="/${requestScope.contextPath}/logado/admin/remocao?id=${user.id_usuario}"
             onclick="return confirm('Tem certeza que deseja excluir este usuário?');">
            Remoção
          </a>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>
