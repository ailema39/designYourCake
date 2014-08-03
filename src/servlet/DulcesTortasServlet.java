package servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.SendEmail;

import command.CommandExecutor;
import domain.Client;
import domain.Order;
import domain.OrderItem;
import domain.StepOption;

/**
 * Servlet implementation class ListStepOptionsServlet
 */
@WebServlet(description = "servlet to list step options", urlPatterns = { "/DulcesTortasServlet" })
public class DulcesTortasServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DulcesTortasServlet() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession();
			Client user = (Client)session.getAttribute("client");
			
			if(user != null){
				// perform list user operations
				Integer typeId = Integer.valueOf(request.getParameter("typeId"));
				@SuppressWarnings("unchecked")
				ArrayList<StepOption> list = (ArrayList<StepOption>)CommandExecutor.getInstance().executeDatabaseCommand(new command.ListDulcesTortas(typeId));
				
				request.setAttribute("options", list);
				
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/dulcesTortas.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("options", new ArrayList<StepOption>());
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/dulcesTortas.jsp");
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
		String[] productos = request.getParameterValues("dulcesTortas");
		if (productos == null)
			doGet(request,response);
		else{
			HttpSession infoPage = request.getSession();
			Client clientAux = (Client) infoPage.getAttribute("client");
			RequestDispatcher rd;	
				
			if (clientAux == null){
				rd = getServletContext().getRequestDispatcher("/HomePageServlet");
				rd.forward(request, response);
			}else{
			
				@SuppressWarnings("unchecked")
				HashMap<String, String> hashMap = (HashMap<String, String>) infoPage.getAttribute("hashMapDulcesTortas");
				@SuppressWarnings("unchecked")
				HashMap<String, Double> hashMapPrice = (HashMap<String, Double>) infoPage.getAttribute("hashMapPriceDulcesTortas");
				@SuppressWarnings("unchecked")
				HashMap<String, Long> hashMapId = (HashMap<String, Long>) infoPage.getAttribute("hashMapIdDulcesTortas");
				
				String price = request.getParameter("priceDulcCake");
				String date = request.getParameter("txtFecha");
				final String[] cantProd = new String[productos.length];
				final String[] cantInt = new String[productos.length];
				
				for (int i = 0 ; i < productos.length ; i++){
					cantInt[i] = request.getParameter("selDulcesTortas" + productos[i]);
					cantProd[i] = cantInt[i] + " " + hashMap.get(productos[i]);
				}
				
					
				final Properties propertiesFile = new Properties();
				propertiesFile.load( new FileInputStream( getServletContext().getInitParameter("properties") ) );
				//propertiesFile.load( new FileInputStream("/home/armatuto/public_html/conf/armatutorta.properties"));
				/* Establezco los valores de las cosas pedidas */
				List<OrderItem> orderItems = new LinkedList<OrderItem>();
				
				OrderItem item = new OrderItem();
				/* Tortas pedidas */
				for (int i = 0; i < productos.length; i++){
					item = new OrderItem();
					String productName = hashMap.get(productos[i]);
					item.setPrice(hashMapPrice.get(productName));
					item.setStepOptionId(hashMapId.get(productName));
					item.setCantDocenas(cantInt[i]);
					orderItems.add(item);
				}
				
			
				try{
					final Client client = (Client) CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectClient(Long.valueOf(clientAux.getId())));
					
					Order order = new Order();
					order.setClientId(client.getId());
					order.setDeliveryDate(date);
					order.setIsPending(1);
					order.setOrderTypeId(3);
					order.setTotal(Double.valueOf(price));
					
					
					final Long rowsUpdated  = (Long) CommandExecutor.getInstance().executeDatabaseCommand(new command.CreateOrder(order, orderItems));	
	
					final String[] datos = {date, price};
					new Thread(new Runnable() {
					    public void run() {
				    		SendEmail.sendEmailOrderDulcesTortas(propertiesFile, String.valueOf(rowsUpdated), false, "ventas", datos, cantProd, client);
								
					    }
					}).start();
					
					request.setAttribute("info", "2");
					request.setAttribute("error", "");
					rd = getServletContext().getRequestDispatcher("/creaTuTortaConfirmation.jsp");
					rd.forward(request, response);
					
				}catch (Exception e) {
					System.out.println("Ocurrio un error al insertar la orden del cliente: " + clientAux.getId() + ", el error fue:" + e.getMessage());
					request.setAttribute("info", "2");
					request.setAttribute("error", "error");
					rd = getServletContext().getRequestDispatcher("/creaTuTortaConfirmation.jsp");
					rd.forward(request, response);
				}
				
			}
		}
	}
}
