package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet(description = "servlet to test", urlPatterns = { "/TestServlet" })
public class TestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/index.jsp");
		rd.forward(request, response);
	}*/

	/*public void service(HttpServletRequest req, 
            HttpServletResponse res)
	throws IOException
	{
		// Must set the content type first
		res.setContentType("text/html");
		// Now obtain a PrintWriter to insert HTML into
		PrintWriter out = res.getWriter();
		out.println("<html><head><title>" +
		      "Hello World!</title></head>");
		out.println("<body><h1>Hello World!</h1></body></html>");
	}*/
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		RequestDispatcher rd;
		   
		if(user != null){
			rd = getServletContext().getRequestDispatcher("/admin/mainMenu.jsp");			

			rd.forward(request, response);
		} else {
			rd = getServletContext().getRequestDispatcher("/admin/index.jsp");			

			rd.forward(request, response);
		}
	}
}
