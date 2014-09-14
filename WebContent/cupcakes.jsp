<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="domain.ListOrder_Step"%>
<%@page import="domain.OrderStep"%>
<%@page import="domain.StepOption"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="domain.Client "%> 
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<title>Design Your Cake</title>
	<link href='http://fonts.googleapis.com/css?family=Handlee' rel='stylesheet' type='text/css'/>
	<link href='http://fonts.googleapis.com/css?family=Economica' rel='stylesheet' type='text/css'/>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link rel="shortcut icon" href="images/ico.ico"/>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
	<script type="text/javascript" src="js/cupcakes.js"></script>
</head>
<body>
<%
	HttpSession infoPage = request.getSession();
	session.setAttribute("prevPage", "CupcakesServlet?typeId=2");
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
				<li><a href="DulcesTortasServlet?typeId=3">Doces</a></li>
				<li  class="current"><a href="CupcakesServlet?typeId=2">Criar Cupcakes</a></li>
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
								<img id="pasoImgBase" src="./images/paso_cupcakes.png" />
								<!-- Sabores ponqué -->	
								<img id="pasoImgSabor" src="" width="120" height="183" alt="Image" 
									style="position: absolute; top:55px; left: 65px; display:none;"  />
								<!-- Sabores ponqué surtidos -->	
								<img id="pasoImgSabor1" src="" width="90" height="143" alt="Image" 
									style="position: absolute; top:55px; left: 5px; display:none;"  />
								<img id="pasoImgSabor2" src="" width="90" height="143" alt="Image" 
									style="position: absolute; top:55px; left: 145px;display:none; "  />
								<img id="pasoImgSabor3" src="" width="120" height="183" alt="Image" 
									style="position: absolute; top:55px; left: 65px;display:none; "  />
								<!-- Cubiertas -->
								<img id="pasoImgCubierta" src="" width="120" height="183" alt="Image" 
									style="position: absolute; top:55px; left: 65px; display:none;"  />
								<!-- Cubiertas Surtidas -->
								<img id="pasoImgCubierta1" src="" width="90" height="143" alt="Image" 
									style="position: absolute; top:55px; left: 5px; display:none;"  />
								<img id="pasoImgCubierta2" src="" width="90" height="143" alt="Image" 
									style="position: absolute; top:55px; left: 145px; display:none;"  />
								<img id="pasoImgCubierta3" src="" width="120" height="183" alt="Image" 
									style="position: absolute; top:55px; left: 65px; display:none;"  />
								<!-- Colores Cubiertas -->
								<img id="pasoImgColor" src=""width="120" height="183"alt="Image"
								style="position: absolute; top:55px; left: 65px;  display: none;"/>
								<!-- Colores Cubiertas Surtidas -->
								<img id="pasoImgColor1" src=""width="90" height="143"alt="Image"
								style="position: absolute; top:55px; left: 5px;  display: none;"/>
								<img id="pasoImgColor2" src=""width="90" height="143"alt="Image"
								style="position: absolute; top:55px; left: 145px;  display: none;"/>
								<img id="pasoImgColor3" src=""width="120" height="183"alt="Image"
								style="position: absolute; top:55px; left: 65px;  display: none;"/>
								<!-- Decoracion -->							
								<img id="pasoImgDecoracion" src="" width="120" height="183" alt="Image" 
									style="position: absolute; top:55px; left: 65px; display:none;" />
								<!-- Decoracion surtida -->							
								<img id="pasoImgDecoracion1" src="" width="90" height="143" alt="Image" 
									style="position: absolute; top:55px; left: 5px; display:none;" />
								<img id="pasoImgDecoracion2" src="" width="90" height="143" alt="Image" 
									style="position: absolute; top:55px; left: 145px; display:none;" />
								<img id="pasoImgDecoracion3" src="" width="120" height="183" alt="Image" 
								style="position: absolute; top:55px; left: 65px; display:none;" />
							</div>	
						</li>
					</ul>
				</div>	
				<span style="font-size: 8.4px; font-family: Arial; ">Imagem de refer&#234;ncia</span>
		
			</div>
			<% if (client != null){ %>
				<div class="titleTortas">Siga e invente os cupcakes que você quiser! </div>
			<% } %>
				
			<jsp:useBean id="options" type="java.util.ArrayList<domain.ListOrder_Step>" scope="request"/>  	
        
			
			<div class="asideRight">
				<% if (client != null){ %>
					<form id="target" action="CupcakesServlet?typeId=2&pr=1" method="post" onSubmit="return setPrice()">
					<input type="hidden" id="priceCake"  name="priceCake" value="0">
					<%
							String [] color = new String[3];
							String [] saborP = new String[3];
							String [] sabor  = new String[3];
							String [] id  = new String[3];
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
						<p> <span class="step1"> Passo <%= i %>: </span>  <span class="desc<%= i %>"> <%= actualOrder.getName() %> </span> </p>
						<div class="options-steps">
								<% for(int j= 1; j<= actualOptions.size(); j++) {
									int aux2 = j - 1;
									StepOption step = actualOptions.get(aux2);
									String description = step.getDescription();
									String imgDescription = "";
									if (description != null && !description.equals(""))
										imgDescription = "<img  id=\"imgFudge\" src=\"./images/question.png\" title=\"" +  description  + "\"/>";
									String imagen = step.getImage();
									hashMap.put(i + "" +j, step.getName());
									hashMapPrice.put(step.getName(), step.getPrice());
									hashMapId.put(step.getName(), step.getId());
								
								%>
							<label><input  class="rdB<%= i %>" onClick="prueba('<%= imagen %>','<%= i %>', '<%= step.getId() %>', this);" type="<%= type %>" name="<%= i %>" value="<%= j %>" ><span id="name<%= i+ "" +j%>">  <%= step.getName()%><%= imgDescription %></span></label>
							<span style="display:none;" class="price-int1<%= i %><%= j %>"> <%= step.getPrice() %></span>
                            <span style="display:none;" class="price-int2<%= i %><%= j %>"> <%= step.getPriceTwo() %></span>
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
					}else if (i==2){ %>
						<div class="block-<%=i%>" style="display:none">
						<p>
						<span class="step1"> Passo <%= i %>: </span> <span class="desc<%= i %>"> <%= actualOrder.getName() %> </span> </p>
						<div class="options-steps">
								<% for(int j= 1; j<= actualOptions.size(); j++) {
									int aux2 = j - 1;
									StepOption step = actualOptions.get(aux2);
									String imagen = step.getImage();
									
									hashMap.put(i + "" +j, step.getName());
									hashMapPrice.put("unidades", step.getPrice());
									hashMapId.put("unidades", step.getId());
									
									boolean regalo = false;
									boolean cantidad = false;
									// pendiente de este id y ver si cambia
									if (step.getId() == 45){
										regalo = true;
									}else if (step.getId() == 46){
										cantidad = true;
									}
									String div = "";
									if (actualOptions.size() > 4){	div = "options-steps-left"; }
								%>
										<div class="<%= div %>">
                                           	<label><input  class="rdB<%= i %>"  type="<%= type %>" name="<%= i %>" value="<%= j+1 %>" > <span id="name<%= i+ "" +j%>">  <%= step.getName()%></span>
											<span style="display:none;" class="price-int1<%= i %><%= j+1 %>"> <%= step.getPrice() %></span>
											<span style="display:none;" class="price-int2<%= i %><%= j+1 %>"> <%= step.getPriceTwo() %></span>
											<% if (regalo){  %>
											<div id="calcomania" style="display:none;">
												<span style="color: gray; font-size: 16px; margin-left: 15px;"> Coloca el texto de tu calcoman&iacute;a aqu&iacute; </span>: 
												<input name="txtCalcomania" id="txtCalcomania" type="text" >
											</div>
											<% }else if (cantidad){ %>
												<select name="cantCupcakes" id="cantCupcakes" style="display: none;">
												<% 
													for (int k = 20 ; k<101 ; k += 10){
												%>
													<option value="<%= k %>"><%= k %></option>
												<% 
													}
												%>
											</select></label>
											<span style="color: gray; font-size: 16px; margin-left: 15px;">
												<br>
											</span>
											<% }else{ %>
											<br>
											<% }%>
										</div>	
								<%
								
									
								}
								%>
							
						</div>
						<span style="display: inline;" class="buttons">
							<input  type="button" name="sbmtButtonPrev" class="buttonR" value="Começar de novo" />
							<input  type="button" name="sbmtButton" class="buttonDisable" value="Próxima" id="bt<%=i%>Disable"  />
							<input  type="button" name="sbmtButton" class="button" value="Próxima"  style="display: none;" id="bt<%= i %>" />
						</span>		
					</div>
					<%
					}else{
					%>
							<div class="block-<%=i%>" style="display:none">
						<p>
						<span class="step1"> Passo <%= i %>: </span>  <%= actualOrder.getName() %> </p>
						<div class="options-steps">
								<% for(int j= 1; j<= actualOptions.size(); j++) {
									int aux2 = j - 1;
									StepOption step = actualOptions.get(aux2);
									String description = step.getDescription();
									String imgDescription = "";
									if (description != null && !description.equals(""))
										imgDescription = "<img  id=\"imgFudge\" src=\"./images/question.png\" title=\"" +  description  + "\"/>";
									String imagen = step.getImage();
									if (i == 5){
										if (aux2 < 3){
											color[aux2] = imagen;
											
										}
									}else if (i == 3){
										if (aux2 < 3){
											saborP[aux2] = imagen;
											
										}	
									}else if (i == 4){
										if (aux2 < 3){
											sabor[aux2] = imagen;
											id[aux2]= String.valueOf(step.getId());
										}	
									}
									hashMap.put(i + "" +j, step.getName());
									hashMapPrice.put(step.getName(), step.getPrice());
									hashMapId.put(step.getName(), step.getId());
                                    String input = " ";
                                    if (i == 5 && step.getName().contains("Pasta americana")){
                                        input = "<input type=\"text\" name=\"pamertext\" class=\"dc-text\" placeholder=\"Decora&#231;&#227;o\"/>";
                                    }
									if (actualOptions.size() > 4){
								%>
										<div class="options-steps-left">
											<label><input  class="rdB<%= i %>" onClick="prueba('<%= imagen %>','<%= i %>','<%= step.getId() %>', this );" type="<%= type %>" name="<%= i %>" value="<%= j %>" ><span id="name<%= i+ "" +j%>">  <%= step.getName()%><%= imgDescription %></span></label>
                                            <%= input %>
											<span style="display:none;" class="price-int1<%= i %><%= j %>"> <%= step.getPrice() %></span>
          	                                <span style="display:none;" class="price-int2<%= i %><%= j %>"> <%= step.getPriceTwo() %></span>
										
										</div>	
								<%
									}else{
								%>
									<label><input  class="rdB<%= i %>" type="<%= type %>" name="<%= i %>" value="<%= j %>" ><span id="name<%= i+ "" +j%>">  <%= step.getName()%><%= imgDescription %></span></label>
                                    <%= input %>
									<span style="display:none;" class="price-int1<%= i %><%= j %>"> <%= step.getPrice() %></span>
        	                       <span style="display:none;" class="price-int2<%= i %><%= j %>"> <%= step.getPriceTwo() %></span>
										
								
								<% 
									}
                               	}
								%>
							
						</div>
						<% if (i != 5){%>
							<span style="display: inline;" class="buttons">
									<input  type="button" name="sbmtButtonPrev" class="buttonR" value="Começar de novo"  />
									<input  type="button" name="sbmtButton" class="buttonDisable" value="Próxima" id="bt<%= i %>Disable"  />
									<input  type="button" name="sbmtButton" class="button" value="Próxima"  style="display: none;" id="bt<%= i %>" />
                                    <input  type="submit" name="sbmtButton" class="button" value="Ordenação"  style="display: none;" id="bt<%= i %>-esp" />
                                    
							</span>		
						<% }else{%>
							<span style="display: inline;" class="buttons">
								<input  type="button" name="sbmtButtonPrev" class="buttonR" value="Começar de novo"  />
								<input  type="button" name="sbmtButton" class="buttonDisable" value="Próxima" id="bt<%= i %>Disable"  />
								<input  type="submit" name="sbmtButton" class="button" value="Ordenação"  style="display: none;" id="bt<%= i %>" />
							</span>		
						<% }%>
					</div>
				<% 
					}
				}
				%>
				<script>
					setColor('<%= color[0] %>','<%= color[1] %>','<%= color[2] %>' );
					setSabor('<%= sabor[0] %>','<%= sabor[1] %>','<%= sabor[2] %>' );
					setSaborP('<%= saborP[0] %>','<%= saborP[1] %>','<%= saborP[2] %>', '<%= id[0] %>', '<%= id[1] %>', '<%= id[2] %>' );
				</script>
				<% 
				session.setAttribute("hashMapCup", hashMap);
				 session.setAttribute("hashMapPriceCup", hashMapPrice);
				 session.setAttribute("hashMapIdCup", hashMapId);
				%>
			</form>
			<%}else{ %>
					<br>
					<div style="text-align: justify;">
						Para criar o seus cupcakes e ter acesso a pre&#231;os voc&#234; precisa-se cadastrar. <br><br>
						
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
				<a href="./ocasionesEspeciales.jsp"><img src="./images/banner_cupcakes.png" alt="Image" /></a>
			</div>
			<% }else{ %>
			<div class="bannerTorta">
				<a href="./ocasionesEspeciales.jsp"><img src="./images/banner_cupcakes.png" alt="Image" /></a>
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