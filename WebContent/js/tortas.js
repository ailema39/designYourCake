$(document).ready(function() {
	var priceTotal = 0;
	var priceTotalAux = 0;
	var capas = 0;
	var priceCapa1 = 0;
	var priceCapa2 = 0;
	var priceCapa3 = 0;
	
	$("a[rel*=leanModal]").leanModal({ top : 200, overlay : 0.4, closeButton: ".modal_close" });

	/* Click en Siguiente Paso 1*/
	$("#bt1").click(function(){
		$(".block").hide();
		$(".block-2").show();
		mostrarPaso2();
		setPriceTotal();
	});
  
  	/* Click en Siguiente Paso 2*/
	$("#bt2").click(function(){
		$(".block-2").hide();
		$(".block-3").show();
		mostrarPaso3();	
		setPriceTotal();
	});
  
  	/* Click en Siguiente Paso 3*/
	$("#bt3").click(function(){
		$(".block-3").hide();
		$(".block-4").show();
		mostrarPaso4();
		setPriceTotal();
	});
  
  	/* Click en Siguiente Paso 4*/
	$("#bt4").click(function(){
		name  = getName(4,capas);
		setPriceTotal();
		if (name.toLowerCase().indexOf("sem") >= 0){
			mostrarPaso6_block5();
			$(".block-4").hide();
			$(".block-6").show();
		}else{
			$(".block-4").hide();
			$(".block-5").show();
			if (capas == 1)	{
				$("#capa1").show();
			}else if (capas == 2)	{
				$("#capa1").show();
				$("#capa2").show();
			}else{
				$("#capa1").show();
				$("#capa2").show();
				$("#capa3").show();
			}
			mostrarPaso5();
		}	
		
	});
	
  	/* Click en Siguiente Paso 5*/
	$("#bt5").click(function(){
		$(".block-5").hide();
		$(".block-6").show();
		mostrarPaso6();
		setPriceTotal();
	});
  
   	/* Click en alguna opcion del Paso 1*/
	$(".rdB1").click(function(){
		$("#bt1Disable").hide();
		$("#bt1").show();
		var j = $(this).val();
		getPrice(1,j);
	
	});
	
	
	/* Click en alguna opcion del Paso 2*/
	$(".rdB2").click(function(){
		$("#bt2Disable").hide();
		$("#bt2").show();
		var j = $(this).val();
		getPrice(2,j);
	
	});
  
  /* Click en alguna opcion del Paso 3*/
   $(".rdB3").click(function(){
		$("#bt3Disable").hide();
		$("#bt3").show();
		var j = $(this).val();
		getPrice(3,j);
		name  = getName(3,j);
		
	});
  
  /* Click en alguna opcion del Paso 4*/
  $(".rdB4").click(function(){
	$("#bt4Disable").hide();
	$("#bt4").show();
	var j = $(this).val();
	getPrice(4,j);
	name  = getName(4,j);
	capas = j;
	mostrarPaso4(j);
  });
  
  /* Click en alguna opcion del Paso 5*/
	$(".rdB5").click(function(){
		$("#bt5Disable").hide();
		$("#bt5").show();
		var j = $(this).val();
		var checked = $(this).is(':checked');
		if (checked)
			getPrice(5,j);
		else
			deletePrice(5,j);
		mostrarSaborCapa(this, j);
		
	});
  
	/* Click en alguna opcion del Paso 6*/
	$(".rdB6").click(function(){
		var j = $(this).val();
		name = getName(6,j);
		getPrice(6,j);
		mostrarSaborCubierta(this, j);
	});
  
	function mostrarSaborCapa(element, j){
		var checked = $(element).is(':checked');
		name  = getName(5,j);
		var valueCapas = $("input:radio[name=4]:checked").val();
		if (checked){
			if (name.toLowerCase().indexOf("arequipe") >= 0){
				$("#pasoImgCapas").attr('src',"./images/tortas/"+ valueCapas +"_capas_arequipe.png");
			}
			else if (name.toLowerCase().indexOf("nutella") >= 0){
				$("#pasoImgCapas").attr('src',"./images/tortas/"+ valueCapas +"_capas_nutella.png");
			}
			else if (name.toLowerCase().indexOf("chantilly con melocotones") >= 0){
				$("#pasoImgCapas").attr('src',"./images/tortas/"+ valueCapas +"_capas_chanti_melocoton.png");
			}
			else if (name.toLowerCase().indexOf("chantilly con fresas") >= 0){
				$("#pasoImgCapas").attr('src',"./images/tortas/"+ valueCapas +"_capas_chanti_fresa.png");
			}
			else if (name.toLowerCase().indexOf("oreo") >= 0){
				$("#pasoImgCapas").attr('src',"./images/tortas/"+ valueCapas +"_crema_oreo.png");
			}
			else if (name.toLowerCase().indexOf("almendras") >= 0){
				$("#pasoImgCapas").attr('src',"./images/tortas/"+ valueCapas +"_almendras.png");
			}
			else if (name.toLowerCase().indexOf("cerezas y chispas de chocolate ") >= 0){
				$("#pasoImgCapas").attr('src',"./images/tortas/"+ valueCapas +"_crema_cereza_chocolate.png");
			}
			else if (name.toLowerCase().indexOf("chispas de chocolate") >= 0){
				$("#pasoImgCapas").attr('src',"./images/tortas/"+ valueCapas +"_chispas_chocolate.png");
			}
			else if (name.toLowerCase().indexOf("choco") >= 0){
				$("#pasoImgCapas").attr('src',"./images/tortas/"+ valueCapas +"_capas_chocolate.png");
			}
			
		}else{
			$("#pasoImgCapas").attr('src',"./images/tortas/"+ valueCapas +"_capa_general.png");
		}
  }
  
	/* Funcion que permite seleccionar tanto sabores de capas, como numero de capas se haya seleccionado */
	$("input:checkbox[name=5]").click(function() {
		var value = $("input:radio[name=4]:checked").val();
		var bol = $("input:checkbox:checked").length >= value;     
		$("input:checkbox").not(":checked").attr("disabled",bol);
	});
  
   
   /* Imagenes pasos */
	function mostrarPaso1(){
		$("#pasoImgBase").attr('src',"./images/paso_torta.png");
	}
	
	function mostrarPaso2(){
		$("#pasoImgBase").attr('src',"./images/paso_torta_2.png");
	}
	
	function mostrarPaso3(){
		$("#pasoImgBase").attr('src',"./images/paso_torta_3.png");
	}
	
	function mostrarPaso4(j){
		if (j != 4)
			$("#pasoImgBase").attr('src',"./images/paso_torta_4.png");
		else 
			$("#pasoImgBase").attr('src',"./images/paso_torta_4_block5.png");
	}
	
	function mostrarPaso5(){
		$("#pasoImgBase").attr('src',"./images/paso_torta_5.png");
	}
	
	function mostrarPaso6(){
		$("#pasoImgBase").attr('src',"./images/paso_torta_6.png");
	}
	
	function mostrarPaso6_block5(){
		$("#pasoImgBase").attr('src',"./images/paso_torta_6_block5.png");
	}
	/* -- Fin funciones de images pasos -- */
  
  
	function eliminarTorta(){
		$("#pasoImgSabor").hide();
		$("#pasoImgCubierta").hide();
	}
	
	function eliminarCapa(){
		$("#pasoImgCapas").hide();
	}
	
	function eliminarRelleno(){
		var valueCapas = $("input:radio[name=4]:checked").val();
		$("#pasoImgCapas").attr('src',"./images/tortas/"+ valueCapas +"_capa_general.png");
	}
	
	function eliminarCubierta(){
		$("#pasoImgCubierta").attr('src',"./images/tortas/capa_general.png");
	}
   
   /* Funcion para reiniciar el formulario */
   $(".buttonR").click(function(){
		priceTotal = 0;
		priceTotalAux = 0;
		capas = 0;
		priceCapa1 = 0;
		priceCapa2 = 0;
		priceCapa3 = 0;
		
		var priceText = priceTotal + '.00';
		$('.price').text(priceText);
		$(".block").show();
		$(".block-2").hide();
		$(".block-3").hide();
		$(".block-4").hide();
		$(".block-5").hide();
		$(".block-6").hide();
		$("#bt1Disable").show();
		$("#bt1").hide();
		$("#bt2Disable").show();
		$("#bt2").hide();
		$("#bt3Disable").show();
		$("#bt3").hide();
		$("#bt4Disable").show();
		$("#bt4").hide();
		$("#bt5Disable").show();
		$("#bt5").hide();
		$("#bt6Disable").show();
		$("#bt6").hide();
		$("input:radio").removeAttr("checked");
		$("input:checkbox").removeAttr("checked");
		$("input:checkbox").attr("disabled",false);
		eliminarTorta();
		eliminarRelleno();
		eliminarCubierta();
		eliminarCapa();
		mostrarPaso1();
		$("#txtImage").hide();
		$('#target').each(function(){
			this.reset();
		});
		
	});
   
   
   /* Funcion que permite saber si el usuario escogio una foto y asi desactivar 
   el boton de siguiente */
   $("#txtImage").change(function (){ 
		$("#bt6Disable").hide();
		$("#bt6").show();
	}); 

	
	/* Obtiene el precio del elemento seleccionado y lo suma al total */
	function getPrice(i,j){
		var priceAux = priceTotal;
		var selectIdAux = '.price-int' + i+j;
		var price = $(selectIdAux).text();
		priceAux = priceAux + Number(price);
		var priceText = priceAux + '.00';
		$('.price').text(priceText);
		priceTotalAux = priceAux;
		if (i == 5)
			setPriceTotal();
		
	}
	
	function getPriceCapa(i,j){
		var priceAux = priceTotal;
		var selectIdAux = '.price-intC' + i+j;
		var price = $(selectIdAux).text();
		priceAux = priceAux + Number(price);
		if ( i == 1)
			priceAux += Number(priceCapa2) + Number(priceCapa3);
		if ( i == 2)
			priceAux += Number(priceCapa1) + Number(priceCapa3);
		if ( i == 3)
			priceAux += Number(priceCapa1) + Number(priceCapa2);
		
		var priceText = priceAux + '.00';
		$('.price').text(priceText);
		priceTotalAux = priceAux;
		if (i == 5)
			setPriceTotal();
		return price;
		
	}
	
	
	/* Obtiene el precio del elemento seleccionado y lo elimina del total */
	function deletePrice(i,j){
		var priceAux = priceTotal;
		var selectIdAux = '.price-int' + i+j;
		var price = $(selectIdAux).text();
		priceAux = priceAux - Number(price);
		var priceText = priceAux + '.00';
		$('.price').text(priceText);
		priceTotalAux = priceAux;
		setPriceTotal();
	}
	
	/* Obtiene el nombre del elemento seleccionad */
	function getName(i,j){
		var selectIdAux = '#name' + i+j;
		return $(selectIdAux).text();
	}
	
	function setPriceTotal(){
		priceTotal = priceTotalAux;
		
	}
	
	/* Funcion que setea el valor del precio antes de hacer submit al formulario */
	$('#target').submit(function() {
		var priceText = priceTotalAux + '.00';
		$("#priceCake").val(priceText);
		return true;
	});
	
	$('#selectCapa1').change(function() {
		j = $(this).val();
		priceCapa1 = getPriceCapa(1,j);
		bool = checkCapas();
		if (bool){
			$("#bt5Disable").hide();
			$("#bt5").show();
		}else{
			$("#bt5Disable").show();
			$("#bt5").hide();
		}
	});
	
	$('#selectCapa2').change(function() {
		j = $(this).val();
		priceCapa2 = getPriceCapa(2,j);
		bool = checkCapas();
		if (bool){
			$("#bt5Disable").hide();
			$("#bt5").show();
		}else{
			$("#bt5Disable").show();
			$("#bt5").hide();
		}
	});
	
	$('#selectCapa3').change(function() {
		j = $(this).val();
		priceCapa3 = getPriceCapa(3,j);
		bool = checkCapas();
		if (bool){
			$("#bt5Disable").hide();
			$("#bt5").show();
		}else{
			$("#bt5Disable").show();
			$("#bt5").hide();
		}
		
	});
	
	function checkCapas(){
		capa1 = $('#selectCapa1').val();
		capa2 = $('#selectCapa2').val();
		capa3 = $('#selectCapa3').val();
		bool = true;
		if (capas == 1){
			if (capa1 == "0") 
				bool = false;
		}
		else if (capas == 2) {
			if (capa1 == "0") 
				bool = false;
			if (capa2 == "0")
				bool = false;
		}else{
			if (capa1 == "0") 
				bool = false;
			if (capa2 == "0")
				bool = false;
			if (capa3 == "0")
				bool = false;
		}
		return bool;
		
	}
	
});




function prueba(texto, i, id, element){
		if (i == 3)
			mostrarPonque(texto);
		else if (i ==4)
			mostrarCapas(texto, id);
		/*else if (i == 6)
			mostrarSaborCubierta(texto, id);*/
		
}

function mostrarPonque(imagen){
	$("#pasoImgCubierta").show();
	$("#pasoImgSabor").attr('src',"./images/tortas/" + imagen);
	$("#pasoImgSabor").show();
}

function mostrarCapas(imagen, id){
	if (id != 25)
		$("#pasoImgCapas").attr('src',"./images/tortas/" + imagen);
	else{
		$("#pasoImgCapas").hide();
		return;
	}
	$("#pasoImgCapas").show();
}


function mostrarSaborCubierta(imagen, id){
	if (id != 9){
			$("#bt6Disable").hide();
			$("#txtImage").hide();
			$("#bt6").show();
			return;
	}else{ 
		$("#txtImage").show();
	}
  }
 
/*function mostrarPaso4_blocke5(){
	$("#pasoImgBase").attr('src',"./images/paso_torta_4_block5.png");
	alert("a");
}*/
