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


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(description = "servlet to create users", urlPatterns = { "/ClientAccountServlet" })
public class ClientAccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientAccountServlet() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));
		RequestDispatcher rd;
		try{
			Client client = (Client) CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectClient(id));
			if (client != null){
				request.setAttribute("clientInfo", client);
				rd = getServletContext().getRequestDispatcher("/editClientAccount.jsp");	
				rd.forward(request, response);
			} 
		} catch (Exception e) {
			rd = getServletContext().getRequestDispatcher("/index.jsp");			
			rd.forward(request, response);
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;

		try{
			
			String typePers = request.getParameter("typePers");
			final String firstName = request.getParameter("txtName");
			final String lastName;
			if (typePers.equals("0"))
				lastName = request.getParameter("txtLastName");
			else 
				lastName = "";
			String identityCardNum = request.getParameter("txtCed");
			//identityCardNum = identityCardNum.replace("-", "");
			//String identityCardId = request.getParameter("txtCedId");
			final String email = request.getParameter("txtEmail");
			String phoneNumber  = request.getParameter("txtPhone");
			String phoneMovNumber  = request.getParameter("txtMovPhone");
			String address = request.getParameter("txtDir");
			
			System.out.println("Client " + typePers + "/" + firstName + "/" + lastName + "/" + identityCardNum+ "/" + email
					+  "/" + phoneMovNumber + "/" + phoneNumber + "/" + address);
			
			String clientId = request.getParameter("clientId");
			
			Client client = new Client();
			client.setId(Long.valueOf(clientId));
			client.setFirstName(firstName);
			client.setLastName(lastName);
			client.setIdentityCard(identityCardNum);
			client.setEmail(email);
			client.setPhone(phoneNumber);
			client.setOtherPhone(phoneMovNumber);
			client.setAddress(address);
			client.setCompany(Integer.valueOf(typePers));
			String checkbox = request.getParameter("checkDir");
			
		
			if (checkbox != null){
				client.setShippingAddress("");
				client.setShippingAddress(1);
				
			}else{
				String addressEnv = request.getParameter("txtDirEnv");
				client.setShippingAddress(addressEnv);
				client.setShippingAddress(0);
			}
		
			Integer userId = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.EditClient(client));
			if(userId > 0){
				HttpSession session = request.getSession();
				session.removeAttribute("client");
				session.setAttribute("client", client);
				request.setAttribute("editClient", "successUser");
				rd = getServletContext().getRequestDispatcher("/editClientMessage.jsp");			
				rd.forward(request, response);
			} 
		} catch (Exception e) {
			request.setAttribute("editClient", "errorUser");
			rd = getServletContext().getRequestDispatcher("/editClientMessage.jsp");			
			rd.forward(request, response);
		}
	}
}