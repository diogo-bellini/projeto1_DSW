<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:bundle basename="messages">
    <html>
    <head>
        <meta charset="UTF-8">
        <title><fmt:message key="titulo.cadastro.sessao"/></title>
    </head>
    <body>

    <h1><fmt:message key="titulo.cadastro.sessao"/></h1>

    <c:if test="${not empty erro}">
        <p style="color: red;">${erro}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/logado/testador/sessaoTeste/cadastrarSessao" method="post">
        <label>
            <fmt:message key="campo.projeto"/>:
            <select name="projetoId" required>
                <c:forEach var="p" items="${projetos}">
                    <option value="${p.id_projeto}">${p.nome}</option>
                </c:forEach>
            </select>
        </label>
        <br/>

        <label>
            <fmt:message key="campo.estrategia"/>:
            <select name="estrategiaId" required>
                <c:forEach var="e" items="${listaEstrategias}">
                    <option value="${e.id_estrategia}">${e.nome}</option>
                </c:forEach>
            </select>
        </label>
        <br/>

        <label>
            <fmt:message key="campo.tempo"/>:
            <input type="number" name="tempo" min="1" required/>
        </label>
        <br/>

        <label>
            <fmt:message key="campo.descricao"/>:
            <br/>
            <textarea name="descricao" rows="4" cols="50" required></textarea>
        </label>
        <br/>

        <button type="submit"><fmt:message key="botao.cadastrar.sessao"/></button>
    </form>

</fmt:bundle>
</body>
</html>