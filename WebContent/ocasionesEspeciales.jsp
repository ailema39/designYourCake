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
</head>
<body>
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
					<a href="HomePageServlet"><img src="images/logo5.png" alt="Logo"/></a>
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
			<% if (client != null){ %>
			<div class="coments-ocEsp">
                <div class="titleTortas">Ocasi&#245;es Especiais</div>
				<form name="ocasEspForm" action="OcasionesEspecialesServlet" method="post" enctype="multipart/form-data" onsubmit="return ocaEsp();" >
					<fieldset>
						<label for="name">Qual &eacute; sua ocasião especial?:</label>
						<input type="text" name="txtName" id="txtName" size="25" maxlength="50" />
						<span class="error" id="errorName">Por favor, digite seu ocasião especial.</span>
						<div style="font-size: 10px; font-family: Arial;">Exemplo: casamento, aniversário.</div><br/>
						<label for="name" style="height:60px;">Quais os produtos que gostaria de pedir?:</label>
						<input type="checkbox" name="eventoT" id="eventoT" value="1"/>Bolo<br/>
						<input type="checkbox" name="eventoC" id="eventoC" value="2"/>Mesa de doces<br/>
						<span class="error" id="errorProducto">Por favor, selecione pelo menos um produto.</span><br/><br/>
						<label for="name">¿Já tem um desenho que você gosta? Anexá-lo aquí::</label>
						<input type="file" name="txtImage" id="txtImage" maxlength="50" lang="es" /> <br/><br/><br/>
						<label for="name"  >Número de convidados:</label>
						<input type="text" name="txtInv" id="txtInv" size="15" maxlength="50" /> <br/>
						<span class="error" id="errorInv">Por favor, digite número de convidados:</span>
                        <div style="font-size: 10px; font-family: Arial;">Exemplo: 5, 25...</div><br/>
						<label for="name">Conte-nos todos os detalhes que você quiser:</label>
						<textarea name="idea" style="resize: none;"></textarea> <br/>
					</fieldset>
					<div class="ocEsp-button">
							<input type="submit" name="sbmtButton" class="button" value="Pedir Orçamento"  />
					</div>
					</form>
			</div>
			<% }else{ %>
					<br><br>
					<div style="text-align: justify; margin-left: 30px;">
						Desculpe, para usar esta se&#231;&#227;o voc&#234; precisa estar cadastrado. <br/><br/>
						
						Se voc&#234; n&#227;o estiver cadastrado, <a href="registro.jsp" class="readmore"> cadastre-se aqu&#237;.</a> <br/><br/>
						
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