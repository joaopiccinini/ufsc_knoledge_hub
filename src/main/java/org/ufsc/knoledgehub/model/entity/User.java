package org.ufsc.knoledgehub.model.entity;

import java.io.Serializable;
import java.util.List;

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
@Table(name = "User")
@NamedQueries({
	@NamedQuery(name = "findAllUsers", query = "SELECT u FROM User u"),
	@NamedQuery(name = "findUserByLogin", query = "SELECT u FROM User u WHERE u.login LIKE :login"),
	@NamedQuery(name = "findAllUsersByRole", query = "SELECT u FROM User u WHERE u.role LIKE :role") })
public class User implements Serializable {
    /** @serial */
    private static final long serialVersionUID = 1183524220659177329L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Long id;
    
    @ManyToMany
    @JoinTable(name = "Author", 
	       joinColumns = {@JoinColumn(name = "idUser")},
	       inverseJoinColumns = {@JoinColumn(name = "idProject")}
    )
    private List<Project> projects;
    
    @Setter
    @Column(unique = true, updatable = false, nullable = false)
    private String login;
    
    @Setter
    @Column(nullable = false)
    private String name;
    
    @Setter
    @Column(nullable = false)
    private String password;
    
    @Setter
    @Column(nullable = false)
    private String role;
    
    @Override
    public String toString() {
    	return login;
    }
}