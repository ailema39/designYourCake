<!DOCTYPE html>
<%@page import="domain.Client"%>
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
			<div id="datosVerif" > 
				<% String error = (String) request.getAttribute("error");
					if (error.equals("")){
				%>
				<div class="bienv">
					<span class="bienv-title">Muito obrigado!. </span><br/><br/>
                    Recebemos o seu mensagem, O mais breve poss&#237;vel entraremos em contato para oferecer um or&#231;amento. <br /><br />
                    Qualquer d&#250;vida voc&#234; pode entrar em contato conosco. 
                    <a href="ContactServlet"  class="readmore">Aqui</a> voc&#234; pode encontrar os nossos dados. 
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