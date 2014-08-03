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

import domain.ListOrder_Step;
import domain.OrderType;
import domain.StepOption;
import domain.User;

/**
 * Servlet implementation class CreateClientOrderServlet
 */
@WebServlet(description = "servlet to create client orders", urlPatterns = { "/CreateClientOrderServlet" })
public class CreateClientOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final int cakesType = 1;
	private static final int cupcakesType = 2;
	private static final Integer sweetcakesType = 3;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateClientOrderServlet() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			HttpSession session = request.getSession(true);
			User user = (User) session.getAttribute("user");
			RequestDispatcher rd;
			   
			if(user != null){
				Integer clientId = Integer.valueOf(request.getParameter("clientId"));
				ArrayList<OrderType> types = (ArrayList<OrderType>)CommandExecutor.getInstance().executeDatabaseCommand(new command.ListOrderTypes());
				
				request.setAttribute("clientId", clientId);				
				request.setAttribute("types", types);
				
				ArrayList<ListOrder_Step> cakes = (ArrayList<ListOrder_Step>)CommandExecutor.getInstance().executeDatabaseCommand(new command.ListPasos(cakesType));
				request.setAttribute("cakes", cakes);
				
				ArrayList<ListOrder_Step> cupcakes = (ArrayList<ListOrder_Step>)CommandExecutor.getInstance().executeDatabaseCommand(new command.ListPasos(cupcakesType));
				request.setAttribute("cupcakes", cupcakes);
				
				ArrayList<StepOption> sweetcakes = (ArrayList<StepOption>)CommandExecutor.getInstance().executeDatabaseCommand(new command.ListDulcesTortas(sweetcakesType));
				request.setAttribute("sweetcakes", sweetcakes);
								
				rd = getServletContext().getRequestDispatcher("/admin/createClientOrder.jsp");			
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
		
	}
}
