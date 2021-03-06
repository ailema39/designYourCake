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
import domain.User;

/**
 * Servlet implementation class CompleteOrderServlet
 */
@WebServlet(description = "servlet to complete orders", urlPatterns = { "/CompleteOrderServlet" })
public class CompleteOrderServlet extends HttpServlet {

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
    public CompleteOrderServlet() {
        super();
    }	
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd;

		try {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			
			if(user != null){
				
				// perform complete client order
				Long orderId = Long.valueOf(request.getParameter("orderId"));
				Integer rowsUpdated = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.CompleteOrder(orderId));
				
				if(rowsUpdated == 1){
					
					request.setAttribute("info", "El pedido fue completado exitosamente.");
					request.setAttribute("error", "");
					rd = getServletContext().getRequestDispatcher("/ListOrdersServlet");
					//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListOrdersServlet");	

					rd.forward(request, response);
				} else {
					request.setAttribute("info", "");
					request.setAttribute("error", "Ocurri� un error durante la culminaci�n del pedido. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
					rd = getServletContext().getRequestDispatcher("/ListOrdersServlet");
					//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListOrdersServlet");

					rd.forward(request, response);
				}
			} else {
				rd = getServletContext().getRequestDispatcher("/admin/index.jsp");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {			
			request.setAttribute("info", "");
			request.setAttribute("error", "Ocurri� un error durante la culminaci�n del pedido. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
			rd = getServletContext().getRequestDispatcher("/ListOrdersServlet");
			//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListOrdersServlet");		

			rd.forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
