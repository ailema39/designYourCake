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

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(description = "servlet to create users", urlPatterns = { "/PasswordGeneratorServlet" })
public class PasswordGeneratorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordGeneratorServlet() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String email = request.getParameter("email");
		final String name = request.getParameter("name");
		RequestDispatcher rd;
		final String password = Util.PasswordGenerator.getPassword(Util.PasswordGenerator.MINUSCULAS+Util.PasswordGenerator.MAYUSCULAS+Util.PasswordGenerator.ESPECIALES,10);
		final String encryptPassword = UserLoginServlet.getEncryptPassword(password);
		String prevPage = (String) request.getSession().getAttribute("prevPage");
		if (prevPage == null)
			prevPage = "index.jsp";
		
		try{
			Integer userId = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.RestoreClientPassword(email, encryptPassword));
			if(userId > 0){
				new Thread(new Runnable() {
				    public void run() {
				    	Properties propertiesFile = new Properties();
						String context = getServletContext().getInitParameter("properties");
						try {
							propertiesFile.load(new FileInputStream(context));
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						SendEmail.sendEmailPassword(propertiesFile, email, name, password, false, "soporte");
						
				    }
				}).start();
				request.setAttribute("error", "");
				rd = getServletContext().getRequestDispatcher("/passwordMessage.jsp");			
				rd.forward(request, response);
			}
			else{
				request.setAttribute("error", "El correo introducido no existe");
				rd = getServletContext().getRequestDispatcher("/" + prevPage);			
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("Ocurrio un error al generar una contrasena nueva");
			request.setAttribute("error", "error");
			rd = getServletContext().getRequestDispatcher("/passwordMessage.jsp");			
			rd.forward(request, response);
		}		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}