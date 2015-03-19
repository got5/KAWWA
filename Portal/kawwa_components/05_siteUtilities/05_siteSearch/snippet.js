(function($){
	'use strict';

	function createAutoComplete(theInput, theSource) {
		var availableTags = theSource;

		$(theInput).autocomplete({

			source: function(request, response){
				var ajaxRequest = {
					url: availableTags,
					success: function(data){
						response(eval(data));
					},
					type: 'POST'
				};
				$.ajax(ajaxRequest);
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
			if ($.ui && $.ui.autocomplete) {
				var inputAutocomplete = $('input.k-autocomplete');
				inputAutocomplete.attr('placeholder', 'Search...');
				createAutoComplete('.k-autocomplete', './autocomplete_tags.txt');
				inputAutocomplete.focus(function() {

					if($(this).val() === $(this).attr('placeholder')) {
						$(this).val('');
					}
				}).blur(function() {
					if($(this).val() === '') {
						$(this).val($(this).attr('placeholder'));
					}
				}).val($(this).attr('placeholde'));
			}
		} else {
			jQuery('input.k-autocomplete').attr('value', 'Search...');
		}
	});
})(jQuery);
