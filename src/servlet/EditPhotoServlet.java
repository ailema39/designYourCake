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

import Util.FilesName;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import command.CommandExecutor;

import domain.Album;
import domain.Photo;
import domain.User;

/**
 * Servlet implementation class EditDownloadAlbumServlet
 */
@WebServlet(description = "servlet to edit photo", urlPatterns = { "/EditPhotoServlet" })
public class EditPhotoServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public EditPhotoServlet() {
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
				Long albumId = Long.valueOf(request.getParameter("albumId"));
				request.setAttribute("albumId", albumId);
				Long photoId = Long.valueOf(request.getParameter("photoId"));
				Photo photoInfo = (Photo)CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectPhoto(photoId));
				request.setAttribute("photoInfo", photoInfo);
				request.setAttribute("albumId", albumId);
				
				rd = getServletContext().getRequestDispatcher("/admin/editPhoto.jsp");			

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		
		RequestDispatcher rd;
		Properties propertiesFile = new Properties();
		Long albumId = Long.valueOf(request.getParameter("albumId"));
		Long photoId = Long.valueOf(request.getParameter("photoId"));
						
		try{
			//propertiesFile.load( new FileInputStream( getServletContext().getInitParameter("properties") ) );
			//propertiesFile.load( new FileInputStream("/home/armatuto/public_html/conf/armatutorta.properties"));
			propertiesFile.load( new FileInputStream("/home/spookie/jvm/apache-tomcat-7.0.27/domains/designyourcakes.com/ROOT/conf/armatutorta.properties"));
			MultipartRequest multipart = new MultipartRequest(request, propertiesFile.getProperty("albumsDirectory"), 200*1024, new DefaultFileRenamePolicy());
			
			String name = multipart.getParameter("txtName");
			File imageFile = multipart.getFile("txtImage");
			String dir = propertiesFile.getProperty("albumsDirectory") + propertiesFile.getProperty("fileSeparator") + Album.getDirectory(albumId);
			String image, fileName;
			
			try{
				image = imageFile.getName();
				
				int pointIndex = image.indexOf(".");
				String extension = image.substring(pointIndex);
				
				fileName = FilesName.remove(name);
				image = photoId + "_" + fileName.toLowerCase().replace(" ", "_") + extension;

				File destination = new File(dir + propertiesFile.getProperty("fileSeparator") + image);	
				String currentImage = multipart.getParameter("txtCurrentImage");
				File currentFile = new File(dir + propertiesFile.getProperty("fileSeparator") + currentImage);
				
				currentFile.delete();				
				imageFile.renameTo(destination);
				
			} catch (NullPointerException e){
				image = multipart.getParameter("txtCurrentImage");
				imageFile = new File(dir + propertiesFile.getProperty("fileSeparator") + image);
				
				int pointIndex = image.indexOf(".");
				String extension = image.substring(pointIndex);

				fileName = FilesName.remove(name);
				image = photoId + "_" + fileName.toLowerCase().replace(" ", "_") + extension;

				File destination = new File(dir + propertiesFile.getProperty("fileSeparator") + image);
				
				imageFile.renameTo(destination);
			}
			
			int isActive = 0;
			int isNew = 0;
			
			if (multipart.getParameter("txtIsActive") != null)
				isActive = 1;			
			
			if (multipart.getParameter("txtIsNew") != null)
				isNew = 1;	
			
			Photo photo = new Photo();
			photo.setId(photoId);
			photo.setAlbumId(albumId);
			photo.setName(name);
			photo.setImage(image);
			photo.setActive(isActive);
			photo.setNew(isNew);			
			
			Integer rowsUpdated = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.EditPhoto(photo));
			
			if(rowsUpdated == 1){
				request.setAttribute("info", "La foto fue editada exitosamente.");
				request.setAttribute("error", "");
				//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListPhotosServlet?albumId="+albumId);	
				rd = getServletContext().getRequestDispatcher("/ListPhotosServlet?albumId="+albumId);			

				rd.forward(request, response);
			} else {
				request.setAttribute("info", "");
				request.setAttribute("error", "Ocurri� un error durante la edici�n de la foto. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
				//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListPhotosServlet?albumId="+albumId);
				rd = getServletContext().getRequestDispatcher("/ListPhotosServlet?albumId="+albumId);						

				rd.forward(request, response);
			}
		} catch (IOException e) {
			
			request.setAttribute("info", "");
			request.setAttribute("error", "La imagen supera el tama�o deseado (M�x. 200kb). Por favor intente de nuevo y si el error persiste contacte a su administrador.");
			request.setAttribute("albumId", albumId);
			request.setAttribute("photoId", photoId);
			try {
				doGet(request, response);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			
		} catch (Exception e) {
			request.setAttribute("info", "");
			request.setAttribute("error", "Ocurri� un error durante la edici�n de la foto. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
			//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListPhotosServlet?albumId="+albumId);
			rd = getServletContext().getRequestDispatcher("/ListPhotosServlet?albumId="+albumId);						

			try {
				rd.forward(request, response);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
