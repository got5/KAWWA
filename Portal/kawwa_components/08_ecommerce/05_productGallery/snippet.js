(function($){
	'use strict';

	$(document).ready(function(){
		if($.fn.jqzoom) {
			$('.jqzoom').jqzoom({
				zoomType: 'standard',
				lens: true,
				preloadImages: true,
				alwaysOn: false
			});
		}
	});
})(jQuery);
