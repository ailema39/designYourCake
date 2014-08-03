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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import command.CommandExecutor;

import domain.Album;
import domain.User;

/**
 * Servlet implementation class CreateAlbumServlet
 */
@WebServlet(description = "servlet to create albums", urlPatterns = { "/CreateAlbumServlet" })
public class CreateAlbumServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAlbumServlet() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			HttpSession session = request.getSession(true);
			User user = (User) session.getAttribute("user");
			RequestDispatcher rd;
			   
			if(user != null){
				rd = getServletContext().getRequestDispatcher("/admin/createAlbum.jsp");			
				rd.forward(request, response);							
			} else {
				rd = getServletContext().getRequestDispatcher("/admin/index.jsp");			
				rd.forward(request, response);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd;	
		Properties propertiesFile = new Properties();	
		propertiesFile.load( new FileInputStream( getServletContext().getInitParameter("properties") ) );
		MultipartRequest multipart = new MultipartRequest(request, propertiesFile.getProperty("albumsDirectory"), 5*1024*1024, new DefaultFileRenamePolicy());
		
		try{			
			String name = multipart.getParameter("txtName");
			File imageFile = multipart.getFile("txtImage");
			String image = imageFile.getName();
			int isActive = 0;
			int isNew = 0;
			
			if (multipart.getParameter("txtIsActive") != null)
				isActive = 1;			
			
			if (multipart.getParameter("txtIsNew") != null)
				isNew = 1;	
			
			Album album = new Album();
			album.setName(name);
			album.setImage(image);
			album.setActive(isActive);
			album.setNew(isNew);
		
			Integer rowsUpdated  = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.CreateAlbum(album));			
			album.setId(rowsUpdated);
			
			String dir = propertiesFile.getProperty("albumsDirectory") + propertiesFile.getProperty("fileSeparator") + album.getDirectory();
			File file = new File(dir);				
			
			file.mkdir();
			
			int pointIndex = image.indexOf(".");
			String extension = image.substring(pointIndex);

			image = album.getId() + "_" + album.getName().toLowerCase().replace(" ", "_") + "_cover" + extension;
			
			File destination = new File(dir + propertiesFile.getProperty("fileSeparator") + image);
				
			imageFile.renameTo(destination);								
			album.setImage(image);
			rowsUpdated  = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.EditAlbum(album));
				
			if(rowsUpdated == 1){
				request.setAttribute("info", "El álbum fue creado exitosamente.");
				request.setAttribute("error", "");
				rd = getServletContext().getRequestDispatcher("/ListAlbumsServlet");			
				rd.forward(request, response);
			} else {
				request.setAttribute("info", "");
				request.setAttribute("error", "Ocurrió un error durante la creación del álbum. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
				rd = getServletContext().getRequestDispatcher("/ListAlbumsServlet");			

				rd.forward(request, response);
			}			
		}catch (Exception e) {
			request.setAttribute("info", "");
			request.setAttribute("error", "Ocurrió un error durante la creación del álbum. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
			rd = getServletContext().getRequestDispatcher("/ListAlbumsServlet");			

			rd.forward(request, response);
		}
	}
}
