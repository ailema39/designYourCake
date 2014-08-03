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



/**
 * Servlet implementation class ContactServlet
 */
@WebServlet(description = "servlet to create users", urlPatterns = { "/ContactServlet" })
public class ContactServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactServlet() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		rd = getServletContext().getRequestDispatcher("/contacto.jsp");			
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;

		try{
			final String name = request.getParameter("txtName");
			final String email = request.getParameter("txtEmail");
			String phoneAux = request.getParameter("txtPhone");
			if (phoneAux == null || phoneAux.equals("null"))
				phoneAux = "-";
			final String phone = phoneAux;
			final String message = request.getParameter("txtMsg");
			String txtCheck = request.getParameter("txtCheck");
			System.out.println("Enviando mensaje de: " + name + ", email: " + email + ", phone: " + phone + 
					", message: " + message);
			
			
			if (txtCheck != null){
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
						SendEmail.sendContactEmail(propertiesFile, email, name, phone, message, false, "soporte");
				    }
				}).start();
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
						SendEmail.sendContactEmailCopy(propertiesFile, email, name, phone, message, false, "soporte");
				    }
				}).start();
				
			}else{
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
						SendEmail.sendContactEmail(propertiesFile, email, name, phone, message, false, "soporte");
				    }
				}).start();
			}
			
			request.setAttribute("mensaje", " Sua mensagem foi enviada com sucesso!");
			rd = getServletContext().getRequestDispatcher("/contacto.jsp");			
			rd.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("mensaje", " Sua mensagem n&#227;o p&#244;de ser enviada. Por favor, Tente novamente mais tarde!");
			rd = getServletContext().getRequestDispatcher("/contacto.jsp");			
			rd.forward(request, response);
		}
	}
}