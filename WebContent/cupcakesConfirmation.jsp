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
			minDate: +3,
            maxDate: "3M"
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
				<input type="hidden" name="cantidad"  id="cantidad" value="unidades">
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
					<span class="bienv-title">Confira os detalhes do seu pedido.</span><br><br>
				  	Por favor, Indique a data de entrega que voc&#234; deseja seu pedido:<input type="text" readonly="readonly" id="datepicker" name="txtFecha" /><br />
			  		<span class="error" id="errorDate">Desculpe, voc� precisa digitar uma data v�lida da entrega</span><br/><br/>
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
						<strong>Produto encomendado:</strong> Cupcakes. <br>
			    		<strong>Tamanho:</strong> <%= pedido.getTamano() %>.<br>
			    		<% if (pedido.getCantidad() != null && pedido.getCantidad().contains("docena en caja")){ %>
			     			<strong>Quantidade:</strong>  1 Docena en caja para regalar.<br>
			     			<strong>Texto de la Calmon&iacute;a:</strong>  <%= pedido.getCalcomania() %>.<br>
			     		<% }else{%>
			     			<strong>Quantidade:</strong>  <%= pedido.getCantidadDocenas() %> Unidades.<br>
			     		<% }%>
			    		<strong>Sabor(es) dos Cupcakes:</strong> <%= pedido.getSabor() %>.<br>
			     		<strong>Cobertura(s):</strong>  <%= pedido.getCubiertas() %>.<br>
			     		<% if (!colores.equals("")) { %>
			     		<strong>Color(es):</strong>  <%= colores %>
			     		<% } %>
                        <% if (pedido.getDecoracion()!="") { %>
			     		- <strong>Decora&#231;&#227;o escolhida</strong> <%= pedido.getDecoracion() %><br>
			     		<% } %>
                        
			    	</div>
					<div class="total"> Total: R$ <%= pedido.getPrecio() %>.<br></div><br>
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
					<span class="bienv-title"> Muito obrigado!</span><br/><br/>
						Recebemos seu pedido nos pr&#243;ximos dias entraremos em contato para confirmar a compra e coordenar entrega.
						<br/><br/>

						Quaisquer d&#250;vidas ou preocupa&#231;&#245;es voc&#234; pode entrar em contato conosco. <a href="ContactServlet"  class="readmore">Aqui</a> voc&#234; pode encontrar os nossos dados. 
						<br/><br/>

						Design Your Cake!

			   </div>
			</div>
			 <% }else{ %>
			   <div class="bienv">
					<span class="bienv-title">Desculpe.</span><br/><br/>
						Ocorreu um erro ao processar seu pedido. N&#243;s convidamos voc&#234; a tentar mais tarde. 
						Se o erro persistir pode entrar em contato com nossa equipe de suporte directamente ou enviar um e-mail <a href="ContactServlet"  class="readmore">Aqui</a>.
						<br/><br/>
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