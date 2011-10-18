package org.ufsc.knoledgehub.control;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController extends HttpServlet {
	private static final String URL_ATTRIB = "url"; //$NON-NLS-1$
	private static final String ACTION_ATTRIB = "action"; //$NON-NLS-1$
	private static final String USER_ATTRIB = "user"; //$NON-NLS-1$

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Principal userPrincipal = request.getUserPrincipal();
		if (userIsLogged(userPrincipal)) {
			request.setAttribute(USER_ATTRIB, userPrincipal.getName());
			request.setAttribute(ACTION_ATTRIB, "Logout"); //$NON-NLS-1$
			request.setAttribute(URL_ATTRIB, "logout"); //$NON-NLS-1$
		} else {
			request.setAttribute(ACTION_ATTRIB, "Login"); //$NON-NLS-1$
			request.setAttribute(URL_ATTRIB, "projectUpload"); //$NON-NLS-1$
		}
	}

	private boolean userIsLogged(Principal principal) {
		return principal != null;
	}
}
