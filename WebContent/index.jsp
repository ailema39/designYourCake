<%@page import="Util.FilesName"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
	
	<script src="js/jqbanner.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="css/jqbanner.css" />
	
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
				<li class="current"><a href="HomePageServlet">Design Your Cake</a></li>
				<li><a href="underConstruction.jsp">Criar Bolo</a></li>
				<li><a href="underConstruction.jsp">Doces</a></li>
				<li><a href="underConstruction.jsp">Criar Cupcakes</a></li>
				<li><a href="underConstruction.jsp">Ocasi&#245;es especiais</a></li>
				<li><a href="GalleryServlet">Portafolio</a></li>
				<li><a href="ContactServlet">Contato</a></li>
			</ul>
		</div>
	</div>
	<%
		String[] archivos = FilesName.getFilesNamesIndex(request);;	
    %>	
	<div id="content">
		<div class="home">
			<div class="aside">
				<p>En <strong>Design Your Cake</strong> gostamos fazer a cozinha uma arte, utilizando a melhor mat&#233;ria-prima de primera qualidade e priorizando o sabor dos alimentos.</p>
				<p>Fazer bolos para todas as ocasi&#245;es com ingredientes, sabores e desenhos de sua escolha. O esp&#237;rito de <strong>Design Your Cake</strong> est&#225; no alimento como um estilo de vida ea 
				transmiss&#227;o de que o prazer em cada um dos nossos produtos.</p>
				<p>Escolha a forma, seus sabores favoritos, recheado e a cobertura que voc&#234; gosta... tudo ao seu gosto, n&#243;s vamos fazer o resto! &nbsp;<a href="underConstruction.jsp" class="readmore"> Solicite aqui o seu pedido!</a></p>
			</div>
			<div class="section">
				<div id="jqb_object">
					<div class="jqb_slides">
						<% for (int i = 0 ; i < archivos.length; i++){
							String src = "./images/galeria_inicio/" + archivos[i];
						%>
							<div class="jqb_slide" title="slide title" ><a href="GalleryServlet"><img src="<%= src %>"  height="220" width="340"/></a></div>
						<% } %>
					</div>
					
					<div class="jqb_bar">
						<div id="btn_next" class="jqb_btn jqb_btn_next"></div>
						<div id="btn_pauseplay" class="jqb_btn jqb_btn_pause"></div>
						<div id="btn_prev" class="jqb_btn jqb_btn_prev"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="push"></div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<jsp:include page="ventanas.jsp"></jsp:include>
</body>
</html>