<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="../css/styleAdmin.css" />
	<link rel="shortcut icon" href="../images/ico.ico">
	<script type="text/javascript" src="../js/messages.js"></script>
	<title>Detalle Cliente</title>
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
        		<h2>Detalle Cliente:</h2>
        		<p>&nbsp;</p> 
				<jsp:useBean id="clientInfo" type="domain.Client" scope="request"/> 
				<div id="scrollDiv">
				<input type="hidden" name="txtClientId" value="<%= request.getAttribute("clientId") %>" />
				<h3>Datos Cliente:</h3>
				<fieldset>
					<label for="name" class="etiq"><span id="idCard">Cédula de Identidad:</span></label> <%= clientInfo.getIdentityCard() %><br>
					<%
					String identityCard = clientInfo.getIdentityCard();

					if(identityCard.startsWith("J-")){
					%>
					<label for="name" class="etiq"><span id="idCard">Nombre:</span></label> <%= clientInfo.getFirstName() %><br>
					<%
					} else {
					%>					
					<label for="name" class="etiq"><span id="idCard">Nombre:</span></label> <%= clientInfo.getFirstName() %><br>
					<label for="name" class="etiq"><span id="idCard">Apellido:</span></label> <%= clientInfo.getLastName() %><br>
					<%
					}
					%>					
				</fieldset>	
				<h3>Datos Contacto:</h3>
				<fieldset>
					<label for="name" class="etiq"><span id="idCard">Correo Electrónico:</span></label> <%= clientInfo.getEmail() %><br>
					<label for="name" class="etiq"><span id="idCard">Número Telefónico:</span></label> <%= clientInfo.getPhone() %><br>
					<label for="name" class="etiq"><span id="idCard">Número Telefónico Adicional:</span></label> <%= clientInfo.getOtherPhone() %><br>
					<label for="name" class="etiq"><span id="idCard">Dirección de Facturación:</span></label> <%= clientInfo.getAddress() %><br>
					<%
					if(clientInfo.isShippingAddress()==1){
					%>
					<label for="name" class="etiq"><span id="idCard">Dirección de Envío:</span></label> (Misma Dirección de Facturación)<br>
					<%
					}else{
					%>
					<label for="name" class="etiq"><span id="idCard">Dirección de Envío:</span></label> <%= clientInfo.getShippingAddress() %><br>
					<%
					}
					%>						
				</fieldset>	
				</div>
		    <div id="footer"></div>
		</div>
	</div>
</body>
</html>