package servlet;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class ListEstimationsServlet
 */
@WebServlet(description = "servlet to list estimations", urlPatterns = { "/ListEstimationsServlet" })
public class ListEstimationsServlet extends HttpServlet {

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
    public ListEstimationsServlet() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			
			if(user != null){
				// perform list orders operations
				String info = (String)request.getAttribute("info")!=null?(String)request.getAttribute("info"):"";
				String error = (String)request.getAttribute("error")!=null?(String)request.getAttribute("error"):"";
				@SuppressWarnings("unchecked")
				ArrayList<Estimation> list = (ArrayList<Estimation>)CommandExecutor.getInstance().executeDatabaseCommand(new command.ListEstimations());
				request.setAttribute("estimations", list);
				request.setAttribute("info", info);
				request.setAttribute("error", error);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/estimations.jsp");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/index.jsp");
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
