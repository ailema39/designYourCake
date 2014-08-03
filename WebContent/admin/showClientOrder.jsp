<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="../css/styleAdmin.css" />
	<link rel="shortcut icon" href="../images/ico.ico">
	<script type="text/javascript" src="../js/messages.js"></script>
	<title>Detalle Pedido</title>
	<script language="javascript">
	/*****************************************************************************
	Pop-up ajustada a foto. Script creado por Tunait!
	Si quieres usar este script en tu sitio eres libre de hacerlo con la condición de que permanezcan intactas estas líneas, osea, los créditos.
	No autorizo a publicar y ofrecer el código en sitios de script sin previa autorización
	Si quieres publicarlo, por favor, contacta conmigo.
	http://javascript.tunait.com/
	tunait@yahoo.com 
	******************************************************************************/
	
	var titulopordefecto = "Imagen Referencia"; //Si no se especifica un título al llamar a la función colocará el que se especifique aquí
	var ventana;
	var cont=0;
	
	function afoto(cual,titulo){
		if(cont==1){ventana.close();ventana=null;}
		if(titulo==null){titulo=titulopordefecto;}
		ventana=window.open('','ventana','resizable=yes,scrollbars=no');
		ventana.document.write('<html><head><title>' + titulo + '</title></head><body style="overflow:hidden" marginwidth="0" marginheight="0" topmargin="0" bottommargin="0" leftmargin="0" rightmargin="0" scroll="no" onUnload="opener.cont=0"><img src="' + cual + '" onLoad="opener.redimensionar(this.width, this.height)">');
		ventana.document.close();
		cont++;
	}
	function redimensionar(ancho, alto){
		ventana.resizeTo(ancho+12,alto+28);
		ventana.moveTo((screen.width-ancho)/2,(screen.height-alto)/2); //centra la ventana. Eliminar si no se quiere centrar el popup
	}
	</script>
<script language=JavaScript> 
	<!-- 
	
	function inhabilitar(){ 
	   	return false;
	} 
	
	document.oncontextmenu=inhabilitar;
	
	// --> 
	</script>
	<script src="../js/jquery.nicescroll.min.js"></script>
	<script>
	$(document).ready(
	  function() { 
	    $("#scrollDiv").niceScroll({cursorcolor:"#00F"});
	  }
	);
	</script>
	<style type="text/css">
	#scrollDiv {
		overflow: auto;
		height: 350px;
	}
	</style>
