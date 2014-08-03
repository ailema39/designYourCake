$(document).ready(function(){
	$("a[rel*=leanModal]").leanModal({ top : 200, overlay : 0.4, closeButton: ".modal_close" });

	var value = $("input:radio[name=typePers]").click(function(){
		value = $(this).val();
		if (value == 0){
			$("#cedIden").text("CPF:");
			$("#name").text("Nomes:");
			$("#persJuridica").hide();
			$("#persNatural").show();
			$("#apellido").show();
			/*$("#txtCedId option[value='V-']").remove();
			$("#txtCedId option[value='E-']").remove();
			$("#txtCedId option[value='J-']").remove();
			$("#txtCedId option[value='G-']").remove();
			$("#txtCedId").prepend("<option value='E-'>E</option>");
			$("#txtCedId").prepend("<option value='V-' selected='selected'>V</option>");*/
			$("#errorCed").hide();
			$("#errorRif").hide();
			
		}else{
			$("#cedIden").text("CPF:");
			$("#name").text("Nome:");
			$("#persJuridica").show();
			$("#persNatural").hide();
			$("#apellido").hide();
			/*$("#txtCedId option[value='V-']").remove();
			$("#txtCedId option[value='E-']").remove();
			$("#txtCedId").prepend("<option value='G-'>G</option>");
			$("#txtCedId").prepend("<option value='J-' selected='selected'>J</option>");*/
			$("#errorCed").hide();
			$("#errorRif").hide();
		}
		
		
	});
});