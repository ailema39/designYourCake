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
import domain.Photo;
import domain.User;

/**
 * Servlet implementation class CreateAlbumServlet
 */
@WebServlet(description = "servlet to create photos", urlPatterns = { "/CreatePhotoServlet" })
public class CreatePhotoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePhotoServlet() {
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
				Integer albumId = Integer.valueOf(request.getParameter("albumId"));
				request.setAttribute("albumId", albumId);
				
				rd = getServletContext().getRequestDispatcher("/admin/createPhoto.jsp");			
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
		Integer albumId = Integer.valueOf(multipart.getParameter("txtAlbumId"));
		
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
			
			Photo photo = new Photo();
			photo.setAlbumId(albumId);
			photo.setName(name);
			photo.setImage(image);
			photo.setActive(isActive);
			photo.setNew(isNew);
		
			Integer rowsUpdated  = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.CreatePhoto(photo));
			
			photo.setId(rowsUpdated);
			
			String dir = propertiesFile.getProperty("albumsDirectory") + propertiesFile.getProperty("fileSeparator") + Album.getDirectory(albumId);
			
			int pointIndex = image.indexOf(".");
			String extension = image.substring(pointIndex);

			image = photo.getId() + "_" + photo.getName().toLowerCase().replace(" ", "_") + extension;

			File destination = new File(dir + propertiesFile.getProperty("fileSeparator") + image);
				
			imageFile.renameTo(destination);
			photo.setImage(image);				
			rowsUpdated  = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.EditPhoto(photo));
				
			if(rowsUpdated == 1){
				request.setAttribute("info", "La foto fue creada exitosamente.");
				request.setAttribute("error", "");
				rd = getServletContext().getRequestDispatcher("/ListPhotosServlet?albumId="+albumId);			
				rd.forward(request, response);
			} else {
				request.setAttribute("info", "");
				request.setAttribute("error", "Ocurrió un error durante la creación de la foto. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
				rd = getServletContext().getRequestDispatcher("/ListPhotosServlet?albumId="+albumId);			

				rd.forward(request, response);
			}
		}catch (Exception e) {
			request.setAttribute("info", "");
			request.setAttribute("error", "Ocurrió un error durante la creación de la foto. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
			rd = getServletContext().getRequestDispatcher("/ListPhotosServlet?albumId="+albumId);			

			rd.forward(request, response);
		}
	}
}
