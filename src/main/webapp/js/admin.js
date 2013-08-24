

var OfferEditor = dejavu.Class.declare({
	
	initialize : function() {
		$("#OfferTable").tablesorter(); 
        $("#users").tablesorter(); 
        $("#fyingFormOffer").dialog({
        	width: 600,
        	height: 500,
        	modal: true,
           autoOpen: false
        });
	},
	clearForm : function() {
		 document.getElementById('offerTypeEdit').reset();
         return false;
	},
	 setData : function(elem) {
        var $elem = $(elem);
        document.getElementById('id').value = $elem.attr('id');
        var eleTd = $elem.children('td').first();
        document.getElementById('name').value = eleTd.text();
    },
    validateForm : function() {
        var name = $('#name');
        name.val(jQuery.trim(name.val()));
        if(name.val().length < 2){
        	$('#notice').text("* Brak nazwy lub nazwa zbyt krótka");
        	return false;
        } 
        else return true;
    },
    
    saveOffer : function (id){
    	var $row = $('#rowid_' + id);
    	
    	var html = '<tr id=rowid_"'+ id + '" onclick="insertToForm(this)"> \
		<td>' + $('#name').val() + '</td><td>' + $('#description').val()  +'</td><td>'  + $('#unitPrice').val()  + '</td> \
		<td>' + $('#unitM').val() + '</td><td>'  + $('#unitGB').val() + '</td></tr>';
		
    	if($row) {
    		$row.replaceWith(html);     		
    	} 
    	else {
    		$("#OfferTable tbody").append(html);
    	}
    	
    	 
        $("#OfferTable").trigger("update"); 
        document.getElementById('offerTypeEdit').reset();
    },
    
    insertToForm : function (elem) {
    	$("#fyingFormOffer").dialog('open');
    	$row = $(elem);
    	var id = $row.attr('id').substr(6);
    	$('#idOffer').val(id);
    	$row.children('td').each(function(index){
    		switch (index) {
    		case 0: 
    			$('#name').val(this.innerHTML);
    			break;
    		case 1: 
    			$('#description').val(this.innerHTML);
    			break;
    		case 2: 
    			$('#unitPrice').val(this.innerHTML);
    			break;
    		case 3: 
    			$('#unitM').val(this.innerHTML);
    			break;
    		case 4: 
    			$('#unitGB').val(this.innerHTML);
    			break;
    		default:
    			break;
    		}
    	});
    	
    },
    addNewOffer : function() {
    	$("#fyingFormOffer").dialog('open');
    	this.clearForm();
    }
	
});



       
   
        
        