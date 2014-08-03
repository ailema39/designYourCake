<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="/armaTuTorta/css/styleAdmin.css" />
	<link rel="shortcut icon" href="/armaTuTorta/images/ico.ico">
	<script type="text/javascript" src="/armaTuTorta/js/messages.js"></script>
	<title>Editar Cliente</title>
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
        		<h2>Editar cliente:</h2>
	        	<p>&nbsp;</p>
           		<p>&nbsp;</p>
				<form action="/armaTuTorta/EditClientServlet" onsubmit="return validateEditClient(this)" method="post">
				<jsp:useBean id="clientInfo" type="domain.Client" scope="request"/> 
				<input type="hidden" name="txtClientId" value="<%= request.getAttribute("clientId") %>" />
				<fieldset>
					<label for="name"><span id="idCard">Cédula de Identidad:</span></label>
					<%
					String identityCard = clientInfo.getIdentityCard();
					String identityCardId = identityCard.substring(0, 2);
					String identityCardNum = identityCard.substring(2);
					%>
					<select name="txtTypeId" id="txtTypeId" onchange="changeForm()">
					<%
					if(identityCardId.equalsIgnoreCase("V-")){
					%>
						<option value="V-" selected="selected">V</option>
						<option value="E-">E</option>
						<option value="J-">J</option>
					<%
					} else if(identityCardId.equalsIgnoreCase("E-")){
					%>
						<option value="V-">V</option>
						<option value="E-" selected="selected">E</option>
						<option value="J-">J</option>
					<%
					} else{
					%>
						<option value="V-">V</option>
						<option value="E-">E</option>
						<option value="J-" selected="selected">J</option>
					<%
					}
					%>
					</select>
					<input type="text" name="txtIdCard" id="txtIdCard" maxlength="50" size="18" value="<%= identityCardNum %>" /> <br><br>
					<%
					if(identityCardId.equalsIgnoreCase("J-")){
					%>
					<div id="person" style="display:none">
						<label for="name">Nombre:</label>
						<input type="text" name="txtFirstName" id="txtFirstName" maxlength="50" size="40" /> <br><br>
						<label for="name">Apellido:</label>
						<input type="text" name="txtLastName" id="txtLastName" maxlength="50" size="40" /> <br><br>
					</div>
					<div id="company" style="display:block">
						<label for="name">Nombre:</label>
						<input type="text" name="txtName" id="txtName" maxlength="50" size="40" value="<%= clientInfo.getFirstName() %>" /> <br><br>
					</div>
					<%
					} else {
					%>					
					<div id="person" style="display:block">
						<label for="name">Nombre:</label>
						<input type="text" name="txtFirstName" id="txtFirstName" maxlength="50" size="40" value="<%= clientInfo.getFirstName() %>" /> <br><br>
						<label for="name">Apellido:</label>
						<input type="text" name="txtLastName" id="txtLastName" maxlength="50" size="40" value="<%= clientInfo.getLastName() %>" /> <br><br>
					</div>
					<div id="company" style="display:none">
						<label for="name">Nombre:</label>
						<input type="text" name="txtName" id="txtName" maxlength="50" size="40" /> <br><br>
					</div>
					<%
					}
					%>	
					<label for="email">Correo Electrónico:</label>
					<input type="email" name="txtEmail" id="txtEmail" maxlength="50" size="40" value="<%= clientInfo.getEmail() %>" /> <br><br>
					<label for="phone">Número Telefónico:</label>
					<input type="tel" name="txtPhone" id="txtPhone" maxlength="50" value="<%= clientInfo.getPhone() %>" /> <br><br>
					<label for="otherPhone">Número Telefónico Adicional:</label>
					<input type="tel" name="txtOtherPhone" id="txtOtherPhone" maxlength="50" value="<%= clientInfo.getOtherPhone() %>" /> <br><br>
					<label for="address">Dirección de Facturación:</label>
					<textarea name="txtAddress" id="txtAddress" rows="2" cols="40"><%= clientInfo.getAddress() %></textarea>&nbsp;
					<%
					if(clientInfo.isShippingAddress()==1){
					%>
					<input type="checkbox" name="txtIsShipping" class="check" id="txtIsShipping" maxlength="50" size="40" value="isShipping" onchange="changeDir()" checked/>&nbsp; 
						&nbsp; Es dirección de envío<br>
					<div id="shippingAddress" style="display:none">
						<label for="sAddress">Dirección de Envío:</label>
						<textarea name="txtShippingAddress" id="txtShippingAddress" rows="2" cols="40"></textarea> <br>
					</div>
					<%
					}else{
					%>
					<input type="checkbox" name="txtIsShipping" class="check" id="txtIsShipping" maxlength="50" size="40" value="isShipping" onchange="changeDir()" />&nbsp; 
						&nbsp; Es dirección de envío<br>
					<div id="shippingAddress" style="display:block">
						<label for="sAddress">Dirección de Envío:</label>
						<textarea name="txtShippingAddress" id="txtShippingAddress" rows="2" cols="40"></textarea> <br>
					</div>
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