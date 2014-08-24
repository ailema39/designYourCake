$(document).ready(function(){
	$("a[rel*=leanModal]").leanModal({ top : 200, overlay : 0.4, closeButton: ".modal_close" });
	
	var priceTotal = 0;
	var priceCant = 0;
	var priceTotalAux = 0;
	var cantCapas = 2;
	
	

	/* Cambio de las secciones y la imagen del paso asociado*/
	$("#bt1").click(function(){
		$(".block").hide();
		$(".block-2").show();
		setPriceTotal();
		mostrarPaso2();
	});
  
	 $("#bt2").click(function(){
		$(".block-2").hide();
		$(".block-3").show();
		setPriceTotal();
		mostrarPaso3();
	});
	  
	$("#bt3").click(function(){
		$(".block-3").hide();
		$(".block-4").show();
		setPriceTotal();
		mostrarPaso4();
		
	});
  
	$("#bt4").click(function(){
		setPriceTotal();
		var value = $("input:radio[name=4]:checked").val();
		$(".block-4").hide();
		$(".block-5").show();
		mostrarPaso5();
	});
  
	/*$("#bt5").click(function(){
		$(".block-5").hide();
		$(".block-6").show();
		setPriceTotal();
		mostrarPaso6();
	});*/
	
	/*-----------------------------------*/
	
	
	/* Desbloqueo de los botones siguientes */
	
	/* Click en cualquier radio button del paso 1*/
	$(".rdB1").click(function(){
		$("#bt1Disable").hide();
		$("#bt1").show();
		var j = $(this).val();
		getPrice(1,j);
   	});

	/* Click en cualquier radio button del paso 2*/
	$(".rdB2").click(function(){
		value = $(this).val();
		$("#cantCupcakes").val('20');
		$("#cantCupcakes").show();
		
        /* Retornar a cero el valor de la cantidad en el select*/
		/*if (value != 2){
			$("#cantCupcakes").val('20');
			$("#bt2Disable").show();
			$("#bt2").hide();
			$("#calcomania").show();
			cantCapas = 1;
		}else{
			$("#bt2Disable").hide();
			$("#bt2").show();
		         //$("#calcomania").hide();
		}*/
		
		getPrice(2,value);
			
	});
	
	$('#txtCalcomania').keyup(function() {
		value = $('input[id=txtCalcomania]').val();
		if( value == ""){
			$("#bt2Disable").show();
			$("#bt2").hide();
		}else{
			$("#bt2Disable").hide();
			$("#bt2").show();
		}
		
	});
	
	
	/* Click en cualquier radio button del paso 3*/
	$(".rdB3").click(function(){
		$("#bt3Disable").hide();
		$("#bt3").show();
		value = $(this).val();
		getPrice(3,value);
		//mostrarImgPonque(this);
		
		
	});
	
	/* Click en cualquier radio button del paso 4*/
	$(".rdB4").click(function(){
        value = $(this).val();
        getPrice(4,value);
	});
	
	
	$(".rdB5").click(function(){
		$("#bt5Disable").hide();
		$("#bt5").show();
		value = $(this).val();
		getPrice(5,value);
		//mostrarImgColor(this);
	});
	
	
	/* Click en cualquier radio button del paso 6*/  
	/*$(".rdB6").click(function(){
		$("#bt6Disable").hide();
		$("#bt6").show();
		value = $(this).val();
		getPrice(6,value);
		//mostrarImgDecoracion(this);
	});*/
  
	/* -----------------------------------*/
	
	
	
	

	
	
	/* Funciones auxiliares */

	function eliminarCubiertas(){
		$("#pasoImgCubierta1").hide();
		$("#pasoImgCubierta2").hide();
		$("#pasoImgCubierta3").hide();
	}


	function eliminarColoresVarios(){
		$("#pasoImgColor1").hide();
		$("#pasoImgColor2").hide();
		$("#pasoImgColor3").hide();
	}

	function eliminarPonquesVarios(){
		$("#pasoImgSabor1").hide();
		$("#pasoImgSabor2").hide();
		$("#pasoImgSabor3").hide();
	}

	function eliminarPonque(){
		$("#pasoImgSabor").hide();
	}

	function mostrarPonque(){
		$("#pasoImgSabor").show();
	}
	
	function mostrarPonques(){
		$("#pasoImgSabor1").show();
		$("#pasoImgSabor2").show();
		$("#pasoImgSabor3").show();
	}

	function mostrarCubierta(){
		$("#pasoImgCubierta").show();
	}
	
	function mostrarCubiertas(){
		$("#pasoImgCubierta1").show();
		$("#pasoImgCubierta2").show();
		$("#pasoImgCubierta3").show();
	}
	
	function mostrarDecoraciones(){
		$("#pasoImgDecoracion1").show();
		$("#pasoImgDecoracion2").show();
		$("#pasoImgDecoracion3").show();
	}

	function eliminarDecoraciones(){
		$("#pasoImgDecoracion1").hide();
		$("#pasoImgDecoracion2").hide();
		$("#pasoImgDecoracion3").hide();
	}

	function mostrarDecoracion(){
		$("#pasoImgDecoracion").show();
	}

	function eliminarDecoracion(){
		$("#pasoImgDecoracion").hide();
	}

	function eliminarCubierta(){
		$("#pasoImgCubierta").hide();
	}

	function mostrarColor(){
		$("#pasoImgColor").show();
	}

	function eliminarColor(){
		$("#pasoImgColor").hide();
	}
	/* ----- Fin funciones auxiliares ----- */
	
	/* Imagenes pasos */
	function mostrarPaso1(){
		$("#pasoImgBase").attr('src',"./images/paso_cupcakes.png");
	}
	
	function mostrarPaso2(){
		$("#pasoImgBase").attr('src',"./images/paso_cupcakes_2.png");
	}
	
	function mostrarPaso3(){
		$("#pasoImgBase").attr('src',"./images/paso_cupcakes_3.png");
	}
	
	function mostrarPaso4(){
		$("#pasoImgBase").attr('src',"./images/paso_cupcakes_4.png");
	}
	
	function mostrarPaso4_block5(){
		$("#pasoImgBase").attr('src',"./images/paso_cupcakes_4_block5.png");
	}
	
	function mostrarPaso5(){
		$("#pasoImgBase").attr('src',"./images/paso_cupcakes_5.png");
	}
	
	function mostrarPaso6(){
		$("#pasoImgBase").attr('src',"./images/paso_cupcakes_6.png");
	}
	/* -- Fin funciones de images pasos -- */
  
  
	/* Funciones q permiten echar para atras */
	
	$("#backLink1").click(function(event) {
		mostrarPaso1();
		$(".block").show();
		$(".block-2").hide();
		$(".rdB2").attr('checked', false);
		$("#bt2Disable").show();
		$("#bt2").hide();
	});
		
	$("#backLink2").click(function(event) {
		$(".block-2").show();
		$(".block-3").hide();
		mostrarPaso2();
		$(".rdB3").attr('checked', false);
		$("#bt3Disable").show();
		$("#bt3").hide();
		
		/* Eliminar ponque*/
		if (unPonque)
			eliminarPonque();
		else	
			eliminarPonquesVarios();
	});
	
	
	$("#backLink3").click(function(event) {
		$(".block-3").show();
		$(".block-4").hide();
		if (unPonque){
			if (unaCubierta){
				eliminarCubierta();
			}else{
				eliminarCubiertas();
				eliminarPonquesVarios();
				mostrarPonque();
			}
		}else{
			eliminarCubiertas();
		}
		mostrarPaso3();
		$(".rdB4").attr('checked', false);
		$("#bt4Disable").show();
		$("#bt4").hide();
	});
	
	
	$("#backLink4").click(function(event) {
		$(".block-4").show();
		$(".block-5").hide();
		mostrarPaso4();
		if (coloresSurtidos){
			/* Si antes habian 3 ponque solo elimino los colores*/
			if (!unPonqueAux || !unPonque){
				eliminarColoresVarios();
			/* Sino elimino los colores y los ponque y solo pinto uno solo*/
			}else{
				eliminarColoresVarios();
				eliminarPonquesVarios();
				mostrarPonque();
				mostrarCubierta();
			}
		}else{
			if (!unPonqueAux || !unPonque)
				eliminarColoresVarios();
			else
				eliminarColor();
		}
		$(".rdB5").removeAttr('disabled');
		$(".rdB5").attr('checked', false);
		$("#bt5Disable").show();
		$("#bt5").hide();
	});
	
	 $("#backLink5").click(function(event) {
		var valueCubierta = $("input:radio[name=4]:checked").val();
		eliminarDecoracion();
		$(".rdB6").attr('checked', false);
		if (valueCubierta == 1){
			mostrarPaso5();
			$(".block-5").show();
			$(".block-6").hide();
			return;
		}
		else if (valueCubierta == 2){
			mostrarPaso5();
			$(".block-5").show();
			$(".block-6").hide();
			return;
		}else
		{
			mostrarPaso4_block5();
			$(".block-4").show();
			$(".block-6").hide();
			return;
		}
		$("#bt6Disable").show();
		$("#bt6").hide();
	});
	
	/* -- Fin funciones para echar para atras -- */

	/* Obtiene el precio del elemento seleccionado y lo suma al total */
	function getPrice(i,j){
		var priceAux = priceTotal;
		var selectIdAux = '.price-int' + i+j;
		var price = $(selectIdAux).text();
		priceCapas = cantCapas*(Number(price));
		priceAux =  priceAux + Number(priceCapas);
		var priceText = priceAux + '.00';
		$('.price').text(priceText);
		priceTotalAux = priceAux;
		if (i == 1) priceCant = price;
   	}
	
	function setPriceTotal(){
		priceTotal = priceTotalAux;
		
	}
	
	$('#cantCupcakes').change(function() {
	    val = $('#cantCupcakes').val()/10;
        priceTemp = (Number(val) * Number(priceCant));
		var priceAux = priceTotal;
		//alert(priceAux);
        priceAux = priceTemp;
		var priceText = priceAux + '.00';
		$('.price').text(priceText);
		priceTotalAux = priceAux;
		cantCapas = val;
	});
	
	/* Funcion que setea el valor del precio antes de hacer submit al formulario */
	$('#target').submit(function() {
		var priceText = priceTotalAux + '.00';
		$("#priceCake").val(priceText);
		return true;
	});
	
	
	   /* Funcion para reiniciar el formulario */
	   $(".buttonR").click(function(){
			priceTotal = 0;
			priceTotalAux = 0;
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
			eliminarColoresVarios();
			eliminarDecoraciones();
			eliminarPonquesVarios();
			eliminarPonque();
			eliminarDecoracion();
			eliminarCubierta();
			eliminarCubiertas();
			eliminarColor();
			mostrarPaso1();
			$("#calcomania").hide();
			$('#target').each(function(){
				this.reset();
			});
			
			priceCant = 0;
			sabor = "";
			unPonque = true;
			unPonqueAux = true;
			unaCubierta = true;
			coloresSurtidos = false;
			cubiertaEscogida = "";
			cantCapas = 2;
		});
	
	
});

