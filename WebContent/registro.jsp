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
	session.setAttribute("prevPage", "registro.jsp");
	
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
					<div class="separator-current" id="infPers">
						<a href="#" onClick="Back();">Informa&#231;&#245;es Pessoais</a>
					</div>
					<div class="separator" id="infEnv">
						<a href="#" onClick="Siguiente();">Informa&#231;&#227;o do Transporte</a>
					</div>
				</div>
				<div class="registration">
					<span class="regis-title">Registro </span><br>
					<form class="formReg" name="regForm" action="RegisterServlet" method="post" onsubmit="return validateRegCont();" >
						<div class="block1" >
							<fieldset>
								<div class="radioPersonas">
									<input type="radio" name="typePers" value="0"> Pessoa Singular
									<input type="radio" name="typePers" value="1" > Pessoa Jur&iacute;dica 
								</div>
								<span class="error" id="errorType" style="float:right; margin-right:50px;">Desculpe, voc&#234; deve indicar se se trata de pessoa singular ou colectiva</span>
								<br> 
								<label for="name" id="name">Nomes:</label>
								<input type="text" name="txtName" id="txtName" size="35" maxlength="100" 
								onBlur="validateName(this, '1');" /> 
								<span class="error" id="errorName">Desculpe, voc&#234; deve digitar um nome v&#225;lido</span>
								<br>
								<div id="apellido">
									<label for="name">Sobrenomes:</label>
									<input type="text" name="txtLastName" id="txtLastName" size="35"  maxlength="50"  
									onBlur="validateName(this, '2');"/> 
									<span class="error" id="errorLastName">Desculpe, voc&#234; deve digitar um sobrenome v&#225;lido</span><br>
								</div>
								<label for="name" id="cedIden">CPF:</label>
								<input type="text" name="txtCed" id="txtCed" size="35" maxlength="50"  
								onBlur="validateField(this);" style="display: inline;" /><br>
								<span class="error" id="errorRif">Desculpe, voc&#234; deve digitar um CPF v&#225;lido</span>
								<span class="error" id="errorCed">Desculpe, voc&#234; deve digitar um CPF v&#225;lido</span>
								<br>
								<label for="name">Email:</label>
								<input type="text" name="txtEmail" id="txtEmail" size="35" maxlength="50"
								 onBlur="validateEmail(this);" /> 
								<span class="error" id="errorEmail" >Desculpe, voc&#234; deve digitar um Email v&#225;lido</span><br>
								<label for="name">Senha:</label>
								<input type="password" name="txtPass" id="txtPass" size="35" maxlength="50"
								onBlur="validatePass(this);" /> 
								<span class="error" id="errorPass" >Desculpe, sua senha deve ser superior a 6 caracteres</span>
								<br>
								<label for="name">Repeti&#231;&#227;o Senha:</label>
								<input type="password" name="txtRptPass" id="txtRptPass" size="35" maxlength="50"
								oninput="validateRptPass(this);" onBlur="validateRptPass(this);" /> 
								<span class="error" id="errorRptPass" >Desculpe, as senhas devem corresponder</span>
								
							</fieldset>
							<div class="reg-button">
									<input type="button" name="sbmtButton" class="button" value="Seguinte" onClick="Siguiente();"  />
							</div>
						</div>
						
						<div class="block2" style="display:none" >
							<br><br><fieldset>
								<label for="name">Telefone:</label>
								<input type="text" name="txtPhone" id="txtPhone" size="35" maxlength="100"
								 onBlur="validatePhone(this, '1');" />
								<img src="images/mas.png" width="20" height="20" class="imgPlus" 
									onClick = "agregarOtro();" >
								<span class="error" id="errorPhone" >Voc&#234; deve digitar um n&#250;mero de telefone v&#225;lido (c&#243;digo de &#225;rea + n&#250;mero) </span>
								
								<div class = "otherPhone" style="display: none;">
								<br>
									<label for="name">Celular:</label>
									<input type="text" name="txtMovPhone" id="txtMovPhone" size="35" maxlength="100"
									onBlur="validatePhone(this, '2');"  />
									<span class="error" id="errorMovPhone" >Voc&#234; deve digitar um n&#250;mero de telefone v&#225;lido (c&#243;digo + n&#250;mero da operadora)</span>									
								</div>

								<br>
								<label for="name">Endere&#231;o:</label>
								<textarea rows="4" cols="28" style="resize: none;" 
								onBlur="validateDir(this, '1');" id="dir" name="txtDir"></textarea><br>
								<span class="error" id="errorDir" >Desculpe, voc&#234; deve digitar um endere&#231;o v&#225;lido</span>
								<br>
								<input type="checkbox" value="remember" checked id="checkDir" name="checkDir"/> &#201; o seu endere&#231;o de entrega? <br>
								
								<div class="otherDir" style="display: none;">
									<label for="name">Endere&#231;o de Entrega:</label>
									<textarea rows="4" cols="28" style="resize: none;" 
									onBlur="validateDir(this, '2');" id="dirEnv" name="txtDirEnv"></textarea><br>
									<span class="error" id="errorDirEnv" >Desculpe, voc&#234; deve digitar um endere&#231;o de entrega v&#225;lido</span>
								</div>
							</fieldset>
							<div class="reg-button">
									<input type="submit" name="sbmtButton" class="button" value="Seguinte"   />
							</div>
						</div>
						
					</form>
					
				</div>
		</div>
	</div>
	<div class="push"></div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<jsp:include page="ventanas.jsp"></jsp:include>
</body>
</html>