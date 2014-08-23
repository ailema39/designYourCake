<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Design Your Cake</title>
	<link rel="shortcut icon" href="images/ico.ico">
	<link href='http://fonts.googleapis.com/css?family=Handlee' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Economica' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/messages.js"></script>
	<script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
	<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$("a[rel*=leanModal]").leanModal({ top : 200, overlay : 0.4, closeButton: ".modal_close" });
	});
	</script>
</head>
<body >
<%
	HttpSession infoPage = request.getSession();
	session.setAttribute("prevPage", "contacto.jsp");
	String mensajeExitoso = (String) request.getAttribute("mensaje");
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
				<li><a href="underConstruction.jsp">Criar Bolo</a></li>
				<li><a href="DulcesTortasServlet?typeId=3">Doces</a></li>
				<li><a href="underConstruction.jsp">Criar Cupcakes</a></li>
				<li><a href="underConstruction.jsp">Ocasi&#245;es Especiais</a></li>
				<li><a href="GalleryServlet">Portafolio</a></li>
				<li class="current"><a href="ContactServlet">Contato</a></li>
			</ul>
		</div>
	</div>
	<div id="content">
		<div class="home">
			<div class="aside">
				<div class="title"> Informa&#231;&#245;es de Contato: </div>
				<div class="title-ins"> Voc&#234; pode entrar em contato conosco diretamente: </div>
				
				<div  style="margin-left: 30px;">
					<img src="images/email.png" width="32" height="32" style="padding-top: 5px;"  alt="Logo"/> E-mail: designyourcake.br@gmail.com<br><br>
					Moema - Sao Paulo<br>
					Brasil<br>
					Loja online<br>
				</div>
			</div>
			<div class="section-contact">
						<span style="font-size: 18px;"> Envie aqui sua mensagem.</span><br>
						<% if (mensajeExitoso != null){ %>
							<span style="color:red;"> <%= mensajeExitoso %></span>
						<% } %>
						<form name="contactForm" action="ContactServlet" method="post" onsubmit="return contact();"  >
						<fieldset>
								<label for="name">Nome (*):</label>
								<input type="text" name="txtName" id="txtName" size="35" maxlength="100" />
								<span class="error" id="errorName" style="float:right; margin-right:50px;">Desculpe, deve digitar o seu nome.</span>
								 <br>
								<label for="name">E-mail (*):</label>
								<input type="text" name="txtEmail" id="txtEmail" size="35"  maxlength="50" />
								<span class="error" id="errorEmail" style="float:right; margin-right:50px;">Desculpe, deve digitar um e-mail v&aacute;lido.</span> <br>
								<label for="name">Fone:</label>
								<input type="text" name="txtPhone" id="txtPhone" size="35" maxlength="50" /> <br>
								<label for="name">Mensagem (*):</label>
								<textarea name="txtMsg"  id="txtMsg" rows="4" cols="28" style="resize: none;"></textarea>
								
								<div class="txtCheck">
									<input type="checkbox" name="txtCheck"  value="1"> Envie uma c&#243;pia desta mensagem para sua e-mail <br>
								</div>
								<span class="error" id="errorMensaje" style="float:right; margin-right:50px;">Desculpe, debe digitar um coment&aacute;rio ou mensagem.</span>
							</fieldset>
							<div class="cont-button">
								<input type="submit" name="sbmtButton" class="button" value="Enviar"  />
							</div>
						</form>
			</div>
			</div>
		</div>
	<div class="push"></div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<jsp:include page="ventanas.jsp"></jsp:include>
</body>
</html>