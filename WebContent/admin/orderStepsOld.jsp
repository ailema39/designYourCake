<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="domain.User"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/armaTuTorta/css/styleAdmin.css" />
<link rel="shortcut icon" href="/armaTuTorta/images/ico.ico">
<jsp:useBean id="typeInfo" type="domain.OrderType" scope="request"/> 
<title>Administrador <%= typeInfo.getName() %></title>
<script type="text/javascript" language="javascript" src="/armaTuTorta/js/jquery.js"></script>
<script type="text/javascript" language="javascript" src="/armaTuTorta/js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" src="/armaTuTorta/js/jquery.leanModal.min.js"></script>
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
				{ "bSearchable": false, "asSorting": false }
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
	var idStep;
	var idOrderType;
			
	$(function() {
		$('a[rel*=leanModal]').leanModal({ top : 200, closeButton: ".close_x" });		
	});
	
	function loadVars(var0, var1, var2) {
		idStep = var0;
		idOrderType = var1;
		$('.step').text(var2);
		
	};
	
	function setV(f){
		f.elements['stepId'].value = idStep;
		f.elements['orderTypeId'].value = idOrderType;
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
        	<img alt="logo" src="/armaTuTorta/images/loguito5.png"/>
        </div>         
        <div id="menu">
			<div class="menuitemHome" ><a href="UserLoginServlet">Home</a></div>	
	    	<ul>
            	<li class="menuitem"><a href="CreateOrderStepServlet?typeId=<%= typeInfo.getId() %>">Agregar Paso</a></li>
            </ul>
			<div class="menuitemPass"><a href="EditUserPasswordServlet">Cambiar Contraseña</a></div>
			<div class="menuitemSalir"><a href="admin/index.jsp">Salir</a></div>	
        </div>        
       <div id="leftmenu">
        	<div id="leftmenu_top"></div>
			<div id="leftmenu_main">                    
				<jsp:include page="/inc/adminMenu.jsp"></jsp:include>
			</div>
            <div id="leftmenu_bottom"></div>
        </div>  
		<div id="content">  
        		<jsp:useBean id="steps" type="java.util.ArrayList<domain.OrderStep>" scope="request"/>  	
        		<h2>Pasos Registrados:</h2>
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
					if (steps.size() == 0) {
				%>	
					<p>&nbsp;</p> 
					<p class="noReg">En estos momentos no hay pasos registrados.</p>  
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
								<th>Paso</th>
								<th>Nombre</th>
								<th>Selección Múltiple</th>
								<th>Acciones</th>
							</tr>
						</thead>
						<tbody>
							<%
							for(domain.OrderStep s : steps) { 											
							%>
								<tr class="gradeA">
									<td><%= s.getId() %></td>
									<td><%= s.getPosition() %></td>
									<td><%= s.getName() %></td>
									<td><%= (s.isMultipleChoice()==1)?"Si":"No"  %></td>
									<td><p>
										<a href="/armaTuTorta/ListStepOptionsServlet?stepId=<%= s.getId() %>&typeId=<%= s.getOrderTypeId() %>" style="color: transparent" >
											<img alt="logo" src="/armaTuTorta/images/step-options.png"  height="16" width="16" />
										</a> 
										<a href="/armaTuTorta/EditOrderStepServlet?stepId=<%= s.getId() %>&typeId=<%= s.getOrderTypeId() %>" style="color: transparent" >
											<img alt="logo" src="/armaTuTorta/images/edit.png"  height="16" width="16" />
										</a> 
										<a id="go" rel="leanModal" href="#deleteStep" style="color: #f7941e; font-weight: bold;" onclick="return loadVars(<%= s.getId()%>, <%= s.getOrderTypeId()%>, '<%= s.getPosition()%>' )" >
										<img alt="logo" src="/armaTuTorta/images/delete.png" height="16" width="16" />
										</a><br>
										</p>
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
	
	<div id="deleteStep">
		<div id="signup-ct">
			<h3 id="see_id" class="sprited" > Eliminar Paso</h3>
			<br><br>
			<span>¿Está seguro que desea eliminar el paso <span class="step"></span>? </span> <br><br>
			<div id="signup-header">
				<a class="close_x" id="close_x"  href="#"></a>
			</div>
			<form action="/armaTuTorta/DeleteOrderStepServlet" method="post"  onsubmit="return setV(this)">
				<input type="hidden" id="stepId" class="good_input" name="stepId"  value=""/>
				<input type="hidden" id="orderTypeId" class="good_input" name="orderTypeId"  value=""/>
				<div class="btn-fld">
					<input type="submit"  class="buttonPopUpDelete"  name="sbmtButton" value="Aceptar"  />
				</div>
		 </form>
		</div>
	</div>
	

</body>
</html>