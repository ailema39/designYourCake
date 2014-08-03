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
import domain.OrderStep;
import domain.OrderType;
import domain.User;

/**
 * Servlet implementation class ListOrderStepsServlet
 */
@WebServlet(description = "servlet to list order steps", urlPatterns = { "/ListOrderStepsServlet" })
public class ListOrderStepsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListOrderStepsServlet() {
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
				// perform list user operations
				Integer typeId = Integer.valueOf(request.getParameter("typeId"));
				String info = (String)request.getAttribute("info")!=null?(String)request.getAttribute("info"):"";
				String error = (String)request.getAttribute("error")!=null?(String)request.getAttribute("error"):"";
				@SuppressWarnings("unchecked")
				ArrayList<OrderStep> list = (ArrayList<OrderStep>)CommandExecutor.getInstance().executeDatabaseCommand(new command.ListOrderSteps(typeId));
				OrderType typeInfo = (OrderType)CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectOrderType(typeId));

				request.setAttribute("steps", list);
				request.setAttribute("typeInfo", typeInfo);
				request.setAttribute("info", info);
				request.setAttribute("error", error);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/orderSteps.jsp");
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
