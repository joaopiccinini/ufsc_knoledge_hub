package org.ufsc.knoledgehub.model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ufsc.knoledgehub.model.entity.Discipline;

public class DisciplineDAOTest {

	@Before
	public void loadDatabaseFixtures() {
		// TODO
	}

	@After
	public void removeDatabaseFixtures() {
		// TODO
	}

	@Test
	public void shouldRetrieveAllDisciplines() throws Exception {
		DisciplineDAO disciplineDAO = new DisciplineDAO();
		List<Discipline> resultList = disciplineDAO.findAllDisciplines();
		assertEquals(3, resultList.size());
	}

	@Test
	public void shouldRetrieveDisciplineById() throws Exception {
		Discipline discipline = new DisciplineDAO().findDisciplineById(1L);
		assertEquals(new Long(1), discipline.getId());
	}

	@Test
	public void shouldPersistDiscipline() throws Exception {
		Discipline discipline = new Discipline();
		String code = "test";
		discipline.setCode(code);
		discipline.setName("Programming");
		DisciplineDAO disciplineDAO = new DisciplineDAO();
		disciplineDAO.insertDiscipline(discipline);
		disciplineDAO.findDisciplineByCode(code);
	}

	@Test
	public void shouldDeleteDiscipline() throws Exception {
		String code = "test";
		new DisciplineDAO().deleteDiscipline(code);
	}

	@Test
	public void shouldUpdateDiscipline() throws Exception {
		DisciplineDAO disciplineDAO = new DisciplineDAO();

		String oldName = "oldName";
		String newName = "newName";
		String code = "code";
		Discipline discipline = new Discipline();
		discipline.setCode(code);
		discipline.setName(oldName);

		disciplineDAO.deleteDiscipline(code);
		disciplineDAO.insertDiscipline(discipline);
		discipline.setName(newName);
		disciplineDAO.updateDiscipline(discipline);
		assertEquals(newName, disciplineDAO.findDisciplineByCode(code).getName());
	}
}
