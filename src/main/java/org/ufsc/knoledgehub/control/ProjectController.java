package org.ufsc.knoledgehub.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ufsc.knoledgehub.business.ProjectBusiness;

import com.google.inject.Guice;

@WebServlet({ "/projects" })
public class ProjectController extends HttpServlet {
	private static final String PROJECTS_ATTRIB = "projects"; //$NON-NLS-1$

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProjectBusiness projectBusiness = Guice.createInjector().getInstance(ProjectBusiness.class);

		request.setAttribute(PROJECTS_ATTRIB, projectBusiness.retrieveProjects());
		request.getRequestDispatcher("/views/projects.jsp").forward(request, response); //$NON-NLS-1$
	}
}