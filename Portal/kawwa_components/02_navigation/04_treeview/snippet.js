(function($){
	'use strict';

	$(document).ready(function(){
		if($.fn.jstree) {
			$('.k-tree').jstree({plugins: ['html_data', 'ui', 'hotkeys']});
			/*if you don't want keyboard navigation, use this line instead :
			jQuery('.k-tree').jstree();*/
			$('.jstree-closed > a').attr('aria-expanded', 'false');
			$('.jstree-open > a').attr('aria-expanded', 'true');
		}
	});
})(jQuery);
