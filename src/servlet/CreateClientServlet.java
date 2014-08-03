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
 * Servlet implementation class CreateClientServlet
 */
@WebServlet(description = "servlet to create clients", urlPatterns = { "/CreateClientServlet" })
public class CreateClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateClientServlet() {
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
				rd = getServletContext().getRequestDispatcher("/admin/createClient.jsp");			
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
			String typeId = request.getParameter("txtTypeId");
			String cardId = request.getParameter("txtIdCard");
			String identityCard = typeId + cardId;
			
			String firstName, lastName = "";
			int isCompany;
			
			if(typeId.equalsIgnoreCase("J-")){
				firstName = request.getParameter("txtName");
				isCompany = 1;
			} else {
				firstName = request.getParameter("txtFirstName");
				lastName = request.getParameter("txtLastName");		
				isCompany = 0;		
			}
			
			String email = request.getParameter("txtEmail");	
			String password = request.getParameter("txtPassword");
			String encryptPassword = UserLoginServlet.getEncryptPassword(password);
			String phone = request.getParameter("txtPhone");
			String otherPhone = request.getParameter("txtOtherPhone");
			String address = request.getParameter("txtAddress");
			int isShippingAddress = 0;
			String shippingAddress = "";
			
			if (request.getParameter("txtIsShipping") != null){
				isShippingAddress = 1;		
			} else {
				shippingAddress = request.getParameter("txtShippingAddress");
			}
						
			Client client = new Client();
			client.setIdentityCard(identityCard);
			client.setFirstName(firstName);
			client.setLastName(lastName);
			client.setCompany(isCompany);
			client.setEmail(email);
			client.setPassword(encryptPassword);
			client.setPhone(phone);
			client.setOtherPhone(otherPhone);
			client.setAddress(address);
			client.setShippingAddress(isShippingAddress);
			client.setShippingAddress(shippingAddress);
			
			Integer rowsUpdated  = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.CreateClient(client));			
			client.setId(rowsUpdated);
			
			if(rowsUpdated != -1){
				request.setAttribute("info", "El cliente fue creado exitosamente.");
				request.setAttribute("error", "");
				rd = getServletContext().getRequestDispatcher("/ListClientsServlet");			
				rd.forward(request, response);
			} else {
				request.setAttribute("info", "");
				request.setAttribute("error", "Ocurrió un error durante la creación del cliente. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
				rd = getServletContext().getRequestDispatcher("/ListClientsServlet");			

				rd.forward(request, response);
			}		
		}catch (Exception e) {
			request.setAttribute("info", "");
			request.setAttribute("error", "Ocurrió un error durante la creación del cliente. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
			rd = getServletContext().getRequestDispatcher("/ListClientsServlet");			

			rd.forward(request, response);
		}
	}
}
