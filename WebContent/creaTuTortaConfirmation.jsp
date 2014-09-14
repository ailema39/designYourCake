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
            maxDate: "+3M"
		});
	});
	</script> 

	
	<script type="text/javascript" charset="utf-8">
		$(document).ready(function() {
			$("a[rel*=leanModal]").leanModal({ top : 200, overlay : 0.4, closeButton: ".modal_close" });
		});
	</script>
	<script type="text/javascript" src="js/messages.js"></script>
</head>
<body >
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
			<jsp:useBean id="pedido" type="domain.OrderCake" scope="request" />  

			<form action="ArmaTuTortaServlet?typeId=1&pr=2" method="post" id="confirm">
				<input type="hidden" name="clientId" id="clientId" value="<%= client.getId() %>">
				<input type="hidden" name="forma" id="forma" value="<%= pedido.getForma() %>">
				<input type="hidden" name="tam"  id="tam" value="<%= pedido.getTamano() %>">
				<input type="hidden" name="sabor"  id="sabor" value="<%= pedido.getSabor() %>">
				<input type="hidden" name="capas" id="capas" value="<%= pedido.getCapas() %>">
				<%
					if (pedido.getRelleno() != null){
						for (int j= 0; j < pedido.getRelleno().length; j++){
				%>
					<input type="hidden" name="relleno" value="<%= pedido.getRelleno()[j] %>">
				<%
						}
					}
				%>	
				<input type="hidden" name="cubierta" id="cubierta" value="<%= pedido.getCubiertas() %>">
				<input type="hidden" name="priceCake" id="priceCake" value="<%= pedido.getPrecio() %>">
				<input type="hidden" name="nombreImagen" id="nombreImagen" value="<%= pedido.getNombreImagen()==null ? "" : pedido.getNombreImagen() %>">

				<div class="bienv">
					<span class="bienv-title">Confira os detalhes do seu pedido.</span><br/><br/>
					Por favor, indique a data de entrega para o qual voc&#234; deseja seu pedido: <input type="text" readonly="readonly" id="datepicker" name="txtFecha" /><br />
			  		<span class="error" id="errorDate">Desculpe, voc&#234; precisa digitar uma data v&#225;lida da entrega</span><br/><br/>
		
					<div id="pedido">
						<%
							String rell = "";
							String[] relleno = pedido.getRelleno();
							if (relleno != null){
							     for (int i = 0; i< relleno.length; i++){
						        	 if (relleno.length != 1){
							        	 if (i == relleno.length - 1)
							        		 rell += "e " + relleno[i] ;
							        	 else
							        		 rell += relleno[i] + ", " ;
						        	 }else
						        		 rell += relleno[i] ;
						         }
							}
			        	 %>
						<strong>Produto encomendado:</strong> Bolo. <br/>
			    		<strong>Forma:</strong>  <%= pedido.getForma() %>.<br/>
			    		<strong>Tamanho:</strong> <%= pedido.getTamano() %>.<br/>
			     		<strong>Sabor da massa:</strong> <%= pedido.getSabor() %>.<br/>
			     		<strong>Número de camadas:</strong>  <%= pedido.getCapas() %>.<br/>
			     		<% if (pedido.getRelleno() != null){ %>
			     			<strong>Sabor(es) Camada(s):</strong>  <%= rell %>.<br/>
			     		<% } %>
			     		<strong>Decora&#231;&#227;o:</strong>   <%= pedido.getCubiertas() %>.<br/>
					</div>
					<div class="total"> Total: R$ <%= pedido.getPrecio() %><br/></div><br/>
     		   </div>
			   <div class="dt-buttonInline">
					<input type="button" name="sbmtButton" class="buttonR" value="Cancelar" onclick="location.href='http://www.designyourcakes.com/ArmaTuTortaServlet?typeId=1';"  />
					<input type="submit" name="sbmtButton" class="buttonInline" value="Encomendar"  />
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