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

import domain.Client;
import domain.User;

/**
 * Servlet implementation class EditClientPasswordServlet
 */
@WebServlet(description = "servlet to edit client's password", urlPatterns = { "/EditClientPasswordServlet" })
public class EditClientPasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditClientPasswordServlet() {
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
				Long clientId = Long.valueOf(request.getParameter("clientId"));
				request.setAttribute("clientId", clientId);
				rd = getServletContext().getRequestDispatcher("/admin/editClientPassword.jsp");			
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
			Integer clientId = Integer.valueOf(request.getParameter("txtClientId"));
			String oldPassword = request.getParameter("txtOldPassword");
			String password = request.getParameter("txtPassword");
			String encryptOldPassword = UserLoginServlet.getEncryptPassword(oldPassword);
			String encryptPassword = UserLoginServlet.getEncryptPassword(password);
				
			Client client = new Client();
			client.setPassword(encryptPassword);
			client.setId(clientId);
			
			Integer rowsUpdated = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.EditClientPassword(client, encryptOldPassword));
			
			if(rowsUpdated == 1){
				request.setAttribute("info", "La contraseña del cliente fue modificada exitosamente.");
				request.setAttribute("error", "");
				rd = getServletContext().getRequestDispatcher("/ListClientsServlet");			

				rd.forward(request, response);
			} 
			else if (rowsUpdated == -2){
				request.setAttribute("info", "");
				request.setAttribute("error", "La contraseña anterior suministrada no es correcta. Por favor intente de nuevo.");
				request.setAttribute("clientId", clientId);
				rd = getServletContext().getRequestDispatcher("/admin/editClientPassword.jsp");		
				rd.forward(request, response);
			}
			else {
				request.setAttribute("info", "");
				request.setAttribute("error", "Ocurrió un error durante la modificación de la contraseña del cliente. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
				rd = getServletContext().getRequestDispatcher("/ListClientsServlet");			

				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			request.setAttribute("info", "");
			request.setAttribute("error", "Ocurrió un error durante la modificación de la contraseña del cliente. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
			rd = getServletContext().getRequestDispatcher("/ListClientsServlet");			

			rd.forward(request, response);
		}
	}
}
