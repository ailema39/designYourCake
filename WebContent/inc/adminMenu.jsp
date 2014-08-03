<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="../js/jquery.ui.core.js"></script>
<script type="text/javascript" src="../js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="../css/jquery-ui-1.8.24.custom.css" />
<ul>
<%
	domain.User user = (domain.User) session.getAttribute("user");

	if (user == null){
		response.sendRedirect("/admin");
	} else {
%>
    <li><a href="../ListEstimationsServlet">Presupuestos</a></li>
    <li><a href="../ListOrdersServlet">Pedidos</a></li>
    <li><a href="../ListClientsServlet">Clientes</a></li>
	<li><a href="../ListOrderStepsServlet?typeId=2">Cupcakes</a></li>
    <li><a href="../ListOrderStepsServlet?typeId=1">Tortas</a></li>
    <li><a href="../ListOrderStepsServlet?typeId=3">Dulces Tortas</a></li>
	<li><a href="../ListAlbumsServlet">Galería</a></li>
<%
	}
%>	    
</ul>
<%@page import="java.sql.*" %>
<%

Class.forName("com.mysql.jdbc.Driver");
Connection  conexion = DriverManager.getConnection("jdbc:mysql://198.38.82.101/spookie_dyc", "spookie_root", "administrator");

Statement Estamento = conexion.createStatement();
ResultSet rs = Estamento.executeQuery("SELECT DATE_FORMAT(O.DELIVERY_DATE, '%m/%d/%Y') AS DELIVERY_DATE FROM `order` O WHERE O.IS_PENDING = 1 AND O.DELIVERY_DATE >= CURDATE() AND O.IS_DELETED = 0 ORDER BY O.DELIVERY_DATE ASC");
String prueba = "[";

while (rs.next()) {
	prueba += "'" + rs.getString("DELIVERY_DATE") + "',";
}

prueba = prueba.substring(0, prueba.length()-1)+"]";

rs.close();
Estamento.close();
conexion.close();

%>
<script type="text/javascript" src="../js/jquery-ui.multidatespicker.js"></script>
<script type="text/javascript">
$(function() {
	var date = <%=prueba%>;
	var mycars = new Array();
	
	for (var i=0;i<date.length;i++) { 
		mycars[i] = date[i];
	}
	
	$('#pre-select-dates').multiDatesPicker({
	addDates: mycars
	});
});
</script>
<div id="pre-select-dates"></div>