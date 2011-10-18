<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="actualPage" required="true" %>

<div id="menu-wrapper">
			<ul id="menu">
				<li <c:if test="${actualPage == 'about'} ">class="active"</c:if>>
					<a href="/knoledgehub/views/about.jsp">Sobre o Projeto</a>
				</li>
				<li <c:if test="${actualPage == 'projects'}">class="active"</c:if>>
					<a href="projects">Buscar Projeto</a>
				</li>
				<li <c:if test="${actualPage == 'sendproject'}">class="active"</c:if>>
					<a href="sendproject">Enviar Projeto</a>
				</li>
				<li <c:if test="${actualPage == 'myprojects'}">class="active"</c:if>>
					<a href="myprojects">Meus Projetos</a>
				</li>
				<li <c:if test="${actualPage == 'about'}">class="active"</c:if>>
					<a href="/knoledgehub/views/login.jsp">Login</a>
				</li>
				<c:choose>
					<c:when test="<%= request.getUserPrincipal() == null %>">
						<li <c:if test="${actualPage == 'login'}">class="active"</c:if>><a href="login">Login</a></li>							
					</c:when>
					<c:otherwise>
						<li><a href="logout">Logout</a></li>							
					</c:otherwise>
				</c:choose>
			</ul>
		</div>