package org.ufsc.knoledgehub.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.ufsc.knoledgehub.model.entity.Project;

public class ProjectDAO {
	EntityManagerFactory emFactory;
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Project> findAllProjects() {
		openResources();
		List<Project> projects = em.createNamedQuery("findAllProjects").getResultList();
		closeResources();
		return projects;
	}

	public void insertProject(Project project) {
		openResources();
		em.getTransaction().begin();
		em.persist(project);
		em.getTransaction().commit();
		closeResources();
	}

	public Project findProjectByTitle(String title) {
		openResources();
		Project loadedProjects = (Project) em.createNamedQuery("findProjectByTitle")
						.setParameter("title", title)
						.getSingleResult();
		closeResources();
		return loadedProjects;
	}

	public void deleteProjectByTitle(String title) {
		openResources();
		em.getTransaction().begin();
		Project loadedProject = (Project) em.createNamedQuery("findProjectByTitle")
						.setParameter("title", title)
						.getSingleResult();
		em.remove(loadedProject);
		em.getTransaction().commit();
		closeResources();
	}

	public void updateProjectByTitle(Project project) {
		openResources();
		em.getTransaction().begin();
		Project loadedProject = (Project) em.createNamedQuery("findProjectByTitle")
						.setParameter("title", project.getTitle())
						.getSingleResult();
		loadedProject.setAuthors(project.getAuthors());
		loadedProject.setDescription(project.getDescription());
		loadedProject.setDiscipline(project.getDiscipline());
		loadedProject.setProgrammingLanguages(project.getProgrammingLanguages());
		loadedProject.setStoragePath(project.getStoragePath());
		em.flush();
		em.getTransaction().commit();
		closeResources();
	}

	private void openResources() {
		emFactory = Persistence.createEntityManagerFactory("Knoledgehub", System.getProperties()); //$NON-NLS-1$
		em = emFactory.createEntityManager();
	}

	private void closeResources() {
		em.close();
		emFactory.close();
	}

	@SuppressWarnings("unchecked")
	public List<Project> findProjectsByUserLogin(String userLogin) {
		openResources();
		List<Project> loadedProjects = em.createNativeQuery(
				"SELECT project " +
						"FROM Project as project " +
						"INNER JOIN Author as author ON project.idProject = author.idProject " +
						"INNER JOIN User as user ON user.idUser = author.idUser " +
						"WHERE user.login = \"" + userLogin + "\"")
				.getResultList();
		closeResources();
		return loadedProjects;
	}
}
