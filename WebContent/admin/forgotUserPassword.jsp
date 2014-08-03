<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="../css/styleAdmin.css" />
	<link rel="shortcut icon" href="../images/ico.ico">
	<title>Arma Tu Torta - Administrador</title>
	<script type="text/javascript" src="../js/messages.js"></script>
<script language=JavaScript> 
	<!-- 
	
	function inhabilitar(){ 
	   	return false;
	} 
	
	document.oncontextmenu=inhabilitar;
	
	// --> 
	</script>
</head>
<body oncontextmenu="return inhabilitar()">
	<div id="container">
		<div id="header">
			<img alt="logo" src="../images/loguito5.png"/>
        </div>           
       
		<div id="contentLogin">
				<form name="userForm" action="../UserPasswordGeneratorServlet" onsubmit="return validateForgotPwd(this)" method="post">
				<%
        			String info = (String)request.getAttribute("info");
        			String error = (String)request.getAttribute("error");
        			
					if(info!=null && !info.equalsIgnoreCase("")){
				%>	
				<p>&nbsp;</p> 
				<p class="info-msg"><%= info %></p> 
				<%	
					}
					if(error!=null && !error.equalsIgnoreCase("")){
				%>	
           		<p>&nbsp;</p>    
				<p class="error-msg"><%= error %></p>      
           		<%	
					}
				%>		
					<br>	
					<p style="font-weight: bold;padding-left: 10px;">Introduce tu correo electrónico, y recibirás un correo con tu nueva contraseña</p>
					<fieldset>
						<label for="name">Email:</label>
						<input type="email" name="txtEmail" id="txtEmail" maxlength="50" />
					</fieldset>				
					<br>	
					<div class="buttonCenter">
						<input type="submit" name="sbmtButton" class="button" value="Restablecer" />
					</div>
				</form>
			</div>
            <div id="footer"></div>
   			</div>
   </body>
</html>