<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="../css/styleAdmin.css" />
	<link rel="shortcut icon" href="../images/ico.ico">
	<script type="text/javascript" language="javascript" src="../js/jquery.js"></script>
	<script type="text/javascript" src="../js/messages.js"></script>
	<title>Editar Cliente</title>	
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
        		<h2>Modificar contraseña del cliente:</h2>
	        	<p>&nbsp;</p>
           		<p>&nbsp;</p>
           		<%
					String error = (String) request.getAttribute("error");
					if (error != null){
					%>
						<p class="error-msg"><%= error %></p>      
					<%
					}
					%>	
				<br>
				<form action="../EditClientPasswordServlet" onsubmit="return validatePassword(this)" method="post">
				<input type="hidden" name="txtClientId" value="<%= request.getAttribute("clientId") %>" />				
				<fieldset>
						<label for="oldPassword">Contraseña anterior:</label>
						<input type="password" name="txtOldPassword" id="txtOldPassword" maxlength="50"  /><br><br>
						<label for="LastName">Contraseña nueva:</label>
						<input type="password" name="txtPassword" id="txtPassword" maxlength="50"  /><br><br>
						<label for="LastName">Repita contraseña:</label>
						<input type="password" name="txtPasswordRpt" id="txtPasswordRpt" maxlength="50" /><br><br>
					
					<br><br><br><br><br>
					<div style="text-align:center">
							<input type="button" class="button" value="Volver"  onClick="javascript:history.back();"/>
							<input type="submit"  class="button"  name="sbmtButton" value="Editar" style="margin-left:20px;" />
					</div>	
				</fieldset>
				</form>
		    <div id="footer"></div>
		</div>
	</div>
</body>
</html>