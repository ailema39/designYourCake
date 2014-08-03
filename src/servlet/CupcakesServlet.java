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
import domain.ListOrder_Step;
import domain.Order;
import domain.OrderCupcake;
import domain.OrderItem;
import domain.StepOption;

/**
 * Servlet implementation class ListStepOptionsServlet
 */
@WebServlet(description = "servlet to list step options", urlPatterns = { "/CupcakesServlet" })
public class CupcakesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CupcakesServlet() {
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
				ArrayList<ListOrder_Step> list = (ArrayList<ListOrder_Step>)CommandExecutor.getInstance().executeDatabaseCommand(new command.ListPasos(typeId));
				
				
				request.setAttribute("options", list);
				
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/cupcakes.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("options", new ArrayList<StepOption>());
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/cupcakes.jsp");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			request.setAttribute("options", new ArrayList<StepOption>());
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/cupcakes.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		String type = request.getParameter("pr");
		if (type == null)
			doGet(request, response);
		else{
			HttpSession infoPage = request.getSession();
			Client clientAux = (Client) infoPage.getAttribute("client");
			RequestDispatcher rd;	
				
			if (clientAux == null){
				rd = getServletContext().getRequestDispatcher("/HomePageServlet");
				rd.forward(request, response);
			}else{
				Properties propertiesFile = new Properties();
				propertiesFile.load( new FileInputStream( getServletContext().getInitParameter("properties") ) );
				//propertiesFile.load( new FileInputStream("/home/armatuto/public_html/conf/armatutorta.properties"));
				OrderCupcake orderCupcake = new OrderCupcake();
				String error = "";
				if  (type.equals("1")){
					orderCupcake = getPedido(request, response);
				}else{
					error = requestPlain(request, response, propertiesFile);
				}
				request.setAttribute("pedido", orderCupcake);
				request.setAttribute("info", type);
				if (error.equals("")){
					request.setAttribute("error", "");
					
				}else{
					request.setAttribute("error", "error");
				}
				rd = getServletContext().getRequestDispatcher("/cupcakesConfirmation.jsp");
				rd.forward(request, response);
			}
		}
	}
	
	

	static OrderCupcake getPedido(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		HttpSession infoPage = request.getSession();
		
		String tamInt = request.getParameter("1");
		String cantInt = request.getParameter("2");
		String saborInt = request.getParameter("3");
		String cubiertaInt = request.getParameter("4");
		String[] colorInt = request.getParameterValues("5");
		String decorInt = request.getParameter("6");
		String precio = request.getParameter("priceCake");
		String cantidadDocenas = "", txtCalcomania = "";
		if (cantInt.equals("2"))
			cantidadDocenas = request.getParameter("cantCupcakes");
		else 
			txtCalcomania =  request.getParameter("txtCalcomania");
		String tamano, cantidad, sabor, cubierta, decoracion;
		String[] color = null;
		if (colorInt != null)
			color = new String[colorInt.length];
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> hashMapCup = (HashMap<String, String>) infoPage.getAttribute("hashMapCup");
		tamano = hashMapCup.get("1"+tamInt);
		cantidad = hashMapCup.get("2"+cantInt);
		sabor = hashMapCup.get("3"+saborInt);
		cubierta = hashMapCup.get("4"+cubiertaInt);
		decoracion = hashMapCup.get("6"+decorInt);
		if (colorInt != null){
			for (int i = 0 ; i < colorInt.length; i++){
				color[i] = hashMapCup.get("5"+ colorInt[i]);
			}
		}
		
		OrderCupcake orderCupcake = new OrderCupcake();
		orderCupcake.setTamano(tamano);
		orderCupcake.setCantidad(cantidad);
		orderCupcake.setSabor(sabor);
		orderCupcake.setCubiertas(cubierta);
		orderCupcake.setColor(color);
		orderCupcake.setDecoracion(decoracion);
		if (cantInt.equals("2"))
			orderCupcake.setCantidadDocenas(cantidadDocenas);
		else
			orderCupcake.setCalcomania(txtCalcomania);
		
		orderCupcake.setPrecio(precio);
		
		return orderCupcake;
	}
	
	static String requestPlain(HttpServletRequest request, HttpServletResponse response, final Properties propertiesFile){
		String tamano = request.getParameter("tamano");
		String cantidad = request.getParameter("cantidad");
		String sabor = request.getParameter("sabor");
		String cubierta = request.getParameter("cubierta");
		final String[] color =request.getParameterValues("color");
		String decoracion = request.getParameter("decoracion");
		String precio = request.getParameter("priceCupcake");
		String fecha = request.getParameter("txtFecha");
		String clientId =  request.getParameter("clientId");
		String cantDocenas = request.getParameter("cantDocenas");
		String txtCalcomania = request.getParameter("txtCalcomania");
		
		System.out.println(tamano + "/" + cantidad + "/" + sabor + "/" + cubierta + "/" + color + "/" +
				decoracion + "/" + precio + "/" + fecha +	"CantDocenas " + cantDocenas + " calcomani " + txtCalcomania);
		
		String error = "";
		
		HttpSession infoPage = request.getSession();
		@SuppressWarnings("unchecked")
		HashMap<String, Double> hashMapPrice = (HashMap<String, Double>) infoPage.getAttribute("hashMapPriceCup");
		@SuppressWarnings("unchecked")
		HashMap<String, Long> hashMapId = (HashMap<String, Long>) infoPage.getAttribute("hashMapIdCup");
		
		/* Establezco los valores de las cosas pedidas */
		List<OrderItem> orderItems = new LinkedList<OrderItem>();
		
		
		/* Tamano */
		OrderItem item = new OrderItem();
		item.setPrice(hashMapPrice.get(tamano));
		item.setStepOptionId(hashMapId.get(tamano));
		item.setCantDocenas(null);
		item.setTxtCalcomania(null);
		orderItems.add(item);
		
		/* Cantidad */
		item = new OrderItem();
		item.setPrice(hashMapPrice.get(cantidad));
		item.setStepOptionId(hashMapId.get(cantidad));
		if (!cantidad.contains("regalar"))
			item.setCantDocenas(cantDocenas);
		else
			item.setTxtCalcomania(txtCalcomania);
		orderItems.add(item);
		
		/* Sabor */
		item = new OrderItem();
		item.setPrice(hashMapPrice.get(sabor));
		item.setStepOptionId(hashMapId.get(sabor));
		item.setCantDocenas(null);
		item.setTxtCalcomania(null);
		orderItems.add(item);
		
		/* Cubierta */ 
		item = new OrderItem();
		item.setPrice(hashMapPrice.get(cubierta));
		item.setStepOptionId(hashMapId.get(cubierta));
		item.setCantDocenas(null);
		item.setTxtCalcomania(null);
		orderItems.add(item);
		
		/* Decoracion */
		item = new OrderItem();
		item.setPrice(hashMapPrice.get(decoracion));
		item.setStepOptionId(hashMapId.get(decoracion));
		item.setCantDocenas(null);
		item.setTxtCalcomania(null);
		orderItems.add(item);
		
		/* Color */
		if (color != null){
			for (int i = 0; i < color.length; i++){
				item = new OrderItem();
				item.setPrice(hashMapPrice.get(color[i]));
				item.setStepOptionId(hashMapId.get(color[i]));
				item.setCantDocenas(null);
				item.setTxtCalcomania(null);
				orderItems.add(item);
			}
		}
		
		try{
			final Client client = (Client) CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectClient(Long.valueOf(clientId)));
			
			/* Almacenamiento de la informacion en bd*/
			
			Order order = new Order();
			order.setClientId(client.getId());
			order.setDeliveryDate(fecha);
			order.setOrderTypeId(2);
			order.setTotal(Double.parseDouble(precio));
			order.setIsPending(1);
			
			
			final Long rowsUpdated  = (Long) CommandExecutor.getInstance().executeDatabaseCommand(new command.CreateOrder(order, orderItems));	
			
			final String[] datos = {tamano, cantidad, sabor, cubierta, decoracion, txtCalcomania, cantDocenas, precio, fecha};
			new Thread(new Runnable() {
			    public void run() {
		    		SendEmail.sendEmailOrderCupcake(propertiesFile, String.valueOf(rowsUpdated), false, "ventas", datos, color, client);
				}
			}).start();
	
			infoPage.removeAttribute("hashMapPrice");
			infoPage.removeAttribute("hashMapId");
			infoPage.removeAttribute("hashMap");
		
		} catch (Exception e) { 
			System.out.println("Ocurrio un error al insertar la orden del cliente: " + clientId + ", el error fue:" + e.getMessage());
			error = "error";
		}
		return error;
	}

	
	
	
}
