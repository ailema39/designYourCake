<!DOCTYPE html>
<%@page import="domain.Client"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Design Your Cake</title>
	<link href='http://fonts.googleapis.com/css?family=Handlee' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Economica' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link rel="stylesheet" type="text/css" href="css/demos.css" />
	<link rel="stylesheet" type="text/css" href="css/ui.theme.css" />
	<link rel="shortcut icon" href="images/ico.ico">
	<!--[if IE 8]>
		<link rel="stylesheet" type="text/css" href="css/ie8.css" />
	<![endif]-->
	<!--[if IE 7]>
		<link rel="stylesheet" type="text/css" href="css/ie7.css" />
	<![endif]-->
	<!--[if IE 6]>
		<link rel="stylesheet" type="text/css" href="css/ie6.css" />
	<![endif]-->
	
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
	
	<script type="text/javascript" src="js/ui.core.js"></script>
	<script type="text/javascript" src="js/ui.datepicker.js"></script>
	<script> 
	$(function() {
		$("#datepicker").datepicker({
			minDate: +2
		});
	});
	</script> 

	
	<script type="text/javascript" charset="utf-8">
		$(document).ready(function() {
			$("a[rel*=leanModal]").leanModal({ top : 200, overlay : 0.4, closeButton: ".modal_close" });
		});
	</script>
	<script type="text/javascript" src="js/messages.js"></script>
	<script type="text/javascript" charset="utf-8">
		function inhabilitar(){ 
		   	return false;
		} 
		document.oncontextmenu=inhabilitar;
	</script>
</head>
<body oncontextmenu="return inhabilitar()">
<%
	HttpSession infoPage = request.getSession();
	session.setAttribute("prevPage", "HomePageServlet");
	Client client = (Client) infoPage.getAttribute("client");
