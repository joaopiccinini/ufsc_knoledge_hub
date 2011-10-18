package org.ufsc.knoledgehub.business;

import java.util.List;

import org.ufsc.knoledgehub.model.UserDAO;
import org.ufsc.knoledgehub.model.entity.User;

public class UserBusiness {

	public List<User> retrieveUsers() {
		return new UserDAO().findAllUsers();
	}
}
