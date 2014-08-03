<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="domain.Client "%> 
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Design Your Cake</title>
	<link href='http://fonts.googleapis.com/css?family=Handlee' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Economica' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link rel="shortcut icon" href="images/ico.ico">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/messages.js"></script>
	<script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
	<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$("a[rel*=leanModal]").leanModal({ top : 200, overlay : 0.4, closeButton: ".modal_close" });
	});
	</script>
	
	<link rel="stylesheet" type="text/css" href="css/demos.css" />
	<link rel="stylesheet" type="text/css" href="css/ui.theme.css" />
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
		function inhabilitar(){ 
		   	return false;
		} 
		document.oncontextmenu=inhabilitar;
	</script>
</head>
<body oncontextmenu="return inhabilitar()">
<%
	HttpSession infoPage = request.getSession();
	session.setAttribute("prevPage", "ocasionesEspeciales.jsp");
	Client client = (Client) infoPage.getAttribute("client");
	
%>
<div class="wrapper">
	<div id="header">
		<div>
			<div>
				<div id="logo">
					<a href="index.html"><img src="images/logo5.png" alt="Logo"/></a>
				</div>
				<jsp:include page="header.jsp"></jsp:include>
			</div>
			<ul style="margin: 0px; ">
				<li><a href="HomePageServlet">Design Your Cake</a></li>
				<li><a href="ArmaTuTortaServlet?typeId=1">Criar Bolo</a></li>
				<li><a href="DulcesTortasServlet?typeId=3">Doces</a></li>
				<li><a href="CupcakesServlet?typeId=2">Criar Cupcakes</a></li>
				<li class="current"><a href="OcasionesEspecialesServlet">Ocasi&#245;es Especiais</a></li>
				<li><a href="GalleryServlet">Portafolio</a></li>
				<li><a href="ContactServlet">Contato</a></li>
			</ul>
		</div>
	</div>
	<div id="content">
		<div class="home">
			<div class="title-ocEsp"> Tenha em mente um desenho especial para um bolo? Quer criar cupcakes especiais? <br>
				Voc&#234; est&#225; no lugar certo! Escreva a sua ideia, envie-nos imagens de refer&#234;ncia que voc&#234; deseja usar e deixe-nos fazer o resto.
			</div>
			<% if (client != null){ %>
			<div class="coments-ocEsp">
				<form name="ocasEspForm" action="OcasionesEspecialesServlet" method="post" enctype="multipart/form-data" onsubmit="return ocaEsp();" >
					<fieldset>
						<label for="name">¿Cu&aacute;l es tu ocasi&oacute;n especial?:</label>
						<input type="text" name="txtName" id="txtName" size="25" maxlength="50" />
						<span class="error" id="errorName">Por favor, introduzca su ocasión especial.</span>
						<div style="font-size: 10px; font-family: Arial;">Ejemplo: boda, bautizo, entre otros. </div><br>
						<label for="name" style="height:60px;">¿Cu&aacute;les de nuestros productos deseas ordenar?:</label>
						<input type="checkbox" name="eventoT" id="eventoT" value="1">Torta<br>
						<input type="checkbox" name="eventoC" id="eventoC" value="2">Cupcakes <br>
						<input type="checkbox" name="eventoG" id="eventoG" value="3">Gelatina 
						<span class="error" id="errorProducto">Por favor, seleccione al menos uno producto.</span><br><br>
						<label for="name">¿Tienes un dise&ntilde;o en mente? C&aacute;rgalo aqu&iacute;:</label>
						<input type="file" name="txtImage" id="txtImage" maxlength="50" lang="es" /> <br><br><br>
						<label for="name"  >N&uacute;mero apr&oacute;ximado de invitados:</label>
						<input type="text" name="txtInv" id="txtInv" size="15" maxlength="50" /> <br>
						<span class="error" id="errorInv">Por favor, ingrese un número estimado de invitados</span><br>
						<label for="name">Cu&eacute;ntanos m&aacute;s acerca de tu idea:</label>
						<textarea name="idea" style="resize: none;"></textarea> <br>
					</fieldset>
					<div class="ocEsp-button">
							<input type="submit" name="sbmtButton" class="button" value=" Solicitar Presupuesto "  />
					</div>
					</form>
			</div>
			<% }else{ %>
					<br><br>
					<div style="text-align: justify; margin-left: 30px;">
						Desculpe, para usar esta se&#231;&#227;o voc&#234; precisa estar cadastrado. <br><br>
						
						Se voc&#234; n&#227;o estiver cadastrado, <a href="registro.jsp" class="readmore"> cadastre-se aqu&#237;.</a> <br><br>
						
						Se voc&#234; j&#225; &#233; cadastrado, <a href="#signup"  rel="leanModal" id="go" class="readmore"> entre aqu&#237;.</a>
					</div>
				<% } %>
			
			
			
		</div>
	</div>
	<div class="push"></div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<jsp:include page="ventanas.jsp"></jsp:include>
</body>
</html>