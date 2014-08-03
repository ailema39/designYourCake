<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
	<script type="text/javascript" src="js/messages.js"></script>
	<script type="text/javascript" charset="utf-8">
		$(document).ready(function() {
			$("a[rel*=leanModal]").leanModal({ top : 200, overlay : 0.4, closeButton: ".modal_close" });
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
	session.setAttribute("prevPage", "HomePageServlet");
	String error = (String) request.getAttribute("error");
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
				<li><a href="underConstruction.jsp">Criar Bolo</a></li>
				<li><a href="underConstruction.jsp">Doces</a></li>
				<li><a href="underConstruction.jsp">Criar Cupcakes</a></li>
				<li><a href="underConstruction.jsp">Ocasi&#245;es Especiais</a></li>
				<li><a href="GalleryServlet">Portafolio</a></li>
				<li><a href="ContactServlet">Contato</a></li>
			</ul>
		</div>
	</div>
	<div id="content">
		<div class="home">
			<div class="bienv">
				<% if (error.equals("")) { %>
					<span class="bienv-title">Enviamos um e-mail!</span><br><br>
					Entre na sua e-mail e verifica a nova senha que lhe envi&#225;mos para entrar em sua conta. <br><br>
					Depois de ter introduzido o nosso sistema, voc&#234; pode facilmente mudar essa senha, se voc&#234; 
					deseeas, entrando: <strong>Minha Conta</strong> e clicar na op&#231;&#227;o de  <strong>Altera&#231;&#227;o de Senha</strong>.
					 
					<br><br>
					Obrigado por nos escolher!.
				<% }else{ %>
					<span class="bienv-title">¡Te pedimos disculpas!</span><br><br>
					Ha ocurrido un error al intentar generar tu nueva contrase&ntilde;a. Por favor, intente m&aacute;s tarde. 
					De persistir el problema lo invitamos a comunicarse con nosotros para transmitirnos el error.
					
					<br><br>
					¡Muchas gracias por elegirnos!. 
				<% } %>
			</div>
		</div>
	</div>
	<div class="push"></div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
<jsp:include page="ventanas.jsp"></jsp:include>
</body>
</html>