(function($){
	'use strict';

	$(document).ready(function(){
		if($.ui && $.ui.accordion) {
			$('.k-menu').accordion({ active: false, collapsible: true });
		}
	});
})(jQuery);
