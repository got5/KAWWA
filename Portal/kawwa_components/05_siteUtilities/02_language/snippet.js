(function($){
	'use strict';

	$(document).ready(function(){
		if ($.ui && $.ui.buttonset) {
			$('form.k-language fieldset.k-radio').buttonset();
		}
	});
})(jQuery);
