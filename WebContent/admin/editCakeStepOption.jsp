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
<title>Editar Opción</title>
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
				<li class="menuitem"><a href="../ListStepOptionsServlet?typeId=<%= request.getAttribute("typeId") %>&stepId=<%= request.getAttribute("stepId") %>">Ver Opciones</a></li>
            	<li class="menuitem"><a href="../CreateStepOptionServlet?typeId=<%= request.getAttribute("typeId") %>&stepId=<%= request.getAttribute("stepId") %>">Agregar opción</a></li>
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
				<h2>Editar Opción:</h2>
	        	<p>&nbsp;</p>
           		<p>&nbsp;</p>
				<form name="form" action="../EditStepOptionServlet" onsubmit="return validateEditOption(this)" method="post">
					<jsp:useBean id="optionInfo" type="domain.StepOption" scope="request"/> 
					<input type="hidden" name="txtTypeId" value="<%= request.getAttribute("typeId") %>" />
					<input type="hidden" name="txtStepId" value="<%= request.getAttribute("stepId") %>" />
					<input type="hidden" name="txtOptionId" value="<%= request.getAttribute("optionId") %>" />
					<input type="hidden" name="txtCurrentPosition" value="<%= optionInfo.getPosition() %>" />
					<input type="hidden" name="txtPriceMode" value="<%= request.getAttribute("priceMode") %>" />
					<fieldset>
						<label for="name">Posición:</label>
						<input type="number" min="1" name="txtPosition" id="txtPosition" maxlength="3" size="3" value="<%= optionInfo.getPosition() %>" /> <br><br>
						<label for="name">Nombre:</label>
						<textarea name="txtName" id="txtName" rows="2" cols="50"><%= optionInfo.getName() %></textarea> <br><br>
						<label for="name">Descripción:</label>
						<textarea name="txtDescription" id="txtDescription" rows="2" cols="50"><%= (optionInfo.getDescription()!=null)?optionInfo.getDescription():"" %></textarea> <br><br>
						<label for="name">Precio 3 Kilos:</label>
						<input type="text" name="txtPrice" id="txtPrice" maxlength="5" size="5" value="<%= optionInfo.getPrice() %>" /> <br><br>
						<label for="name">Precio 4 Kilos:</label>
						<input type="text" name="txtPrice2" id="txtPrice2" maxlength="5" size="5" value="<%= optionInfo.getPriceTwo() %>" /> <br><br>
						<label for="name">Precio 6 Kilos:</label>
						<input type="text" name="txtPrice3" id="txtPrice3" maxlength="5" size="5" value="<%= optionInfo.getPriceThree() %>" /> <br><br>
						<label for="name">Precio 8 Kilos:</label>
						<input type="text" name="txtPrice4" id="txtPrice4" maxlength="5" size="5" value="<%= optionInfo.getPriceFour() %>" /> <br><br>
						<%
						if (optionInfo.isUnavailable() == 1){
						%>
						<input type="checkbox" name="txtIsUnavailable" class="check" id="txtIsUnavailable" maxlength="50" size="40" value="isUnavailable" checked />&nbsp; 
							&nbsp;&nbsp; Está Agotado<br><br>
						<%
						}else{
						%>
						<input type="checkbox" name="txtIsUnavailable" class="check" id="txtIsUnavailable" maxlength="50" size="40" value="isUnavailable" />&nbsp; 
							&nbsp;&nbsp; Está Agotado<br><br>
					<%
						}
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
							<input type="submit"  class="button"  name="sbmtButton" value="Editar" style="margin-left:20px;" />
					</div>	
					</fieldset>			
			</form>
		    <div id="footer"></div>
		</div>
	</div>
</body>
</html>