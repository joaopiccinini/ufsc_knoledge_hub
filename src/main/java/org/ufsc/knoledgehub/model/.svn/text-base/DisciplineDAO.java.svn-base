package org.ufsc.knoledgehub.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.ufsc.knoledgehub.model.entity.Discipline;

public class DisciplineDAO {

	EntityManagerFactory emFactory;
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Discipline> findAllDisciplines() {
		openResources();
		List<Discipline> resultList = em.createNamedQuery("findAllDisciplines").getResultList(); //$NON-NLS-1$
		closeResources();
		return resultList;
	}

	public Discipline findDisciplineById(Long id) {
		openResources();
		Discipline discipline = em.find(Discipline.class, id);
		closeResources();
		return discipline;
	}

	public Discipline findDisciplineByCode(String code) {
		openResources();
		Discipline discipline = queryDisciplineByCode(code);
		closeResources();
		return discipline;
	}

	public void insertDiscipline(Discipline discipline) {
		openResources();
		em.getTransaction().begin();
		em.persist(discipline);
		em.getTransaction().commit();
		closeResources();
	}

	public void deleteDiscipline(String code) {
		openResources();
		em.getTransaction().begin();
		Discipline discipline = queryDisciplineByCode(code);
		em.remove(discipline);
		em.getTransaction().commit();
		closeResources();
	}

	public void updateDiscipline(Discipline discipline) {
		openResources();
		em.getTransaction().begin();
		Discipline loadedDiscipline = em.find(Discipline.class, discipline.getId());
		loadedDiscipline.setName(discipline.getName());
		em.flush();
		em.getTransaction().commit();
		closeResources();
	}

	private Discipline queryDisciplineByCode(String code) {
		return (Discipline) em.createNamedQuery("findDisciplineByCode").setParameter("code", code).getSingleResult();
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
