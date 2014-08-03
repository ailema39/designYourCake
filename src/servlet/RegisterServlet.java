package servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.SendEmail;

import command.CommandExecutor;
import domain.Client;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(description = "servlet to create users", urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		HttpSession infoPage = request.getSession();
		final Client client = (Client) infoPage.getAttribute("client");
		if (client == null)
			rd = getServletContext().getRequestDispatcher("/registro.jsp");	
		else
			rd = getServletContext().getRequestDispatcher("/index.jsp");	
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;

		try{
			String typePers = request.getParameter("typePers");
			final String firstName = request.getParameter("txtName");
			String lastName;
			if (typePers.equals("0"))
				lastName = request.getParameter("txtLastName");
			else 
				lastName = "";
			String identityCardNum = request.getParameter("txtCed");
			//String identityCardId = request.getParameter("txtCedId");
			identityCardNum = identityCardNum.replace("-", "");
			final String email = request.getParameter("txtEmail");
			final String password =request.getParameter("txtPass");
			String encryptPassword = UserLoginServlet.getEncryptPassword(password);
			String phoneNumber  = request.getParameter("txtPhone");
			String phoneMovNumber  = request.getParameter("txtMovPhone");
			String address = request.getParameter("txtDir");
			
			System.out.println("Client " + typePers + "/" + firstName + "/" + lastName + "/" + identityCardNum+ "/" + email
					+ "/" + password + "/" + phoneMovNumber + "/" + phoneNumber + "/" + address);
		
			Client client = new Client();
			client.setFirstName(firstName);
			client.setLastName(lastName);
			client.setIdentityCard(identityCardNum);
			client.setEmail(email);
			client.setPassword(encryptPassword);
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
		
			Integer userId = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.CreateClient(client));

			final String lastN = lastName;
			
			if(userId > 0){
				new Thread(new Runnable() {
				    public void run() {
				    	Properties propertiesFile = new Properties();
						//String context = getServletContext().getInitParameter("properties");
						try {
							//propertiesFile.load(new FileInputStream(context));
							//propertiesFile.load( new FileInputStream("/home/armatuto/public_html/conf/armatutorta.properties"));
							propertiesFile.load( new FileInputStream("/home/spookie/jvm/apache-tomcat-7.0.27/domains/designyourcakes.com/ROOT/conf/armatutorta.properties"));
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						SendEmail.sendEmail(propertiesFile, email, firstName + " " + lastN, password, false, "soporte");
				    }
				}).start();
				request.setAttribute("emailExist", "");
				rd = getServletContext().getRequestDispatcher("/registroBienv.jsp");			
				rd.forward(request, response);
			}
			else if (userId == -2){
				request.setAttribute("emailExist", email);
				request.setAttribute("name", firstName);
				rd = getServletContext().getRequestDispatcher("/registroBienv.jsp");			
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}