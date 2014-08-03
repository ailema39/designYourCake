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
	String error = (String) request.getAttribute("emailExist");
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
			<%
				if (error != null && error == ""){
			%>
				<span class="bienv-title">Bem-vindo a Design Your Cake! </span><br><br>
				Seu cadastro terminou com sucesso. A partir deste ponto, voc&#234; pode 
				desfrutar de todos os nossos servi&#231;os. Para fazer isso, fa&#231;a o 
				login no topo da p&#225;gina web, dando-lhe clicar em &quot;Entrar&quot; 
				ou clique  <a href="#signup"  rel="leanModal" id="go" class="readmore"> aqui</a> dando
				<br><br>
				PD: Enviamos um e-mail com informa&#231;&#245;es de sua conta.
				<br><br>
				Obrigado por nos escolher!
				
			<% }else if(!error.equals("no se creo")){ 
				String name = (String) request.getAttribute("name");
			%>
					<span class="bienv-title">Lamentamos, mas o e-mail que tenta registar j&#225; existe.</span><br><br>
					O e-mail que voc&#234; est&#225; tentando registrar j&#225; est&#225; listado no nosso sistema. 
					Se voc&#234; esquecer sua senha, voc&#234; pode <a href="PasswordGeneratorServlet?email=<%= error %>&name=<%= name %>" 
					class="readmore"> clicar aqui</a>
					 e n&#243;s lhe enviaremos uma nova senha para seu e-mail para que voc&#234; possa continuar a desfrutar de nossos servi&#231;os.
					<br><br>
					Obrigado por nos escolher!

			<% }else{ 
			%>
					<span class="bienv-title">¡Te pedimos disculpas!</span><br><br>
					Ha ocurrido un error al intentar registrar su cuenta en el sistema. Por favor, intente m&aacute;s tarde. 
					De persistir el problema lo invitamos a comunicarse con nosotros para transmitirnos el error.
					
					<br><br>
					¡Muchas gracias por elegirnos!

			<% }%>
			
			</div>
		</div>
	</div>
	<div class="push"></div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
<jsp:include page="ventanas.jsp"></jsp:include>
</body>
</html>