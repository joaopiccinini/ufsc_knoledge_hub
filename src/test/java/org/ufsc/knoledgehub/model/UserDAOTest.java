package org.ufsc.knoledgehub.model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ufsc.knoledgehub.model.entity.User;

public class UserDAOTest {

	static final Logger logger = LoggerFactory.getLogger(UserDAOTest.class);

	@Rule
	public MethodRule watchman = new TestWatchman() {
		@Override
		public void starting(FrameworkMethod method) {
			logger.info("{} being run...", method.getName());
		}

		@Override
		public void failed(Throwable e, FrameworkMethod method) {
			logger.error("{} failed: {}", method.getName(), e.getMessage());
			super.failed(e, method);
		}
	};

	@Test
	public void shouldRetrieveAllUsers() throws Exception {
		List<User> resultList = new UserDAO().findAllUsers();
		assertEquals(4, resultList.size());
	}

	@Test
	public void shouldInsertAndRetrieveUserById() throws Exception {
		UserDAO userDAO = new UserDAO();
		String login = "meulogin";

		User user = new User();
		user.setPassword("pass");
		user.setName("name");
		user.setLogin(login);
		user.setRole("user");
		userDAO.insertUser(user);
		User loadedUser = userDAO.findUserByLogin(login);
		assertEquals(login, loadedUser.getLogin());
	}

	@Test(expected = NoResultException.class)
	public void shouldDeleteUserByLogin() throws Exception {
		UserDAO userDAO = new UserDAO();
		User loadedUser = userDAO.findUserByLogin("meulogin");
		userDAO.deleteUserByLogin(loadedUser.getLogin());
		assertEquals(null, userDAO.findUserByLogin("login"));
	}

	@Test
	public void shouldUpdateUser() throws Exception {
		UserDAO userDAO = new UserDAO();
		String oldName = "oldName";
		String newName = "newName";
		String login = "login3";

		User user = new User();
		user.setLogin(login);
		user.setName(oldName);
		user.setPassword("pass");
		user.setRole("admin");
		userDAO.insertUser(user);

		user.setName("newName");
		userDAO.updateUserByLogin(user);

		User loadedUser = userDAO.findUserByLogin(login);
		assertEquals(newName, loadedUser.getName());
		userDAO.deleteUserByLogin(loadedUser.getLogin());
	}
}
