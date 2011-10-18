package org.ufsc.knoledgehub.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "ProgrammingLanguage")
@NamedQueries({
	@NamedQuery(name = "findAllProgrammingLanguages", query = "SELECT pL FROM ProgrammingLanguage pL"),
	@NamedQuery(name = "findProgramminLanguageByName", query = "SELECT pL FROM ProgrammingLanguage pL WHERE pL.name LIKE :name") })
public class ProgrammingLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProgrammingLanguage")
    private Long id; 

    @Setter
    @Column(updatable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(name = "LanguageCombination",
	       joinColumns = {@JoinColumn(name = "idProgrammingLanguage")},
	       inverseJoinColumns = {@JoinColumn(name = "idProject"),
	    			     @JoinColumn(name = "idDiscipline")
    	       }
    )
    private Set<Project> projects;
    
    @Override
    public String toString() {
    	return name;
    }
    
}
