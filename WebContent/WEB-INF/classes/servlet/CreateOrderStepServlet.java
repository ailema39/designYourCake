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

import domain.OrderStep;
import domain.User;

/**
 * Servlet implementation class CreateOrderStepServlet
 */
@WebServlet(description = "servlet to create order steps", urlPatterns = { "/CreateOrderStepServlet" })
public class CreateOrderStepServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOrderStepServlet() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			HttpSession session = request.getSession(true);
			User user = (User) session.getAttribute("user");
			RequestDispatcher rd;
			   
			if(user != null){
				Integer typeId = Integer.valueOf(request.getParameter("typeId"));
				Integer position = (Integer)CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectNextStep(typeId));
				request.setAttribute("typeId", typeId);
				request.setAttribute("position", position);
				
				rd = getServletContext().getRequestDispatcher("/admin/createOrderStep.jsp");			
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
		Integer typeId = Integer.valueOf(request.getParameter("txtTypeId"));
		
		try{
			String name = request.getParameter("txtName");
			Integer position = Integer.valueOf(request.getParameter("txtPosition"));
			int isMultipleChoice = 0;
			
			if (request.getParameter("txtIsMultSel") != null)
				isMultipleChoice = 1;			
					
			OrderStep step = new OrderStep();
			step.setOrderTypeId(typeId);
			step.setName(name);
			step.setPosition(position);
			step.setMultipleChoice(isMultipleChoice);
		
			Integer rowsUpdated  = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.CreateOrderStep(step));
			
			step.setId(rowsUpdated);
	
			if(rowsUpdated != -1){
				request.setAttribute("info", "EL paso fue creado exitosamente.");
				request.setAttribute("error", "");
				rd = getServletContext().getRequestDispatcher("/ListOrderStepsServlet?typeId="+typeId);			
				rd.forward(request, response);
			} else {
				request.setAttribute("info", "");
				request.setAttribute("error", "Ocurrió un error durante la creación del paso. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
				rd = getServletContext().getRequestDispatcher("/ListOrderStepsServlet?typeId="+typeId);			

				rd.forward(request, response);
			}
		}catch (Exception e) {
			request.setAttribute("info", "");
			request.setAttribute("error", "Ocurrió un error durante la creación del paso. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
			rd = getServletContext().getRequestDispatcher("/ListOrderStepsServlet?typeId="+typeId);			

			rd.forward(request, response);
		}
	}

}
