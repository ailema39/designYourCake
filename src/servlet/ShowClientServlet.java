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
import domain.Client;
import domain.User;

/**
 * Servlet implementation class ShowClientServlet
 */
@WebServlet(description = "servlet to show client", urlPatterns = { "/ShowClientServlet" })
public class ShowClientServlet extends HttpServlet {

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
    public ShowClientServlet() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	try {
    		HttpSession session = request.getSession(true);
			User user = (User) session.getAttribute("user");
			RequestDispatcher rd;
			   
			if(user != null){
				Long clientId = Long.valueOf(request.getParameter("clientId"));
				Client clientInfo = (Client)CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectClient(clientId));
				
				request.setAttribute("clientInfo", clientInfo);				
				request.setAttribute("clientId", clientId);
				
				rd = getServletContext().getRequestDispatcher("/admin/showClient.jsp");			

				rd.forward(request, response);			
			} else {
				rd = getServletContext().getRequestDispatcher("/admin/index.jsp");			

				rd.forward(request, response);
			}			
		} catch (Exception e) {
			throw new ServletException(e);
		}
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
