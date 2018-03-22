package miagebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractServlet extends HttpServlet{

	public AbstractServlet() {
		super();
	}
	
	protected void forwardTo(HttpServletRequest request, HttpServletResponse response,String jspFilepath) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(jspFilepath).forward(request, response);
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
