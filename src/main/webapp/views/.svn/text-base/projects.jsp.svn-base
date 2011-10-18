<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><fmt:message key="projectView.title"/></title>
		<link href="<c:url value="/styles/main.css"/>" rel="stylesheet" type="text/css" />
	</head>
	<body>

	<div id="tudo">

		<div id="conteudo">
			<h3><fmt:message key="projectView.title"/></h3>

			<c:forEach var="project" items="${projects}" varStatus="rowCounter">
					<c:choose>
						<c:when test="${rowCounter.count % 2 == 0}">
							<c:set var="trClass" value="last"/>
						</c:when>
						<c:otherwise>
							<c:set var="trClass" value=""/>
						</c:otherwise>
					</c:choose>

					<div class="projeto ${trClass}">
						<div class="info">
							<h4>${project.title}</h4>
								<small>Enviado em 16/07/2011</small>
								<a href="${project.filePath}" class="download">Baixar Projeto</a>
								<p>${project.description}</p>	
								<a href="#" class="excluir">Excluir Projeto</a>
						</div>

						<dl>
							<dt class="first">Autores</dt>
							<dd>${project.authors}</dd>
							
							<dt>Linguagens de Programação</dt>
							<dd>${project.programmingLanguages}</dd>
							
							<dt>Disciplinas</dt>
							<dd>${project.discipline}</dd>								
							
						</dl>
					</div>
		 		</c:forEach>						
				
		</div>
		
		<div id="footer">
			<address>Desenvolvido por Kaléu Caminha e João Paulo Piccinini</address>
		</div>
	</div>				

	</body>
</html>