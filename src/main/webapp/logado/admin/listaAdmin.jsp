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
    <a href="/${requestScope.contextPath}/logado/admin/index.jsp">Menu Principal</a>
    &nbsp;&nbsp;&nbsp;
    <a href="/${requestScope.contextPath}/logado/admins/cadastro">Adicionar Novo Admin</a>
  </h2>
</div>
<div align="center">
  <table border="1">
    <caption>Lista de Administradores</caption>
    <tr>
      <th>ID</th>
      <th>Nome</th>
      <th>Email</th>
      <th>Ações</th>
    </tr>
    <c:forEach var="admin" items="${requestScope.listaAdmins}">
      <tr>
        <td>${admin.id_usuario}</td>
        <td>${admin.nome}</td>
        <td>${admin.email}</td>
        <td>
          <a href="/${requestScope.contextPath}/logado/admins/edicao?id=${admin.id_usuario}">Edição</a>
          &nbsp;&nbsp;&nbsp;
          <a href="/${requestScope.contextPath}/logado/admins/remocao?id=${admin.id_usuario}"
             onclick="return confirm('Tem certeza que deseja excluir este administrador?');">
            Remoção
          </a>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>
