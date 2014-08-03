package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.CommandExecutor;
import domain.Client;
import domain.Estimation;
import domain.Item;
import domain.Order;
import domain.User;

/**
 * Servlet implementation class ShowClientOrderServlet
 */
@WebServlet(description = "servlet to show client order", urlPatterns = { "/ShowClientOrderServlet" })
public class ShowClientOrderServlet extends HttpServlet {

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
    public ShowClientOrderServlet() {
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
				Long orderId = Long.valueOf(request.getParameter("orderId"));
				Order orderInfo = (Order)CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectOrder(orderId));
				
				Client clientInfo = (Client)CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectClient(clientId));
				
				orderInfo.setClient(clientInfo);
				
				if(orderInfo.getEstimationId() != null){
					Estimation estimationInfo = (Estimation)CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectEstimation(orderInfo.getEstimationId()));
					orderInfo.setEstimation(estimationInfo);
				}
				
				@SuppressWarnings("unchecked")
				List<Item> items = (List<Item>)CommandExecutor.getInstance().executeDatabaseCommand(new command.ListItems(orderId));
				
				orderInfo.setItems(items);
				
				request.setAttribute("orderInfo", orderInfo);				
				request.setAttribute("orderId", orderId);			
				request.setAttribute("clientId", clientId);
				
				rd = getServletContext().getRequestDispatcher("/admin/showClientOrder.jsp");			

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
