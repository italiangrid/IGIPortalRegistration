function confirmRemove() {
	return confirm("Do you want to delete the selected user ?");
}

function onchangeVO() {
	var idVo = $("#<portlet:namespace/>VOids").val();
	if (idVo==""){
		$("#<portlet:namespace/>VOrole option").hide();
		return;
	}
	$("#<portlet:namespace/>VOrole option[class != 'class_" + idVo + "']").hide();
	$("#<portlet:namespace/>VOrole option").removeAttr("selected");
	var i=0;
	while (i<stampa.length){
		$("#<portlet:namespace/>VOrole option[value = '" + stampa[i].idVorole + "']").remove();
		i++;
	}
	
	$("#<portlet:namespace/>VOrole option[class = 'class_" + idVo + "']").show();
}

var lista;
var stampa;

function preview(){
	$("#listaVorole").html("");
	var output = "";
	var i=0;
	   while (i<stampa.length) {
	       output = output + stampa[i].testo + "<input name='idRoleList' type='hidden' value='" + stampa[i].idVorole+ "' />" + "<br/>";
	       i++;
	   }
	  
	$("#listaVorole").html(output);
}

function addVorole(){
	var idVorole = $("#<portlet:namespace/>VOrole").val();
	var tmp = new Object();
	tmp["idVorole"] = idVorole;
	tmp["testo"] = $("#<portlet:namespace/>VOrole option:selected").text();
	stampa.push(tmp);
	lista.push(idVorole);
	preview();
	onchangeVO();
	
}

$(document).ready(function(){
	onchangeVO();
	lista = new Array();
	stampa = new Array();

});

