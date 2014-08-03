<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="domain.User"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/styleAdmin.css" />
<link rel="shortcut icon" href="../images/ico.ico">
<title>Administrador Clientes</title>
<script type="text/javascript" language="javascript" src="../js/jquery.js"></script>
<script type="text/javascript" language="javascript" src="../js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" src="../js/jquery.leanModal.min.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#example').dataTable( {
			"iDisplayLength": 8,
			"bLengthChange": false,
			"sScrollY": "250px",
			"bPaginate": false,
			"aoColumns": [
				null,
				null,
				null,
				null,
				null,
				null,
				{ "bSearchable": false, "asSorting": false, "sWidth": "15%" }
			],
			"oLanguage": {
	            "sLengthMenu": "Mostrar _MENU_ registros",
	            "sZeroRecords": "No hay ningún registro que coincida con su búsqueda",
	            "sInfo": "Mostrando _START_ a _END_ de _TOTAL_ registros",
	            "sInfoEmpty": "Mostrando 0 a 0 de 0 registros",
	            "sInfoFiltered": "(filtrando de _MAX_ registros totales)",
	            "sEmptyTable": "No hay datos disponibles en la tabla",
	            "sLoadingRecords": "Por favor, espere - cargando...",
	            "sSearch": "Buscar:",
	            "oPaginate": {
	                "sNext": "Siguiente",
	                "sPrevious": "Anterior"
	              }
        	}
		} );
	} );
</script>
<script type="text/javascript">
	var idEstimation;
			
	$(function() {
		$('a[rel*=leanModal]').leanModal({ top : 200, closeButton: ".close_x" });		
	});
	
	function loadVars(var1, var2) {
		idEstimation = var1;
		$('.estimation').text(var2);
		
	};
	
	function setV(f){
		f.elements['estimationId'].value = idEstimation;
		return true;
	}
</script>
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
        <div id="menu">
			<div class="menuitemHome" ><a href="../UserLoginServlet">Home</a></div>
			<div class="menuitemPass"><a href="../EditUserPasswordServlet">Cambiar Contraseña</a></div>
			<div class="menuitemSalir"><a href="../admin/index.jsp">Salir</a></div>	
        </div>        
       <div id="leftmenu">
        	<div id="leftmenu_top"></div>
			<div id="leftmenu_main">                    
				<jsp:include page="../inc/adminMenu.jsp"></jsp:include>
			</div>
            <div id="leftmenu_bottom"></div>
        </div>  
		<div id="content">  
        		<jsp:useBean id="estimations" type="java.util.ArrayList<domain.Estimation>" scope="request"/>  	
        		<h2>Presupuestos Solicitados:</h2>
				<%
        			String info = (String)request.getAttribute("info");
        			String error = (String)request.getAttribute("error");
					if(!info.equalsIgnoreCase("")){
				%>	
				<p>&nbsp;</p> 
				<p class="info-msg"><%= info %></p> 
				<%	
					}
					if(!error.equalsIgnoreCase("")){
				%>	
           		<p>&nbsp;</p>    
				<p class="error-msg"><%= error %></p>      
           		<%	
					}
					if (estimations.size() == 0) {
				%>	
					<p>&nbsp;</p> 
					<p class="noReg">En estos momentos no hay presupuestos solicitados.</p>  
				<%
				} else {
				%>		
				<div id="dt_example">
					<div id="container">
						<div id="demo">
						<table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
						<thead>
							<tr>
								<th>ID</th>
								<th>Cliente</th>
								<th>Ocasión Especial</th>
								<th>Productos</th>
								<th>Invitados</th>
								<th>Fecha Solicitud</th>
								<th>Acciones</th>
							</tr>
						</thead>
						<tbody>
							<%
							for(domain.Estimation e : estimations) { 											
							%>
								<tr class="gradeA">
									<td><%= e.getId() %></td>
									<td><%= e.getClient().getFirstName() + " " + e.getClient().getLastName() %></td>
									<td><%= e.getSpecialOccasion() %></td>
									<td><%= e.getProducts() %></td>
									<td><%= e.getGuestsNumber()%></td>
									<td><%= (e.getApplicationDate()==null)?"":e.getApplicationDate() %></td>
									<td>
										<a href="../ShowEstimationServlet?estimationId=<%= e.getId() %>" style="color: transparent" >
											<img alt="logo" src="/images/detail.png"  height="16" width="16" title="Ver Detalle" />
										</a> 
										<a href="../PrintEstimationServlet?estimationId=<%= e.getId() %>" style="color: transparent" >
											<img alt="logo" src="/images/print.png"  height="16" width="16" title="Imprimir" />
										</a> 
										<a href="../ConvertInOrderServlet?estimationId=<%= e.getId() %>&clientId=<%= e.getClientId() %>&orderTypeId=4" style="color: transparent" >
											<img alt="logo" src="/images/convert.png"  height="16" width="16" title="Convertir en Pedido" />
										</a> 
										<a id="go" rel="leanModal" href="#deleteEstimation" style="color: #f7941e; font-weight: bold;" 
										onclick="return loadVars(<%= e.getId()%>,'<%= e.getId()%>' )" >
										<img alt="logo" src="/images/delete.png" height="16" width="16" title="Eliminar Presupuesto" />
										</a>
									</td>
								</tr>
							<% 
								}
							%>
						</tbody>
					</table>
					</div>
				</div>
			</div>
			<div class="spacer"></div>
			<% 
			}
			%>
		</div>
 	</div>
	
	<div id="deleteEstimation">
		<div id="signup-ct">
			<h3 id="see_id" class="sprited" > Eliminar Presupuesto</h3>
			<br><br>
			<span>¿Está seguro que desea eliminar el presupuesto número <span class="estimation"></span>? </span> <br><br>
			<div id="signup-header">
				<a class="close_x" id="close_x"  href="#"></a>
			</div>
			<form action="../DeleteEstimationServlet" method="post"  onsubmit="return setV(this)">
				<input type="hidden" id="estimationId" class="good_input" name="estimationId"  value=""/>
				<div class="btn-fld">
					<input type="submit"  class="buttonPopUpDelete"  name="sbmtButton" value="Aceptar"  />
				</div>
		 </form>
		</div>
	</div>
	

</body>
</html>