var sabor = "";
var unPonque = true;
var unPonqueAux = true;
var unaCubierta = true;
var coloresSurtidos = false;
var cubiertaEscogida = "";
var color1 = "";
var color2 = "";
var color3 = "";
var sabor1 = "";
var sabor2 = "";
var sabor3 = "";
var saborP1 = "";
var saborP2 = "";
var saborP3 = "";
var idP1 = "";
var idP2 = "";
var idP3 = "";


	

function prueba(texto, i, id, element){
	
	if (i == 3)
		mostrarImgPonque(texto, id);
	else if (i == 4)
		mostrarImgCubierta(texto, id);
	else if (i == 5)
		mostrarImgColor(texto, id, element);
	else if (i == 6)
		mostrarImgDecoracion(texto, id);
	
}

function mostrarImgPonque(imagen, id){
		if (id != "54")
			sabor = "./images/cupcakes/" + imagen;
		else{
			unPonque = false;
			$("#pasoImgSabor1").attr('src',"./images/cupcakes/"+ saborP1);
			$("#pasoImgSabor2").attr('src',"./images/cupcakes/"+ saborP2);
			$("#pasoImgSabor3").attr('src',"./images/cupcakes/"+ saborP3);
			$("#pasoImgSabor1").show();
			$("#pasoImgSabor2").show();
			$("#pasoImgSabor3").show();
			
			$("#pasoImgSabor").hide();
			return;
		}
		unPonque = true;
		$("#pasoImgSabor").attr('src', sabor);
		$("#pasoImgSabor").show();
		$("#pasoImgSabor1").hide();
		$("#pasoImgSabor2").hide();
		$("#pasoImgSabor3").hide();
}


