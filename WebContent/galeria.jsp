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
	session.setAttribute("prevPage", "GalleryServlet");
	
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
				<li class="current"><a href="GalleryServlet">Portafolio</a></li>
				<li><a href="ContactServlet">Contato</a></li>
			</ul>
		</div>
	</div>
	<div id="content">
		<jsp:useBean id="albums" type="java.util.ArrayList<domain.Album>" scope="request"/>  	
        		
		<div class="home">
			<div class="titleGallery"> Produtos </div>
			<div class="section-img">
				<div >
					<ul>
						<li>
							<a href="GalleryCakesServlet?albumId=<%=albums.get(0).getId() %>">
								<img src="<%= "files/" + albums.get(0).getDirectory() + "/" + albums.get(0).getImage() %>" alt="Image" height="195" width="205" /></a>
							<%=  albums.get(0).getName() %>
						</li>
						
						<li>
							<a href="GalleryCupcakesServlet?albumId=<%=albums.get(1).getId() %>">
								<img src="<%= "files/" + albums.get(1).getDirectory() + "/" + albums.get(1).getImage() %>" alt="Image" height="195" width="205" /></a>
							<%=  albums.get(1).getName() %>

						</li>
						<li>
							<a href="GallerySpecialsCakesServlet?albumId=<%=albums.get(2).getId() %>">
								<img src="<%= "files/" + albums.get(2).getDirectory() + "/" + albums.get(2).getImage() %>" alt="Image" height="195" width="205" /></a>
							<%=  albums.get(2).getName() %>
							
						</li>
					</ul>
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