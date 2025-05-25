<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Menu do Sistema</title>
</head>
<body>
<h1>Página do Testador</h1>
<p>Olá ${sessionScope.usuarioLogado.nome}</p>
<ul>
  <li>
    <a href="${pageContext.request.contextPath}/logado/testador/sessaoTeste/cadastroSessaoTeste">Cadastrar Sessão de Teste</a>
  </li>
  <li>
    <a href="${pageContext.request.contextPath}/logado/testador/sessaoTeste/executarSessaoTeste">Listar e executar Sessões de Teste</a>
  </li>
  <li>
    <a href="${pageContext.request.contextPath}/logout.jsp">Sair</a>
  </li>

</ul>
</body>
</html>