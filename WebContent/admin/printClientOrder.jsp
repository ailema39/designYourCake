<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="../css/styleAdmin.css" />
		<link rel="shortcut icon" href="../images/ico.ico">
		<title>Pedido</title>	
		<script type="text/javascript">
		
		function printPageContentB() {
			div = document.getElementById('botonera');
			div.style.display = "none";
			window.print();
		}
		
		function unPrintPageContentB() {
			div = document.getElementById('botonera');
			div.style.display = "block";
		}
		
		</script>
		<style type="text/css">
		#especial{
			background-image:url(null);
			margin-left: 10%;
			margin-right: 10%;
		}
		input{
			border: none;
		}
		
		label, span {
			font-weight: bold;
		}
		</style>
	<script language=JavaScript> 
	<!-- 
	
	function inhabilitar(){ 
	   	return false;
	} 
	
	document.oncontextmenu=inhabilitar;
	
	// --> 
	</script>
</head>
	<body id="especial" oncontextmenu="return inhabilitar()">
		<div id="printHeader">
        	<img alt="logo" src="../images/loguito5.png"/>
        </div>  
		<jsp:useBean id="orderInfo" type="domain.Order" scope="request"/>
		<br>
		<div id="title" style="font-size:16px; font-weight: bold;"> Informacion de pedido n° <%= orderInfo.getId()  %> </div>
		<br>
		<% 
			domain.Client clientInfo = orderInfo.getClient();
		%>
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
				<label for="name" class="etiq"><span id="idCard">Imagen Fondant:</span></label><br>
					<img alt="ref" src="<%= "/files/pedidosTortas/" + item.getAdditionalInfo() %>"/><br>
				<%		
					}
					
					lastStep = item.getOrderStepId();
				}
				
				if(orderInfo.getAdditionalInfo()!=null){
				%>
					<label for="name" class="etiq"><span id="idCard">Otras Anotaciones:</span></label>"<%= orderInfo.getAdditionalInfo() %>"<br>
				<%
				}
				
				if(orderInfo.getEstimationId()!=null && orderInfo.getEstimationId()!=0){
					domain.Estimation estimationInfo = orderInfo.getEstimation();
				%>							
					<label for="name" class="etiq"><span id="idCard">Ocasión Especial: </span></label><%= estimationInfo.getSpecialOccasion() %><br>
					<label for="name" class="etiq"><span id="idCard">Productos Deseados: </span></label><%= estimationInfo.getProducts() %><br>
					<label for="name" class="etiq"><span id="idCard">Número Invitados: </span></label><%= estimationInfo.getGuestsNumber() %><br>
					<label for="name" class="etiq"><span id="idCard">Detalles Adicionales: </span></label>"<%= estimationInfo.getDescription() %>"<br>	
					
					<%
				}				
			}					
			%>
		</fieldset>	
		<%
		if(orderInfo.getEstimationId()!=null && orderInfo.getEstimationId()!=0){
			
			domain.Estimation estimationInfo = orderInfo.getEstimation();
			if(estimationInfo.getImage()!=null && !estimationInfo.getImage().equalsIgnoreCase("")){
		%>
		<h3>Imagen de Referencia:</h3>
		<fieldset>
			<img alt="ref" src="<%= "/files/pedidosOcasionesEspeciales/" + estimationInfo.getImage() %>"/><br>
		</fieldset>	
		<%	
			}
		}					
		%>
		<br>
		<div id="botonera">
				<form onsubmit="printPageContentB();">
				<div id="botonP">
							<input type="submit"  class="button"  name="sbmtButton" value="Imprimir" style="margin-left:30%;" onclick="printPageContentB();unPrintPageContentB();return false" />
				</div>	
				</form>
				<form>
					<div id="botonV" style="position:relative; margin-left: 450px; top: -20px;">
							<input type="button" class="button" value="Volver"  onClick="javascript:history.back();" style="margin-left:40%;" />		
					</div>	
				</form>
			</div>			
	</body>
</html>