<script type="text/javascript" src="js/messages.js"></script>

<div id="signup" style="display: none;">
	<div id="signup-ct">
		<div id="signup-header">
			<span class="titleVent">Conecte-se</span>
			<p>Introduzir os seus dados:</p>
			<a class="modal_close" href="#"></a>
		</div>
		<%
		String error = (String) request.getAttribute("error");
		if (error != null){
			if (error.startsWith("La información de nombre")){ %>
				<div class="errorLogin" style="color:red; padding-left: 15px; font-size: 16px;"></div>
			<%	}
		}
		%>
		<div id="login">
			<form name="loginForm" action="LoginPageServlet" method="post">
			  <div class="missing-info"></div>
			  <div class="txt-fld">
				<label for="name">Email:</label>
				<input type="text" name="txtNameLogin" id="txtNameLogin" maxlength="50" class="good_input" oninput="show();" onkeydown="show();" onmousedown="show()"  onBlur="show();" />
			  </div>
			  <div class="txt-fld-pass">
				<label for="password">Senha:</label>
				<input type="password" name="txtPasswordLogin" id="txtPasswordLogin" maxlength="50" oninput="show();" onkeydown="show();" onmousedown="show()" onBlur="show();"  /> <br>
				<div >
				  <a href="#" style="margin-top: 5px; text-decoration:none;" onClick="forgotPass();">Esqueceu sua senha?</a>
				</div>
			  </div>
			  <div class="btn-fld">
					<input type="submit" id="loginPage" value ="Conecte-se" disabled="disabled" />
				</div>
			 </form>
		 </div>
		 
		 <div id="passRcv" style="display:none">
			<form name="passForm" action="PasswordGeneratorServlet" method="post">
			  <input type="hidden" name="name" id="name" value="" />
			  <div class="missing-info">Digite seu e-mail e n&#243;s lhe enviaremos um e-mail com sua nova senha</div>
			<% 
				if (error != null){
					if (error.startsWith("El correo introducido")){ %>
						<div class="errorLogin" style="color:red; padding-left: 15px; font-size: 16px;"></div>
					<%	}
				}
				%> 
		 	 <div class="txt-fld">
				<label for="name">Email:</label>
				<input type="text" name="email" id="email" maxlength="50" class="good_input" oninput="showAux();" onkeydown="showAux();" onmousedown="showAux()" onBlur="showAux();" />
			  </div>
			  
			  
			  <div class="btn-fld">
					<input type="submit" id="passRcvB" value ="Restaurar" disabled="disabled" />
			  </div>
			 </form>
		 </div>
	</div>
	
</div>


