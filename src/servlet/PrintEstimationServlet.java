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
import domain.Estimation;
import domain.User;

/**
 * Servlet implementation class PrintEstimationServlet
 */
@WebServlet(description = "servlet to print estimation", urlPatterns = { "/PrintEstimationServlet" })
public class PrintEstimationServlet extends HttpServlet {

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
    public PrintEstimationServlet() {
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
				Long estimationId = Long.valueOf(request.getParameter("estimationId"));
				Estimation estimationInfo = (Estimation)CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectEstimation(estimationId));
				
				request.setAttribute("estimationInfo", estimationInfo);				
				request.setAttribute("estimationId", estimationId);
				
				rd = getServletContext().getRequestDispatcher("/admin/printEstimation.jsp");			

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
