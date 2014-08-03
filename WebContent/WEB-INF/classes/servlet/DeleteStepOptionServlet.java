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
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet(description = "servlet to delete step options", urlPatterns = { "/DeleteStepOptionServlet" })
public class DeleteStepOptionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStepOptionServlet() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd;
		Long typeId = Long.valueOf(request.getParameter("orderTypeId"));
		Long stepId = Long.valueOf(request.getParameter("stepId"));
		
		try {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			
			if(user != null){
				
				// perform delete order step
				Long optionId = Long.valueOf(request.getParameter("optionId"));
				StepOption option = (StepOption)CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectStepOption(optionId)); 
				Integer rowsUpdated = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.DeleteStepOption(option));
				
				if(rowsUpdated == 1){
					
					request.setAttribute("info", "La opción fue eliminada exitosamente.");
					request.setAttribute("error", "");
					rd = getServletContext().getRequestDispatcher("/ListStepOptionsServlet?typeId="+typeId+"&stepId="+stepId);			

					rd.forward(request, response);
				} else {
					request.setAttribute("info", "");
					request.setAttribute("error", "Ocurrió un error durante la eliminación de la opción. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
					rd = getServletContext().getRequestDispatcher("/ListStepOptionsServlet?typeId="+typeId+"&stepId="+stepId);	

					rd.forward(request, response);
				}
			} else {
				rd = getServletContext().getRequestDispatcher("/admin/index.jsp");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {			
			request.setAttribute("info", "");
			request.setAttribute("error", "Ocurrió un error durante la eliminación de la opción. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
			rd = getServletContext().getRequestDispatcher("/ListStepOptionsServlet?typeId="+typeId+"&stepId="+stepId);			

			rd.forward(request, response);
		}
	}

}
