<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="domain.User"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/styleAdmin.css" />
<link rel="shortcut icon" href="../images/ico.ico">
<jsp:useBean id="typeInfo" type="domain.OrderType" scope="request"/> 
<title>Administrador <%= typeInfo.getName() %></title>
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
	var idOption;
	var idStep;
	var idOrderType;
	var priceMode;
			
	$(function() {
		$('a[rel*=leanModal]').leanModal({ top : 200, closeButton: ".close_x" });		
	});
	
	function loadVars(var0, var1, var2, var3, var4) {
		idOption = var0;
		idStep = var1;
		idOrderType = var2;
		$('.option').text(var3);
		priceMode = var4;
		
	};
	
	function setV(f){
		f.elements['optionId'].value = idOption;
		f.elements['stepId'].value = idStep;
		f.elements['orderTypeId'].value = idOrderType;
		f.elements['priceMode'].value = priceMode;
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
<%
	int priceMode = (Integer)request.getAttribute("priceMode");
%>
	<div id="container">
		<div id="header">
        	<img alt="logo" src="../images/loguito5.png"/>
        </div>         
        <div id="menu">
			<div class="menuitemHome" ><a href="../UserLoginServlet">Home</a></div>	
	    	<ul>
	    		<li class="menuitem"><a href="../ListOrderStepsServlet?typeId=<%= typeInfo.getId() %>">Ver Pasos</a></li>
            	<li class="menuitem"><a href="../CreateStepOptionServlet?typeId=<%= typeInfo.getId() %>&stepId=<%= request.getAttribute("stepId") %>&priceMode=<%= priceMode %>">Agregar opción</a></li>
            </ul>
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
        		<jsp:useBean id="options" type="java.util.ArrayList<domain.StepOption>" scope="request"/>
        		<h2>Opciones <%= typeInfo.getName() %>:</h2>
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
					if (options.size() == 0) {
				%>	
					<p>&nbsp;</p> 
					<p class="noReg">En estos momentos no hay opciones registradas.</p>  
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
								<th>Precio Mini</th>
								<th>Precio Tradicional</th>
								<th>Agotado</th>
								<th>Acciones</th>
							</tr>
						</thead>
						<tbody>
							<%
							for(domain.StepOption o : options) { 											
							%>
								<tr class="gradeA">
									<td><%= o.getId() %></td>
									<td><%= o.getPosition() %></td>
									<td><%= o.getName() %></td>
									<td><%= o.getPrice() %></td>
									<td><%= o.getPriceTwo() %></td>
									<td><%= (o.isUnavailable()==1)?"Si":"No"  %></td>
									<td><p> 
										<a href="../EditStepOptionServlet?optionId=<%= o.getId() %>&stepId=<%= o.getOrderStepId() %>&typeId=<%= typeInfo.getId() %>&priceMode=<%= priceMode %>" style="color: transparent" >
											<img alt="logo" src="/images/edit.png"  height="16" width="16" title="Editar Opción" />
										</a> 
										<a id="go" rel="leanModal" href="#deleteOption" style="color: #f7941e; font-weight: bold;" onclick="return loadVars(<%= o.getId()%>, <%= o.getOrderStepId()%>, <%= typeInfo.getId() %>, '<%= o.getPosition()%>', <%= priceMode %> )" >
										<img alt="logo" src="/images/delete.png" height="16" width="16" title="Eliminar Opción" />
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
	
	<div id="deleteOption">
		<div id="signup-ct">
			<h3 id="see_id" class="sprited" > Eliminar Paso</h3>
			<br><br>
			<span>¿Está seguro que desea eliminar el paso <span class="option"></span>? </span> <br><br>
			<div id="signup-header">
				<a class="close_x" id="close_x"  href="#"></a>
			</div>
			<form action="../DeleteStepOptionServlet" method="post"  onsubmit="return setV(this)">
				<input type="hidden" id="optionId" class="good_input" name="optionId"  value=""/>
				<input type="hidden" id="stepId" class="good_input" name="stepId"  value=""/>
				<input type="hidden" id="orderTypeId" class="good_input" name="orderTypeId"  value=""/>
				<input type="hidden" id="priceMode" class="good_input" name="priceMode"  value=""/>
				<div class="btn-fld">
					<input type="submit"  class="buttonPopUpDelete"  name="sbmtButton" value="Aceptar"  />
				</div>
		 </form>
		</div>
	</div>
	

</body>
</html>