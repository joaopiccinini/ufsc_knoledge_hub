package org.ufsc.knoledgehub.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.ufsc.knoledgehub.dto.ProjectDTO;
import org.ufsc.knoledgehub.model.ProjectDAO;
import org.ufsc.knoledgehub.model.entity.Discipline;
import org.ufsc.knoledgehub.model.entity.ProgrammingLanguage;
import org.ufsc.knoledgehub.model.entity.Project;
import org.ufsc.knoledgehub.model.entity.User;

@SuppressWarnings("nls")
@RunWith(MockitoJUnitRunner.class)
public class ProjectBusinessTest {

	private static final String PROJECT_TITLE = "projectA";
	private static final String PROJECT_DESCRIPTION = "description of the projectA";
	private static final String PROJECT_PATH = "path/to/file/of/projectA";

	private static final String DISCIPLINE_NAME = "Programming";

	private static final String USER_A_LOGIN = "author";
	private static final String USER_B_LOGIN = "admin";
	private static final String USER_C_LOGIN = "user";

	private static final String LANGUAGE_A_NAME = "java";
	private static final String LANGUAGE_B_NAME = "C#";
	private static final String LANGUAGE_C_NAME = "cobol";

	@Mock
	private ProjectDAO projectDAO;

	private ProjectBusiness projectBusiness;

	private List<Project> projects;

	@Before
	public void loadFixtures() {
		User authorA = createUser(USER_A_LOGIN, "author", "author", "user");
		User authorB = createUser(USER_B_LOGIN, "admin", "admin", "admin");
		User authorC = createUser(USER_C_LOGIN, "user", "user", "user");

		Discipline disciplineA = createDiscipline("1111", DISCIPLINE_NAME);
		Discipline disciplineB = createDiscipline("2222", "Administration");

		String titleA = PROJECT_TITLE;
		String descriptionA = PROJECT_DESCRIPTION;
		String storagePathA = PROJECT_PATH;

		List<User> authorsA = new ArrayList<User>();
		authorsA.add(authorA);
		authorsA.add(authorB);

		List<ProgrammingLanguage> programmingLanguagesA = new ArrayList<ProgrammingLanguage>();
		programmingLanguagesA.add(createProgrammingLanguage(LANGUAGE_A_NAME));
		programmingLanguagesA.add(createProgrammingLanguage(LANGUAGE_B_NAME));

		Project projectA = createProject(titleA, descriptionA, storagePathA, disciplineA, authorsA, programmingLanguagesA);

		String titleB = "projectB";
		String descriptionB = "description of the projectB";
		String storagePathB = "path/to/file/of/projectB";

		List<User> authorsB = new ArrayList<User>();
		authorsB.add(authorB);
		authorsB.add(authorC);

		List<ProgrammingLanguage> programmingLanguagesB = new ArrayList<ProgrammingLanguage>();
		programmingLanguagesB.add(createProgrammingLanguage(LANGUAGE_C_NAME));

		Project projectB = createProject(titleB, descriptionB, storagePathB, disciplineB, authorsB, programmingLanguagesB);

		projects = Arrays.asList(new Project[] { projectA, projectB });
	}

	@Before
	public void executeInjections() {
		projectBusiness = new ProjectBusiness(projectDAO);
	}

	@Test
	public void shouldCreateAProject() throws Exception {
		String title = "title";
		String storagePath = title + ".zip";
		String description = "description";

		List<User> authors = Arrays.asList(new User[] { new User(), new User() });
		List<ProgrammingLanguage> programmingLanguages = Arrays.asList(new ProgrammingLanguage[] { new ProgrammingLanguage() });
		Discipline discipline = new Discipline();

		projectBusiness.createProject(title, authors, programmingLanguages, discipline, storagePath, description);
		verify(projectDAO).insertProject(any(Project.class));
	}

	@Test
	public void shouldReturnEmptyProjectsList() {
		when(projectDAO.findProjectsByUserLogin(any(String.class))).thenReturn(null);
		List<ProjectDTO> projects = projectBusiness.retrieveProjects();
		assertEquals(0, projects.size());
	}

	@Test
	public void shouldReturnTwoProjects() throws Exception {
		when(projectDAO.findAllProjects()).thenReturn(projects);
		List<ProjectDTO> projectDTOs = projectBusiness.retrieveProjects();

		assertEquals(2, projectDTOs.size());
	}

	@Test
	public void shouldReturnAPrintableProject() throws Exception {
		when(projectDAO.findAllProjects()).thenReturn(projects);
		ProjectDTO projectDTO = projectBusiness.retrieveProjects().get(0);

		assertEquals(PROJECT_TITLE, projectDTO.getTitle());
		assertEquals(PROJECT_DESCRIPTION, projectDTO.getDescription());
		assertEquals(PROJECT_PATH, projectDTO.getFilePath());
		assertEquals(DISCIPLINE_NAME, projectDTO.getDiscipline());
		assertEquals(USER_A_LOGIN + " - " + USER_B_LOGIN, projectDTO.getAuthors());
		assertEquals(LANGUAGE_A_NAME + " - " + LANGUAGE_B_NAME, projectDTO.getProgrammingLanguages());
	}

	@Test
	public void shouldReturnAPrintableProjectWithOneLanguage() throws Exception {
		when(projectDAO.findAllProjects()).thenReturn(projects);
		ProjectDTO projectDTO = projectBusiness.retrieveProjects().get(1);

		assertEquals(LANGUAGE_C_NAME, projectDTO.getProgrammingLanguages());

	}

	@Test
	public void shouldReturnOnlyUserProjects() throws Exception {
		when(projectDAO.findProjectsByUserLogin(any(String.class))).thenReturn(projects);
		List<ProjectDTO> projectDTOs = projectBusiness.retrieveProjects(USER_C_LOGIN);
		assertEquals(1, projectDTOs.size());

		ProjectDTO projectDTO = projectDTOs.get(0);
		assertEquals(USER_B_LOGIN, projectDTO.getAuthors());
	}

	private Project createProject(String title, String description, String storagePath, Discipline disciplineA, List<User> authors,
			List<ProgrammingLanguage> programmingLanguages) {
		Project projectA = new Project();
		projectA.setTitle(title);
		projectA.setDescription(description);
		projectA.setStoragePath(storagePath);
		projectA.setDiscipline(disciplineA);
		projectA.setAuthors(authors);
		projectA.setProgrammingLanguages(programmingLanguages);
		return projectA;
	}

	private Discipline createDiscipline(String code, String name) {
		Discipline discipline = new Discipline();
		discipline.setCode(code);
		discipline.setName(name);
		return discipline;
	}

	private ProgrammingLanguage createProgrammingLanguage(String name) {
		ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
		programmingLanguage.setName(name);
		return programmingLanguage;
	}

	private User createUser(String login, String name, String password, String role) {
		User user = new User();
		user.setLogin(login);
		user.setName(name);
		user.setPassword(password);
		user.setRole(role);
		return user;
	}
}