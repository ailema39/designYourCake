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
import domain.Photo;
import domain.User;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet(description = "servlet to delete photos", urlPatterns = { "/DeletePhotoServlet" })
public class DeletePhotoServlet extends HttpServlet {

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
    public DeletePhotoServlet() {
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
		Long albumId = Long.valueOf(request.getParameter("albumId"));
		
		try {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			
			if(user != null){
				
				// perform delete photo
				Long photoId = Long.valueOf(request.getParameter("photoId"));
				Integer rowsUpdated = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.DeletePhoto(photoId));
				Properties propertiesFile = new Properties();
				//propertiesFile.load( new FileInputStream( getServletContext().getInitParameter("properties") ) );
				//propertiesFile.load( new FileInputStream("/home/armatuto/public_html/conf/armatutorta.properties"));
				propertiesFile.load( new FileInputStream("/home/spookie/jvm/apache-tomcat-7.0.27/domains/designyourcakes.com/ROOT/conf/armatutorta.properties"));
				
				if(rowsUpdated == 1){
					Photo photo = (Photo) CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectPhoto(photoId));
					String dir = propertiesFile.getProperty("albumsDirectory") + propertiesFile.getProperty("fileSeparator") + Album.getDirectory(albumId) + 
					propertiesFile.getProperty("fileSeparator") + photo.getImage();
					File file = new File(dir);
					file.delete();
					
					request.setAttribute("info", "La foto fue eliminada exitosamente.");
					request.setAttribute("error", "");
					//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListPhotosServlet?albumId="+albumId);	
					rd = getServletContext().getRequestDispatcher("/ListPhotosServlet?albumId="+albumId);		

					rd.forward(request, response);
				} else {
					request.setAttribute("info", "");
					request.setAttribute("error", "Ocurrió un error durante la eliminación de la foto. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
					//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListPhotosServlet?albumId="+albumId);	
					rd = getServletContext().getRequestDispatcher("/ListPhotosServlet?albumId="+albumId);					

					rd.forward(request, response);
				}
			} else {
				rd = getServletContext().getRequestDispatcher("/admin/index.jsp");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			request.setAttribute("info", "");
			request.setAttribute("error", "Ocurrió un error durante la eliminación de la foto. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
			//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListPhotosServlet?albumId="+albumId);		
			rd = getServletContext().getRequestDispatcher("/ListPhotosServlet?albumId="+albumId);					

			rd.forward(request, response);
		}
	}
}
