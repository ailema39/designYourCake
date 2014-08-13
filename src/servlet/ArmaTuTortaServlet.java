package servlet;

import java.io.File;
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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Util.SendEmail;


import command.CommandExecutor;
import domain.Client;
import domain.ListOrder_Step;
import domain.Order;
import domain.OrderCake;
import domain.OrderItem;
import domain.StepOption;

/**
 * Servlet implementation class ListStepOptionsServlet
 */
@WebServlet(description = "servlet to list step options", urlPatterns = { "/ArmaTuTortaServlet" })
public class ArmaTuTortaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static String formaInt;
	private static String tamInt;
	private static String saborInt;
	private static String capasInt;
	private static String cubiertaInt;
	private static String capa1 ="", capa2="", capa3="";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArmaTuTortaServlet() {
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
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/creaTuTorta.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("options", new ArrayList<StepOption>());
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/creaTuTorta.jsp");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage() + e.getStackTrace());
			request.setAttribute("options", new ArrayList<StepOption>());
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/creaTuTorta.jsp");
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
				//propertiesFile.load( new FileInputStream("/home/armatuto/public_html/conf/armatutorta.properties"));
				propertiesFile.load( new FileInputStream("/home/spookie/jvm/apache-tomcat-7.0.27/domains/designyourcakes.com/ROOT/conf/armatutorta.properties"));
				//propertiesFile.load( new FileInputStream("C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 7.0\\webapps\\armaTuTorta\\conf\\armatutorta.properties"));
				
				//propertiesFile.load( new FileInputStream( getServletContext().getInitParameter("properties") ) );
				OrderCake orderCake = new OrderCake();
				String error = "";
				if  (type.equals("1")){
					String dirPath = propertiesFile.getProperty("pedidosTortasDirectory");
					orderCake = requestMultipart(request, response, dirPath );
				}else{
					error = requestPlain(request, response, propertiesFile);
				}
				request.setAttribute("pedido", orderCake);
				request.setAttribute("info", type);
				if (error.equals("")){
					request.setAttribute("error", "");
					
				}else{
					request.setAttribute("error", "error");
				}
				rd = getServletContext().getRequestDispatcher("/creaTuTortaConfirmation.jsp");
				rd.forward(request, response);
			}
		}
	}
	
	static OrderCake requestMultipart(HttpServletRequest request, HttpServletResponse response, String dirPath) throws IOException{
		
		MultipartRequest multipart = new MultipartRequest(request, dirPath, 5*1024*1024, new DefaultFileRenamePolicy());
		
		HttpSession infoPage = request.getSession();
		final Client client = (Client) infoPage.getAttribute("client");
		
		formaInt = multipart.getParameter("1");
		tamInt = multipart.getParameter("2");
		saborInt = multipart.getParameter("3");
		capasInt = multipart.getParameter("4");
		
		if (Integer.valueOf(capasInt) >= 1 && Integer.valueOf(capasInt) != 4)
			capa1 = multipart.getParameter("capa1");
		if (Integer.valueOf(capasInt) >= 2 && Integer.valueOf(capasInt) != 4)
			capa2  = multipart.getParameter("capa2");
		if (Integer.valueOf(capasInt) == 3 && Integer.valueOf(capasInt) != 4)
			capa3 = multipart.getParameter("capa3");
		
		
		cubiertaInt = multipart.getParameter("6");
		
		String forma, tamano, sabor, capas,  cubierta;
		
		String precio = multipart.getParameter("priceCake");
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> hashMap = (HashMap<String, String>) infoPage.getAttribute("hashMap");
		forma = hashMap.get("1"+formaInt);
		tamano = hashMap.get("2"+tamInt);
		sabor = hashMap.get("3"+saborInt);
		capas = hashMap.get("4"+capasInt);
		cubierta = hashMap.get("6"+cubiertaInt);
		String[] rellenoAux = null;
		if (!capasInt.equals("4"))
			rellenoAux = new String[Integer.valueOf(capasInt)];
		
		final String[] relleno = rellenoAux;
		
		if (!capa1.equals(""))
			relleno[0] = hashMap.get("5"+ capa1);
		if (!capa2.equals(""))
			relleno[1] = hashMap.get("5"+ capa2);
		if (!capa3.equals(""))
			relleno[2] = hashMap.get("5"+ capa3);
	
		OrderCake orderCake = new OrderCake();
		orderCake.setForma(forma);
		orderCake.setSabor(sabor);
		orderCake.setTamano(tamano);
		orderCake.setCapas(capas);
		orderCake.setCubiertas(cubierta);
		orderCake.setRelleno(relleno);
		orderCake.setPrecio(precio);
	
		@SuppressWarnings("unchecked")
		HashMap<String, Long> hashMapId = (HashMap<String, Long>) infoPage.getAttribute("hashMapId");
	
		if (hashMapId.get("6"+cubiertaInt) == 42){	
			File imageFile = multipart.getFile("txtImage");
			String image = imageFile.getName();
			String dir = dirPath;
			
			int pointIndex = image.indexOf(".");
			String extension = image.substring(pointIndex);
			String name = image.substring(0,pointIndex);
			if (name.length() > 10)
				name = name.substring(0, 10);
			
			name = name.toLowerCase().replace(" ", "_") ;
			image = name.toLowerCase().replace("\\", "_") + "_" + client.getFirstName().trim() + extension;
			File destination = new File(dir + image);
			imageFile.renameTo(destination);
			
			orderCake.setNombreImagen(dir + image);
		}
		
		return orderCake;
	}
	
	static String requestPlain(HttpServletRequest request, HttpServletResponse response, final Properties propertiesFile){
		String forma = request.getParameter("forma");
		String tam = request.getParameter("tam");
		String sabor = request.getParameter("sabor");
		String capas = request.getParameter("capas");
		String[] relleno =request.getParameterValues("relleno");
		String cubierta = request.getParameter("cubierta");
		String precio = request.getParameter("priceCake");
		String fecha = request.getParameter("txtFecha");
		String clientId =  request.getParameter("clientId");
		String nombreImagen = request.getParameter("nombreImagen");
		
		String error = "";
		
		HttpSession infoPage = request.getSession();
		@SuppressWarnings("unchecked")
		HashMap<String, Double> hashMapPrice = (HashMap<String, Double>) infoPage.getAttribute("hashMapPrice");
		@SuppressWarnings("unchecked")
		HashMap<String, Long> hashMapId = (HashMap<String, Long>) infoPage.getAttribute("hashMapId");
		
		/* Establezco los valores de las cosas pedidas */
		List<OrderItem> orderItems = new LinkedList<OrderItem>();
		
		/* Forma */
		OrderItem item = new OrderItem();
		item.setPrice(hashMapPrice.get("1"+formaInt));
		item.setStepOptionId(hashMapId.get("1"+formaInt));
		orderItems.add(item);
	
		/* Tamano */
		item = new OrderItem();
		item.setPrice(hashMapPrice.get("2"+tamInt));
		item.setStepOptionId(hashMapId.get("2"+tamInt));
		orderItems.add(item);
		
		/* Sabor */
		item = new OrderItem();
		item.setPrice(hashMapPrice.get("3"+saborInt));
		item.setStepOptionId(hashMapId.get("3"+saborInt));
		orderItems.add(item);
		
		/* Capas */
		item = new OrderItem();
		item.setPrice(hashMapPrice.get("4"+capasInt));
		item.setStepOptionId(hashMapId.get("4"+capasInt));
		orderItems.add(item);
		
		/* Relleno */
		if (relleno != null){
			for (int i = 0; i < relleno.length; i++){
				item = new OrderItem();
				String index = "";
				if (i == 0)	
					index = "5" + capa1;
				else if (i == 1)
					index = "5" + capa2;
				else if (i == 2)
					index = "5" + capa3;
				
				item.setPrice(hashMapPrice.get(index));
				item.setStepOptionId(hashMapId.get(index));
				orderItems.add(item);
			}
		}
		
		/* Cubierta */ 
		item = new OrderItem();
		item.setPrice(hashMapPrice.get("6"+cubiertaInt));
		item.setStepOptionId(hashMapId.get("6"+cubiertaInt));
		if (hashMapId.get("6"+cubiertaInt) == 42){
			// Para que funcione local descomentar esta linea y comentar la otra
			//int index = nombreImagen.lastIndexOf("\\") + 1;
			int index = nombreImagen.lastIndexOf("/") + 1;
			String nombreImagenAux = nombreImagen.substring(index);
			item.setNombreImg(nombreImagenAux);
		}
		orderItems.add(item);
		
		
		try{
			final Client client = (Client) CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectClient(Long.valueOf(clientId)));
			
			/* Almacenamiento de la informacion en bd*/
			
			Order order = new Order();
			order.setClientId(client.getId());
			order.setDeliveryDate(fecha);
			order.setOrderTypeId(1);
			order.setTotal(Double.parseDouble(precio));
			order.setIsPending(1);
			
			
			final Long rowsUpdated  = (Long) CommandExecutor.getInstance().executeDatabaseCommand(new command.CreateOrder(order, orderItems));	
			
			
			boolean attach = false;
			if (nombreImagen != "")
				attach = true;
			final boolean attachment = attach;
			final String[] relle = relleno;
			
			final String[] datos = {forma, tam, sabor, capas, cubierta, precio, nombreImagen, fecha};
			new Thread(new Runnable() {
			    public void run() {
		    		SendEmail.sendEmailOrderCake(propertiesFile, String.valueOf(rowsUpdated), attachment, "ventas", datos, relle, client);
						
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