function mostrarImgCubierta(imagen, id){
	
		unaCubierta = true;
		unPonqueAux = true;
	
		/* Cubierta escogida */
		//var value = $(element).val();
		cubiertaEscogida = id;
		var cubierta = "";
        
        $("#pasoImgBase").attr('src',"./images/paso_cupcakes_4.png");
		cubierta = "./images/cupcakes/" + imagen;
		
		if (id == "58"|| id == "59" || id == "81" || id == "82"){
			$("#pasoImgBase").attr('src',"./images/paso_cupcakes_4_block5.png");
             $("#bt4Disable").hide();
             $("#bt4").hide();
             $("#bt4-esp").show();
        }
        if (id == "83" || id == "84" || id == "85")
            mostrarMsj2();
        else  mostrarMsj1();
		if (id == "60"){
			unaCubierta = false;
			$("#pasoImgBase").attr('src',"./images/paso_cupcakes_4_block5.png");
			/* Si ya habia pintado 3 ponques, solo pinto las cubiertas*/
			if (!unPonque){
				$("#pasoImgCubierta1").attr('src',"./images/cupcakes/" + sabor1);
				$("#pasoImgCubierta2").attr('src',"./images/cupcakes/" + sabor2);
				$("#pasoImgCubierta3").attr('src',"./images/cupcakes/" + sabor3);
				$("#pasoImgCubierta1").show();
				$("#pasoImgCubierta2").show();
				$("#pasoImgCubierta3").show();
				$("#pasoImgCubierta").hide();
				return;
			}
			/* Sino, debo pintar las 3 cubiertas y los 3 ponques*/
			else{
				unPonqueAux = false;
				$("#pasoImgCubierta1").attr('src',"./images/cupcakes/" + sabor1);
				$("#pasoImgCubierta2").attr('src',"./images/cupcakes/" + sabor2);
				$("#pasoImgCubierta3").attr('src',"./images/cupcakes/" + sabor3);
				$("#pasoImgCubierta1").show();
				$("#pasoImgCubierta2").show();
				$("#pasoImgCubierta3").show();
				
				$("#pasoImgSabor1").attr('src',sabor);
				$("#pasoImgSabor2").attr('src',sabor);
				$("#pasoImgSabor3").attr('src',sabor);
				$("#pasoImgSabor1").show();
				$("#pasoImgSabor2").show();
				$("#pasoImgSabor3").show();
			
				$("#pasoImgSabor").hide();
				$("#pasoImgCubierta").hide();
				return;
			}
		}

				
		if (unPonque){
			$("#pasoImgCubierta").attr('src',cubierta);
			$("#pasoImgSabor1").hide();
			$("#pasoImgSabor2").hide();
			$("#pasoImgSabor3").hide();
			$("#pasoImgCubierta1").hide();
			$("#pasoImgCubierta2").hide();
			$("#pasoImgCubierta3").hide();
			
			$("#pasoImgSabor").show();
			$("#pasoImgCubierta").show();
		}else{
			$("#pasoImgCubierta1").attr('src',cubierta);
			$("#pasoImgCubierta2").attr('src',cubierta);
			$("#pasoImgCubierta3").attr('src',cubierta);
			$("#pasoImgCubierta1").show();
			$("#pasoImgCubierta2").show();
			$("#pasoImgCubierta3").show();
			
			$("#pasoImgCubierta").hide();
		}
        
        if (id != "58" && id != "59" && id != "81" && id != "82"){
            $("#bt4Disable").hide();
		    $("#bt4").show();
            $("#bt4-esp").hide();
        }
       
	}
	
	function mostrarImgColor(imagen, id, element){
		var valueC = cubiertaEscogida;
		var value = id;
		var color = "";
		var checked = $(element).is(':checked');
		
		coloresSurtidos = false;

		var preColor = "";
        var show = "true";
		if (valueC == "84" || valueC == "85" || valueC == "86"){
			return;
		}
        else
        	preColor = "./images/cupcakes/55_";
		
		if (value == "69"){
				coloresSurtidos = true;
				/* Se verifica si se está seleccionando o deseleccionando la opcion*/
				if (checked){
					/* Se bloquean los otros checkboxes */
					$("input:checkbox").not(element).attr("disabled",true);
					$("input:checkbox").not(element).removeAttr("checked");
					/* Solo se pintan los 3 colores diferentes*/
					if (!unPonqueAux || !unPonque){
						$("#pasoImgColor1").attr('src',preColor + color1);
						$("#pasoImgColor2").attr('src',preColor + color2);
						$("#pasoImgColor3").attr('src',preColor + color3);
						$("#pasoImgColor1").show();
						$("#pasoImgColor2").show();
						$("#pasoImgColor3").show();
						$("#pasoImgColor").hide();
						$("#pasoImgSabor").hide();
						$("#pasoImgCubierta").hide();
						return;
					}
					/* Se debe pintar los 3 ponques y las 3 cubiertas de colores variados */
					else{	
						$("#pasoImgColor1").attr('src',preColor + color1);
						$("#pasoImgColor2").attr('src',preColor + color2);
						$("#pasoImgColor3").attr('src',preColor + color3);
						$("#pasoImgColor1").show();
						$("#pasoImgColor2").show();
						$("#pasoImgColor3").show();
						$("#pasoImgColor").hide();
						
						$("#pasoImgSabor1").attr('src',sabor);
						$("#pasoImgSabor2").attr('src',sabor);
						$("#pasoImgSabor3").attr('src',sabor);
						$("#pasoImgSabor1").show();
						$("#pasoImgSabor2").show();
						$("#pasoImgSabor3").show();
						
						$("#pasoImgSabor").hide();
						$("#pasoImgCubierta").hide();
						
						return;
					}
				}else{
					$("input:checkbox").attr("disabled",false);
					/* Verifico si antes habia 1 ponque o 3*/
					if (!unPonqueAux || !unPonque){
						/* Eliminio los colores surtidos*/
						$("#pasoImgColor1").hide();
						$("#pasoImgColor2").hide();
						$("#pasoImgColor3").hide();
						return;
					}else{
						/* Elimino los colores surtidos, los 3 ponques. Muestro uno solo y una sola cobertura*/
						$("#pasoImgColor1").hide();
						$("#pasoImgColor2").hide();
						$("#pasoImgColor3").hide();
						
						$("#pasoImgSabor1").hide();
						$("#pasoImgSabor2").hide();
						$("#pasoImgSabor3").hide();
						
						$("#pasoImgSabor").show();
						$("#pasoImgCubierta").show();
						return;
					}
				}
			
			
			}else{
				color = preColor + imagen;
				
			}

		/* Para pintar los colores verifico si habian 3 ponques o uno, para pintar la cantidad correcta de capas */
		if (!unPonqueAux || !unPonque){
			if (checked){
				$("#pasoImgColor1").attr('src',color);
				$("#pasoImgColor2").attr('src',color);
				$("#pasoImgColor3").attr('src',color);
				$("#pasoImgColor1").show();
				$("#pasoImgColor2").show();
				$("#pasoImgColor3").show();
				$("#pasoImgColor").hide();
				mostrarSabores();
			}
			else{
				eliminarColoresVarios();
				mostrarCubiertas();
				mostrarPonques();
			}
		}else{
			if (checked){
				$("#pasoImgColor").attr('src',color);
				$("#pasoImgColor").show();
				$("#pasoImgSabor").show();
			}else{
				$("#pasoImgColor").hide();
				mostrarPonque();
				mostrarCubierta();
			}

		}
	
	}
	
	function mostrarImgDecoracion(imagen, id){
		/* Obtengo el valor de la cubierta escogida */
		var valueCubierta = cubiertaEscogida;
        alert(valueCubierta);
		/* Valor de la decoracion escogida*/
		var value = id;
		var decoracion = "";
		
		
		var idPAUX1 = "0" , idPAUX2 = "0", idPAUX3 = "0";
	
		if (idP1 == "55" || idP1 == "57")
			idPAUX1 = "55";
		else if (idP1 == "56")
			idPAUX1 = "56";
		if (idP1 == "58" || idP1 == "59")
			idPAUX1 = "59";
			
		if (idP2 == "55" || idP2 == "57")
			idPAUX2 = "55";
		else if (idP2 == "56")
			idPAUX2 = "56";
		if (idP2 == "58" || idP2 == "59")
			idPAUX2 = "59";
			
		if (idP3 == "55" || idP3 == "57")
			idPAUX3 = "55";
		else if (idP3 == "56")
			idPAUX3 = "56";
		if (idP3 == "58" || idP3 == "59")
			idPAUX3 = "59";			
			
		/* Verifico cual cubierta se escogio para mostrar la imagen correspondiente de la decoracion */
		if (valueCubierta == "55" || valueCubierta == "57"){
			decoracion = "./images/cupcakes/55_";
			//$("#pasoImgDecoracion").attr('src',"./images/cupcakes/chispas_" + imagen);
		}
		else if (valueCubierta == "56"){
			decoracion = "./images/cupcakes/56_";
			//$("#pasoImgDecoracion").attr('src',"./images/cupcakes/chispas_glaseado_" + imagen);
		}
		else if (valueCubierta == "58" || valueCubierta == "59"){
			decoracion = "./images/cupcakes/59_";
		//	$("#pasoImgDecoracion").attr('src',"../images/cupcakes/chispas_limon_" + imagen);
		}
		else if (valueCubierta == "60")
		{
			if (value != 74){
				decoracionSurt = true;
				$("#pasoImgDecoracion1").attr('src',"./images/cupcakes/" + idPAUX1 + "_" + imagen);
				$("#pasoImgDecoracion2").attr('src',"./images/cupcakes/" + idPAUX2 + "_" + imagen);
				$("#pasoImgDecoracion3").attr('src',"./images/cupcakes/" + idPAUX3 + "_" + imagen);
				$("#pasoImgDecoracion1").show();
				$("#pasoImgDecoracion2").show();
				$("#pasoImgDecoracion3").show();
				$("#pasoImgDecoracion").hide();
				return;
			}else{
				$("#pasoImgDecoracion").hide();
				$("#pasoImgDecoracion1").hide();
				$("#pasoImgDecoracion2").hide();
				$("#pasoImgDecoracion3").hide();
				
				return;
			}
		}
		
		if (value == "74"){
			
			$("#pasoImgDecoracion").hide();
			$("#pasoImgDecoracion1").hide();
			$("#pasoImgDecoracion2").hide();
			$("#pasoImgDecoracion3").hide();
			
			return;
		}
		
		if (!unPonque){
				$("#pasoImgDecoracion1").attr('src', decoracion + imagen);
				$("#pasoImgDecoracion2").attr('src', decoracion + imagen);
				$("#pasoImgDecoracion3").attr('src', decoracion + imagen);
				$("#pasoImgDecoracion1").show();
				$("#pasoImgDecoracion2").show();
				$("#pasoImgDecoracion3").show();
				$("#pasoImgDecoracion").hide();
				return;
		}else if (coloresSurtidos){
			$("#pasoImgDecoracion1").attr('src', decoracion + imagen);
			$("#pasoImgDecoracion2").attr('src', decoracion + imagen);
			$("#pasoImgDecoracion3").attr('src', decoracion + imagen);
			$("#pasoImgDecoracion1").show();
			$("#pasoImgDecoracion2").show();
			$("#pasoImgDecoracion3").show();
			$("#pasoImgDecoracion").hide();
			return;
		}
		else{
			$("#pasoImgDecoracion").attr('src', decoracion + imagen);
			$("#pasoImgDecoracion").show();
			eliminarDecoraciones();

		}
		decoracionSurt = false;
		
	}
	
	function setColor(colorImg1, colorImg2, colorImg3){
		color1 = colorImg1;
		color2 = colorImg2;
		color3 = colorImg3;
	}
	
	function setSabor(saborImg1, saborImg2, saborImg3){
		sabor1 = saborImg1;
		sabor2 = saborImg2;
		sabor3 = saborImg3;
		
	}
	
	function setSaborP(saborImg1, saborImg2, saborImg3,  id1, id2, id3){
		saborP1 = saborImg1;
		saborP2 = saborImg2;
		saborP3 = saborImg2;
		
		idP1 = id1;
		idP2 = id2;
		idP3 = id2;
	}
	
    
    function mostrarMsj1(){
         $('.rdB5').attr("disabled", false);
         $( ".rdB5" ).each(function( i ) {
            text = $(this).next('span').text();
            if (text.indexOf("Pasta americana") > 0) $(this).attr("disabled", true);
         });
         $(".dc-text").hide();
    }
    
    function mostrarMsj2(){
        $('.rdB5').attr("disabled", true);
         $( ".rdB5" ).each(function( i ) {
            text = $(this).next('span').text();
            if (text.indexOf("Pasta americana") > 0) $(this).attr("disabled", false);
                
         });
         $(".dc-text").show();
    }