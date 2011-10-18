package org.ufsc.knoledgehub.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ufsc.knoledgehub.business.ProjectBusiness;
import org.ufsc.knoledgehub.dto.ProjectDTO;

import com.google.inject.Guice;

@WebServlet({ "/myprojects" })
@ServletSecurity(@HttpConstraint(rolesAllowed = { "member" }))
public class UserProjectController extends HttpServlet {
	private static final String PROJECTS_ATTRIB = "projects"; //$NON-NLS-1$

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProjectBusiness projectBusiness = Guice.createInjector().getInstance(ProjectBusiness.class);

		List<ProjectDTO> projects = projectBusiness.retrieveProjects(getUserLogin(request));

		request.setAttribute(PROJECTS_ATTRIB, projects);
		request.getRequestDispatcher("/views/projects.jsp").forward(request, response); //$NON-NLS-1$
	}

	private String getUserLogin(HttpServletRequest request) {
		return request.getUserPrincipal().getName();
	}
}
