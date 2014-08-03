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
import domain.Order;
import domain.OrderItem;
import domain.User;

/**
 * Servlet implementation class DeleteOrderServlet
 */
@WebServlet(description = "servlet to delete orders", urlPatterns = { "/DeleteOrderServlet" })
public class DeleteOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOrderServlet() {
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
				
				// perform delete order step
				Long orderId = Long.valueOf(request.getParameter("orderId"));
				Integer rowsUpdated = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.DeleteOrder(orderId));
				Properties propertiesFile = new Properties();
				//propertiesFile.load( new FileInputStream( getServletContext().getInitParameter("properties")));
				propertiesFile.load( new FileInputStream("/home/armatuto/public_html/conf/armatutorta.properties"));

				if(rowsUpdated == 1){
					
					Order order = (Order) CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectOrder(orderId));
					
					if(order.getEstimationId() != null){
						Estimation estimation = (Estimation) CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectEstimation(order.getEstimationId()));						
						rowsUpdated = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.DeleteEstimation(order.getEstimationId()));

						if(rowsUpdated == 1 && estimation.getImage()!=null){
							String dir = propertiesFile.getProperty("pedidosOcasionesEspecialesDirectory") + estimation.getImage();
							File file = new File(dir);
							file.delete();							
						}
					}	
					
					OrderItem item = (OrderItem) CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectOrderItem(orderId, (long) 42));
					
					if(item!=null && item.getNombreImg()!=null){
						String dir = propertiesFile.getProperty("pedidosTortasDirectory") + item.getNombreImg();
						File file = new File(dir);
						file.delete();	
					}
					
					request.setAttribute("info", "El pedido fue eliminado exitosamente.");
					request.setAttribute("error", "");
					//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListOrdersServlet");	
					rd = getServletContext().getRequestDispatcher("/ListOrdersServlet");			

					rd.forward(request, response);
				} else {
					request.setAttribute("info", "");
					request.setAttribute("error", "Ocurrió un error durante la eliminación del pedido. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
					//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListOrdersServlet");
					rd = getServletContext().getRequestDispatcher("/ListOrdersServlet");			

					rd.forward(request, response);
				}
			} else {
				rd = getServletContext().getRequestDispatcher("/admin/index.jsp");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			request.setAttribute("info", "");
			request.setAttribute("error", "Ocurrió un error durante la eliminación del paso. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
			//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListOrdersServlet");
			rd = getServletContext().getRequestDispatcher("/ListOrdersServlet");				

			rd.forward(request, response);
		}
	}
}
