function createAutoComplete(theInput, theSource) {
	var availableTags = theSource;
	
	jQuery(theInput).autocomplete({
		
		source: function(request, response){
            var ajaxRequest = {
            	url:availableTags,
                success: function(data){
                    response(eval(data));
                },
                type:"POST"
            };
            jQuery.ajax(ajaxRequest);
        }
	});
}

function supports_input_placeholder() {
  var i = document.createElement('input');
  return 'placeholder' in i;
}

/* Tests if browser supports placeholder attr... */
$(document).ready(function(){
	if (supports_input_placeholder()) {
		if (jQuery.ui && jQuery.ui.autocomplete) {
			jQuery('input.k-autocomplete').attr("placeholder", "Search...");
			createAutoComplete(".k-autocomplete", "./autocomplete_tags.txt");
			jQuery('input.k-autocomplete').focus(function() {
				
				if(jQuery(this).val() == $(this).attr("placeholder")) {
					jQuery(this).val("");
				}
			}).blur(function() {
				if(jQuery(this).val() == "") {
					jQuery(this).val(jQuery(this).attr("placeholder"));
				}
			}).val(jQuery(this).attr("placeholder"));
		}
	} else {
		jQuery('input.k-autocomplete').attr("value", "Search...");
	}
});
