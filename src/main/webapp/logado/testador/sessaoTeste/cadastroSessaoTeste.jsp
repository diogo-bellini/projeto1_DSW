<%--
  Created by IntelliJ IDEA.
  User: pvini
  Date: 22/05/2025
  Time: 13:59
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="titulo.cadastro.sessao" /></title>
</head>
<body>

<fmt:bundle basename="messages">

    <h1><fmt:message key="titulo.cadastro.sessao" /></h1>

    <form action="${pageContext.request.contextPath}/logado/testador/sessaoTeste/cadastrarSessao" method="post">
        <label>
            <fmt:message key="campo.projeto" />:
            <select name="projetoId" required>
                <c:forEach var="p" items="${projetos}">
                    <option value="${p.id}">${p.nome}</option>
                </c:forEach>
            </select>
        </label>
        <br/>

        <label>
            <fmt:message key="campo.estrategia" />:
            <select name="estrategiaId" required>
                <c:forEach var="e" items="${applicationScope.listaEstrategias}">
                    <option value="${e.id}">${e.nome}</option>
                </c:forEach>
            </select>
        </label>
        <br/>

        <label>
            <fmt:message key="campo.tempo" />:
            <input type="number" name="tempo" min="1" required />
        </label>
        <br/>

        <label>
            <fmt:message key="campo.descricao" />:
            <br/>
            <textarea name="descricao" rows="4" cols="50" required></textarea>
        </label>
        <br/>

        <button type="submit"><fmt:message key="botao.cadastrar.sessao" /></button>
    </form>

</fmt:bundle>

</body>
</html>
