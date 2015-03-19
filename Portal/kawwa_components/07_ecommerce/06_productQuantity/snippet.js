(function($){
	'use strict';

	/* Feature detection */
	function isNumberInput() {
		var i = document.createElement('input');
		i.setAttribute('type', 'number');
		return i.type !== 'text';
	}
	$(document).ready(function(){

		if ($.fn.uppydowner) {
			if(!isNumberInput()) {
				$('input[type=number]').addClass('uppydowner');
				$('input.uppydowner').uppydowner();
			} else {
				$('input[type=number]').css('width', '3em');
			}
		}

	});
})(jQuery);
