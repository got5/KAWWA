$(document).ready(function(){
	if (jQuery.fn.dataTable) {
		
		jQuery('table.k-data-table').dataTable({
			"sPaginationType": "full_numbers", 
			"oLanguage": {
                "sSearch": "You Search :", 
                "oPaginate": {
			        "sFirst":    "1st",
			        "sPrevious": "Prev"
			    }
            }
		});
	}
});
