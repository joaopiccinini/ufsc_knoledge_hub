package org.ufsc.knoledgehub.model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Test;
import org.ufsc.knoledgehub.model.entity.ProgrammingLanguage;

public class ProgrammingLanguageDAOTest {
	@Test
	public void shouldRetrieveAllUsers() throws Exception {
		List<ProgrammingLanguage> programmingLanguages = new ProgrammingLanguageDAO().findAllProgrammingLanguages();
		assertEquals(1, programmingLanguages.size());
	}

	@Test
	public void shouldInsertAndRetrieveUserByName() throws Exception {
		ProgrammingLanguageDAO programmingLanguageDAO = new ProgrammingLanguageDAO();

		ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
		String name = "java";
		programmingLanguage.setName(name);

		programmingLanguageDAO.insertProgrammingLanguage(programmingLanguage);
		ProgrammingLanguage loadedProgrammingLanguage = programmingLanguageDAO.findProgrammingLanguageByName(name);
		assertEquals(name, loadedProgrammingLanguage.getName());
	}

	@Test(expected = NoResultException.class)
	public void shouldDeleteProgrammingLanguageUserByName() throws Exception {
		ProgrammingLanguageDAO programmingLanguageDAO = new ProgrammingLanguageDAO();
		ProgrammingLanguage loadedProgrammingLanguage = programmingLanguageDAO.findProgrammingLanguageByName("java");
		programmingLanguageDAO.deleteProgrammingLanguage(loadedProgrammingLanguage.getName());
		assertEquals(null, programmingLanguageDAO.findProgrammingLanguageByName("java"));
	}
}
