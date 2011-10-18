package org.ufsc.knoledgehub.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.ufsc.knoledgehub.model.entity.Discipline;
import org.ufsc.knoledgehub.model.entity.ProgrammingLanguage;
import org.ufsc.knoledgehub.model.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "Project")
@NamedQueries({
	@NamedQuery(name = "findAllProjects", query = "SELECT p FROM Project p"),
	@NamedQuery(name = "findProjectByTitle", query = "SELECT p FROM Project p WHERE p.title LIKE :title"),
	@NamedQuery(name = "findProjectsByAuthorLogin", query = "SELECT project FROM Project project JOIN project.authors authors WHERE authors.login = 'kaleu'")
})
public class Project {

    @Id
    @Column(name = "idProject")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Setter
    @Column(nullable = false, length = 20)
    private String title;
    
    @Setter
    @Column(length = 90)
    private String description;

    @Setter
    @Column(nullable = false, length = 45)
    private String storagePath;

    @Setter
    @ManyToMany
    @JoinTable(name = "Author",
	       joinColumns = { @JoinColumn(name = "idProject") },
	       inverseJoinColumns = { @JoinColumn(name = "idUser") }
    )
    private List<User> authors;

    @Setter
    @ManyToMany
    @JoinTable(name = "LanguageCombination",
	       joinColumns = { @JoinColumn(name = "idProject"),
	    		       @JoinColumn(name = "idDiscipline")
	    		     },
	       inverseJoinColumns = {@JoinColumn(name="idProgrammingLanguage")}
    )
    private List<ProgrammingLanguage> programmingLanguages;
    
    @Setter
    @ManyToOne
    @JoinColumn(name = "idDiscipline")
    private Discipline discipline; 
}
