<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="/armaTuTorta/css/styleAdmin.css" />
	<link rel="shortcut icon" href="/armaTuTorta/images/ico.ico">
	<script type="text/javascript" src="/armaTuTorta/js/messages.js"></script>
<title>Crear Paso</title>
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
        	<img alt="logo" src="/armaTuTorta/images/loguito5.png"/>
        </div>           
        <div id="menu">
			<div class="menuitemHome"><a href="UserLoginServlet">Home</a></div>	
			<ul>      
			<li class="menuitem"><a href="ListOrderStepsServlet?typeId=<%= request.getAttribute("typeId") %>">Ver Pasos</a></li>
			</ul>
			<div class="menuitemPass"><a href="EditUserPasswordServlet">Cambiar Contraseña</a></div>
			 <div class="menuitemSalir"><a href="admin/index.jsp">Salir</a></div>	
        </div>       
	  <div id="leftmenu">
        	<div id="leftmenu_top"></div>
			<div id="leftmenu_main">                    
                <jsp:include page="/inc/adminMenu.jsp"></jsp:include>
			</div>
            <div id="leftmenu_bottom"></div>
        </div>  
		<div id="content">
				<h2>Agregar Paso:</h2>
	        	<p>&nbsp;</p>
           		<p>&nbsp;</p>
				<form name="form" action="/armaTuTorta/CreateOrderStepServlet" onsubmit="return validateCreateStep(this)" method="post">
					<input type="hidden" name="txtTypeId" value="<%= request.getAttribute("typeId") %>" />
					<fieldset>
						<label for="name">Paso:</label>
						<input type="number" min="1" name="txtPosition" id="txtPosition" maxlength="3" size="3" value="<%= request.getAttribute("position") %>" /> <br><br>
						<label for="name">Nombre:</label>
						<textarea name="txtName" id="txtName" rows="4" cols="50"></textarea> <br><br>
						<input type="checkbox" name="txtIsMultSel" class="check" id="txtIsMultSel" maxlength="50" size="40" value="isMulSel" />&nbsp; 
							&nbsp;&nbsp; Es de Selección Múltiple<br><br>
						
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