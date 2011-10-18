<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>

<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title><fmt:message key="project.title" /></title>
		<link href="styles/main.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<t:menu actualPage="index"></t:menu>
		<div id="conteudo">
			<h3><fmt:message key="info.title" /></h3>
			<fmt:message key="info.body" />
		</div>
	</body>
</html>