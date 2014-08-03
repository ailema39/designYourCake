<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="../css/styleAdmin.css" />
		<link rel="shortcut icon" href="../images/ico.ico">
		<title>Presupuesto</title>	
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
		<jsp:useBean id="estimationInfo" type="domain.Estimation" scope="request"/>
		<br>
		<div id="title" style="font-size:16px; font-weight: bold;"> Informacion de solicitud de presupuesto n° <%= estimationInfo.getId()  %> </div>
		<br>
		<h3>Datos Cliente:</h3>
		<fieldset>
			<label for="name" class="etiq"><span id="idCard"><%= estimationInfo.getClient().getIdentityCard() %></span></label><br>
			<label for="name" class="etiq"><span id="idCard"><%= estimationInfo.getClient().getFirstName() + " " + estimationInfo.getClient().getLastName() %></span></label><br>					
			<label for="name" class="etiq"><span id="idCard">Correo Electrónico: </span></label><%= estimationInfo.getClient().getEmail() %><br>
			<label for="name" class="etiq"><span id="idCard">Teléfono: </span></label><%= (estimationInfo.getClient().getOtherPhone()==null || estimationInfo.getClient().getOtherPhone().equalsIgnoreCase(""))? estimationInfo.getClient().getPhone(): estimationInfo.getClient().getPhone() + " / "+estimationInfo.getClient().getOtherPhone() %><br>
			<label for="name" class="etiq"><span id="idCard">Dirección: </span></label><%= estimationInfo.getClient().getAddress() %><br> 
			<label for="name" class="etiq"><span id="idCard">Dirección Envío: </span></label><%= (estimationInfo.getClient().getShippingAddress()==null || estimationInfo.getClient().getShippingAddress().equalsIgnoreCase(""))?"(Igual a la anterior)":estimationInfo.getClient().getShippingAddress() %><br>
		</fieldset>
		<br>
		<h3>Datos Solicitud:</h3>
		<fieldset>
			<label for="name" class="etiq"><span id="idCard">Ocasión Especial: </span></label><%= estimationInfo.getSpecialOccasion() %><br>
			<label for="name" class="etiq"><span id="idCard">Productos Deseados: </span></label><%= estimationInfo.getProducts() %><br>
			<label for="name" class="etiq"><span id="idCard">Número Invitados: </span></label><%= estimationInfo.getGuestsNumber() %><br>
			<label for="name" class="etiq"><span id="idCard">Detalles Adicionales: </span></label>"<%= estimationInfo.getDescription() %>"<br>
		</fieldset>
		<%
		if(estimationInfo.getImage()!=null && !estimationInfo.getImage().equalsIgnoreCase("")){
		%>
		<br>
		<h3>Imagen de Referencia:</h3>
		<fieldset>
			<img alt="ref" src="<%= "/files/pedidosOcasionesEspeciales/" + estimationInfo.getImage() %>"/><br>
		</fieldset>
		<%
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