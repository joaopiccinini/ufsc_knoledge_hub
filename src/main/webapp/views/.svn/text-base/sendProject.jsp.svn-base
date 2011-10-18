<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>Hub - Projetos do Curso de Sistemas de Informação da UFSC</title>
		<link href="<c:url value="/styles/main.css"/>" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div id="tudo">
			<div id="header">
				<h1>HUB</h1>
				<h2>Projetos do Curso de Sistemas de Informação da UFSC</h2>		
			</div>
	
			<div id="menu-wrapper">
				<ul id="menu">
					<li><a href="about.jsp">Sobre o Projeto</a></li>
					<li><a href="projects">Buscar Projeto</a></li>
					<li class="active"><a href="sendproject">Enviar Projeto</a></li>
					<li><a href="myprojects">Meus Projetos</a></li>
					<c:choose>
						<c:when test="<%= request.getUserPrincipal() == null %>">
							<li><a href="login">Login</a></li>							
						</c:when>
						<c:otherwise>
							<li><a href="logout">Logout</a></li>							
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
	
	
			<div id="conteudo">
				<h3>Enviar Projeto</h3>
				<p class="error"></p>
				
				<form action="<c:url value="sendproject" />" enctype="multipart/form-data" method="post">
					<fieldset>
						<label for="titulo">Título:</label>
						<input id="titulo" class="grande" type="text" name="projectTitle" />
		
						<label for="descricao">Descrição:</label>
						<textarea id="descricao" class="grande" name="projectDescription"></textarea>
		
						<label for="arquivo">Arquivo:</label>
						<input id="arquivo" type="file" name="projectFile" />
					</fieldset>
					<fieldset style="width: 100%;">
						<div style="width:290px;">
							<label for="projectAuthors">Autores:</label>
							<select style="width: 280px;" multiple="multiple" name="projectAuthors" size="8" id="autores" >
								<c:forEach var="user" items="${users}">
									<option value="${user}">${user}</option><br />
								</c:forEach>
							</select>
						</div>
					
						<div style="width:290px;">
							<label for="disciplinas">Disciplinas:</label>
							<select style="width: 280px;" name="projectDisciplines" size="8" id="disciplinas" >
								<c:forEach var="discipline" items="${disciplines}">
									<option value="${discipline}">${discipline}</option><br />
								</c:forEach>
							</select>
						</div>
	
						<div style="width:275px;">
							<label for="projectLanguages">Linguagens de Programação:</label>
							<select style="width: 275px;" multiple="multiple" name="projectLanguages" size="8" id="linguagens" >
								<c:forEach var="language" items="${languages}">
									<option value="${language}">${language}</option><br />
								</c:forEach>
							</select>
						</div>
					</fieldset>
					<fieldset>
						<input style="margin-top: 10px;" type="submit" value="Co-Elaborar com a Comunidade (Enviar Projeto)" />
					</fieldset>
				</form>
	
			</div>
			
			<div id="footer">
				<address>Desenvolvido por Kaléu Caminha e João Paulo Piccinini</address>
			</div>
		</div>
	</body>
</html>