package org.ufsc.knoledgehub.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.ufsc.knoledgehub.business.DisciplineBusiness;
import org.ufsc.knoledgehub.business.LanguageBusiness;
import org.ufsc.knoledgehub.business.ProjectBusiness;
import org.ufsc.knoledgehub.business.UploadBusiness;
import org.ufsc.knoledgehub.business.UserBusiness;
import org.ufsc.knoledgehub.model.entity.Discipline;
import org.ufsc.knoledgehub.model.entity.ProgrammingLanguage;
import org.ufsc.knoledgehub.model.entity.User;

import com.google.inject.Guice;

@WebServlet({ "/sendproject" })
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@ServletSecurity(@HttpConstraint(rolesAllowed = { "member" }))
public class ProjectUploadController extends HttpServlet {
	private static final String USERS_ATRIB = "users"; //$NON-NLS-1$
	private static final String LANGUAGES_ATTRIB = "languages"; //$NON-NLS-1$
	private static final String DISCIPLINES_ATTRIB = "disciplines"; //$NON-NLS-1$

	private static final String PROJECT_TITLE_ATTRIB = "projectTitle"; //$NON-NLS-1$
	private static final String PROJECT_AUTHORS_ATRIB = "projectAuthors"; //$NON-NLS-1$
	private static final String PROJECT_LANGUAGES_ATTRIB = "projectLanguages"; //$NON-NLS-1$
	private static final String PROJECT_DISCIPLINE_ATTRIB = "projectDisciplines"; //$NON-NLS-1$
	private static final String PROJECT_DESCRIPTION_ATTRIB = "projectDescription"; //$NON-NLS-1$
	private static final String PROJECT_FILE_ATTRIB = "projectFile"; //$NON-NLS-1$

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DisciplineBusiness disciplineBusiness = Guice.createInjector().getInstance(DisciplineBusiness.class);

		request.setAttribute(USERS_ATRIB, new UserBusiness().retrieveUsers());
		request.setAttribute(LANGUAGES_ATTRIB, new LanguageBusiness().retrieveLanguages());
		request.setAttribute(DISCIPLINES_ATTRIB, disciplineBusiness.retrieveDisciplines());
		request.getRequestDispatcher("/views/sendProject.jsp").forward(request, response); //$NON-NLS-1$
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProjectBusiness projectBusiness = Guice.createInjector().getInstance(ProjectBusiness.class);

		response.setContentType("text/html"); //$NON-NLS-1$

		Part filePart = request.getPart(PROJECT_FILE_ATTRIB);
		String title = request.getParameter(PROJECT_TITLE_ATTRIB);
		List<User> authors = getAuthors(request.getParameterValues(PROJECT_AUTHORS_ATRIB));
		List<ProgrammingLanguage> programmingLanguages = getProgrammingLanguages(request.getParameterValues(PROJECT_LANGUAGES_ATTRIB));
		String description = request.getParameter(PROJECT_DESCRIPTION_ATTRIB);
		Discipline discipline = getDiscipline(request.getParameter(PROJECT_DISCIPLINE_ATTRIB));

		String filePath = new UploadBusiness(getServletContext()).processUpload(filePart.getInputStream(), authors.get(0).getLogin(),
				title, filePart.getContentType());
		projectBusiness.createProject(title, authors, programmingLanguages, discipline, filePath, description);

		response.sendRedirect("/knoledgehub/projects");
	}

	private List<ProgrammingLanguage> getProgrammingLanguages(String[] languagesParameter) {
		List<ProgrammingLanguage> projectLanguages = new ArrayList<ProgrammingLanguage>();
		List<ProgrammingLanguage> programmingLanguages = new LanguageBusiness().retrieveLanguages();
		for (ProgrammingLanguage programmingLanguage : programmingLanguages)
			for (String language : languagesParameter)
				if (programmingLanguage.toString().equals(language)) projectLanguages.add(programmingLanguage);
		return projectLanguages;
	}

	private List<User> getAuthors(String[] authorsParameter) {
		List<User> projectAuthors = new ArrayList<User>();
		List<User> authors = new UserBusiness().retrieveUsers();
		for (User author : authors)
			for (String login : authorsParameter)
				if (author.getLogin().equals(login)) projectAuthors.add(author);
		return projectAuthors;
	}

	private Discipline getDiscipline(String disciplineName) {
		List<Discipline> disciplines = new DisciplineBusiness().retrieveDisciplines();
		for (Discipline discipline : disciplines)
			if (discipline.getName().equals(disciplineName)) return discipline;
		return null;
	}
}
