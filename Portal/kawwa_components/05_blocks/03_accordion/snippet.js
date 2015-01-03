(function($){
	'use strict';

	$(document).ready(function(){
		if($.ui && $.ui.accordion){
			$('.k-accordion' ).accordion({
				heightStyle: 'content'
			});
		}
	});
})(jQuery);
