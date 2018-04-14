package miagebook.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Profile;
import beans.UserProfile;
import persistence.ProfileMapper;
import services.FriendsService;

public class MyProfileServlet extends AbstractServlet{
	
	 @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			UserProfile user = (UserProfile) request.getSession().getAttribute("user");
			if(user != null) {
				List<Profile> friends = profileMapper.findFriendsByProfile(user);
				request.setAttribute("friends", friends);
				forwardTo(request,response,"/myprofile.jsp");
			}
			else
				response.sendRedirect("login");
	 }
	 
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	UserProfile user = getUserFromSession(request);
			if(user != null) {
				String loginRemove = (String) request.getParameter("loginRemove");
				String loginAdd = (String) request.getParameter("loginAdd");
				if(loginRemove != null) {
					FriendsService.deleteFriends(user, loginRemove);
				}else if(loginAdd != null) {
					FriendsService.makeFriends(user, loginAdd);
				}
				List<Profile> users = profileMapper.findAll();
				setUserInSession(request, user);
				request.setAttribute("users", users);
				response.sendRedirect("myprofile");
			}else {
				response.sendRedirect("login");
			}
	 }

}
