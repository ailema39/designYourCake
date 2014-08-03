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
 * Servlet implementation class EditOrderStepServlet
 */
@WebServlet(description = "servlet to edit step option", urlPatterns = { "/EditStepOptionServlet" })
public class EditStepOptionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public EditStepOptionServlet() {
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
				Long optionId = Long.valueOf(request.getParameter("optionId"));
				StepOption optionInfo = (StepOption)CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectStepOption(optionId));
				
				request.setAttribute("optionInfo", optionInfo);
				
				request.setAttribute("stepId", stepId);
				request.setAttribute("typeId", typeId);
				request.setAttribute("optionId", optionId);
				
				rd = getServletContext().getRequestDispatcher("/admin/editStepOption.jsp");		
				
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
		Long stepId = Long.valueOf(request.getParameter("txtStepId"));
		
		try{
			Long optionId = Long.valueOf(request.getParameter("txtOptionId"));
			String name = request.getParameter("txtName");
			Integer oldPosition = Integer.valueOf(request.getParameter("txtCurrentPosition"));
			Integer position = Integer.valueOf(request.getParameter("txtPosition"));
			Double price = Double.valueOf(request.getParameter("txtPrice"));
			
			int isUnavailable = 0;
			
			if (request.getParameter("txtIsUnavailable") != null)
				isUnavailable = 1;			
						
			StepOption option = new StepOption();
			option.setId(optionId);
			option.setOrderStepId(stepId);
			option.setName(name);
			option.setPrice(price);
			option.setPosition(position);
			option.setOldPosition(oldPosition);
			option.setUnavailable(isUnavailable);			
			
			Integer rowsUpdated = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.EditStepOption(option));
			
			if(rowsUpdated == 1){
				request.setAttribute("info", "La opción fue editada exitosamente.");
				request.setAttribute("error", "");
				rd = getServletContext().getRequestDispatcher("/ListStepOptionsServlet?typeId="+typeId+"&stepId="+stepId);				

				rd.forward(request, response);
			} else {
				request.setAttribute("info", "");
				request.setAttribute("error", "Ocurrió un error durante la edición de la opción. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
				rd = getServletContext().getRequestDispatcher("/ListStepOptionsServlet?typeId="+typeId+"&stepId="+stepId);				

				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			request.setAttribute("info", "");
			request.setAttribute("error", "Ocurrió un error durante la edición de la opción. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
			rd = getServletContext().getRequestDispatcher("/ListStepOptionsServlet?typeId="+typeId+"&stepId="+stepId);			

			rd.forward(request, response);
		}
	}
}
