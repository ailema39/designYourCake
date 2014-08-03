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

import domain.StepOption;
import domain.User;

/**
 * Servlet implementation class CreateStepOptionServlet
 */
@WebServlet(description = "servlet to create step options", urlPatterns = { "/CreateStepOptionServlet" })
public class CreateStepOptionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateStepOptionServlet() {
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
				Integer stepId = Integer.valueOf(request.getParameter("stepId"));
				Integer position = (Integer)CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectNextOption(stepId));
				
				request.setAttribute("typeId", typeId);
				request.setAttribute("stepId", stepId);
				request.setAttribute("position", position);
				
				rd = getServletContext().getRequestDispatcher("/admin/createStepOption.jsp");			
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
		Long stepId = Long.valueOf(request.getParameter("txtStepId"));
		
		try{
			String name = request.getParameter("txtName");
			Integer position = Integer.valueOf(request.getParameter("txtPosition"));
			Double price = Double.valueOf(request.getParameter("txtPrice"));
			int isUnavailable = 0;
			
			if (request.getParameter("txtIsUnavailable") != null)
				isUnavailable = 1;			
					
			StepOption option = new StepOption();
			option.setOrderStepId(stepId);
			option.setName(name);
			option.setPosition(position);
			option.setPrice(price);
			option.setUnavailable(isUnavailable);
		
			Integer rowsUpdated  = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.CreateStepOption(option));
			
			option.setId(rowsUpdated);
	
			if(rowsUpdated != -1){
				request.setAttribute("info", "La opción fue creada exitosamente.");
				request.setAttribute("error", "");
				rd = getServletContext().getRequestDispatcher("/ListStepOptionsServlet?typeId="+typeId+"&stepId="+stepId);			
				rd.forward(request, response);
			} else {
				request.setAttribute("info", "");
				request.setAttribute("error", "Ocurrió un error durante la creación de la opción. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
				rd = getServletContext().getRequestDispatcher("/ListStepOptionsServlet?typeId="+typeId+"&stepId="+stepId);	

				rd.forward(request, response);
			}
		}catch (Exception e) {
			request.setAttribute("info", "");
			request.setAttribute("error", "Ocurrió un error durante la creación de la opción. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
			rd = getServletContext().getRequestDispatcher("/ListStepOptionsServlet?typeId="+typeId+"&stepId="+stepId);	

			rd.forward(request, response);
		}
	}

}
