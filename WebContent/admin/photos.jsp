<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="domain.User"%> 
<%@ page import="domain.Album"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/styleAdmin.css" />
<link rel="shortcut icon" href="../images/ico.ico">
<title>Administrador Fotos</title>
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
	var idPhoto;
	var idAlbum;
			
	$(function() {
		$('a[rel*=leanModal]').leanModal({ top : 200, closeButton: ".close_x" });		
	});
	
	function loadVars(var0, var1, var2) {
		idAlbum = var0;
		idPhoto = var1;
		$('.photo').text(var2);
		
	};
	
	function setV(f){
		f.elements['photoId'].value = idPhoto;
		f.elements['albumId'].value = idAlbum;
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
	    	<ul>
	    		<li class="menuitem"><a href="../ListAlbumsServlet">Ver Álbumes</a></li>
            	<li class="menuitem"><a href="../CreatePhotoServlet?albumId=<%= request.getAttribute("albumId") %>">Agregar Foto</a></li>
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
        		<jsp:useBean id="photos" type="java.util.ArrayList<domain.Photo>" scope="request"/>  	
        		<h2>Fotos Registradas:</h2>
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
					if (photos.size() == 0) {
				%>	
					<p>&nbsp;</p> 
					<p class="noReg">En estos momentos no hay fotos registradas.</p>  
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
								<th>Nombre</th>
								<th>Imagen</th>
								<th>Activo</th>
								<th>Nuevo</th>
								<th>Acciones</th>
							</tr>
						</thead>
						<tbody>
							<%
							for(domain.Photo p : photos) { 											
							%>
								<tr class="gradeA">
									<td><%= p.getId() %></td>
									<td><%= p.getName() %></td>
									<td><img alt="image" src="<%= "/files/" + Album.getDirectory(p.getAlbumId()) + "/" + p.getImage() %>" width="50" height="50" /></td>
									<td><%= (p.isActive()==1)?"Si":"No"  %></td>
									<td><%= (p.isNew()==1)?"Si":"No" %></td>
									<td><p> 
										<a href="../EditPhotoServlet?photoId=<%= p.getId() %>&albumId=<%= p.getAlbumId() %>" style="color: transparent" >
											<img alt="logo" src="/images/edit.png"  height="16" width="16" title="Editar Foto" />
										</a> 
										<a id="go" rel="leanModal" href="#deletePhoto" style="color: #f7941e; font-weight: bold;" onclick="return loadVars(<%= p.getAlbumId()%>, <%= p.getId()%>,'<%= p.getName()%>' )" >
										<img alt="logo" src="/images/delete.png" height="16" width="16" title="Eliminar Foto" />
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
	
	<div id="deletePhoto">
		<div id="signup-ct">
			<h3 id="see_id" class="sprited" > Eliminar Foto</h3>
			<br><br>
			<span>¿Está seguro que desea eliminar la foto <span class="photo"></span>? </span> <br><br>
			<div id="signup-header">
				<a class="close_x" id="close_x"  href="#"></a>
			</div>
			<form action="../DeletePhotoServlet" method="post"  onsubmit="return setV(this)">
				<input type="hidden" id="photoId" class="good_input" name="photoId"  value=""/>
				<input type="hidden" id="albumId" class="good_input" name="albumId"  value=""/>
				<div class="btn-fld">
					<input type="submit"  class="buttonPopUpDelete"  name="sbmtButton" value="Aceptar"  />
				</div>
		 </form>
		</div>
	</div>
	

</body>
</html>