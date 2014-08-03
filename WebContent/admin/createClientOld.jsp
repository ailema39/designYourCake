<%@page import="domain.Client"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="/armaTuTorta/css/styleAdmin.css" />
	<link rel="shortcut icon" href="/armaTuTorta/images/ico.ico">
	<script type="text/javascript" src="/armaTuTorta/js/messages.js"></script>
<title>Crear Cliente</title>
<script type="text/javascript">

	function changeDir(){
		var check = document.getElementById("txtIsShipping");
		var div = document.getElementById("shippingAddress");
		
		if(check.checked){
			div.style.display = "none";
		} else {
			div.style.display = "block";
		}
	}
	
	function changeForm(){
		var select = document.getElementById("txtTypeId");
		var position = select.options.selectedIndex;
		var typeId = select.options[position].value;
		var personDiv = document.getElementById("person");
		var companyDiv = document.getElementById("company");
		var span = document.getElementById("idCard");
		
		if((typeId == "V-") || (typeId == "E-")){
			companyDiv.style.display="none";
			personDiv.style.display = "block";
			span.innerHTML = "Cédula de Identidad:";
		} else {
			companyDiv.style.display="block";
			personDiv.style.display = "none";
			span.innerHTML = "RIF:";
		}
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
</head>
<body oncontextmenu="return inhabilitar()">
	<div id="container">
		<div id="header">
        	<img alt="logo" src="/armaTuTorta/images/loguito5.png"/>
        </div>           
        <div id="menu">
			<div class="menuitemHome"><a href="UserLoginServlet">Home</a></div>	
			<ul>      
			<li class="menuitem"><a href="ListClientsServlet">Ver Clientes</a></li>
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
				<h2>Agregar Cliente:</h2>
				<form name="form" action="/armaTuTorta/CreateClientServlet" onsubmit="return validateCreateClient(this)" method="post">
					<fieldset>
						<label for="name"><span id="idCard">Cédula de Identidad:</span></label>
						<select name="txtTypeId" id="txtTypeId" onchange="changeForm()">
							<option value="V-">V</option>
							<option value="E-">E</option>
							<option value="J-">J</option>
						</select>
						<input type="text" name="txtIdCard" id="txtIdCard" maxlength="50" size="18" /> <br><br>
						<div id="person" style="display:block">
							<label for="name">Nombre:</label>
							<input type="text" name="txtFirstName" id="txtFirstName" maxlength="50" size="40" /> <br><br>
							<label for="name">Apellido:</label>
							<input type="text" name="txtLastName" id="txtLastName" maxlength="50" size="40" /> <br><br>
						</div>
						<div id="company" style="display:none">
							<label for="name">Nombre:</label>
							<input type="text" name="txtName" id="txtName" maxlength="50" size="40" /> <br><br>
						</div>
						<label for="email">Correo Electrónico:</label>
						<input type="email" name="txtEmail" id="txtEmail" maxlength="50" size="40" /> <br><br>
						<label for="pwd">Contraseña:</label>
						<input type="password" name="txtPassword" id="txtPassword" maxlength="50" /><br><br>
						<label for="pwdRpt">Repetir Contraseña:</label>
						<input type="password" name="txtPasswordRpt" id="txtPasswordRpt" maxlength="50" /><br><br>
						<label for="phone">Número Telefónico:</label>
						<input type="tel" name="txtPhone" id="txtPhone" maxlength="50" /> <br><br>
						<label for="otherPhone">Número Telefónico Adicional:</label>
						<input type="tel" name="txtOtherPhone" id="txtOtherPhone" maxlength="50" /> <br><br>
						<label for="address">Dirección de Facturación:</label>
						<textarea name="txtAddress" id="txtAddress" rows="2" cols="40"></textarea>&nbsp;
						<input type="checkbox" name="txtIsShipping" class="check" id="txtIsShipping" maxlength="50" size="40" value="isShipping" onchange="changeDir()" />&nbsp; 
							&nbsp; Es dirección de envío<br>
						<div id="shippingAddress">
							<label for="sAddress">Dirección de Envío:</label>
							<textarea name="txtShippingAddress" id="txtShippingAddress" rows="2" cols="40"></textarea> <br>
						</div>							
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