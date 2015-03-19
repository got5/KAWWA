(function($){
	'use strict';

	$(document).ready(function(){
		if ($.fn.tipsy) {
			$('.k-field-comment').tipsy({
				gravity: 'w',
				fade: true,
				trigger: 'focus'
			});
		}
	});
})(jQuery);
