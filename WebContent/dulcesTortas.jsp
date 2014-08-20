<%@page import="Util.FilesName"%>
<%@page import="java.util.HashMap"%>
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
	<script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
    <script src="js/jqbanner.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="css/jqbanner.css" />
	<script type="text/javascript" src="js/dulcesTortas.js"></script>
	<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$("a[rel*=leanModal]").leanModal({ top : 200, overlay : 0.4, closeButton: ".modal_close" });
	});
	</script>
</head>
<body>
<%
	HttpSession infoPage = request.getSession();
	session.setAttribute("prevPage", "DulcesTortasServlet?typeId=3");
	Client client = (Client) infoPage.getAttribute("client");
	HashMap<String, String> hashMap = new HashMap<String, String>();
	HashMap<String, Double> hashMapPrice = new HashMap<String, Double>();
	HashMap<String, Long> hashMapId = new HashMap<String, Long>();
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
				<li class="current"><a href="DulcesTortasServlet?typeId=3">Doces</a></li>
				<li><a href="CupcakesServlet?typeId=2">Criar Cupcakes</a></li>
				<li><a href="OcasionesEspecialesServlet">Ocasi&#245;es Especiais</a></li>
				<li><a href="GalleryServlet">Portafolio</a></li>
				<li><a href="ContactServlet">Contato</a></li>
			</ul>
		</div>
	</div>
	<div id="content">
		<form id="formDulcesTortas" action="ContactServlet" method="get" id="confirm">
		<input type="hidden" id="priceDulcCake" name="priceDulcCake" value = "0">
    	<%
    		String[] archivos = FilesName.getFilesNamesIndex(request);
        %>	
		<div class="home">
			<div class="aside">
				<div class="title"> Desfrutar de nossos mais deliciosos doces!</div>
				<jsp:useBean id="options" type="java.util.ArrayList<domain.StepOption>" scope="request"/>  	
        		<% if (client != null){ %>
				<div class="cakes">
                    <div class="st1">
    					<div class="dt-title">Pirulitos recheados</div>
                        Dois biscoitos, recheados com o sabor de sua escolha e coberto com chocolate.
    					<br/><br/>
    					<div class="dt-title">Cake Pops</div>
                        Delicioso bolo de chocolate, baunilha, red velvet ou cookie em forma de bolas, coberto de chocolate meio amargo ou branco.<br/><br/>
    					<div class="dt-title">Cake Shots</div>
                        Delicados doces e bolos apresentado em copinhos, uma op&#231;&#227;o exclente para decorar a sua mesa e experimentar um pouco de tudo. 
                    	<div><ul>
                            <li>Bolo duplo Chocolate</li>
                        	<li>Mouse de Maracuy&aacute;</li>
                        	<li>Torta de lim&#227;o</li>
                        	<li>Tiramis&uacute;</li>
                        	<li>Delicia de Morango</li>
                         </ul></div>
                     </div>
                    <div class="st2" style="display: none;">
    					<div class="dt-title">Bolo de Cenoura</div>
                        Con cobertura crocante ou cobertura de cream cheese.<br/><br/>
    					<div class="dt-title">Cheesecake de Oreo</div>
                        Uma mistura entre um cheesecake macio e biscoitos Oreo.
                        <div class="dt-button">
    						<input type="submit" name="sbmtButton" class="button" value="Pedir Or&#231;amento"  /><br />
                            <span class="span-inq">Indique que voc&#234; quer e n&oacute;s enviaremos o seu or&#231;amento.</span>
  					   </div> 
					</div>
                    <img src="../images/1407908056_prev.png" class="dt-prev" style="display: none;"/>
                    <img src="../images/1407908056_next.png" class="dt-next"/>
             	</div>	
				<% }else{ %>
					<br>
					<div style="text-align: justify; margin-left: 30px;">
						Desculpe, para aceder a esta sec&#231;&#227;o precisa de estar registado. <br><br>
						
						Nesta se&#231;&#227;o voc&#234; pode encomendar qualquer um dos nossos deliciosos bolos.<br><br>
						
						Se voc&#234; n&#227;o estiver cadastrado, <a href="registro.jsp" class="readmore"> cadastre-se aqui.</a> <br><br>
						
						Se voc&#234; j&#225; &#233; cadastrado, <a href="#signup"  rel="leanModal" id="go" class="readmore"> entre aqui.</a>
					</div>
				<% } %>
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
		</form>
	</div>
	<div class="push"></div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<jsp:include page="ventanas.jsp"></jsp:include>
</body>
</html>