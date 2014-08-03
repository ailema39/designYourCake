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

import Util.SendEmail;

import command.CommandExecutor;
import domain.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(description = "servlet to change user password", urlPatterns = { "/UserPasswordGeneratorServlet" })
public class UserPasswordGeneratorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPasswordGeneratorServlet() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd;		
		
		rd = getServletContext().getRequestDispatcher("/admin/forgotUserPassword.jsp");			
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String email = request.getParameter("txtEmail");
		RequestDispatcher rd;
		final String password = Util.PasswordGenerator.getPassword(Util.PasswordGenerator.MINUSCULAS+Util.PasswordGenerator.MAYUSCULAS+Util.PasswordGenerator.ESPECIALES,10);
		final String encryptPassword = UserLoginServlet.getEncryptPassword(password);
			
		try{
			Integer userId = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.RestoreUserPassword(email, encryptPassword));

			if(userId > 0){
				
				final User user = (User) CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectUser(email));
				
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
						SendEmail.sendEmailPassword(propertiesFile, email, user.getFirstName(), user.getUserName(), password, false, "soporte");
						
				    }
				}).start();
				
				request.setAttribute("info", "Tu contraseña fue enviada exitosamente.");
				request.setAttribute("error", "");
				rd = getServletContext().getRequestDispatcher("/admin/index.jsp");			
				rd.forward(request, response);
			}
			else{
				request.setAttribute("info", "");
				request.setAttribute("error", "El correo introducido no existe");
				rd = getServletContext().getRequestDispatcher("/admin/forgotUserPassword.jsp");			
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("info", "");
			request.setAttribute("error", "Ocurrio un error al generar una contraseña nueva");
			rd = getServletContext().getRequestDispatcher("/admin/forgotUserPassword.jsp");			
			rd.forward(request, response);
		}		
	}
}
