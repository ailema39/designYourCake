<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="../css/styleAdmin.css" />
	<link rel="shortcut icon" href="../images/ico.ico">
	<title>Design Your Cake - Administrador</title>
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
				<form name="userForm" action="../UserLoginServlet" onsubmit="return validateLogin(this)" method="post">
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
					<fieldset>
						<label for="name">Nombre de usuario:</label>
						<input type="text" name="txtName" id="txtName" maxlength="50" /> <br><br>
						<label for="name">Contraseña:</label>
						<input type="password" name="txtPassword" id="txtPassword" maxlength="50" />
					</fieldset>
					
					
					<br>
					<div class="buttonCenter">
						<input type="submit" name="sbmtButton" class="button" value="Acceder" />
					</div>
				</form>
				<div>
					<a href="../UserPasswordGeneratorServlet" style="margin-top: 5px; text-decoration:none;" onClick="forgotPass();">&iquest;Olvidaste tu contrase&ntilde;a?</a>
				</div>
			</div>
            <div id="footer"></div>
   			</div>
   </body>
</html>