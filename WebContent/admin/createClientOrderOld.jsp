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
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<title>Crear Cliente</title>
<script>
  $(function() {
    $( "#tabs" ).tabs();
  });
  </script>
<script type="text/javascript">
	
	function changeTab(){
		var typesDiv = document.getElementById("types");
		var stepsDiv = document.getElementById("steps");
		var cakesDiv = document.getElementById("cakes");
		var cupcakesDiv = document.getElementById("cupcakes");
		var sweetcakesDiv = document.getElementById("sweetcakes");
		var specialDiv = document.getElementById("specialOccasions");
		
		if(document.getElementById("txtTypeId1").checked){
			cakesDiv.style.display = "block";
			cupcakesDiv.style.display = "none";
			sweetcakesDiv.style.display = "none";
			specialDiv.style.display = "none";
		} else if(document.getElementById("txtTypeId2").checked){
			cakesDiv.style.display = "none";
			cupcakesDiv.style.display = "block";
			sweetcakesDiv.style.display = "none";
			specialDiv.style.display = "none";
		} else if(document.getElementById("txtTypeId3").checked){
			cakesDiv.style.display = "none";
			cupcakesDiv.style.display = "none";
			sweetcakesDiv.style.display = "block";
			specialDiv.style.display = "none";
		} else if(document.getElementById("txtTypeId4").checked){
			cakesDiv.style.display = "none";
			cupcakesDiv.style.display = "none";
			sweetcakesDiv.style.display = "none";
			specialDiv.style.display = "block";
		}
		
		typesDiv.style.display = "none";
		stepsDiv.style.display = "block";
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
			<li class="menuitem"><a href="ListClientOrdersServlet?clientId=<%= request.getAttribute("clientId") %>">Ver Pedidos</a></li>
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
				<h2>Armar Pedido:</h2>
				<form name="form" action="/armaTuTorta/CreateClientOrderServlet" onsubmit="return validateCreateClient(this)" method="post">
					<input type="hidden" name="txtClientId" value="<%= request.getAttribute("clientId") %>" />
					<div id="types" style="display:block">
						<span id="idType">Seleccione el producto que desea encargar:</span><br><br>
						<jsp:useBean id="types" type="java.util.ArrayList<domain.OrderType>" scope="request"/> 
						<%
							for(domain.OrderType t : types) { 											
						%>
						<input type="radio" id="<%= "txtTypeId" + t.getId() %>" name="txtTypeId" value="<%= t.getId() %>"><%= t.getName() %><br>
						<%
							}											
						%>
						<div style="text-align:center">
							<input type="button" class="button" value="Volver"  onClick="javascript:history.back();"/>
							<input type="button" class="button" value="Agregar" style="margin-left:20px;" onclick="changeTab()" />
						</div>	
					</div>
					<div id="steps" style="display:none">
						
	<div id="tabs">
  <ul>
    <li><a href="#tabs-1">Nunc tincidunt</a></li>
    <li><a href="#tabs-2">Proin dolor</a></li>
    <li><a href="#tabs-3">Aenean lacinia</a></li>
  </ul>
  <div id="tabs-1">
    <p>Proin elit arcu, rutrum commodo, vehicula tempus, commodo a, risus. Curabitur nec arcu. Donec sollicitudin mi sit amet mauris. Nam elementum quam ullamcorper ante. Etiam aliquet massa et lorem. Mauris dapibus lacus auctor risus. Aenean tempor ullamcorper leo. Vivamus sed magna quis ligula eleifend adipiscing. Duis orci. Aliquam sodales tortor vitae ipsum. Aliquam nulla. Duis aliquam molestie erat. Ut et mauris vel pede varius sollicitudin. Sed ut dolor nec orci tincidunt interdum. Phasellus ipsum. Nunc tristique tempus lectus.</p>
  </div>
  <div id="tabs-2">
    <p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus gravida ante, ut pharetra massa metus id nunc. Duis scelerisque molestie turpis. Sed fringilla, massa eget luctus malesuada, metus eros molestie lectus, ut tempus eros massa ut dolor. Aenean aliquet fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam. Praesent in eros vestibulum mi adipiscing adipiscing. Morbi facilisis. Curabitur ornare consequat nunc. Aenean vel metus. Ut posuere viverra nulla. Aliquam erat volutpat. Pellentesque convallis. Maecenas feugiat, tellus pellentesque pretium posuere, felis lorem euismod felis, eu ornare leo nisi vel felis. Mauris consectetur tortor et purus.</p>
  </div>
  <div id="tabs-3">
    <p>Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a, lacus.</p>
    <p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.</p>
  </div>
</div>
						<div id="cakes" style="display:none">
						<jsp:useBean id="cakes" type="java.util.ArrayList<domain.ListOrder_Step>" scope="request"/> 
						<%
							for(domain.ListOrder_Step s : cakes) { 				
								domain.OrderStep step = s.getOrder();
								java.util.List<domain.StepOption> options = s.getOrderTypeId();
						%>
						<%= step.getPosition() + ".-" + step.getName() %><br>
						<%		
								for(domain.StepOption o : options) { 	
						%>
						<%= o.getName() + "+" + o.getPrice() %><br>
						<%		
								}
							}
						%>
						</div>		
						<div id="cupcakes" style="display:none">	
						<jsp:useBean id="cupcakes" type="java.util.ArrayList<domain.ListOrder_Step>" scope="request"/> 
						<%
							for(domain.ListOrder_Step s : cupcakes) { 				
								domain.OrderStep step = s.getOrder();
								java.util.List<domain.StepOption> options = s.getOrderTypeId();
						%>
						<%= step.getPosition() + ".-" + step.getName() %><br>
						<%		
								for(domain.StepOption o : options) { 	
						%>
						<%= o.getName() + "+" + o.getPrice() %><br>
						<%		
								}
							}
						%>
						</div>
						<div id="sweetcakes" style="display:none">	
						<jsp:useBean id="sweetcakes" type="java.util.ArrayList<domain.StepOption>" scope="request"/> 
						<%		
							for(domain.StepOption o : sweetcakes) { 	
						%>
						<%= o.getName() + "+" + o.getPrice() %><br>
						<%		
							}
						%>
						</div>
						<div id="specialOccasions" style="display:none">
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
					</div>		
			</form>
		    <div id="footer"></div>
		</div>
	</div>
</body>
</html>