%>	
<div class="wrapper">
	<div id="header">
		<div>
			<div>
				<div id="logo">
					<a href="HomePageServlet"><img src="images/logo5.png" alt="Logo"/></a>
				</div>
				<jsp:include page="header.jsp"></jsp:include>
			</div>
			<ul style="margin: 0px; ">
				<li><a href="HomePageServlet">Design Your Cake</a></li>
				<li><a href="ArmaTuTortaServlet?typeId=1">Criar Bolo</a></li>
				<li><a href="DulcesTortasServlet?typeId=3">Doces</a></li>
				<li><a href="CupcakesServlet?typeId=2">Criar Cupcakes</a></li>
				<li><a href="OcasionesEspecialesServlet">Ocasi&#245;es Especiais</a></li>
				<li><a href="GalleryServlet">Portafolio</a></li>
				<li><a href="ContactServlet">Contato</a></li>
			</ul>
		</div>
	</div>
	<div id="content">
		<div class="home">
			<% String type = (String) request.getAttribute("info"); 
				if (type.equals("1")){
			%>
			<div id="datosVerif">
			<jsp:useBean id="pedido" type="domain.OrderCupcake" scope="request" />  

			<form action="CupcakesServlet?typeId=1&pr=2" method="post" id="confirm">
				<input type="hidden" name="clientId" id="clientId" value="<%= client.getId() %>">
				<input type="hidden" name="tamano" id="tamano" value="<%= pedido.getTamano() %>">
				<input type="hidden" name="cantidad"  id="cantidad" value="<%= pedido.getCantidad() %>">
				<input type="hidden" name="sabor"  id="sabor" value="<%= pedido.getSabor() %>">
				<input type="hidden" name="cubierta" id="cubierta" value="<%= pedido.getCubiertas() %>">
				<%
					if (pedido.getColor() != null){
						for (int j= 0; j < pedido.getColor().length; j++){
				%>
					<input type="hidden" name="color" value="<%= pedido.getColor()[j] %>">
				<%
						}
					}
				%>	
				<input type="hidden" name="decoracion" id="decoracion" value="<%= pedido.getDecoracion() %>">
				<input type="hidden" name="priceCupcake" id="priceCupcake" value="<%= pedido.getPrecio() %>">
				<input type="hidden" name="cantDocenas" id="cantDocenas" value="<%= pedido.getCantidadDocenas() %>">
				<input type="hidden" name="txtCalcomania" id="txtCalcomania" value="<%= pedido.getCalcomania() ==null ? "" : pedido.getCalcomania() %>">

				<div class="bienv">
					<span class="bienv-title">Verifica los datos de tu pedido.</span><br><br>
				  	Por favor ingrese la fecha de entrega para la cual desea su pedido: <input type="text" readonly="readonly" id="datepicker" name="txtFecha" />
			  		<span class="error" id="errorDate">Disculpe, debe introducir una fecha de entrega válida</span><br><br>
					<div id="pedido">
						<%
						String colores = "";
						if (pedido.getColor() != null){
								String[] color = pedido.getColor();
								for (int i = 0; i< color.length; i++){
									 if (i == color.length - 1)
										 if (color.length > 1)
					        			 	colores += "e " + color[i] ;
										 else
											 colores = color[i] ;
					        	 	else
					        			 colores += color[i] + ", " ;
					         	}
						}
			        	 %>
						<strong>Producto pedido:</strong> Cupcakes. <br>
			    		<strong>Tama&ntilde;o:</strong> <%= pedido.getTamano() %>.<br>
			    		<% if (pedido.getCantidad() != null && pedido.getCantidad().contains("docena en caja")){ %>
			     			<strong>Cantidad:</strong>  1 Docena en caja para regalar.<br>
			     			<strong>Texto de la Calmon&iacute;a:</strong>  <%= pedido.getCalcomania() %>.<br>
			     		<% }else{%>
			     			<strong>Cantidad:</strong>  <%= pedido.getCantidadDocenas() %> Unidades.<br>
			     		<% }%>
			    		<strong>Sabor(es) de los cupcakes:</strong> <%= pedido.getSabor() %>.<br>
			     		<strong>Cubierta(s):</strong>  <%= pedido.getCubiertas() %>.<br>
			     		<% if (!colores.equals("")) { %>
			     		<strong>Color(es):</strong>  <%= colores %><br>
			     		<% } %>
			    	</div>
					<div class="total"> Total: <%= pedido.getPrecio() %>.<br></div><br>
			   </div>
			   <div class="dt-buttonInline">
					<input type="button" name="sbmtButton" class="buttonR" value="Cancelar" onclick="location.href='http://www.designyourcakes.com/CupcakesServlet?typeId=2';"  />
					<input type="submit" name="sbmtButton" class="buttonInline" value="Ordenar"  />
				</div> 
			</form>
			</div>
			<% 
				}else{
					String error = (String) request.getAttribute("error");
					if (error.equals("")){
			%>
			<div id="datosVerif" >
				<div class="bienv">
					<span class="bienv-title">&iexcl; Muchas gracias!.</span><br><br>
						Hemos recibido su pedido, en los pr&oacute;ximos d&iacute;as lo
						estaremos contactando para confirmar su compra y coordinar el env&iacute;o. 
						<br><br>

						Cualquier duda o inquietud lo invitamos a comunicarse con nosotros. <a href="ContactServlet"  class="readmore">Aqu&iacute;</a> 
						podr&aacute; encontrar nuestros datos.

						<br><br>

						&iexcl;Hasta pronto!

			   </div>
			</div>
			 <% }else{ %>
			   <div class="bienv">
					<span class="bienv-title">&iexcl; Le pedimos mil disculpas!.</span><br><br>
						Ha ocurrido un error al intentar procesar su solicitud. Lo invitamos a que intente m&aacute;s tarde. 
						En caso de que el error persista puede comunicarse con nuestro equipo de soporte, bien sea
						llam&aacute;ndonos directamente o mand&aacute;ndonos un correo. <a href="ContactServlet"  class="readmore">Aqu&iacute;</a> 
						podr&aacute; encontrar nuestros datos.

						<br><br>
			   </div>
			    <% } 
				}
			%>
		</div>
	</div>
	<div class="push"></div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
<jsp:include page="ventanas.jsp"></jsp:include>
</body>
</html>