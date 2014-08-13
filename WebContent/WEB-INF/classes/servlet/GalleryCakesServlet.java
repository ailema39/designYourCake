package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandExecutor;
import domain.Photo;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet(description = "servlet to log in users", urlPatterns = { "/GalleryCakesServlet" })
public class GalleryCakesServlet extends HttpServlet {
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
    public GalleryCakesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		try {
			Integer albumId = Integer.valueOf(request.getParameter("albumId"));
			@SuppressWarnings("unchecked")
			ArrayList<Photo> list = (ArrayList<Photo>)CommandExecutor.getInstance().executeDatabaseCommand(new command.ListPhotos(albumId));
			request.setAttribute("photos", list);
			rd = getServletContext().getRequestDispatcher("/galeria-tortas.jsp");			
			rd.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("photos", new ArrayList<Photo>() );
			rd = getServletContext().getRequestDispatcher("/galeria-tortas.jsp");	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}