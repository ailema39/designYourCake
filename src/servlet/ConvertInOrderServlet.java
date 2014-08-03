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
import domain.Order;
import domain.User;

/**
 * Servlet implementation class ConvertInOrderServlet
 */
@WebServlet(description = "servlet to convert estimation in order", urlPatterns = { "/ConvertInOrderServlet" })
public class ConvertInOrderServlet extends HttpServlet {

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
    public ConvertInOrderServlet() {
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
				Long clientId = Long.valueOf(request.getParameter("clientId"));
				Integer orderTypeId = Integer.valueOf(request.getParameter("orderTypeId"));
				
				request.setAttribute("clientId", clientId);				
				request.setAttribute("estimationId", estimationId);				
				request.setAttribute("orderTypeId", orderTypeId);
				
				rd = getServletContext().getRequestDispatcher("/admin/convertInOrder.jsp");			

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
		
		RequestDispatcher rd;	
		
		try{			
			Long estimationId = Long.valueOf(request.getParameter("txtEstimationId"));
			Long clientId = Long.valueOf(request.getParameter("txtClientId"));
			Integer orderTypeId = Integer.valueOf(request.getParameter("txtOrderTypeId"));
			Double total = Double.valueOf(request.getParameter("txtPrice"));
			String deliveryDate = request.getParameter("txtDeliveryDate");
			String additionalInfo = request.getParameter("txtAdditionalInfo");
									
			Order order = new Order();
			order.setClientId(clientId);
			order.setDeliveryDate(deliveryDate);
			order.setOrderTypeId(orderTypeId);
			order.setTotal(total);
			order.setAdditionalInfo(additionalInfo);
			order.setEstimationId(estimationId);
			order.setIsPending(1);
			
			Integer rowsUpdated  = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.CreateSpecialOccasionOrder(order));			
			order.setId(rowsUpdated);
			
			if(rowsUpdated != -1){
				request.setAttribute("info", "El presupuesto fue convertido en pedido exitosamente.");
				request.setAttribute("error", "");
				rd = getServletContext().getRequestDispatcher("/ListEstimationsServlet");
				//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListEstimationsServlet");			
				rd.forward(request, response);
			} else {
				request.setAttribute("info", "");
				request.setAttribute("error", "Ocurrió un error durante la conversión del presupuesto en pedido. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
				rd = getServletContext().getRequestDispatcher("/ListEstimationsServlet");	
				//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListEstimationsServlet");			

				rd.forward(request, response);
			}		
			
		}catch (Exception e) {
			request.setAttribute("info", "");
			request.setAttribute("error", "Ocurrió un error durante la conversión del presupuesto en pedido. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
			rd = getServletContext().getRequestDispatcher("/ListEstimationsServlet");	
			//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListEstimationsServlet");			

			rd.forward(request, response);
		}
	}
}
