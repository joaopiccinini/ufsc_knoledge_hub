package org.ufsc.knoledgehub.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "Discipline")
@NamedQueries({
		@NamedQuery(name = "findAllDisciplines", query = "SELECT d FROM Discipline d"),
		@NamedQuery(name = "findDisciplineByCode", query = "SELECT d FROM Discipline d WHERE d.code LIKE :code") })
public class Discipline implements Serializable {
    /** @serial */
    private static final long serialVersionUID = 4230666054788542360L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDiscipline")
    private Long id;

    @Setter
    @Column(nullable = false, updatable = false, unique = true, length = 45)
    private String code;

    @Setter
    @Column(nullable = false, length = 60)
    private String name;

    @OneToMany(mappedBy="discipline")
    private List<Project> projects;
    
    @Override
    public String toString() {
    	return name;
    }
}
