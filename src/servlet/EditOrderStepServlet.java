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
 * Servlet implementation class EditOrderStepServlet
 */
@WebServlet(description = "servlet to edit order step", urlPatterns = { "/EditOrderStepServlet" })
public class EditOrderStepServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public EditOrderStepServlet() {
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
				Long stepId = Long.valueOf(request.getParameter("stepId"));
				Long typeId = Long.valueOf(request.getParameter("typeId"));
				OrderStep stepInfo = (OrderStep)CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectOrderStep(stepId));
				
				request.setAttribute("stepInfo", stepInfo);
				
				request.setAttribute("stepId", stepId);
				request.setAttribute("typeId", typeId);
				
				rd = getServletContext().getRequestDispatcher("/admin/editOrderStep.jsp");			

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
		Long typeId = Long.valueOf(request.getParameter("txtTypeId"));
				
		try{
			Long stepId = Long.valueOf(request.getParameter("txtStepId"));
			String name = request.getParameter("txtName");
			Integer oldPosition = Integer.valueOf(request.getParameter("txtCurrentPosition"));
			Integer position = Integer.valueOf(request.getParameter("txtPosition"));
			
			int isMultipleChoice = 0;
			
			if (request.getParameter("txtIsMultSel") != null)
				isMultipleChoice = 1;			
						
			OrderStep step = new OrderStep();
			step.setId(stepId);
			step.setOrderTypeId(typeId);
			step.setName(name);
			step.setPosition(position);
			step.setOldPosition(oldPosition);
			step.setMultipleChoice(isMultipleChoice);			
			
			Integer rowsUpdated = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.EditOrderStep(step));
			
			if(rowsUpdated == 1){
				request.setAttribute("info", "El paso fue editado exitosamente.");
				request.setAttribute("error", "");
				//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListOrderStepsServlet?typeId="+typeId);	
				rd = getServletContext().getRequestDispatcher("/ListOrderStepsServlet?typeId="+typeId);				

				rd.forward(request, response);
			} else {
				request.setAttribute("info", "");
				request.setAttribute("error", "Ocurrió un error durante la edición del paso. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
				//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListOrderStepsServlet?typeId="+typeId);	
				rd = getServletContext().getRequestDispatcher("/ListOrderStepsServlet?typeId="+typeId);				
			

				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			
			
			request.setAttribute("info", "");
			request.setAttribute("error", "Ocurrió un error durante la edición del paso. Por favor intente de nuevo y si el error persiste contacte a su administrador." + e.getMessage() + e.getCause());
			//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListOrderStepsServlet?typeId="+typeId);		
			rd = getServletContext().getRequestDispatcher("/ListOrderStepsServlet?typeId="+typeId);				
	

			rd.forward(request, response);
		}
	}
}
