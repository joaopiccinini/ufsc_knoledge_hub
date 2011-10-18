package org.ufsc.knoledgehub.business;

import java.util.ArrayList;
import java.util.List;

import org.ufsc.knoledgehub.dto.ProjectDTO;
import org.ufsc.knoledgehub.dto.ProjectDTOBuilder;
import org.ufsc.knoledgehub.model.ProjectDAO;
import org.ufsc.knoledgehub.model.entity.Discipline;
import org.ufsc.knoledgehub.model.entity.ProgrammingLanguage;
import org.ufsc.knoledgehub.model.entity.Project;
import org.ufsc.knoledgehub.model.entity.User;

import com.google.inject.Inject;

public class ProjectBusiness {

	private final ProjectDAO projectDAO;

	@Inject
	public ProjectBusiness(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	public void createProject(String title, List<User> authors, List<ProgrammingLanguage> programmingLanguages, Discipline discipline,
			String storagePath, String description) {
		Project project = new Project();
		project.setTitle(title);
		project.setDescription(description);
		project.setStoragePath(storagePath);
		project.setDiscipline(discipline);
		project.setAuthors(authors);
		project.setProgrammingLanguages(programmingLanguages);

		projectDAO.insertProject(project);
	}

	public List<ProjectDTO> retrieveProjects() {
		return createProjectDTOList(projectDAO.findAllProjects(), "");
	}

	public List<ProjectDTO> retrieveProjects(String userLogin) {
		List<Project> projects = projectDAO.findProjectsByUserLogin(userLogin);
		return projects != null ? createProjectDTOList(projects, userLogin) : null;
	}

	private List<ProjectDTO> createProjectDTOList(List<Project> projects, String userLogin) {
		ProjectDTOBuilder projectDTOBuilder = new ProjectDTOBuilder();
		List<ProjectDTO> projectsDTO = new ArrayList<ProjectDTO>();
		for (Project project : projects) {
			List<User> authors = project.getAuthors();
			if (isUserSpecified(userLogin)) {
				if (isProjectAuthor(userLogin, authors)) projectsDTO.add(projectDTOBuilder
						.createUserProjectDTO(project, authors, userLogin));
			} else
				projectsDTO.add(projectDTOBuilder.createProjectDTO(project, authors));
		}
		return projectsDTO;
	}

	private boolean isUserSpecified(String userLogin) {
		return userLogin != null && !("".equals(userLogin)); //$NON-NLS-1$
	}

	private boolean isProjectAuthor(String userLogin, List<User> authors) {
		for (User author : authors)
			if (author.getLogin().equals(userLogin)) return true;
		return false;
	}
}
