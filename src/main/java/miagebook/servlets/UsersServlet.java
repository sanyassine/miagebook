package miagebook.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Profile;
import beans.UserProfile;
import persistence.ProfileMapper;

public class UsersServlet extends AbstractServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserProfile user = getUserFromSession(request);
		if(user != null) {
			List<Profile> users = profileMapper.findAll();
			request.setAttribute("users", users);
			request.getSession().setAttribute("user", user);
			forwardTo(request,response,"/users.jsp");
		}else
			response.sendRedirect("login");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserProfile user = getUserFromSession(request);
		if(user != null) {
			String loginRemove = (String) request.getParameter("loginRemove");
			String loginAdd = (String) request.getParameter("loginAdd");
			if(loginRemove != null) {
				profileMapper.removeFriend(user, loginRemove);
			}else if(loginAdd != null) {
				profileMapper.addFriend(user, loginAdd);
			}
			setUserInSession(request, user);
			response.sendRedirect("users");
		}else {
			response.sendRedirect("login");
		}
	}
}