</head>
<body oncontextmenu="return inhabilitar()">
	<div id="container">
		<div id="header">
        	<img alt="logo" src="../images/loguito5.png"/>
        </div>           
        <div id="menu">
        	<div class="menuitemHome"><a href="../UserLoginServlet">Home</a></div>	
	  		<ul>
           		<li class="menuitem"><a href="../ListClientsServlet">Ver Clientes</a></li>
           		<li class="menuitem"><a href="../ListClientOrdersServlet?clientId=<%= request.getAttribute("clientId") %>">Ver Pedidos</a></li>
            </ul>
			<div class="menuitemPass"><a href="../EditUserPasswordServlet">Cambiar Contraseña</a></div>
        	<div class="menuitemSalir"><a href="../admin/index.jsp">Salir</a></div>	
	 	</div>        
    	<div id="leftmenu">
        	<div id="leftmenu_top"></div>
			<div id="leftmenu_main">                    
            	<jsp:include page="../inc/adminMenu.jsp"></jsp:include>
			</div>
            <div id="leftmenu_bottom"></div>
        </div>  
		<div id="content">
        		<h2>Detalle Pedido:</h2>
        		<p>&nbsp;</p> 
				<jsp:useBean id="orderInfo" type="domain.Order" scope="request"/> 
				<input type="hidden" name="txtOrderId" value="<%= request.getAttribute("orderId") %>" />
				<input type="hidden" name="txtClientId" value="<%= request.getAttribute("clientId") %>" />
				<% 
					domain.Client clientInfo = orderInfo.getClient();
				%>
				<div id="scrollDiv">
					<h3>Datos Cliente:</h3>
					<fieldset>
						<label for="name" class="etiq"><span id="idCard"><%= clientInfo.getIdentityCard() %></span></label><br>
						<label for="name" class="etiq"><span id="idCard"><%= clientInfo.getFirstName() + " " + clientInfo.getLastName() %></span></label><br>					
						<label for="name" class="etiq"><span id="idCard">Correo Electrónico: </span></label><%= clientInfo.getEmail() %><br>
						<label for="name" class="etiq"><span id="idCard">Teléfono: </span></label><%= (clientInfo.getOtherPhone()==null || clientInfo.getOtherPhone().equalsIgnoreCase(""))? clientInfo.getPhone(): clientInfo.getPhone() + " / "+clientInfo.getOtherPhone() %><br>
						<label for="name" class="etiq"><span id="idCard">Dirección: </span></label><%= clientInfo.getAddress() %><br> 
						<label for="name" class="etiq"><span id="idCard">Dirección Envío: </span></label><%= (clientInfo.getShippingAddress()==null || clientInfo.getShippingAddress().equalsIgnoreCase(""))?"(Igual a la anterior)":clientInfo.getShippingAddress() %>
					</fieldset>
					<h3>Datos Solicitud:</h3>
					<fieldset>
						<label for="name" class="etiq"><span id="idCard">Fecha Pedido: </span></label><%= orderInfo.getOrderDate() %><br>
						<label for="name" class="etiq"><span id="idCard">Producto Pedido: </span></label><%= orderInfo.getOrderTypeName() %><br>
						<label for="name" class="etiq"><span id="idCard">Fecha Entrega: </span></label><%= orderInfo.getDeliveryDate() %><br>
						<label for="name" class="etiq"><span id="idCard">Status: </span></label><%= (orderInfo.getIsPending()==1)?"Pendiente":"Entregado" %><br>
						<% 
						java.util.List<domain.Item> items = orderInfo.getItems();
						long lastStep = 0;
						
						if(orderInfo.getOrderTypeId()==3){
							%>
						<table id="sweetTable">
						<tbody>
							<tr>
								<th>Cantidad</th>
								<th>Dulce</th>
							</tr>
							<%
							for(domain.Item item : items){
							%>
							<tr>
								<td><%= item.getAdditionalInfo() %></td>
								<td><%= item.getOptionName() %></td>
							</tr>
							<%
							}
							%>
							<tr id="totalTr">
								<td>Total</td>
								<td><%= orderInfo.getTotal() %></td>
							</tr>
						</tbody>
						</table>
							<% 	
						} else {
							for(domain.Item item : items){
								if(lastStep!=item.getOrderStepId()){
									if(item.getStepOptionId()==46){
							%>
							<label for="name" class="etiq"><span id="idCard"><%= item.getStepLabel() %></span></label><%= item.getAdditionalInfo() %> Docena(s)<br>
							<% 
									}else{
							%>
							<label for="name" class="etiq"><span id="idCard"><%= item.getStepLabel() %></span></label><%= item.getOptionName() %><br>
							<% 			
									}
								}else{
									if(item.getStepOptionId()==46){
							%>
							<label for="name" class="etiq"><span id="idCard" style="color:white;"><%= item.getStepLabel() %></span></label><%= item.getAdditionalInfo() %> Docena(s)<br>
							<% 
									}else{
							%>
							<label for="name" class="etiq"><span id="idCard" style="color:white;"><%= item.getStepLabel() %></span></label><%= item.getOptionName() %><br>
							<% 
									}
								}
								
								if(item.getStepOptionId()==45){
							%>
							<label for="name" class="etiq"><span id="idCard">Texto Calcomania:</span></label>"<%= item.getAdditionalInfo() %>"<br>
							<%
								} else if(item.getStepOptionId()==42){
							%>
							<br>
							<a class="seeImage" href= "<%= "/files/pedidosTortas/" + item.getAdditionalInfo() %>" onclick="afoto('<%= "/files/pedidosTortas/" + item.getAdditionalInfo() %>','Imagen Fondant');return false" > Ver Imagen de Fondant </a><br>
							<%		
								}
								
								lastStep = item.getOrderStepId();
							}

							if(orderInfo.getEstimationId()!=null && orderInfo.getEstimationId()!=0){
								domain.Estimation estimationInfo = orderInfo.getEstimation();
							%>							
								<label for="name" class="etiq"><span id="idCard">Ocasión Especial: </span></label><%= estimationInfo.getSpecialOccasion() %><br>
								<label for="name" class="etiq"><span id="idCard">Productos Deseados: </span></label><%= estimationInfo.getProducts() %><br>
								<label for="name" class="etiq"><span id="idCard">Número Invitados: </span></label><%= estimationInfo.getGuestsNumber() %><br>
								<label for="name" class="etiq"><span id="idCard">Detalles Adicionales: </span></label>"<%= estimationInfo.getDescription() %>"<br><br>	
								<%
								if(estimationInfo.getImage()!=null && !estimationInfo.getImage().equalsIgnoreCase("")){
								%>
								<a class="seeImage" href= "<%= "/files/pedidosOcasionesEspeciales/" + estimationInfo.getImage() %>" onclick="afoto('<%= "/files/pedidosOcasionesEspeciales/" + estimationInfo.getImage() %>','Imagen Referencia');return false" > Ver Imagen de Referencia </a>
								<%
								}
							}
							
							if(orderInfo.getAdditionalInfo()!=null){
							%>
								<br><br>
								<label for="name" class="etiq"><span id="idCard">Otras Anotaciones:</span></label>"<%= orderInfo.getAdditionalInfo() %>"
							<%
							}
						}					
						%>
					</fieldset>		
				</div>	
				<div style="text-align:center">
					<form action="../PrintClientOrderServlet?orderId=<%= request.getAttribute("orderId") %>&clientId=<%= request.getAttribute("clientId") %>"  method="post">
						<div id="botonP">
							<input type="submit"  class="button"  name="sbmtButton" value="Imprimir" style="margin-left:30%;" />
						</div>
					</form>
					<form>
						<div id="botonV" style="position:relative; margin-left: 400px; top: -22px;">
							<input type="button" class="button" value="Volver"  onClick="javascript:history.back();" />						
						</div>	
					</form>
				</div>	
		    <div id="footer"></div>
		</div>
	</div>
</body>
</html>