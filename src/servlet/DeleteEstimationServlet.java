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
import domain.Estimation;
import domain.User;

/**
 * Servlet implementation class DeleteEstimationServlet
 */
@WebServlet(description = "servlet to delete estimations", urlPatterns = { "/DeleteEstimationServlet" })
public class DeleteEstimationServlet extends HttpServlet {

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
    public DeleteEstimationServlet() {
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
				
				// perform delete estimation
				Long estimationId = Long.valueOf(request.getParameter("estimationId"));
				Integer rowsUpdated = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.DeleteEstimation(estimationId));
				Properties propertiesFile = new Properties();
				//propertiesFile.load( new FileInputStream( getServletContext().getInitParameter("properties") ) );
				propertiesFile.load( new FileInputStream("/home/armatuto/public_html/conf/armatutorta.properties"));
				
				if(rowsUpdated == 1){
					Estimation estimation = (Estimation) CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectEstimation(estimationId));
					
					if(estimation.getImage()!=null){
						String dir = propertiesFile.getProperty("pedidosOcasionesEspecialesDirectory") + estimation.getImage();
						File file = new File(dir);
						file.delete();						
					}
					
					request.setAttribute("info", "La solicitud de presupuesto fue eliminada exitosamente.");
					request.setAttribute("error", "");
					//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListEstimationsServlet");	
					rd = getServletContext().getRequestDispatcher("/ListEstimationsServlet");		

					rd.forward(request, response);
				} else {
					request.setAttribute("info", "");
					request.setAttribute("error", "Ocurrió un error durante la eliminación de la solicitud de presupuesto. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
					//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListEstimationsServlet");	
					rd = getServletContext().getRequestDispatcher("/ListEstimationsServlet");					

					rd.forward(request, response);
				}
			} else {
				rd = getServletContext().getRequestDispatcher("/admin/index.jsp");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			request.setAttribute("info", "");
			request.setAttribute("error", "Ocurrió un error durante la eliminación de la solicitud de presupuesto. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
			//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListEstimationsServlet");		
			rd = getServletContext().getRequestDispatcher("/ListEstimationsServlet");					

			rd.forward(request, response);
		}
	}
}
