$(document).ready(function() {
	$("a[rel*=leanModal]").leanModal({ top : 200, overlay : 0.4, closeButton: ".modal_close" });

	
	$(".dulcesTortasCheck").click(function() {
		$("input:checkbox.dulcesTortasCheck:").each(function()
		{
			if (this.checked){
				var sThisVal = $(this).val();
				var selectId = '.sel' + sThisVal;
				$(selectId).show();
			}else{
				var sThisVal = $(this).val();
				var selectId = '.sel' + sThisVal;
				$(selectId).hide();
				var selectIdAux = '.selDulcesTortas' + sThisVal;
				$(selectIdAux).val(0);
				cambiarPrecio(1);
				
			}
		});
		verificarSelectsVacios();
		verificarCheckboxes();
		
	});
	

	
	function cambiarPrecio(index){
		if (index == 1){
			var priceTotal = 0;
			$("input:checkbox.dulcesTortasCheck:").each(function()
			{
				if (this.checked){
					var sThisVal = $(this).val();
					var selectId = '.selDulcesTortas' + sThisVal;
					var cant = $(selectId).val();
					var selectIdAux = '.price-int' + sThisVal;
					var price = $(selectIdAux).text();
					var total = Number(price) * Number(cant);
					priceTotal = priceTotal + total;
					
				}
			});
			var priceText = priceTotal + '.00';
			$('.price').text(priceText);
		}else{
			var priceTotal = 0;
			$("input:checkbox.dulcesTortasCheck:").each(function()
			{
				if (this.checked){
					var sThisVal = $(this).val();
					var selectId = '.selDulcesTortas' + sThisVal;
					var cant = $(selectId).val();
					var selectIdAux = '.price-int' + sThisVal;
					var price = $(selectIdAux).text();
					var total = Number(price) * Number(cant);
					priceTotal = priceTotal + total;
					
				}
			});
			var priceText = priceTotal + '.00';
			$('.price').text(priceText);
		}
		
	}
	
	
	/* Actualize el precio al seleccionar la cantidad de tortas */
	$('select').change(function(){
		cambiarPrecio(2);
		verificarSelectsVacios();
	});
	
	/* Verifica si hay al menos un checkbox seleccionado, sino lo hay bloquea el botón de ordenar*/
	function verificarCheckboxes(){
		
		var checkboxes = document.getElementsByClassName('dulcesTortasCheck');
		var cont = 0; 
		for (var x=0; x < checkboxes.length; x++) {
			if (checkboxes[x].checked) {
			cont = cont + 1;
			}
		}
		if (cont == 0){
			$('.dt-button-dis').show();
			$('.dt-button').hide();
		}
	}
	
	/* Verifica que todos los checkboxes que han sido seleccionados tenga su cantidad respectiva */
	function verificarSelectsVacios(){
		var checkboxes = document.getElementsByClassName('dulcesTortasCheck');
		var bool = false;	
		for (var x = 0; x < checkboxes.length; x++) {
			if (checkboxes[x].checked) {
				var sThisVal = checkboxes[x].value;
				var selectId = '.selDulcesTortas' + sThisVal;
				var cant = $(selectId).val();
				if (cant == '0'){
					bool= true;
				}
			}
		}
		if (bool){
			$('.dt-button-dis').show();
			$('.dt-button').hide();
		}else{
			$('.dt-button-dis').hide();
			$('.dt-button').show();
		}
	}
	
	$('#formDulcesTortas').submit(function() {
	  var fecha = $("#datepicker").val();
	  if (fecha == ""){
	  	$("#errorDate").show();
	  	return false;
	  }
	  $("#errorDate").hide();
	  var priceText = Number($("#priceTotal").text());
	  $("#priceDulcCake").val(priceText);
	  return true;
	});
});