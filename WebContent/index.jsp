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
</head>
<body >
<%
	HttpSession infoPage = request.getSession();
	session.setAttribute("prevPage", "HomePageServlet");
	
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
		String[] archivos = FilesName.getFilesNamesIndex(request, FilesName.INICIO);	
    %>	
	<div id="content">
        <div class="back"></div>
    		<div class="home">
    			<div class="aside">
                    <p><span class="dyc">Design Your Cake</span> nascido em Caracas, Venezuela h&aacute; 4 anos com a id&eacute;ia de combiar os melhores sabores,
                     com desenhos exclusivos e criados para cada pessoa. </p>
                    <p>Voc&#234; n&#227;o tem que se ajustar a um determinado tipo de bolo, voc&#234; pode criar o seu bolo favorito, com o sabor que voc&#234; 
                    gosta e todos os recheios que imagina! </p>
                    <p>Cada um dos nossos sabores &eacute; feito com os melhores ingredientes para alcan&#231;ar o melhor resultado, voc&#234; s&oacute; tem que imaginar,
                    n&oacute;s fazemos o resto. </p>
                    <p>Para n&oacute;s cada pessoa &eacute; &uacute;nica e, portanto, merecem um bolo &uacute;nico.</p>
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