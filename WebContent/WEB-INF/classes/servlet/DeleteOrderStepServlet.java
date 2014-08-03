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
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet(description = "servlet to delete order steps", urlPatterns = { "/DeleteOrderStepServlet" })
public class DeleteOrderStepServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOrderStepServlet() {
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
		Long orderTypeId = Long.valueOf(request.getParameter("orderTypeId"));
		
		try {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			
			if(user != null){
				
				// perform delete order step
				Long stepId = Long.valueOf(request.getParameter("stepId"));
				OrderStep stepInfo = (OrderStep)CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectOrderStep(stepId));
				Integer rowsUpdated = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.DeleteOrderStep(stepInfo));
				
				if(rowsUpdated == 1){
					
					request.setAttribute("info", "El paso fue eliminado exitosamente.");
					request.setAttribute("error", "");
					rd = getServletContext().getRequestDispatcher("/ListOrderStepsServlet?typeId="+orderTypeId);			

					rd.forward(request, response);
				} else {
					request.setAttribute("info", "");
					request.setAttribute("error", "Ocurrió un error durante la eliminación del paso. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
					rd = getServletContext().getRequestDispatcher("/ListOrderStepsServlet?typeId="+orderTypeId);		

					rd.forward(request, response);
				}
			} else {
				rd = getServletContext().getRequestDispatcher("/admin/index.jsp");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			request.setAttribute("info", "");
			request.setAttribute("error", "Ocurrió un error durante la eliminación del paso. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
			rd = getServletContext().getRequestDispatcher("/ListOrderStepsServlet?typeId="+orderTypeId);		

			rd.forward(request, response);
		}
	}

}
