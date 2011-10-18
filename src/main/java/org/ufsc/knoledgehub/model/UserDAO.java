package org.ufsc.knoledgehub.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.ufsc.knoledgehub.model.entity.User;

public class UserDAO {
	EntityManagerFactory emFactory;
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		openResources();
		List<User> users = em.createNamedQuery("findAllUsers").getResultList();
		closeResources();
		return users;
	}

	private void openResources() {
		emFactory = Persistence.createEntityManagerFactory("Knoledgehub", System.getProperties()); //$NON-NLS-1$
		em = emFactory.createEntityManager();
	}

	private void closeResources() {
		em.close();
		emFactory.close();
	}

	public void insertUser(User user) {
		openResources();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		closeResources();
	}

	public User findUserByLogin(String login) {
		openResources();
		User loadedUser = (User) em
				.createNamedQuery("findUserByLogin").setParameter("login", login).getSingleResult(); //$NON-NLS-1$ //$NON-NLS-2$
		closeResources();
		return loadedUser;
	}

	public void deleteUserByLogin(String login) {
		openResources();
		em.getTransaction().begin();
		User loadedUser = (User) em.createNamedQuery("findUserByLogin")
					.setParameter("login", login)
					.getSingleResult();
		em.remove(loadedUser);
		em.getTransaction().commit();
		closeResources();
	}

	public void updateUserByLogin(User user) {
		openResources();
		em.getTransaction().begin();
		User loadedUser = (User) em.createNamedQuery("findUserByLogin")
					.setParameter("login", user.getLogin())
					.getSingleResult();
		loadedUser.setName(user.getName());
		loadedUser.setPassword(user.getPassword());
		loadedUser.setRole(user.getRole());
		em.flush();
		em.getTransaction().commit();
		closeResources();
	}
}