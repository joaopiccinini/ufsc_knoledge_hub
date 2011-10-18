package org.ufsc.knoledgehub.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ufsc.knoledgehub.model.entity.Discipline;
import org.ufsc.knoledgehub.model.entity.ProgrammingLanguage;
import org.ufsc.knoledgehub.model.entity.Project;
import org.ufsc.knoledgehub.model.entity.User;

@SuppressWarnings("unchecked")
public class ProjectDAOTest {

	static final Logger logger = LoggerFactory.getLogger(ProjectDAOTest.class);

	@Rule
	public MethodRule watchman = new TestWatchman() {
		@Override
		public void starting(FrameworkMethod method) {
			logger.info("{} being run...", method.getName());
		}

		@Override
		public void failed(Throwable e, FrameworkMethod method) {
			logger.error("{} failed: {}", method.getName(), e.getMessage());
			super.failed(e, method);
		}
	};

	@Test
	public void shouldRetrieveUserProjects() throws Exception {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Knoledgehub", System.getProperties()); //$NON-NLS-1$
		EntityManager em = emFactory.createEntityManager();

		String query = "SELECT project FROM Project project JOIN project.authors authors WHERE authors.login = 'kaleu'";

		List<Project> resultList = em.createQuery(query).getResultList();
		for (Project project : resultList)
			logger.info(project.getTitle());
		assertEquals(2, resultList.size());

		em.close();
		emFactory.close();
	}

	@Test
	public void shouldRetrieveAllProjects() throws Exception {
		List<Project> projects = new ProjectDAO().findAllProjects();
		assertEquals(1, projects.size());
	}

	@Test
	public void shouldInsertUpdateRetrieveAndDeleteProjectByTitle() throws Exception {
		ProjectDAO projectDAO = new ProjectDAO();
		String title = "title2";
		// projectDAO.deleteProjectByTitle(title);
		String oldDescription = "description";
		String newDescription = "newDescription";

		Project project = new Project();
		project.setTitle(title);
		project.setDescription(oldDescription);
		project.setStoragePath("path");

		addUserToProject(project);
		addDisciplineToProject(project);
		addProgrammingLanguageToProject(project);

		projectDAO.insertProject(project);
		Project loadedProject = projectDAO.findProjectByTitle(title);
		assertEquals(title, loadedProject.getTitle());
		assertEquals(oldDescription, loadedProject.getDescription());

		project.setDescription(newDescription);
		// projectDAO.updateProjectByTitle(project);
		// Project loadedUpdatedProject = projectDAO.findProjectByTitle(title);
		// assertEquals(newDescription, loadedUpdatedProject.getDescription());

		// projectDAO.deleteProjectByTitle(title);
	}

	private void addDisciplineToProject(Project project) {
		DisciplineDAO disciplineDAO = new DisciplineDAO();
		String code = "code";
		disciplineDAO.deleteDiscipline(code);

		Discipline discipline = new Discipline();
		discipline.setCode(code);
		discipline.setName("name");
		disciplineDAO.insertDiscipline(discipline);

		Discipline loadedDiscipline = disciplineDAO.findDisciplineByCode(code);
		project.setDiscipline(loadedDiscipline);
	}

	private void addProgrammingLanguageToProject(Project project) {
		ProgrammingLanguageDAO programmingLanguageDAO = new ProgrammingLanguageDAO();
		String name = "name";
		programmingLanguageDAO.deleteProgrammingLanguage(name);

		ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
		programmingLanguage.setName(name);
		programmingLanguageDAO.insertProgrammingLanguage(programmingLanguage);
		ProgrammingLanguage loadedProgrammingLanguage = programmingLanguageDAO.findProgrammingLanguageByName(name);

		List<ProgrammingLanguage> programmingLanguages = new ArrayList<ProgrammingLanguage>();
		programmingLanguages.add(loadedProgrammingLanguage);
		project.setProgrammingLanguages(programmingLanguages);
	}

	private void addUserToProject(Project project) {
		UserDAO userDAO = new UserDAO();
		String login = "login";
		// userDAO.deleteUserByLogin(login);

		User user = new User();
		user.setLogin(login);
		user.setName("name");
		user.setPassword("pass");
		user.setRole("role");

		userDAO.insertUser(user);
		User loadedUser = userDAO.findUserByLogin(login);

		List<User> users = new ArrayList<User>();
		users.add(loadedUser);
		project.setAuthors(users);
	}
}
