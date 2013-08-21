$(function() {
$( "#sliderGB" ).slider({
value:25,
min: 25,
max: 250,
step: 25,
slide: function( event, ui ) {
$( "#amountGB" ).val(  ui.value );
$( "#amountGBInfo" ).text(  ui.value  );
}
});
var val =  $( "#sliderGB" ).slider( "value" );
$( "#amountGB" ).val( val );
$( "#amountGBInfo" ).text( val );
});

$(function() {
$( "#sliderTime" ).slider({
value:3,
min: 3,
max: 24,
step: 3,
slide: function( event, ui ) {
$( "#amountTime" ).val(  ui.value );
$( "#amountTimeInfo" ).text(  ui.value);
}
});
var val = $( "#sliderTime" ).slider( "value" );
$( "#amountTime" ).val( val );
$( "#amountTimeInfo" ).text( val );
});

$(function() {
	$('#flyingForm').dialog({
		modal: true,
		width: 600, 
		height: 400,
       autoOpen: false
	});
});

function addNewService(){
	var $form = $('#flyingForm');
	$('#serviceKind').show();
	$form.dialog( "option", "title", "Zamów usłlugę" );
	
	$form.dialog('open');
	$('#flyingForm').children('form').get(0).reset();
	onchangeOfferType();
}

function changeService(elem){
	var $form = $('#flyingForm');
	$form.dialog('open');
	form.dialog( "option", "title", "Zmień usługę" );
	$('#serviceKind').hide();
	var $elem = $(elem).parent();
	var amountGB = 0;
	var amountTime = 0;
	var amountAdd = $elem.children('.amountGBtoAdd');
	if(amountAdd.length > 0){ //już była zamawiana zmiana
		amountGB = parseInt(amountAdd.text());
		amountTime = parseInt($elem.children('.amountTimetoAdd').text());
	} else {
		var $newE = $elem.parent().parent().children('.serviceInfo').children('p');
		amountGB = parseInt($newE.children('.amountGB').text());
		amountTime = parseInt($newE.children('.amountTime').text());
	}
	
	onchageOfferType();
	setSliderValue(amountGB, amountTime);
	var idSer = $elem.parent().parent().children('h4').attr('id').substring(9);
    $('#serviceId').val(idSer);
}

function affterAdd(idService){
	var offerName = $('#serviceKind option:selected').html();
	var amountGB = $('#amountGB').val();
	var amountTime = $('#amountTime').val();
	var serviceIdCont = 'seriviceId_' + idService;
	var $serviceH4 = $('#'+serviceId);
	if($serviceH4.length < 1) {
		var entryService = '<div class="serviceCont row" > \
			<h4 id=" '+ serviceIdCont + '">' + offerName + '</h4> \
			<div class="serviceInfo"><p>Usługa nieaktywna</p></div> \
	<div class="serviceOrder"><p><span class="amountGBtoAdd">'  + amountGB + '</span> GB \
			<span class="amountTimetoAdd">' + amountTime + '</span> \
			M-cy </p><button onclick="changeService(this)">Przedłuż/Zmień usługę</button></div> \
	</div>';
		$sddServices = $('#services').children('.addNewService"');
		$(entryService).insertBefore($addServices);
	}
	else {
		var pElem = '<p><span class="amountGBtoAdd">'  + amountGB + '</span> GB \
		<span class="amountTimetoAdd">' + amountTime + '</span> \
		M-cy </p>'
		$serviceButton = $serviceH4.parent().children('div').children();
		$(pElem).insertBefore($serviceButton);
	}
	
}

function affterDelete(idService){
	var serviceIdCont = 'seriviceId_' + idService;
	$('#'+serviceId).parent().children('.serviceOrder').children('p').remove();
}

function onchangeOfferType(){
	var idOffer = $('#serviceKind').children('option:selected').attr('value');
	var idOfferInt = parseInt(idOffer);
	var j = searchServiceIdInArray(idOfferInt);
	var gb = j.gb;
	var time = j.time;
	
	setSliderOption(gb, time);
	setSliderValue(gb, time);
}


function setSliderOption(GB, time) {
	$slideGB =  $('#sliderGB');
	$slideGB.slider( "option", "max", GB*10 );
	$slideGB.slider( "option", "min", GB);
	$slideGB.slider( "option", "step", GB);
	
	$slideTime =  $('#sliderTime');
	$slideTime.slider( "option", "max", time*8 );
	$slideTime.slider( "option", "min", time );
	$slideTime.slider( "option", "step", time);
}

function setSliderValue(GB, time) {
	$slideGB =  $('#sliderGB');
	$slideGB.slider( "option", "value", GB);
	$( "#amountGB" ).val( GB );
	$( "#amountGBInfo" ).text( GB );
	
	$slideTime =  $('#sliderTime');
	$slideTime.slider( "option", "value", time);
	$( "#amountTime" ).val( time );
	$( "#amountTimeInfo" ).text( time );
}

function searchServiceIdInArray(id){
	for (i in offerTypes) {
		if(offerTypes[i].id == id){
			return offerTypes[i];
		}
	}
	var j = {};
	j.id = -1;
	return  j; 
}

