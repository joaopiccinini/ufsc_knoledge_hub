package org.ufsc.knoledgehub.business;

import java.util.List;

import org.ufsc.knoledgehub.model.DisciplineDAO;
import org.ufsc.knoledgehub.model.entity.Discipline;

import com.google.inject.Inject;

public class DisciplineBusiness {

	@Inject
	private DisciplineDAO disciplineDAO;

	public List<Discipline> retrieveDisciplines() {
		return disciplineDAO.findAllDisciplines();
	}
}
