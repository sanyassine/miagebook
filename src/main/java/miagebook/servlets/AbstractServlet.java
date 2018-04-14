package miagebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;

import beans.UserProfile;
import persistence.CommentMapper;
import persistence.PostMapper;
import persistence.ProfileMapper;

public abstract class AbstractServlet extends HttpServlet{
	protected static final String USER = "user";
	protected static final CommentMapper commentMapper = CommentMapper.getInstance();
	protected static final PostMapper postMapper = PostMapper.getInstance();
	protected static final ProfileMapper profileMapper =ProfileMapper.getInstance();
	public AbstractServlet() {
		super();
	}
	
	protected void forwardTo(HttpServletRequest request, HttpServletResponse response,String jspFilepath) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(jspFilepath).forward(request, response);
	}
	
	protected void setUserInSession(HttpServletRequest request,UserProfile user) {
		request.getSession().setAttribute(USER, user);
	}
	
	protected void removeUserInSession(HttpServletRequest request) {
		request.getSession().removeAttribute(USER);
	}
	
	protected UserProfile getUserFromSession(HttpServletRequest request) {
		UserProfile user = (UserProfile) request.getSession().getAttribute(USER);
		return user;
	}
	
	@Override
    public void init() throws ServletException {
        System.out.println("Servlet " + this.getServletName() + " has started");
    }
    @Override
    public void destroy() {
        System.out.println("Servlet " + this.getServletName() + " has stopped");
    }

}
