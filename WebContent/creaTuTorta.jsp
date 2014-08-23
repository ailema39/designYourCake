<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="domain.StepOption"%>
<%@page import="domain.ListOrder_Step"%>
<%@page import="domain.OrderStep"%>
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
	<script type="text/javascript" src="js/tortas.js"></script>
</head>
<body>
<%
	HttpSession infoPage = request.getSession();
	session.setAttribute("prevPage", "ArmaTuTortaServlet?typeId=1");
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
				<li class="current"><a href="ArmaTuTortaServlet?typeId=1">Criar Bolo</a></li>
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
			<div class="sectionLeft">
				<div>
					<ul>
						<li>
							<div style="position: relative; left: -10px; top: -10px; background-color: black;">
								<img id="pasoImgBase" src="./images/paso_torta.png" alt="Image"  style="position: relative; top: 0; left: 0;" />
								<img id="pasoImgSabor" src="" width="160" height="170" alt="Image" 
									style="position: absolute;top:70px; left: 45px; display:none;"  />
								<img id="pasoImgCapas" src="" width="160" height="170" alt="Image" 
								style="position: absolute; top:70px; left: 45px; display:none;"  />
								<img id="pasoImgRelleno" src="" width="160" height="170" alt="Image"
								style="position: absolute;top:70px; left: 45px;  display: none;"/>
								<img id="pasoImgCubierta" src="./images/tortas/capa_general.png" width="160" height="170" alt="Image" 
									style="position: absolute; top:70px; left: 45px;  display:none;" />
							</div>	
						</li>
					</ul>
				</div>	
				<span style="font-size: 8.4px; font-family: Arial; ">Imagem de refer&#234;ncia</span>
			</div>
			<jsp:useBean id="options" type="java.util.ArrayList<domain.ListOrder_Step>" scope="request"/>  	
        		
			<% if (client != null){ %>
				<div class="titleTortas"> Siga os passos para criar seu bolo! </div>
			<% } %>
			
			<div class="asideRight">
				<% if (client != null){ %>
				<form id="target" action="ArmaTuTortaServlet?typeId=1&pr=1" method="post"
				 enctype="multipart/form-data" onSubmit="return setPrice()">
				<input type="hidden" id="priceCake"  name="priceCake" value="0">
					<%
							for(int i= 1; i<= options.size(); i++) { 	
								int aux = i -1;
								ListOrder_Step listO = options.get(aux);
								List<StepOption> actualOptions = listO.getOrderTypeId();
								OrderStep actualOrder = listO.getOrder();
								int isMultChoice = actualOrder.isMultipleChoice();
								String type = "radio";
								if (isMultChoice == 1)	type = "checkbox";
					%>
					<% if ( i == 1) { %>
					<div class="block">
						<p> <span class="step1"> Passo <%= i %>: </span> <span class="desc<%= i %>"> <%= actualOrder.getName() %> </span></p>
						<div class="options-steps">
								<% for(int j= 1; j<= actualOptions.size(); j++) {
									int aux2 = j - 1;
									StepOption step = actualOptions.get(aux2);
									String description = step.getDescription();
									String imagen = step.getImage();
									hashMap.put(i + "" +j, step.getName());
									hashMapPrice.put(i + "" +j, step.getPrice());
									hashMapId.put(i + "" +j, step.getId());
								%>
							
							<label><input  class="rdB<%= i %>" onClick="prueba('<%= imagen %>','<%= i %>', '<%= step.getId() %>', this);" type="<%= type %>" name="<%= i %>" value="<%= j %>" > <span id="name<%= i+ "" +j%>">  <%= step.getName()%></span> <br>
							<span style="display:none;" class="price-int1<%= i %><%= j %>"> <%= step.getPrice() %></span></label>
							<% }
							%>
						</div>
						<span style="display: inline;" class="buttons">
							<input  type="button" name="sbmtButtonPrev" class="buttonR" value="Começar de novo" />
							<input  type="button" name="sbmtButton" class="buttonDisable" value="Próxima" id="bt1Disable"  />
							<input  type="button" name="sbmtButton" class="button" value="Próxima"  style="display: none;" id="bt<%= i %>" />
						</span>						
					</div>
				<% 
					}else{
				
					if (i == 5){
				%>	
						<div class="block-<%=i%>" style="display:none">
							<p>
							<span class="step1"> Passo <%= i %>: </span> <span class="desc<%= i %>"> <%= actualOrder.getName() %> </span> 
							</p>
							<div class="options-steps">
								<div id="capa1" style="display:none">
									Capa 1: <select name="capa1"  id="selectCapa1"> 
									<option value="0">  Selecione Sabor
									<% for(int j= 1; j<= actualOptions.size(); j++) {
										int aux2 = j - 1;
										StepOption step = actualOptions.get(aux2);
										String imagen = step.getImage();
										hashMap.put(i + "" +j, step.getName());
										hashMapPrice.put(i + "" +j, step.getPrice());
										hashMapId.put(i + "" +j, step.getId());
											
									%>
									<option value="<%= j %>"> <%= step.getName()%>
									<% }
									%>
									</select>
									<span style="display:none;" class="price-intC10"> 0</span>
									<% for(int j= 1; j<= actualOptions.size(); j++) {
										int aux2 = j - 1;
										StepOption step = actualOptions.get(aux2);
									%>
									<span style="display:none;" class="price-intC11<%= j %>"> <%= step.getPrice() %></span>
                                    <span style="display:none;" class="price-intC21<%= j %>"> <%= step.getPriceTwo() %></span>
                                    <span style="display:none;" class="price-intC31<%= j %>"> <%= step.getPriceThree() %></span>
                                    <span style="display:none;" class="price-intC41<%= j %>"> <%= step.getPriceFour() %></span>
									<% }%>
								</div>
								<div id="capa2" style="display:none">
									Capa 2: <select name="capa2"  id="selectCapa2"> 
									<option value="0">  Selecione Sabor
									<% for(int j= 1; j<= actualOptions.size(); j++) {
										int aux2 = j - 1;
										StepOption step = actualOptions.get(aux2);
									%>
									<option value="<%= j %>"><%= step.getName()%>
									<% }
									%>
									</select>
									<span style="display:none;" class="price-intC20"> 0</span>
									<% for(int j= 1; j<= actualOptions.size(); j++) {
										int aux2 = j - 1;
										StepOption step = actualOptions.get(aux2);
									%>
								    <span style="display:none;" class="price-intC12<%= j %>"> <%= step.getPrice() %></span>
                                    <span style="display:none;" class="price-intC22<%= j %>"> <%= step.getPriceTwo() %></span>
                                    <span style="display:none;" class="price-intC32<%= j %>"> <%= step.getPriceThree() %></span>
                                    <span style="display:none;" class="price-intC42<%= j %>"> <%= step.getPriceFour() %></span>
									<% }%>
								</div>
								
								<div id="capa3" style="display:none">
									Capa 3: <select name="capa3" id="selectCapa3"> 
									<option value="0">  Selecione Sabor 
									<% for(int j= 1; j<= actualOptions.size(); j++) {
										int aux2 = j - 1;
										StepOption step = actualOptions.get(aux2);
										
											
									%>
									<option value="<%= j %>"><%= step.getName()%>
									<% }
									%>
									</select>
									<span style="display:none;" class="price-intC30"> 0</span>
									<% for(int j= 1; j<= actualOptions.size(); j++) {
										int aux2 = j - 1;
										StepOption step = actualOptions.get(aux2);
									%>
										<span style="display:none;" class="price-intC13<%= j %>"> <%= step.getPrice() %></span>
                                        <span style="display:none;" class="price-intC23<%= j %>"> <%= step.getPriceTwo() %></span>
                                        <span style="display:none;" class="price-intC33<%= j %>"> <%= step.getPriceThree() %></span>
                                        <span style="display:none;" class="price-intC43<%= j %>"> <%= step.getPriceFour() %></span>
									<% }%>
								</div>
							</div>
					
						
						<span style="display: inline;" class="buttons">
							<input  type="button" name="sbmtButtonPrev" class="buttonR" value="Começar de novo"  />
							<input  type="button" name="sbmtButton" class="buttonDisable" value="Próxima" id="bt<%= i %>Disable"  />
							<input  type="button" name="sbmtButton" class="button" value="Próxima"  style="display: none;" id="bt<%= i %>" />
						</span>	
						</div>						
					
				<%	}else{ %>	
				
					<div class="block-<%=i%>" style="display:none">
						<p>
						<span class="step1"> Passo <%= i %>: </span> <span class="desc<%= i %>"> <%= actualOrder.getName() %> </span> </p>
						<div class="options-steps">
								<% for(int j= 1; j<= actualOptions.size(); j++) {
									int aux2 = j - 1;
									StepOption step = actualOptions.get(aux2);
									hashMap.put(i + "" +j, step.getName());
									hashMapPrice.put(i + "" +j, step.getPrice());
									hashMapId.put(i + "" +j, step.getId());
									String imagen = step.getImage();	
									String description = step.getDescription();
                                    String classI = "div-sp";
                                    if (actualOptions.size() > 4){ classI = "options-steps-left";}
                                    
								%>
									<%
										if (step.getId() != 42){
									%>
										<div class="<%= classI %>">
											<label><input  class="rdB<%= i %>" onClick="prueba('<%= imagen %>','<%= i %>', '<%= step.getId() %>', this);" type="<%= type %>" name="<%= i %>" value="<%= j %>" > <span id="name<%= i+ "" +j%>">  <%= step.getName()%> </span></label>
											<span style="display:none;" class="price-int1<%= i %><%= j %>"> <%= step.getPrice() %></span>
                                            <span style="display:none;" class="price-int2<%= i %><%= j %>"> <%= step.getPriceTwo() %></span>
                                            <span style="display:none;" class="price-int3<%= i %><%= j %>"> <%= step.getPriceThree() %></span>
                                            <span style="display:none;" class="price-int4<%= i %><%= j %>"> <%= step.getPriceFour() %></span>
										</div>	
									<%
										}else{
									%>
										<div class="options-steps-especial">
											<label><input  class="rdB<%= i %>"  onClick="prueba('<%= imagen %>','<%= i %>', '<%= step.getId() %>', this);" type="<%= type %>" name="<%= i %>" value="<%= j %>" > <span id="name<%= i+ "" +j%>">  <%= step.getName()%> </span></label>
                                            <span style="display:none;" class="price-int1<%= i %><%= j %>"> <%= step.getPrice() %></span>
                                            <span style="display:none;" class="price-int2<%= i %><%= j %>"> <%= step.getPriceTwo() %></span>
                                            <span style="display:none;" class="price-int3<%= i %><%= j %>"> <%= step.getPriceThree() %></span>
                                            <span style="display:none;" class="price-int4<%= i %><%= j %>"> <%= step.getPriceFour() %></span>
											<input type="file" accept='image/*' name="txtImage" id="txtImage" maxlength="25" lang="es" style="display: none;" />  <br>
										</div>
									
									<%	
										}
								}
								%>
							
						</div>
				<% if (i != 6){%>
					<span style="display: inline;" class="buttons">
							<input  type="button" name="sbmtButtonPrev" class="buttonR" value="Começar de novo"  />
							<input  type="button" name="sbmtButton" class="buttonDisable" value="Próxima" id="bt<%= i %>Disable"  />
							<input  type="button" name="sbmtButton" class="button" value="Próxima"  style="display: none;" id="bt<%= i %>" />
					</span>		
					
				<% }else{%>
					<span style="display: inline;" class="buttons">
						<input  type="button" name="sbmtButtonPrev" class="buttonR" value="Começar de novo"  />
						<input  type="button" name="sbmtButton" class="buttonDisable" value="Próxima" id="bt<%= i %>Disable"  />
						<input  type="submit" name="sbmtButton" class="button" value="Ordenação"  style="display: none;" id="bt<%= i %>" />
					</span>		
				<% }%>
				</div>
				<%	} %>	
				
				<% 
					}
				}
				session.setAttribute("hashMap", hashMap);
				session.setAttribute("hashMapPrice", hashMapPrice);
				session.setAttribute("hashMapId", hashMapId);
			%>	
				</form>
			<%
			}else{ 
			%>
					<br>
					<div class="msg-inicio" style="text-align: justify;">
						Para criar o seu bolo e ter acesso a pre&#231;os voc&#234; precisa-se cadastrar. <br><br>
						
						Se voc&#234; j&#225; &#233; cadastrado, <a href="#signup"  rel="leanModal" id="go" class="readmore"> acesse aqui.</a> <br><br>
						
						Caso contrario, <a href="registro.jsp" class="readmore"> registre-se aqui.</a><br><br>						
						
						S&#243; vai levar um minuto! <br><br><br><br>
					</div>
				<% } %>
				
			</div>
			
			<% if (client != null){ %>
				<div class="subtotal-sectionTortas"> Sub-total: R$ <span class="price"> 0,00</span> </div>
			<% } %>
			<% if (client != null){ %>
			<div class="banner">
				<a href="./ocasionesEspeciales.jsp"><img src="./images/banner_tortas.png" alt="Image" /></a>
			</div>
			<% }else{ %>
			<div class="bannerTorta">
				<a href="./ocasionesEspeciales.jsp"><img src="./images/banner_tortas.png" alt="Image" /></a>
			</div>
			<% } %>
			
			
			
		</div>
	</div>
	<div class="push"></div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<jsp:include page="ventanas.jsp"></jsp:include>
</body>
</html>