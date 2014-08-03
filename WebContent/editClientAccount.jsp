<%@page import="domain.Client"%>
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
	<script type="text/javascript" src="js/registro.js"></script>
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
	infoPage.setAttribute("prevPage", "registro.jsp");
	String message  = (String) request.getAttribute("message");
	String error = (String) request.getAttribute("editClient");
	Client client = (Client) infoPage.getAttribute("client");

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
				<div class="datos">
					<% if (message == null || message == ""){ %>
					<div class="separator-current" id="infPers">
						<a href="#" onClick="BackAux();">Informa&#231;&#245;es Pessoais</a>
					</div>
					<div class="separator" id="infPass">
						<a href="#" onClick="SiguienteAuxC();">Alterar Senha</a>
					</div>
					<% }else{  %>
						<script>
							$(function(){
								$('#passId').click();
							});
						</script>
					<div class="separator" id="infPers">
						<a href="#" onClick="BackAux();">Informa&#231;&#245;es Pessoais</a>
					</div>
					<div class="separator-current" id="infPass">
						<a href="#" onClick="SiguienteAuxC();" id="passId">Alterar Senha</a>
					</div>
					<% }  %>
				</div>
				<div class="registration">
				<jsp:useBean id="clientInfo" type="domain.Client" scope="request"/> 
					<span class="regis-title">Editar Datos</span><br>
					<form name="regForm" action="ClientAccountServlet" method="post" onsubmit="return validateRegCont();" >
					<input type="hidden" name="clientId" value=<%= client.getId() %>>
						<div class="block1" >
							<fieldset>
								<% if (clientInfo.isCompany() == 0){ %>
									<div style="float: right; margin-right: 45px; margin-top: -25px;">
										<input type="radio" name="typePers" value="0" checked="checked"> Perssoa Singular
										<input type="radio" name="typePers" value="1" >  Perssoa Jur&#237;dica 
									</div>
									<span class="error" id="errorType" style="float:right; margin-right:50px;">Desculpe, voc&#234; deve indicar se se trata de pessoa singular ou colectiva</span>
									<br> 
								<% }else{ %>
									<div style="float: right; margin-right: 45px; margin-top: -25px;">
										<input type="radio" name="typePers" value="0" > Perssoa Singular
										<input type="radio" name="typePers" value="1" checked="checked" >  Perssoa Jur&#237;dica  
									</div>
									<span class="error" id="errorType" style="float:right; margin-right:50px;">Desculpe, voc&#234; deve indicar se se trata de pessoa singular ou colectiva</span>
									<br> 
								<% } %>
								<label for="name"  id="name">Nomes:</label>
								<input type="text" name="txtName" id="txtName" size="35" maxlength="100" 
								onBlur="validateName(this, '1');" value="<%= clientInfo.getFirstName() %>" /> 
								<span class="error" id="errorName">Desculpe, voc&#234; deve digitar um nome v&#225;lido</span>
								<br>
								<% String div="display:none";
									if (clientInfo.isCompany() == 0){ 
										div="";
									}
								%>
								<div id="apellido" style="<%= div %>" >
									<label for="name">Sobrenomes:</label>
									<input type="text" name="txtLastName" id="txtLastName" size="35"  maxlength="50"  
									onBlur="validateName(this, '2');" value="<%= clientInfo.getLastName() %>"/> 
									<span class="error" id="errorLastName">Desculpe, voc&#234; deve digitar um sobrenome v&#225;lido</span><br>
								</div>
								<label for="name" id="cedIden">CPF:</label>
								<input type="text" name="txtCed" id="txtCed" size="35" maxlength="50"  
								onBlur="validateCedIdnt(this);"
								style="display: inline;" value="<%=  clientInfo.getIdentityCard() %>" /><br>
								 
								<span class="error" id="errorCed">Desculpe, voc&#234; deve digitar um CPF v&#225;lido</span>
								<br>
								<label for="name">Email:</label>
								<input type="text" name="txtEmail" id="txtEmail" size="35" maxlength="50" 
								 onBlur="validateEmail(this);" value="<%= clientInfo.getEmail() %>" /> 
								<span class="error" id="errorEmail" >Desculpe, voc&#234; deve digitar um Email v&#225;lido</span>
								<br>
							</fieldset>
							<div class="reg-button">
									<input type="button" name="sbmtButton" class="button" value="Seguinte" onClick="SiguienteAux();"  />
							</div>
						</div>
						
						<div class="block2" style="display:none" >
							<fieldset>
								<label for="name">Telefone:</label>
								<input type="text" name="txtPhone" id="txtPhone" size="35" maxlength="100" 
								onBlur="validatePhone(this, '1');" value="<%= clientInfo.getPhone() %>"/>
								<% if (clientInfo.getOtherPhone() == null || clientInfo.getOtherPhone().equals("")) { %>
									<img src="images/mas.png" width="20" height="20" class="imgPlus" 
										onClick = "agregarOtro();" >
								<% } %>
									
								<span class="error" id="errorPhone" >Voc&#234; deve digitar um n&#250;mero de telefone v&#225;lido (c&#243;digo de &#225;rea + n&#250;mero) </span>
								
								<%	String style=""; 
								if (clientInfo.getOtherPhone() == null || clientInfo.getOtherPhone().equals("")){
									style = "style=\"display: none;\"";
								}
								%>
								<div class = "otherPhone" <%= style %>>
								<br>
								
									<label for="name">Celular:</label>
									<input type="text" name="txtMovPhone" id="txtMovPhone" size="35" maxlength="100"
									onBlur="validatePhone(this, '2');"  value="<%= clientInfo.getOtherPhone() %>" />
									<span class="error" id="errorMovPhone" >Voc&#234; deve digitar um n&#250;mero de telefone v&#225;lido (c&#243;digo + n&#250;mero da operadora)</span>									
								</div>

								<br>
								<label for="name">Endere&#231;o:</label>
								<textarea rows="4" cols="28" style="resize: none;" 
								 onBlur="validateDir(this, '1');" id="dir" 
								name="txtDir"><%= clientInfo.getAddress()%></textarea><br>
								<span class="error" id="errorDir" >Desculpe, voc&#234; deve digitar um endere&#231;o v&#225;lido</span>
								<br>
								<% if (clientInfo.isShippingAddress() == 1){%>
									<input type="checkbox" value="remember" checked id="checkDir" name="checkDir"/> &#201; o seu endere&#231;o de entrega? <br>
								<% }else { %>
									<input type="checkbox" value="remember" id="checkDir" name="checkDir"/> &#201; o seu endere&#231;o de entrega? <br>
								<% } %>
								
								<%	style=""; 
									if (clientInfo.isShippingAddress() == 1){
										style = "style=\"display: none;\"";
									}
								%>
									<div class="otherDir" <%= style %>>
											<label for="name">Endere&#231;o de entrega:</label>
											<textarea rows="4" cols="28" style="resize: none;" 
											onBlur="validateDir(this, '2');" id="dirEnv" name="txtDirEnv"><%= clientInfo.getShippingAddress() %></textarea><br>
											<span class="error" id="errorDirEnv" >Desculpe, voc&#234; deve digitar um endere&#231;o de entrega v&#225;lido</span>
									</div>
								</fieldset>
								<div class="reg-button">
										<input type="submit" name="sbmtButton" class="button" value="Salvar"   />
								</div>
							</div>
							
							</form>
							
							<div class="block3" style="display:none" >
								<form name="passForm" action="ChangePasswordServlet" method="post" onsubmit="return validatePassCont();" >
								<% 
									infoPage.setAttribute("clientAux", clientInfo); 
								%>
								<input type="hidden" name="email" value ="<%= clientInfo.getEmail() %>" /> 
								<br>
								<fieldset>
									<label for="name">Senha Antiga:</label>
									<input type="password" name="txtOldPass" id="txtOldPass" size="35" maxlength="100" 
									onBlur="validatePassAux(this);" />
									<span class="error" id="errorPassOld" >Desculpe, voc&#234; deve digitar sua senha antiga </span>
									<br>
									<label for="name">Nova Senha:</label>
									<input type="password" name="txtNewPass" id="txtNewPass" size="35" maxlength="100" 
									 onBlur="validatePassNew(this);" />
									<span class="error" id="errorPassNew" >Desculpe, voc&#234; deve digitar uma nova senha</span>
									<br>
								
									<label for="name">Repeti&#231;&#227;o Senha:</label>
									<input type="password" name="txtNewPassRpt" id="txtNewPassRpt" size="35" maxlength="100" 
									oninput="validatePassRptNew(this);" onBlur="validatePassRptNew(this);" />
									<span class="error" id="errorPassRpt" >Desculpe, as senhas devem corresponder</span>
									<br>
									
								</fieldset>
								<div class="reg-button">
										<input type="submit" name="sbmtButton" class="button" value="Alterar"   />
								</div>
								</form>	
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