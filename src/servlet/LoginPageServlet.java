package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.CommandExecutor;

import domain.Client;
import domain.StepOption;

import sun.misc.BASE64Encoder;

/**
 * Servlet implementation class LoginPageServlet
 */
@WebServlet(description = "servlet to log in users", urlPatterns = { "/LoginPageServlet" })
public class LoginPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		super.init();
		try {
			CommandExecutor.getInstance();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginPageServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Client user = (Client) session.getAttribute("client");
		RequestDispatcher rd;
		   
		if(user != null){
			rd = getServletContext().getRequestDispatcher("/index.jsp");			
			rd.forward(request, response);
		} else {
			rd = getServletContext().getRequestDispatcher("/index.jsp");			
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			String name = request.getParameter("txtNameLogin");
			String password = request.getParameter("txtPasswordLogin");
			String encryptPassword = getEncryptPassword(password);
			String prevPage = (String) request.getSession().getAttribute("prevPage");
			if (prevPage == null)
				prevPage = "index.jsp";
			
			Client client = (Client) CommandExecutor.getInstance().executeDatabaseCommand(new command.CustomerExists(name, encryptPassword));
			RequestDispatcher rd;
			
			if(client != null){
				HttpSession session = request.getSession(true);
				session.setAttribute("client", client);
				request.setAttribute("error", "");
				request.setAttribute("options", new ArrayList<StepOption>());
				rd = getServletContext().getRequestDispatcher("/" + prevPage);			
				rd.forward(request, response);
			
			} 	else {
				request.setAttribute("error", "La información de nombre de usuario o contraseña introducida no es correcta.");
				request.setAttribute("options", new ArrayList<StepOption>());
				rd = getServletContext().getRequestDispatcher("/" + prevPage);			
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	/**
	 * 
	 * @param password
	 * @return
	 */
	public static String getEncryptPassword(String password){
		MessageDigest md;
		
		try {
			
			md = MessageDigest.getInstance("SHA");
			String clearPassword = password;
			md.update(clearPassword.getBytes("UTF-8"));
	        byte[] digestedPassword = md.digest();
		    String hash = (new BASE64Encoder()).encode(digestedPassword);
		    return hash;
	        
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
