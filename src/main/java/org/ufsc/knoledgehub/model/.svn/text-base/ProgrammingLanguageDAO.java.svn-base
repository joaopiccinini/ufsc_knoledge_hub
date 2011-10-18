package org.ufsc.knoledgehub.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.ufsc.knoledgehub.model.entity.ProgrammingLanguage;

public class ProgrammingLanguageDAO {

	EntityManagerFactory emFactory;
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ProgrammingLanguage> findAllProgrammingLanguages() {
		openResources();
		List<ProgrammingLanguage> programmingLanguages = em.createNamedQuery("findAllProgrammingLanguages")
													.getResultList();
		closeResources();
		return programmingLanguages;
	}

	public ProgrammingLanguage findProgrammingLanguageById(Long id) {
		openResources();
		ProgrammingLanguage programmingLanguage = em.find(ProgrammingLanguage.class, id);
		closeResources();
		return programmingLanguage;
	}

	public ProgrammingLanguage findProgrammingLanguageByName(String name) {
		openResources();
		ProgrammingLanguage programmingLanguage = (ProgrammingLanguage) em.createNamedQuery("findProgramminLanguageByName")
																			.setParameter("name", name)
																			.getSingleResult();
		closeResources();
		return programmingLanguage;
	}

	public void insertProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
		openResources();
		em.getTransaction().begin();
		em.persist(programmingLanguage);
		em.getTransaction().commit();
		closeResources();
	}

	public void deleteProgrammingLanguage(String name) {
		openResources();
		em.getTransaction().begin();
		ProgrammingLanguage programmingLanguage = (ProgrammingLanguage) em.createNamedQuery("findProgramminLanguageByName")
																			.setParameter("name", name)
																			.getSingleResult();
		em.remove(programmingLanguage);
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
}
