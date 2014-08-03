package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.CommandExecutor;
import domain.Album;
import domain.User;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet(description = "servlet to delete albums", urlPatterns = { "/DeleteAlbumServlet" })
public class DeleteAlbumServlet extends HttpServlet {

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
    public DeleteAlbumServlet() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		
		try {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			
			if(user != null){
				
				// perform delete album
				Long albumId = Long.valueOf(request.getParameter("albumId"));
				Integer rowsUpdated = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.DeleteAlbum(albumId));
				Properties propertiesFile = new Properties();
				propertiesFile.load( new FileInputStream( getServletContext().getInitParameter("properties") ) );
				
				if(rowsUpdated == 1){
					String dir = propertiesFile.getProperty("albumsDirectory") + propertiesFile.getProperty("fileSeparator") + Album.getDirectory(albumId);
					File file = new File(dir);
					deleteDirectory(file);
					file.delete();
					
					request.setAttribute("info", "El álbum fue eliminado exitosamente.");
					request.setAttribute("error", "");
					rd = getServletContext().getRequestDispatcher("/ListAlbumsServlet");			

					rd.forward(request, response);
				} else {
					request.setAttribute("info", "");
					request.setAttribute("error", "Ocurrió un error durante la eliminación del álbum. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
					rd = getServletContext().getRequestDispatcher("/ListAlbumsServlet");			

					rd.forward(request, response);
				}
			} else {
				rd = getServletContext().getRequestDispatcher("admin/index.jsp");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			request.setAttribute("info", "");
			request.setAttribute("error", "Ocurrió un error durante la eliminación del álbum. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
			rd = getServletContext().getRequestDispatcher("/ListAlbumsServlet");			

			rd.forward(request, response);
		}
	}
	
	private static void deleteDirectory(File dir){
		
		File[] files = dir.listFiles();
		
		for (int x=0; x<files.length; x++){
			
			if (files[x].isDirectory()) {
				deleteDirectory(files[x]);
			}
			
			files[x].delete();
		}
		
	}
}
