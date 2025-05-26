<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Menu do Sistema</title>
</head>
<body>
<h1>Administração</h1>
<p>Olá ${sessionScope.usuarioLogado.nome}</p>
<ul>
  <li>
    <a href="${pageContext.request.contextPath}/logado/admin/lista-testador">Gerenciar Testadores</a>
  </li>
  <li>
    <a href="${pageContext.request.contextPath}/logado/admin/lista-admin">Gerenciar Administradores</a>
  </li>
  <li>
    <a href="${pageContext.request.contextPath}/logado/admin/projeto/cadastroProjeto">Cadastro de Projetos</a>
  </li>
  <li>
    <a href="${pageContext.request.contextPath}/logado/admin/estrategia/cadastroEstrategia">Cadastro de Estratégias</a>
  </li>

  <li>
    <a href="${pageContext.request.contextPath}/logout.jsp">Sair</a>
  </li>
</ul>
</body>
</html>