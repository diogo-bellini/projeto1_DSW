<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<table border="1">
    <caption>
        <c:choose>
            <c:when test="${user != null}">Edição</c:when>
            <c:otherwise>Cadastro</c:otherwise>
        </c:choose>
    </caption>
    <c:if test="${user != null}">
        <input type="hidden" name="id" value="${user.id_usuario}" />
    </c:if>
    <tr>
        <td><label for="nome">Nome</label></td>
        <td><input type="text" id="nome" name="nome" size="45" required value="${user.nome}" /></td>
    </tr>
    <tr>
        <td><label for="email">Email</label></td>
        <td><input type="email" id="email" name="email" size="45" required value="${user.email}" /></td>
    </tr>
    <tr>
        <td><label for="senha">Senha</label></td>
        <td><input type="password" id="senha" name="senha" size="45" required /></td>
    </tr>

    <tr>
        <td colspan="2" align="center"><input type="submit" value="Salvar" /></td>
    </tr>
</table>
