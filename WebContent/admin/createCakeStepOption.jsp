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
<title>Crear Opción</title>
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
			<li class="menuitem"><a href="../ListOrderStepsServlet?typeId=<%= request.getAttribute("typeId") %>">Ver Pasos</a></li>      
			<li class="menuitem"><a href="../ListStepOptionsServlet?typeId=<%= request.getAttribute("typeId") %>&stepId=<%= request.getAttribute("stepId") %>&priceMode=<%= request.getAttribute("priceMode") %>">Ver Opciones</a></li>
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
				<h2>Agregar Opción:</h2>
	        	<p>&nbsp;</p>
           		<p>&nbsp;</p>
				<form name="form" action="../CreateStepOptionServlet" onsubmit="return validateCreateOption(this)" method="post">
					<input type="hidden" name="txtTypeId" value="<%= request.getAttribute("typeId") %>" />
					<input type="hidden" name="txtStepId" value="<%= request.getAttribute("stepId") %>" />
					<input type="hidden" name="txtPriceMode" value="<%= request.getAttribute("priceMode") %>" />
					<fieldset>
						<label for="name">Posición:</label>
						<input type="number" min="1" name="txtPosition" id="txtPosition" maxlength="3" size="3" value="<%= request.getAttribute("position") %>" /> <br><br>
						<label for="name">Nombre:</label>
						<textarea name="txtName" id="txtName" rows="2" cols="50"></textarea> <br><br>
						<label for="name">Descripción:</label>
						<textarea name="txtDescription" id="txtDescription" rows="3" cols="50"></textarea> <br><br>
						<label for="name">Precio Pequeña:</label>
						<input type="text" name="txtPrice" id="txtPrice" maxlength="5" size="5" /> <br><br>
						<label for="name">Precio Mediana:</label>
						<input type="text" name="txtPrice2" id="txtPrice2" maxlength="5" size="5" /> <br><br>
						<label for="name">Precio Grande:</label>
						<input type="text" name="txtPrice3" id="txtPrice3" maxlength="5" size="5" /> <br><br>
						<input type="checkbox" name="txtIsUnavailable" class="check" id="txtIsUnavailable" maxlength="50" size="40" value="isUnavailable" />&nbsp; 
							&nbsp;&nbsp; Está Agotado<br><br>
						
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
							<input type="submit"  class="button"  name="sbmtButton" value="Agregar" style="margin-left:20px;" />
					</div>	
					</fieldset>			
			</form>
		    <div id="footer"></div>
		</div>
	</div>
</body>
</html>