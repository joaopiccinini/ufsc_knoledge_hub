<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
				<li><a href="sendproject">Enviar Projeto</a></li>
				<li><a href="myprojects">Meus Projetos</a></li>
				<li class="active"><a href="login">Login</a></li>
			</ul>
		</div>


		<div id="conteudo">
			<h3>Bem Vindo!!!</h3>
			<form action="j_security_check" method="post" id="cadastro_usuario">

				<label for="j_username">email:</label>
				<input id="username" type="text" class="grande" name="j_username" />

				<label for="j_password">Senha:</label>
				<input type="password" class="grande" name="j_password" id="senha" />			

				<input style="margin-top: 10px;" type="submit" value="Acessar" />

				<br />
				<br />

				<a href="newuser.jsp">Quero me cadastrar</a>			
			
			</form>

		</div>
		
		<div id="footer">
			<address>Desenvolvido por Kaléu Caminha e João Paulo Piccinini</address>
		</div>
	</div>
</body>
</html>


