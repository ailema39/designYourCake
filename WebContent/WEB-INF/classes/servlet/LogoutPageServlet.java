package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.CommandExecutor;


/**
 * Servlet implementation class LoginPageServlet
 */
@WebServlet(description = "servlet to log in users", urlPatterns = { "/LogoutPageServlet" })
public class LogoutPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		super.init();
		try {
			CommandExecutor.getInstance();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutPageServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd;
		HttpSession session = request.getSession(true);
		session.removeAttribute("client");
		rd = getServletContext().getRequestDispatcher("/index.jsp");			
		rd.forward(request, response);
    }
    
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
