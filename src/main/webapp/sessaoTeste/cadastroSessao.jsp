<%--
  Created by IntelliJ IDEA.
  User: pvini
  Date: 22/05/2025
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Cadastrar Sessão</title></head>
<body>
<form action="CadastroSessaoTesteServlet" method="post">
    Projeto:
    <select name="projetoId">
        <c:forEach var="p" items="${projetos}">
            <option value="${p.id}">${p.nome}</option>
        </c:forEach>
    </select><br/>
    Estratégia:
    <select name="estrategiaId">
        <c:forEach var="e" items="${estrategias}">
            <option value="${e.id}">${e.nome}</option>
        </c:forEach>
    </select><br/>
    Tempo (min): <input type="number" name="tempo"/><br/>
    Descrição: <textarea name="descricao"></textarea><br/>
    <input type="submit" value="Cadastrar Sessão"/>
</form>
</body>
</html>
