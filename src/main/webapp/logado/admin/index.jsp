<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Menu do Sistema</title>
</head>
<body>
<h1>Página do Administrador</h1>
<p>Olá ${sessionScope.usuarioLogado.nome}</p>
<ul>
  <li>
    <a href="${pageContext.request.contextPath}">CRUD Testador</a>
  </li>
  <li>
    <a href="${pageContext.request.contextPath}/logado/admin/listaAdmin.jsp">CRUD Administrador</a>
  </li>
  <li>
    <a href="${pageContext.request.contextPath}">Cadastrar Projeto</a>
  </li>
  <li>
    <a href="${pageContext.request.contextPath}/logado/admin/estrategia/cadastroEstrategia.jsp">Cadastrar Estratégia</a>
  </li>
  <li>
    <a href="${pageContext.request.contextPath}/logout.jsp">Sair</a>
  </li>
</ul>
</body>
</html>