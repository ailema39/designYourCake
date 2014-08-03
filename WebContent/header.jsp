<%@ page import="domain.Client "%> 
<%
	HttpSession infoPage = request.getSession();
	
	String error = (String) request.getAttribute("error");
	if (error != null){
		if (error.startsWith("La información de nombre")){
			
	%>
			<script>
				$(function(){
					$('.errorLogin').text('O nome de usuário ou senha que você digitou está incorreta.');
					$('#go').click();
				});
			</script>
	<%
		}else if (error.startsWith("El correo introducido")){
			
	%>
			<script>
				$(function(){
					$('.errorLogin').text('O e-mail que você entrou não está registrado. Por favor, verifique e tente novamente.');
					$('#go').click();
					$('#login').hide();
					$('#passRcv').show();
				});
			</script>
	<%
		}
	}
	Client client = (Client) infoPage.getAttribute("client");
	if (client == null){
%>
<div>
	<div>
		<a href="#signup"  rel="leanModal" id="go" class="pestana">Entrar</a>
		<a href="RegisterServlet" class="pestana">Cadastre-se</a>
	</div>
</div>
<% 
	}else{
%>		
	
	<div id="pestana_larga">
			<%
				String name = client.getFirstName();
				if (name.length() > 14 ){
					name = client.getFirstName().substring(0, 12);
					name += "...";
				}
			%>
			<a class="pestN" >Bem-vindo, <%= name %> </a>
			<span class="divisor">|</span> 
			<a href="ClientAccountServlet?id=<%= client.getId() %>" class="pestCuenta"> Minha conta </a>
			<span class="divisor3">|</span> 
			<a href="LogoutPageServlet" class="pestSalir"> Sair <img src="images/exit.png" width="18" height="18"></a>
			
	</div>
<% 				
	}
%>