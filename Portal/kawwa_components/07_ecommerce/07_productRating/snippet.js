(function($){
	'use strict';

	$(document).ready(function(){
		if($.fn.rating) {
			$('fieldset.k-rating input').rating();
		}
	});
})(jQuery);
