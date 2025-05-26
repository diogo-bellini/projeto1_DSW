<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
  <title>Execução da Sessão de Teste</title>
</head>
<body>
<h1>Página de Execução de Teste</h1>
<h2>Executando Sessão: ${sessao.descricao}</h2>
<p><strong>Status atual:</strong> ${sessao.status}</p>

<h3>Adicionar novo bug</h3>
<form id="formBug">
  <input type="hidden" name="sessaoId" value="${sessao.idSessao}" id="sessaoId"/>
  <textarea name="descricao" id="descricao" rows="4" cols="50" placeholder="Descreva o bug aqui..." required></textarea><br/>
  <button type="submit">Adicionar Bug</button>
</form>

<div id="mensagemBug" style="color: green; margin-top: 10px;"></div>

<ul id="listaBugs">
  <c:forEach var="bug" items="${bugs}">
    <li>
        ${bug.descricao} (Registrado em:
      <fmt:formatDate value="${bug.dataCriacao}" pattern="yyyy-MM-dd HH:mm:ss" />)
    </li>
  </c:forEach>
</ul>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
  $(document).ready(function () {
    $("#formBug").submit(function (e) {
      e.preventDefault();

      const descricao = $("#descricao").val().trim();
      if (descricao === "") return;

      $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/sessaoTeste/adicionarBug",
        data: {
          sessaoId: $("#sessaoId").val(),
          descricao: descricao
        },
        dataType: 'json',
        success: function (data) {
          console.log("Resposta do servidor:", data);
          $("#mensagemBug").text("Bug adicionado com sucesso!").css("color", "green");
          $("#descricao").val("");

          const bugItem = `<li data-id="${data.idBug}">${data.descricao} (Registrado em: ${data.dataCriacao})</li>`;
          $("#listaBugs").append(bugItem);
        },
        error: function () {
          $("#mensagemBug").text("Erro ao adicionar bug.").css("color", "red");
        }
      });
    });
  });
</script>

</body>
</html>
