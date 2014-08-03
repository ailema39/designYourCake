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

import Util.SendEmail;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import command.CommandExecutor;

import domain.Client;
import domain.Estimation;

/**
 * Servlet implementation class ListStepOptionsServlet
 */
@WebServlet(description = "servlet to list step options", urlPatterns = { "/OcasionesEspecialesServlet" })
public class OcasionesEspecialesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OcasionesEspecialesServlet() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/ocasionesEspeciales.jsp");
			rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession infoPage = request.getSession();
		Client clientAux = (Client) infoPage.getAttribute("client");
		RequestDispatcher rd;	
			
		if (clientAux == null){
			rd = getServletContext().getRequestDispatcher("/HomePageServlet");
			rd.forward(request, response);
		}else{
			
			final Properties propertiesFile = new Properties();
			//propertiesFile.load( new FileInputStream( getServletContext().getInitParameter("properties") ) );
			//propertiesFile.load( new FileInputStream("/home/armatuto/public_html/conf/armatutorta.properties"));
			propertiesFile.load( new FileInputStream("/home/spookie/jvm/apache-tomcat-7.0.27/domains/designyourcakes.com/ROOT/conf/armatutorta.properties"));
			String dirPath = propertiesFile.getProperty("pedidosOcasionesEspecialesDirectory");
			//String dirPath = "C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 7.0\\webapps\\armaTuTorta\\files\\pedidosOcasionesEspeciales\\";
			MultipartRequest multipart = new MultipartRequest(request, dirPath,
						5*1024*1024, new DefaultFileRenamePolicy());
			
			try{			
				String name = multipart.getParameter("txtName");
				String torta = multipart.getParameter("eventoT");
				String cupcakes = multipart.getParameter("eventoC");
				String gelatina = multipart.getParameter("eventoG");
				String invitados = multipart.getParameter("txtInv");
				String idea = multipart.getParameter("idea");
				String fecha = multipart.getParameter("txtFecha");
				
				/* Establezco la estimacion solicitada por el cliente */
				Estimation estimation = new Estimation();
				estimation.setClientId(clientAux.getId());
				estimation.setSpecialOccasion(name);
				
				if (invitados == null || invitados.equals(""))
					invitados = "0";
				estimation.setGuestsNumber(Integer.valueOf(invitados));
				estimation.setDescription(idea);
				
				
				File imageFile = multipart.getFile("txtImage");
				String image = "";
				boolean attach = false;
				if (imageFile != null){
					image = imageFile.getName();
					attach = true;	
					
					/* Creación del archivo para enviarlo por correo*/	
					String dir = dirPath;
					int pointIndex = image.indexOf(".");
					String extension = image.substring(pointIndex);
					String nameImg = image.substring(0,pointIndex);
					if (nameImg.length() > 10)
						nameImg = nameImg.substring(0, 10);

					image = nameImg.toLowerCase().replace(" ", "_") + "_" + clientAux.getFirstName().trim() + extension;
					File file = new File(dir + image);
					imageFile.renameTo(file);
					
					// Para que funcione local descomentar esta linea y comentar la otra
					//int index = nombreImagen.lastIndexOf("\\") + 1;
					
					int index = image.lastIndexOf("/") + 1;
					String nombreImagenAux = image.substring(index);
					estimation.setImage(nombreImagenAux);
				
				}
				
				
				
				String products = "";
				if (torta != null)
					products = "Torta" ;
				if (gelatina != null){
					if (!products.equals(""))
						products += ", Gelatina" ;
					else
						products += "Gelatina" ;
				}
				if (cupcakes != null){
					if (!products.equals(""))
						products += ", Cupcakes" ;
					else
						products += "Cucpcakes" ;
				}
				final String productos = products;
				estimation.setProducts(products);
				
				
				/* Guardo en bd la ocasion */
				final Long rowsUpdated  = (Long) CommandExecutor.getInstance().executeDatabaseCommand(new command.CreateOrderSpecial(estimation));	
				System.out.println("aqui45");
				final boolean attachment = attach;
	
				/* Obtengo todos datos del cliente para ser enviados por correo*/
				final Client client = (Client) CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectClient(Long.valueOf(clientAux.getId())));
				
				final String[] datos = {name, invitados,  idea, fecha, dirPath + image};
				
				new Thread(new Runnable() {
				    public void run() {
				    	
			    		SendEmail.sendEmailOrderOcEsp(propertiesFile, String.valueOf(rowsUpdated), attachment, "ventas", datos, productos, client);
						
				    }
				}).start();
				
				request.setAttribute("error", "");
				rd = getServletContext().getRequestDispatcher("/ocaEspConfirmation.jsp");
				rd.forward(request, response);
			
			}catch (Exception e) {
				e.getStackTrace();
				request.setAttribute("error", "error");
				rd = getServletContext().getRequestDispatcher("/ocaEspConfirmation.jsp");			
				rd.forward(request, response);
			}
		}
	}
}
