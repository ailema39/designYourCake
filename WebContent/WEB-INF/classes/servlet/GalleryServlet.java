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
import domain.Album;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet(description = "servlet to log in users", urlPatterns = { "/GalleryServlet" })
public class GalleryServlet extends HttpServlet {
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
    public GalleryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			@SuppressWarnings("unchecked")
			ArrayList<Album> list = (ArrayList<Album>)CommandExecutor.getInstance().executeDatabaseCommand(new command.ListAlbums());
			RequestDispatcher rd;
			//String[] files = FilesName.getFilesNamesCakes(request);
			//request.setAttribute("cakesFilesName", files);
			request.setAttribute("albums", list);
			rd = getServletContext().getRequestDispatcher("/galeria.jsp");			
			rd.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
