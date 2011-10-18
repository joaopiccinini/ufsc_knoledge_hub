package org.ufsc.knoledgehub.dto;

import java.util.List;

import org.ufsc.knoledgehub.model.entity.Project;
import org.ufsc.knoledgehub.model.entity.User;

/**
 * Creates a ProjectDTO with the string parsed attributes, to show in the view
 * layer.
 * 
 * @author João Paulo Girardi Piccinini <br/>
 *         Kaleu Puskas Diedrich Caminha
 */
public class ProjectDTOBuilder {

	/**
	 * Creates a Project DTO that returns all the projects of the user
	 * specified.
	 * 
	 * @param project
	 *            Project bean, that will originate the DTO.
	 * @param authors
	 *            Authors list, that will be used as an attribute in the DTO.
	 * @param userLogin
	 *            Name of the actual user, that will be used to filter the
	 *            projects list.
	 * @return a new DTO for a project bean.
	 */
	public ProjectDTO createUserProjectDTO(Project project, List<User> authors, String userLogin) {
		authors.remove(getProjectAuthor(userLogin, authors));
		return createProjectDTO(project, authors);
	}

	/**
	 * Creates a Project DTO that returns all the projects.
	 * 
	 * @param project
	 *            Project bean, that will originate the DTO.
	 * @param authors
	 *            Authors list, that will be used as an attribute in the DTO.
	 * @return a new DTO for a project bean.
	 */
	public ProjectDTO createProjectDTO(Project project, List<User> authors) {
		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setTitle(project.getTitle());
		projectDTO.setAuthors(getPrintableList(authors));
		projectDTO.setProgrammingLanguages(getPrintableList(project.getProgrammingLanguages()));
		projectDTO.setDescription(project.getDescription());
		projectDTO.setDiscipline(project.getDiscipline().toString());
		projectDTO.setFilePath(project.getStoragePath());
		return projectDTO;
	}

	/**
	 * Gets the instance of the projects author, if the same exists.
	 * 
	 * @param userLogin
	 *            User login, to be compared with the list.
	 * @param authors
	 *            List of all authors in the system.
	 * @return the user instance, if exists. Otherwise, return null.
	 */
	private User getProjectAuthor(String userLogin, List<User> authors) {
		User projectAuthor = null;
		for (User author : authors)
			if (author.getLogin().equals(userLogin)) projectAuthor = author;
		return projectAuthor;
	}

	/**
	 * Constructs a String to be used in the view layer, with the list of
	 * objects.
	 * 
	 * @param list
	 *            List of objects to construct the String.
	 * @return A String to send to view layer, by the DTO.
	 */
	@SuppressWarnings("rawtypes")
	private String getPrintableList(List list) {
		String result = new String();
		for (int i = 0; i < list.size() - 1; i++)
			result += list.get(i).toString() + " - "; //$NON-NLS-1$
		if (list.size() > 0) result += list.get(list.size() - 1).toString();
		return result;
	}
}
