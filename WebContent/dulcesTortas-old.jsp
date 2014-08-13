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
	<link rel="stylesheet" type="text/css" href="css/demos.css" />
	<link rel="stylesheet" type="text/css" href="css/ui.theme.css" />
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	
	<link rel="shortcut icon" href="images/ico.ico">
	<!--[if IE 8]>
		<link rel="stylesheet" type="text/css" href="css/ie8.css" />
	<![endif]-->
	<!--[if IE 7]>
		<link rel="stylesheet" type="text/css" href="css/ie7.css" />
	<![endif]-->
	<!--[if IE 6]>
		<link rel="stylesheet" type="text/css" href="css/ie6.css" />
	<![endif]-->
	
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
	<script type="text/javascript" src="js/dulcesTortas.js"></script>
	<script type="text/javascript" src="js/ui.core.js"></script>
	<script type="text/javascript" src="js/ui.datepicker.js"></script>
	<script> 
	$(function() {
		$("#datepicker").datepicker({minDate: +1});
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
		<form id="formDulcesTortas" action="DulcesTortasServlet" method="post" id="confirm">
		<input type="hidden" id="priceDulcCake" name="priceDulcCake" value = "0">
	
		<div class="home">
			<div class="aside">
				<div class="title"> Desfrutar de nossos mais deliciosos doces!</div>
				<jsp:useBean id="options" type="java.util.ArrayList<domain.StepOption>" scope="request"/>  	
        		<% if (client != null){ %>
				<div class="cakes">
					<table style="border:none;">
						<%
							for(int i= 1; i<= options.size(); i++) { 	
								int aux = i -1;
								domain.StepOption o = options.get(aux);
								hashMap.put(i +"", o.getName());
								hashMapPrice.put(o.getName(), o.getPrice());
								hashMapId.put(o.getName(), o.getId());
								String description = o.getDescription();
								String imgDescription = "";
								if (description != null && !description.equals(""))
									imgDescription = "<img  id=\"imgDulcesTortas\" src=\"./images/question.png\" title=\"" +  description  + "\"/>";
						%>
						<tr height="28">
							<td width="220px"> 
								<input type="checkbox" name="dulcesTortas" class="dulcesTortasCheck" value="<%= i %>" > <%= o.getName() %> <%= imgDescription %></td>
							<td width="100px">Bs.<span class="price-int<%= i %>"> <%= o.getPrice() %></span> </td>
							<td>
								<div class="sel<%= i %>" style="display:none">
								Cantidad: &nbsp;
								<select class="selDulcesTortas<%= i %>" name="selDulcesTortas<%= i %>"> 
									<option value="0"> - </option>
									<% for(int j = 1; j < 7; j++){ %>
									<option value="<%= j %>"><%= j %></option>
									<% } %>
								</select>
								</div>
								
							</td>
						</tr>
						<% }
						session.setAttribute("hashMapDulcesTortas", hashMap);
						session.setAttribute("hashMapPriceDulcesTortas", hashMapPrice);
						session.setAttribute("hashMapIdDulcesTortas", hashMapId);
						%>
					</table>
					<br>
					<div class="dt-button" style="display: none">
						<input type="submit" name="sbmtButton" class="button" value="Ordenar"  />
					</div> 
					<div class="dt-button-dis">
						<input type="button" name="sbmtButton" class="buttonDisable" value="Ordenar"  />
					</div> 
					<div class="subtotal-section"> Sub-total: Bs. <span class="price" id="priceTotal"> 0,00</span> </div><br>
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
			
			<div class="sectionDT">
				<div>
					<ul>
						<li>
							<img src="./images/imagen.png" alt="Image" />
						</li>
					</ul>
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