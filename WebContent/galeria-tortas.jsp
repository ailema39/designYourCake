<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="domain.Album"%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Design Your Cake</title>
	<link href='http://fonts.googleapis.com/css?family=Handlee' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Economica' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link rel="stylesheet" type="text/css" href="css/styleImage.css" />
	<link rel="stylesheet" type="text/css" href="css/styleCar.css" />
	<link rel="stylesheet" type="text/css" href="css/gridNavigation.css" />
	

	<link rel="shortcut icon" href="images/ico.ico">
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
	
	<script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
	<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$("a[rel*=leanModal]").leanModal({ top : 200, overlay : 0.4, closeButton: ".modal_close" });
	});
	
	
	</script>
	
	<link rel="stylesheet" type="text/css" href="css/shadowbox.css">
	<script type="text/javascript" src="js/shadowbox.js"></script>
	<script type="text/javascript">
		Shadowbox.init({
			handleOversize: "drag",
			modal: true
	});
	</script>
	
	<script type="text/javascript" src="js/jquery.gridnav.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#tj_container').gridnav({
				type	: {
					mode		: 'sequpdown', 	// use def | fade | seqfade | updown | sequpdown | showhide | disperse | rows
					speed		: 500,			// for fade, seqfade, updown, sequpdown, showhide, disperse, rows
					easing		: '',			// for fade, seqfade, updown, sequpdown, showhide, disperse, rows	
					factor		: 100,			// for seqfade, sequpdown, rows
					reverse		: ''			// for sequpdown
				}
			});
		});
	</script>
	<script type="text/javascript" charset="utf-8">
		function inhabilitar(){ 
		   	return false;
		} 
		document.oncontextmenu=inhabilitar;
	</script>
	<script>
		jQuery(window).load(function(){
			jQuery('#loading').fadeOut(500);
			jQuery('#loadingMsg').fadeOut(500);
		});
	</script>
	<style type="text/css">
		#loading {
			background: url(../images/ajax-loader.gif) no-repeat center center;
			height: 50px;
			width: 50px;
			position: fixed;
			left: 50%;
			top: 50%;
			margin: 10px 0 0 -25px;
			z-index: 1000;
		}
	</style>
</head>
<body oncontextmenu="return inhabilitar()">
<jsp:useBean id="photos" type="java.util.ArrayList<domain.Photo>" scope="request"/>  	
    
<%
	HttpSession infoPage = request.getSession();
	session.setAttribute("prevPage", "GalleryCakesServlet?albumId=" + photos.get(0).getAlbumId());
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
				<li class="current"><a href="GalleryServlet">Portafolio</a></li>
				<li><a href="ContactServlet">Contato</a></li>
			</ul>
		</div>
	</div>
	<div id="content-aux">
		<div id="loading"></div>
		<div class="title-img"><a href="GalleryServlet"> <img src="images/return.png"> </a> Bolos </div>
	    <% 	if (photos.size() > 0){ %>
        	<div class="content example3">
				<div id="tj_container" class="tj_container">
					<div class="tj_nav">
						<span id="tj_prev" class="tj_prev">Previous</span>
						<span id="tj_next" class="tj_next">Next</span>
					</div>
					<div class="tj_wrapper">
						<ul class="tj_gallery">
							<%
									for(domain.Photo p : photos) { 											
									String src = "files/" + Album.getDirectory(p.getAlbumId()) + "/" + p.getImage();
							%>
							<li><a href="<%= src %>" rel="shadowbox[Cakes];height=340;width=340">
								<img src="<%= src %>" alt="image01"   style="width:180px; height:155px;" />
								</a>
							</li>
							<% 
								}
							%>
							
							
						</ul>
					</div>
				</div>
			</div>
	<% 
	}else{
	%>	
		<br> <span style="margin-left: 30px">No momento n&#227;o existem fotos para mostrar. Tente novamente mais tarde.</span>
	<% } %>
	</div>
	<div class="push"></div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<jsp:include page="ventanas.jsp"></jsp:include>
</body>
</html>