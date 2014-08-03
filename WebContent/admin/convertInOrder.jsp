<%@page import="domain.Album"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="../css/styleAdmin.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery-ui-1.8.24.custom.css" />
	<link rel="shortcut icon" href="../images/ico.ico">
	<script type="text/javascript" src="../js/messages.js"></script>
	<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="../js/jquery-ui-1.8.24.custom.min.js"></script>
	<script type="text/javascript" src="../js/jquery.ui.datepicker.js"></script>
	<script type="text/javascript">
	$(function(){
		$('#txtDeliveryDate').datepicker({
			showOn: "button",
			buttonImage: "../images/calendar.png",
			buttonImageOnly: true,
			buttonText: "Seleccione una fecha",
			dateFormat:'dd/mm/yy',
			changeMonth: true,
		    changeYear: true,
		    defaultDate: "+1w",
		    minDate: '0d'
		});
	} );
</script>
	<title>Convertir en Pedido</title>
<script language=JavaScript> 
	<!-- 
	
	function inhabilitar(){ 
	   	return false;
	} 
	
	document.oncontextmenu=inhabilitar;
	
	// --> 
	</script>
</head>
<body oncontextmenu="return inhabilitar()">
	<div id="container">
		<div id="header">
        	<img alt="logo" src="../images/loguito5.png"/>
        </div>           
        <div id="menu">
			<div class="menuitemHome"><a href="../UserLoginServlet">Home</a></div>	
	  		<ul>
           		<li class="menuitem"><a href="../ListEstimationsServlet">Ver Presupuestos</a></li>
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
				<h2>Convertir en Pedido:</h2>
	        	<p>&nbsp;</p>
           		<p>&nbsp;</p>
				<form name="form" action="../ConvertInOrderServlet" onsubmit="return validateConverInOrder(this)" method="post">
					<input type="hidden" name="txtEstimationId" value="<%= request.getAttribute("estimationId") %>" />
					<input type="hidden" name="txtOrderTypeId" value="<%= request.getAttribute("orderTypeId") %>" />
					<input type="hidden" name="txtClientId" value="<%= request.getAttribute("clientId") %>" />
					<fieldset>
						<label for="name">Total Presupuestado:</label>
						<input type="number" name="txtPrice" id="txtPrice" min="1" /> <br><br>
						<label for="image">Fecha de Entrega:</label>
						<input type="text" name="txtDeliveryDate" id="txtDeliveryDate" maxlength="50" size="10" /> <br><br>
						<label for="image">Información Adicional:</label>
						<textarea name="txtAdditionalInfo" id="txtAdditionalInfo" rows="5" cols="40"></textarea> <br><br>
						
					<%
					String error = (String) request.getAttribute("error");
					if (error != null){
					%>
						<div>
							<%= error %>
						</div>
						<%
					}
					%>	
					<br>
					<div style="text-align:center">
							<input type="button" class="button" value="Volver"  onClick="javascript:history.back();"/>
							<input type="submit"  class="button"  name="sbmtButton" value="Convertir" style="margin-left:20px;" />
					</div>	
					</fieldset>			
			</form>
		    <div id="footer"></div>
		</div>
	</div>
</body>
</html>